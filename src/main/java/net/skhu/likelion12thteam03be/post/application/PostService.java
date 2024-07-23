package net.skhu.likelion12thteam03be.post.application;

import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.category.domain.Category;
import net.skhu.likelion12thteam03be.category.domain.repository.CategoryRepository;
import net.skhu.likelion12thteam03be.post.api.dto.request.PostSaveReqDto;
import net.skhu.likelion12thteam03be.post.api.dto.request.PostUpdateReqDto;
import net.skhu.likelion12thteam03be.post.api.dto.response.PostInfoResDto;
import net.skhu.likelion12thteam03be.post.api.dto.response.PostListResDto;
import net.skhu.likelion12thteam03be.post.domain.Post;
import net.skhu.likelion12thteam03be.post.domain.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void postSave(PostSaveReqDto postSaveReqDto) {
        Category category = categoryRepository.findById(postSaveReqDto.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다. categoryId = " + postSaveReqDto.categoryId()));

        Post post = Post.builder()
                .title(postSaveReqDto.title())
                .content(postSaveReqDto.content())
                .imgUrl(postSaveReqDto.imgUrl())
                .locationId(postSaveReqDto.locationId())
                .time(postSaveReqDto.time())
                .price(postSaveReqDto.price())
                .category(category)
                .emotionId(postSaveReqDto.emotionId())
                .colorId(postSaveReqDto.colorId())
                .build();

        postRepository.save(post);
    }

    // 글 전체 조회
    public PostListResDto postFindAll() {
        List<Post> posts = postRepository.findAll();

        List<PostInfoResDto> postInfoResDtoList = posts.stream()
                .map(PostInfoResDto::from)
                .toList();

        return PostListResDto.from(postInfoResDtoList);
    }

    // 글 개별 조회
    public PostInfoResDto postFindById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 글을 조회할 수 없습니다. postId = " + postId)
        );
        return PostInfoResDto.from(post);
    }

    // 글 카테고리별 조회
    public PostListResDto postFindByCategoryId(Long categoryId) {
        Optional<Post> posts = postRepository.findById(categoryId);

        List<PostInfoResDto> postInfoResDtoList = posts.stream()
                .map(PostInfoResDto::from)
                .toList();

        return PostListResDto.from(postInfoResDtoList);
    }

    // 글 작성자별 조회(내 글 조회)
    public PostListResDto postFindByUserId(Long userId) {
        Optional<Post> posts = postRepository.findById(userId);

        List<PostInfoResDto> postInfoResDtoList = posts.stream()
                .map(PostInfoResDto::from)
                .toList();

        return PostListResDto.from(postInfoResDtoList);
    }


    // 글 검색 조회


    // 글 수정
    @Transactional
    public void postUpdate(Long postId, PostUpdateReqDto postUpdateReqDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 글을 수정할 수 없습니다. postId = " + postId)
        );

        Category category = categoryRepository.findById(postUpdateReqDto.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다. categoryId = " + postUpdateReqDto.categoryId()));

        post.update(category, postUpdateReqDto);
        PostInfoResDto.from(post);
    }

    // 글 삭제
    @Transactional
    public void postDelete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 글을 삭제할 수 없습니다. postId = " + postId)
        );

        postRepository.delete(post);
    }

}
