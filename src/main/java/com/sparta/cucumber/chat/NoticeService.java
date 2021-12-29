package com.sparta.cucumber.chat;

import com.sparta.cucumber.error.CustomException;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sparta.cucumber.error.ErrorCode.*;
import static org.springframework.web.util.HtmlUtils.htmlEscape;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;

    @Transactional
    public Notice message(ChatRequestDto requestDto) {
        User sender = userRepository
                .findById(requestDto.getUserId()).orElseThrow(
                        () -> new CustomException(USER_NOT_FOUND));
        ChatRoom room = chatRoomRepository
                .findById(requestDto.getRoomSubscribeId()).orElseThrow(
                        () -> new CustomException(CHATROOM_NOT_FOUND));
        Notice message = Notice
                .builder()
                .chatRoom(room)
                .senderId(sender.getId())
                .content(htmlEscape(requestDto.getContent()))
                .type(NoticeType.MESSAGE)
                .build();
        return messageRepository.save(message);
    }

    @Transactional
    public Notice invite(ChatRequestDto requestDto) {
        User sender = userRepository
                .findById(requestDto.getUserId()).orElseThrow(
                        () -> new CustomException(USER_NOT_FOUND));
        User subscriber = userRepository
                .findById(requestDto.getTargetId()).orElseThrow(
                        () -> new CustomException(USER_NOT_FOUND));
        if (sender == subscriber) {
            throw new CustomException(INVALID_CHAT_REQUEST);
        } else {
            return Notice
                    .builder()
                    .senderId(sender.getId())
                    .subscriberId(subscriber.getId())
                    .content(requestDto.getContent())
                    .type(NoticeType.CALLING)
                    .build();
        }
    }

    @Transactional
    public Notice article(ChatRequestDto requestDto) {
        User sender = userRepository
                .findById(requestDto.getUserId()).orElseThrow(
                        () -> new CustomException(USER_NOT_FOUND));
        String title = requestDto.getTitle();
        String content = requestDto.getContent();
        return Notice
                .builder()
                .senderId(sender.getId())
                .title(title)
                .content(content)
                .type(NoticeType.ARTICLE)
                .build();
    }

    @Transactional
    public Notice comment(ChatRequestDto requestDto) {
        User sender = userRepository
                .findById(requestDto.getUserId()).orElseThrow(
                        () -> new CustomException(USER_NOT_FOUND));
        return Notice
                .builder()
                .senderId(sender.getId())
                .targetId(requestDto.getTargetId())
                .content(requestDto.getContent())
                .type(NoticeType.COMMENT)
                .build();
    }

    @Transactional
    public Notice uncomment(ChatRequestDto requestDto) {
        User sender = userRepository
                .findById(requestDto.getUserId()).orElseThrow(
                        () -> new CustomException(USER_NOT_FOUND));
        return Notice
                .builder()
                .senderId(sender.getId())
                .targetId(requestDto.getTargetId())
                .type(NoticeType.DEL_CMT)
                .build();
    }
}
