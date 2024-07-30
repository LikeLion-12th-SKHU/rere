package net.skhu.likelion12thteam03be.post.application;

import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.category.domain.Category;
import net.skhu.likelion12thteam03be.category.domain.repository.CategoryRepository;
import net.skhu.likelion12thteam03be.location.domain.Location;
import net.skhu.likelion12thteam03be.location.domain.repository.LocationRepository;
import net.skhu.likelion12thteam03be.mood.domain.Mood;
import net.skhu.likelion12thteam03be.mood.domain.repository.MoodRepository;
import net.skhu.likelion12thteam03be.post.api.dto.request.PostSaveReqDto;
import net.skhu.likelion12thteam03be.post.api.dto.request.PostUpdateReqDto;
import net.skhu.likelion12thteam03be.post.api.dto.response.PostInfoResDto;
import net.skhu.likelion12thteam03be.post.api.dto.response.PostListResDto;
import net.skhu.likelion12thteam03be.post.domain.Post;
import net.skhu.likelion12thteam03be.post.domain.repository.PostRepository;
import net.skhu.likelion12thteam03be.s3.S3Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final S3Service s3Service;
    private final MoodRepository moodRepository;

    @Transactional
    public void postSave(PostSaveReqDto postSaveReqDto, MultipartFile multipartFile, Principal principal) throws IOException {
        String imgUrl = s3Service.upload(multipartFile, "post");
        Long id = Long.parseLong(principal.getName());

        Location location = locationRepository.findById(postSaveReqDto.locationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 위치가 존재하지 않습니다. locationId = " + postSaveReqDto.locationId()));

        Category category = categoryRepository.findById(postSaveReqDto.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다. categoryId = " + postSaveReqDto.categoryId()));

        Mood mood = moodRepository.findById(postSaveReqDto.moodId())
                .orElseThrow(() -> new IllegalArgumentException("해당 분위기가 존재하지 않습니다. moodId = " + postSaveReqDto.moodId()));

        Post post = Post.builder()
                .title(postSaveReqDto.title())
                .content(postSaveReqDto.content())
                .location(location)
                .time(postSaveReqDto.time())
                .price(postSaveReqDto.price())
                .category(category)
                .mood(mood)
                .imgUrl(imgUrl)
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

    // 글 위치별 조회
    public PostListResDto postFindByLocationId(Long locationId) {
        Optional<Post> post = Optional.ofNullable(postRepository.findById(locationId).orElseThrow(
                () -> new IllegalArgumentException("해당 위치의 글을 조회할 수 없습니다. locationId = " + locationId)
        ));

        List<PostInfoResDto> postInfoResDtoList = post.stream()
                .map(PostInfoResDto::from)
                .toList();

        return PostListResDto.from(postInfoResDtoList);
    }

    // 글 카테고리별 조회
    public PostListResDto postFindByCategoryId(Long categoryId) {
        Optional<Post> posts = Optional.ofNullable(postRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리의 글을 찾을 수 없습니다. categoryId = " + categoryId)
        ));

        List<PostInfoResDto> postInfoResDtoList = posts.stream()
                .map(PostInfoResDto::from)
                .toList();

        return PostListResDto.from(postInfoResDtoList);
    }

    // 글 분위기별 조회
    public PostListResDto postFindByMoodId(Long moodId) {
        Optional<Post> posts = Optional.ofNullable(postRepository.findById(moodId).orElseThrow(
                () -> new IllegalArgumentException("해당 분위기의 글을 찾을 수 없습니다. moodId = " + moodId)
        ));

        List<PostInfoResDto> postInfoResDtoList = posts.stream()
                .map(PostInfoResDto::from)
                .toList();

        return PostListResDto.from(postInfoResDtoList);
    }

    // 글 작성자별 조회(내 글 조회)
    public PostListResDto postFindByUserId(Long userId) {
        Optional<Post> posts = Optional.ofNullable(postRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자가 작성한 글을 찾을 수 없습니다. userId = " + userId)
        ));

        List<PostInfoResDto> postInfoResDtoList = posts.stream()
                .map(PostInfoResDto::from)
                .toList();

        return PostListResDto.from(postInfoResDtoList);
    }

    // 글 검색 조회

    // 글 수정
    @Transactional
    public void postUpdate(Long postId, PostUpdateReqDto postUpdateReqDto, MultipartFile multipartFile) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 글을 수정할 수 없습니다. postId = " + postId)
        );

        Location location = locationRepository.findById(postUpdateReqDto.locationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 위치가 존재하지 않습니다. locationId = " + postUpdateReqDto.locationId()));

        Category category = categoryRepository.findById(postUpdateReqDto.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다. categoryId = " + postUpdateReqDto.categoryId()));

        Mood mood = moodRepository.findById(postUpdateReqDto.moodId())
                .orElseThrow(() -> new IllegalArgumentException("해당 분위기가 존재하지 않습니다. moodId = " + postUpdateReqDto.moodId()));

        String imgUrl = s3Service.upload(multipartFile, "post");

        post.update(location, category, postUpdateReqDto, mood, imgUrl);
        PostInfoResDto.from(post);
    }

    // 글 삭제
    @Transactional
    public void postDelete(Long postId) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 글을 삭제할 수 없습니다. postId = " + postId)
        );

        Optional<String> imgUrl = Optional.ofNullable(post.getImgUrl());

        imgUrl.ifPresentOrElse(
                url -> {
                    try {
                        s3Service.delete(url, "post");
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("이미지 삭제 중 오류 발생", e);
                    }
                    postRepository.delete(post);
                },
                () -> {
                    throw new IllegalArgumentException("이미지 URL이 존재하지 않습니다. postId = " + postId);
                }
        );
    }
}
