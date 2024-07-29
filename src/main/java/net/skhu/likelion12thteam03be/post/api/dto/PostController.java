package net.skhu.likelion12thteam03be.post.api.dto;

import net.skhu.likelion12thteam03be.post.api.dto.request.PostSaveReqDto;
import net.skhu.likelion12thteam03be.post.api.dto.request.PostUpdateReqDto;
import net.skhu.likelion12thteam03be.post.api.dto.response.PostInfoResDto;
import net.skhu.likelion12thteam03be.post.api.dto.response.PostListResDto;
import net.skhu.likelion12thteam03be.post.application.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) { this.postService = postService; }

    // 글 저장
    @PostMapping()
    public ResponseEntity<String> postSave(@RequestBody PostSaveReqDto postSaveReqDto) {
        postService.postSave(postSaveReqDto);
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
    public ResponseEntity<PostInfoResDto> postFindById(@PathVariable Long postId) {
        PostInfoResDto postInfoResDto = postService.postFindById(postId);
        return new ResponseEntity<>(postInfoResDto, HttpStatus.OK);
    }

    // 글 카테고리별 조회
    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<PostListResDto> postFindByCategoryId(@PathVariable("categoryId") Long categoryId) {
        PostListResDto postListResDto = postService.postFindByCategoryId(categoryId);
        return new ResponseEntity<>(postListResDto, HttpStatus.OK);
    }

    // 글 작성자별 조회(내 글 조회)
    /*@GetMapping("/users/{userId}")
    public ResponseEntity<PostListResDto> postFindByUserId(@PathVariable("userId") Long userId) {
        PostListResDto postListResDto = postService.postFindByUserId(userId);
        return new ResponseEntity<>(postListResDto, HttpStatus.OK);
    }*/

    // 글 검색 조회


    // 글 수정
    @PatchMapping("/{postId}")
    public ResponseEntity<String> postUpdate(@PathVariable("postId") Long postId, @RequestBody PostUpdateReqDto postUpdateReqDto) {
        postService.postUpdate(postId, postUpdateReqDto);
        return new ResponseEntity<>("Successful Post Update", HttpStatus.OK);
    }

    // 글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> postDelete(@PathVariable("postId") Long postId) {
        postService.postDelete(postId);
        return new ResponseEntity<>("Successful Post Delete", HttpStatus.OK);
    }
}