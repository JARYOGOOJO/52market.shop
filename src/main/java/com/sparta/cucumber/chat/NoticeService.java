package com.sparta.cucumber.chat;

import com.sparta.cucumber.error.CustomException;
import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sparta.cucumber.error.ErrorCode.CHATROOM_NOT_FOUND;
import static com.sparta.cucumber.error.ErrorCode.USER_NOT_FOUND;
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
                .senderId(sender.getId())
                .content(htmlEscape(requestDto.getContent()))
                .type(NoticeType.MESSAGE)
                .build();
        room.talk(message);
        return messageRepository.save(message);
    }

    @Transactional
    public Notice invite(ChatRequestDto requestDto) {
        User sender = userRepository
                .findById(requestDto.getUserId()).orElseThrow(
                        () -> new NullPointerException("잘못된 접근입니다."));
        return Notice
                .builder()
                .senderId(sender.getId())
                .subscriberId(requestDto.getTargetId())
                .content(requestDto.getContent())
                .type(NoticeType.CALLING)
                .build();
    }

    @Transactional
    public Notice article(ChatRequestDto requestDto) {
        User sender = userRepository
                .findById(requestDto.getUserId()).orElseThrow(
                        () -> new NullPointerException("잘못된 접근입니다."));
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
                        () -> new NullPointerException("잘못된 접근입니다."));
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
                        () -> new NullPointerException("잘못된 접근입니다."));
        return Notice
                .builder()
                .senderId(sender.getId())
                .targetId(requestDto.getTargetId())
                .type(NoticeType.DEL_CMT)
                .build();
    }
}
