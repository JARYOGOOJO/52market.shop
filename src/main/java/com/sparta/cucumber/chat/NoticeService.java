package com.sparta.cucumber.chat;

import com.sparta.cucumber.models.User;
import com.sparta.cucumber.repository.UserRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class NoticeService {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;

    public Notice message(ChatRequestDto requestDto) {
        User sender = userRepository
                .findById(requestDto.getUserId()).orElseThrow(
                        () -> new NullPointerException("잘못된 유저입니다."));
        User subscribeUser = userRepository
                .findBySubscribeId(requestDto.getUserSubscribeId())
                .orElseThrow(
                        () -> new NullPointerException("잘못된 유저입니다."));
        ChatRoom room = chatRoomRepository
                .findById(requestDto.getRoomSubscribeId()).orElseThrow(
                        () -> new NullPointerException("잘못된 대화방입니다."));
        Notice message = Notice
                .builder()
                .senderId(sender.getId())
                .subscriberId(subscribeUser.getId())
                .content(requestDto.getContent())
                .type(NoticeType.MESSAGE)
                .build();
        room.talk(message);
        return messageRepository.save(message);
    }

    public Notice invite(ChatRequestDto requestDto) {
        User sender = userRepository
                .findById(requestDto.getUserId()).orElseThrow(
                        () -> new NullPointerException("잘못된 접근입니다."));
        return Notice
                .builder()
                .senderId(sender.getId())
                .content(requestDto.getContent())
                .type(NoticeType.CALLING)
                .build();
    }

    public Notice article(ChatRequestDto requestDto) {
        User sender = userRepository

                .findById(requestDto.getUserId()).orElseThrow(
                        () -> new NullPointerException("잘못된 접근입니다."));
        return Notice
                .builder()
                .senderId(sender.getId())
                .content(requestDto.getContent())
                .type(NoticeType.ARTICLE)
                .build();
    }

    public Notice comment(ChatRequestDto requestDto) {
        User sender = userRepository
                .findById(requestDto.getUserId()).orElseThrow(
                        () -> new NullPointerException("잘못된 접근입니다."));
        return Notice
                .builder()
                .senderId(sender.getId())
                .content(requestDto.getContent())
                .type(NoticeType.COMMENT)
                .build();
    }
}
