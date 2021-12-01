package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.ChatRequestDto;
import com.sparta.cucumber.models.ChatRoom;
import com.sparta.cucumber.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    @Transactional(readOnly = true)
    public ChatRoom getRoom(Long id){
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 방이 존재하지 않습니다.")
        );
        return chatRoom;
    }

    @Transactional(readOnly = true)
    public List<ChatRoom> getRooms(){
        return chatRoomRepository.findAll();
    }

    @Transactional
    public ChatRoom createRoom(ChatRequestDto chatRequestDto){
        ChatRoom chatRoom =ChatRoom
                .builder()
                .title(chatRequestDto.getTitle())
                .roomSubscribeId(chatRequestDto.getRoomSubscribeId())
                .isActive(chatRequestDto.isActive())
                .build();

        return chatRoomRepository.save(chatRoom);
    }


}
