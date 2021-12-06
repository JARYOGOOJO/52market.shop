package com.sparta.cucumber.service;

import com.sparta.cucumber.dto.ChatRequestDto;
import com.sparta.cucumber.models.ChatRoom;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.ChatRoomRepository;
import com.sparta.cucumber.repository.EnterRoomRepository;
import com.sparta.cucumber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final EnterRoomRepository enterRoomRepository;


    @Transactional
    public void exitRoom(ChatRequestDto chatRequestDto){
        User user = userRepository.findById(chatRequestDto.getUserId()).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );
        ChatRoom chatRoom = chatRoomRepository.findById(chatRequestDto.getRoomId()).orElseThrow(
                () -> new NullPointerException("해당 방이 존재하지 않습니다.")
        );
        Optional<EnterRoom> findEnterRoom = enterRoomRepository.findByUserAndRoom(user,chatRoom);
        if(findEnterRoom.isPresent()){
            enterRoomRepository.deleteById(findEnterRoom.get().getId());
        }
    }

    @Transactional
    public EnterRoom enterRoom(ChatRequestDto chatRequestDto){
        User user = userRepository.findById(chatRequestDto.getUserId()).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );
        ChatRoom chatRoom = chatRoomRepository.findById(chatRequestDto.getRoomId()).orElseThrow(
                () -> new NullPointerException("해당 방이 존재하지 않습니다.")
        );

        // 중복검사
        Optional<EnterRoom> findEnterRoom = enterRoomRepository.findByUserAndRoom(user,chatRoom);
        if(findEnterRoom.isPresent()){
            return null;
        }

        EnterRoom enterRoom = EnterRoom
                .builder()
                .user(user)
                .room(chatRoom)
                .build();
        return enterRoomRepository.save(enterRoom);
    }

    @Transactional(readOnly = true)
    public ChatRoom getRoom(Long id){
        return chatRoomRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 방이 존재하지 않습니다.")
        );
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
