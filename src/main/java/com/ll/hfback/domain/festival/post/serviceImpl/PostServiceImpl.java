package com.ll.hfback.domain.festival.post.serviceImpl;

import com.ll.hfback.domain.festival.post.dto.DetailPostDto;
import com.ll.hfback.domain.festival.post.dto.PostDto;
import com.ll.hfback.domain.festival.post.entity.Post;
import com.ll.hfback.domain.festival.post.repository.PostRepository;
import com.ll.hfback.domain.festival.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    // 모든 게시글 조회
    @Override
    public Page<PostDto> findAll(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);
        return posts.map(this::convertToPostDto);
    }

    // 키워드로 게시글 조회
    @Override
    public Page<PostDto> searchByKeyword(String keyword, Pageable pageable) {
        Page<Post> posts = postRepository.findByFestivalNameContaining(keyword, pageable);
        return posts.map(this::convertToPostDto);
    }

    // 게시글ID로 상세 조회
    @Override
    public DetailPostDto searchByFestivalId(String festivalId) {
        Post post = postRepository.findByFestivalId(festivalId);
        return convertToDetailPostDto(post);
    }

    // 장르별 게시글 조회(축제, 연극, 무용(서양/한국무용), 대중무용, 서양음악(클래식),
    // 한국음악(국악), 대중음악, 복합, 서커스/마술, 뮤지컬)
    @Override
    public Page<PostDto> searchByGenreOrAll(String genre, Pageable pageable) {
        Page<Post> posts = postRepository.findByGenreOrAll(genre, pageable);
        return posts.map(this::convertToPostDto);

    }

    // 지역 기준으로 게시글 조회
    @Override
    public List<PostDto> searchByFestivalArea(String area, Integer count) {
        List<Post> posts = postRepository.findByFestivalAreaContaining(area);
        return posts.stream()
                .map(this::convertToPostDto)
                .limit(count != null ? count : posts.size())
                .collect(Collectors.toList());
    }

    // Post를 PostDto로 변환
    private PostDto convertToPostDto(Post post) {
        return new PostDto(
                post.getFestivalId(),
                post.getFestivalName(),
                post.getFestivalArea(),
                post.getFestivalStartDate(),
                post.getFestivalEndDate(),
                post.getFestivalUrl()
        );
    }

    // Post를 DetailPostDto로 변환
    private DetailPostDto convertToDetailPostDto(Post post) {
        return new DetailPostDto(
                post.getFestivalId(),
                post.getFestivalName(),
                post.getFestivalStartDate(),
                post.getFestivalEndDate(),
                post.getFestivalArea(),
                post.getFestivalHallName(),
                post.getFestivalUrl()
        );
    }
}
