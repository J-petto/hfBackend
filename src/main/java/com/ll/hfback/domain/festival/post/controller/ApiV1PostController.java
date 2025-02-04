package com.ll.hfback.domain.festival.post.controller;

import com.ll.hfback.domain.festival.post.dto.DetailPostDto;
import com.ll.hfback.domain.festival.post.dto.PostDto;
import com.ll.hfback.domain.festival.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springdoc.core.annotations.ParameterObject;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ApiV1PostController {
    private final PostService postService;

    // 모든 게시글 조회
    @GetMapping("/all")
    public Page<PostDto> getAllPosts(@ParameterObject Pageable pageable) {
        return postService.findAll(pageable);
    }

    // 키워드로 게시글 조회
    @GetMapping("/search")
    public Page<PostDto> searchPosts(@RequestParam("keyword") String keyword, Pageable pageable) {
        return postService.searchByKeyword(keyword, pageable);
    }

    // 게시글ID로 상세 조회
    @GetMapping("/{festival-id}")
    public DetailPostDto getPost(@PathVariable("festival-id") String festivalId) {
        return postService.searchByFestivalId(festivalId);
    }

    // 장르별 게시글 조회(축제, 연극, 무용(서양/한국무용), 대중무용, 서양음악(클래식),
    // 한국음악(국악), 대중음악, 복합, 서커스/마술, 뮤지컬)
    @GetMapping("/select")
    public Page<PostDto> searchByGenreOrAll(@RequestParam(value = "genre", required = false) String genre, Pageable pageable) {
        return postService.searchByGenreOrAll(genre, pageable);
    }

    // 지역 기준으로 게시글 조회
    @GetMapping("/view")
    public List<PostDto> areaPosts(@RequestParam("area") String area, @RequestParam(value = "count", required = false) Integer count) {
        return postService.searchByFestivalArea(area, count);
    }
}
