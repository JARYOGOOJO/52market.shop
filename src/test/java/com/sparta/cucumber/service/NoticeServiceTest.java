package com.sparta.cucumber.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class NoticeServiceTest {

    @Nested
    @DisplayName("테스트")
    class MessageTest {
        @Test
        @Rollback
        @Transactional
        @DisplayName("")
        void success() {

        }

        @Test
        @Rollback
        @Transactional
        @DisplayName("")
        void fail() {

        }
    }

    @Nested
    @DisplayName("테스트")
    class InviteTest {
        @Test
        @Rollback
        @Transactional
        @DisplayName("")
        void success() {

        }

        @Test
        @Rollback
        @Transactional
        @DisplayName("")
        void fail() {

        }
    }

    @Nested
    @DisplayName("테스트")
    class ArticleTest {
        @Test
        @Rollback
        @Transactional
        @DisplayName("")
        void success() {

        }

        @Test
        @Rollback
        @Transactional
        @DisplayName("")
        void fail() {

        }
    }

    @Nested
    @DisplayName("테스트")
    class CommentTest {
        @Test
        @Rollback
        @Transactional
        @DisplayName("")
        void success() {

        }

        @Test
        @Rollback
        @Transactional
        @DisplayName("")
        void fail() {

        }
    }

    @Nested
    @DisplayName("테스트")
    class UncommentTest {
        @Test
        @Rollback
        @Transactional
        @DisplayName("")
        void success() {

        }

        @Test
        @Rollback
        @Transactional
        @DisplayName("")
        void fail() {

        }
    }
}