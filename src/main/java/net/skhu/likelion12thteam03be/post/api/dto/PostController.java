package net.skhu.likelion12thteam03be.post.api.dto;

import net.skhu.likelion12thteam03be.post.api.dto.request.PostSaveReqDto;
import net.skhu.likelion12thteam03be.post.api.dto.request.PostUpdateReqDto;
import net.skhu.likelion12thteam03be.post.api.dto.response.PostInfoResDto;
import net.skhu.likelion12thteam03be.post.api.dto.response.PostListResDto;
import net.skhu.likelion12thteam03be.post.application.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 글 저장
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> postSave(
            @RequestPart("post") PostSaveReqDto postSaveReqDto,
            @RequestPart(value = "imgUrl", required = false) MultipartFile imgUrl,
            Principal principal) throws IOException {
        postService.postSave(postSaveReqDto, imgUrl, principal);
        return new ResponseEntity<>("Successful Post Save", HttpStatus.CREATED);
    }

    // 글 전체 조회
    @GetMapping()
    public ResponseEntity<PostListResDto> postFindAll() {
        PostListResDto postListResDto = postService.postFindAll();
        return new ResponseEntity<>(postListResDto, HttpStatus.OK);
    }

    // 글 개별 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostInfoResDto> postFindById(@PathVariable("postId") Long postId) {
        PostInfoResDto postInfoResDto = postService.postFindById(postId);
        return new ResponseEntity<>(postInfoResDto, HttpStatus.OK);
    }

    // 글 위치별 조회
    @GetMapping("/locations/{locationId}")
    public ResponseEntity<PostListResDto> postFindByLocationId(@PathVariable("locationId") Long locationId) {
        PostListResDto postListResDto = postService.postFindByLocationId(locationId);
        return new ResponseEntity<>(postListResDto, HttpStatus.OK);
    }

    // 글 카테고리별 조회
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<PostListResDto> postFindByCategoryId(@PathVariable("categoryId") Long categoryId) {
        PostListResDto postListResDto = postService.postFindByCategoryId(categoryId);
        return new ResponseEntity<>(postListResDto, HttpStatus.OK);
    }

    // 글 분위기별 조회
    @GetMapping("/moods/{moodId}")
    public ResponseEntity<PostListResDto> postFindByMoodId(@PathVariable("moodId") List<Long> moodIds) {
        PostListResDto postListResDto = postService.postFindByMoodIds(moodIds);
        return new ResponseEntity<>(postListResDto, HttpStatus.OK);
    }

    // 글 작성자별 조회(내 글 조회)
    @GetMapping("/users/{userId}")
    public ResponseEntity<PostListResDto> postFindByUserId(@PathVariable("userId") Long userId) {
        PostListResDto postListResDto = postService.postFindByUserId(userId);
        return new ResponseEntity<>(postListResDto, HttpStatus.OK);
    }

    // 글 검색 조회
    @GetMapping("/search/{input}")
    public ResponseEntity<PostListResDto> postFindByInput(@PathVariable("input") String input) {
        PostListResDto postListResDto = postService.postFindByInput(input);
        return new ResponseEntity<>(postListResDto, HttpStatus.OK);
    }

    // 글 추천 조회
    @GetMapping("/recommend/{recommend}")
    public ResponseEntity<PostListResDto> postFindByRecommend(@PathVariable("recommend") String recommend) {
        PostListResDto postListResDto = postService.postFindByRecommend(recommend);
        return new ResponseEntity<>(postListResDto, HttpStatus.OK);
    }

    // 글 수정
    @PatchMapping("/{postId}")
    public ResponseEntity<String> postUpdate(
            @PathVariable("postId") Long postId,
            @RequestPart("post") PostUpdateReqDto postUpdateReqDto,
            @RequestPart("imgUrl") MultipartFile imgUrl,
            Principal principal) throws IOException {
        postService.postUpdate(postId, postUpdateReqDto, imgUrl, principal);
        return new ResponseEntity<>("Successful Post Update", HttpStatus.OK);
    }

    // 글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> postDelete(@PathVariable("postId") Long postId, Principal principal) throws IOException {
        postService.postDelete(postId, principal);
        return new ResponseEntity<>("Successful Post Delete", HttpStatus.OK);
    }
}