package com.ll.hfback.domain.group.chat.repository;

import com.ll.hfback.domain.group.chat.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * packageName    : com.ll.hfback.domain.group.chat.repository
 * fileName       : ChatRepository
 * author         : sungjun
 * date           : 2025-01-22
 * description    : 자동 주석 생성
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-01-22        kyd54       최초 생성
 */
public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long>, QuerydslPredicateExecutor<ChatMessage> {
    Page<ChatMessage> findByChatRoomId(Long chatRoomId, Pageable pageable);
}
