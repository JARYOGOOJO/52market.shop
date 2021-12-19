package com.sparta.cucumber.chat;

import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Transactional
    public ChatRoom createRoom(ChatRequestDto chatRequestDto) {
        User user = userRepository.findById(chatRequestDto.getUserId()).orElseThrow(NullPointerException::new);
        ChatRoom chatRoom = ChatRoom
                .builder()
                .host(user)
                .title(chatRequestDto.getTitle())
                .build();
        return chatRoomRepository.save(chatRoom.enter(user));
    }

    @Transactional
    public ChatRoom enterRoom(ChatRequestDto chatRequestDto) {
        User user = userRepository.findById(chatRequestDto.getUserId()).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );
        ChatRoom chatRoom = chatRoomRepository.findById(chatRequestDto.getRoomSubscribeId()).orElseThrow(
                () -> new NullPointerException("해당 방이 존재하지 않습니다.")
        );
        return chatRoomRepository.save(chatRoom.enter(user));
    }

    @Transactional
    public void exitRoom(ChatRequestDto chatRequestDto) {
        User user = userRepository.findById(chatRequestDto.getUserId()).orElseThrow(
                () -> new NullPointerException("해당 유저가 존재하지 않습니다.")
        );
        ChatRoom chatRoom = chatRoomRepository.findById(chatRequestDto.getRoomSubscribeId()).orElseThrow(
                () -> new NullPointerException("해당 방이 존재하지 않습니다.")
        );
        chatRoom.exit(user);
        if (!chatRoom.isActive()) {
            chatRoomRepository.delete(chatRoom);
        } else {
            chatRoomRepository.save(chatRoom);
        }
    }
}