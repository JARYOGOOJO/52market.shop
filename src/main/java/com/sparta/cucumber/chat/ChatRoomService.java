package com.sparta.cucumber.chat;

import com.sparta.cucumber.error.CustomException;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sparta.cucumber.error.ErrorCode.CHATROOM_NOT_FOUND;
import static com.sparta.cucumber.error.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    @Transactional
    public ChatRoom createRoom(ChatRequestDto chatRequestDto) {
        User user = userRepository.findById(chatRequestDto.getUserId()).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND));
        ChatRoom chatRoom = ChatRoom
                .builder()
                .host(user)
                .title(chatRequestDto.getTitle())
                .build();
        return chatRoomRepository.save(chatRoom);
    }

    @Transactional
    public ChatRoom enterRoom(ChatRequestDto chatRequestDto) {
        User guest = userRepository.findById(chatRequestDto.getUserId()).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );
        ChatRoom chatRoom = chatRoomRepository.findByRoomSubscribeId(chatRequestDto.getRoomSubscribeId()).orElseThrow(
                () -> new CustomException(CHATROOM_NOT_FOUND)
        );
        return chatRoomRepository.save(chatRoom.enter(guest));
    }

    @Transactional
    public ChatRoom exitRoom(ChatRequestDto chatRequestDto) {
        User user = userRepository.findById(chatRequestDto.getUserId()).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );
        ChatRoom chatRoom = chatRoomRepository.findById(chatRequestDto.getRoomSubscribeId()).orElseThrow(
                () -> new CustomException(CHATROOM_NOT_FOUND)
        );
        chatRoom.exit(user);
        if (!chatRoom.isActive()) {
            chatRoomRepository.delete(chatRoom);
        } else {
            chatRoomRepository.save(chatRoom);
        }
        return chatRoom;
    }

    @Transactional(readOnly = true)
    public List<ChatRoom> isInvited(ChatRequestDto chatRequestDto){
        User user = userRepository.findById(chatRequestDto.getUserId()).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );
        return chatRoomRepository.findByGuest(user).orElseThrow(
                () -> new CustomException(CHATROOM_NOT_FOUND)
        );
    }

    @Transactional(readOnly = true)
    public List<ChatRoom> isInvite(ChatRequestDto chatRequestDto){
        User user = userRepository.findById(chatRequestDto.getUserId()).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );
        return chatRoomRepository.findByHost(user).orElseThrow(
                () -> new CustomException(CHATROOM_NOT_FOUND)
        );
    }

    @Transactional(readOnly = true)
    public List<ChatRoom> isRoomCreated(ChatRequestDto chatRequestDto){
        User user = userRepository.findById(chatRequestDto.getUserId()).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );
        return chatRoomRepository.findByGuestOrHost(user,user).orElseThrow(
                () -> new CustomException(CHATROOM_NOT_FOUND)
        );
    }


}