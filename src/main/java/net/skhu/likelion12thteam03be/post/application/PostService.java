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
import net.skhu.likelion12thteam03be.user.domain.User;
import net.skhu.likelion12thteam03be.user.domain.repository.UserRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.addAll;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final S3Service s3Service;
    private final MoodRepository moodRepository;
    private final UserRepository userRepository;

    @Transactional
    public void postSave(PostSaveReqDto postSaveReqDto, @RequestPart(required = false) MultipartFile multipartFile, Principal principal) throws IOException {
        String loginId = principal.getName();

        String imgUrl = s3Service.upload(multipartFile, "post");
        
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. loginId = " + loginId));

        Location location = locationRepository.findById(postSaveReqDto.locationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 위치가 존재하지 않습니다. locationId = " + postSaveReqDto.locationId()));

        Category category = categoryRepository.findById(postSaveReqDto.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다. categoryId = " + postSaveReqDto.categoryId()));

        List<Mood> moods = moodRepository.findAllById(postSaveReqDto.moodIds());

        Post post = Post.builder()
                .title(postSaveReqDto.title())
                .content(postSaveReqDto.content())
                .location(location)
                .time(postSaveReqDto.time())
                .price(postSaveReqDto.price())
                .category(category)
                .moods(moods)
                .imgUrl(imgUrl)
                .user(user)
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
    public PostListResDto postFindByMoodIds(List<Long> moodIds) {
        List<PostInfoResDto> postInfoResDtoList = new ArrayList<>();

        for (Long moodId : moodIds) {
            List<Post> posts = postRepository.findByMoodId(moodId);

            List<PostInfoResDto> collectPostInfoResDtoList = posts.stream()
                    .map(PostInfoResDto::from)
                    .collect(Collectors.toList());

            postInfoResDtoList.addAll(collectPostInfoResDtoList);
        }
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
    public PostListResDto postFindByInput(String input) {
        String searchInput = "%" + input + "%";
        List<Post> posts = postRepository.findByInput(searchInput);

        List<PostInfoResDto> postInfoResDtoList = posts.stream()
                .map(PostInfoResDto::from)
                .toList();

        return PostListResDto.from(postInfoResDtoList);
    }

    // 글 추천 조회
    public PostListResDto postFindByRecommend(String recommend) {
        List<PostInfoResDto> postInfoResDtoList = new ArrayList<>();
        List<PostInfoResDto> collectPostInfoResDtoList = new ArrayList<>();
        String recommendWord = "%" + recommend + "%";

        List<Post> posts = postRepository.findByInput(recommendWord);
        collectPostInfoResDtoList = posts.stream()
                .map(PostInfoResDto::from)
                .toList();
        postInfoResDtoList.addAll(collectPostInfoResDtoList);

        posts = postRepository.findByRecommend(recommendWord);
        collectPostInfoResDtoList = posts.stream()
                .map(PostInfoResDto::from)
                .toList();

        postInfoResDtoList.addAll(collectPostInfoResDtoList);

        return PostListResDto.from(postInfoResDtoList);
    }

    // 글 수정
    @Transactional
    public void postUpdate(Long postId, PostUpdateReqDto postUpdateReqDto,@RequestPart(required = false) MultipartFile multipartFile, Principal principal) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 글을 수정할 수 없습니다. postId = " + postId)
        );

        String loginId = principal.getName();
        User currentUser = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("현재 사용자 정보를 찾을 수 없습니다. username = " + loginId));
        if (!post.getUser().getLoginId().equals(currentUser.getLoginId())) {
            throw new SecurityException("이 글을 수정할 권한이 없습니다.");
        }

        Location location = locationRepository.findById(postUpdateReqDto.locationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 위치가 존재하지 않습니다. locationId = " + postUpdateReqDto.locationId()));

        Category category = categoryRepository.findById(postUpdateReqDto.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다. categoryId = " + postUpdateReqDto.categoryId()));

        List<Mood> moods = moodRepository.findAllById(postUpdateReqDto.moodIds());

        String imgUrl = s3Service.upload(multipartFile, "post");

        post.update(location, category, postUpdateReqDto, moods, imgUrl);
        postRepository.save(post);
    }

    // 글 삭제
    @Transactional
    public void postDelete(Long postId, Principal principal) throws IOException {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 글을 삭제할 수 없습니다. postId = " + postId)
        );
        String loginId = principal.getName();
        User currentUser = userRepository.findByLoginId(loginId)
                .orElseThrow(()-> new IllegalArgumentException("현재 사용자 정보를 찾을 수 없습니다. username = " + loginId));
        if (!post.getUser().getLoginId().equals(currentUser.getLoginId())) {
            throw new SecurityException("이 글을 삭제할 권한이 없습니다.");
        }
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
