package net.skhu.likelion12thteam03be.category.application;

import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.category.api.dto.request.CategorySaveReqDto;
import net.skhu.likelion12thteam03be.category.api.dto.request.CategoryUpdateReqDto;
import net.skhu.likelion12thteam03be.category.api.dto.response.CategoryInfoResDto;
import net.skhu.likelion12thteam03be.category.api.dto.response.CategoryListResDto;
import net.skhu.likelion12thteam03be.category.domain.Category;
import net.skhu.likelion12thteam03be.category.domain.repository.CategoryRepository;
import net.skhu.likelion12thteam03be.post.api.dto.response.PostInfoResDto;
import net.skhu.likelion12thteam03be.post.api.dto.response.PostListResDto;
import net.skhu.likelion12thteam03be.post.domain.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public void categorySave(CategorySaveReqDto categorySaveReqDto) {
        if (categoryRepository.existsByName(categorySaveReqDto.name())) {
            throw new IllegalArgumentException("해당 카테고리가 이미 존재합니다.");
        }
        Category category = Category.builder()
                .name(categorySaveReqDto.name())
                .build();

        categoryRepository.save(category);
    }

    public CategoryListResDto categoryFindAll() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryInfoResDto> categoryInfoResDtoList = categories.stream()
                .map(CategoryInfoResDto::from)
                .toList();

        return CategoryListResDto.from(categoryInfoResDtoList);
    }

    @Transactional
    public void categoryUpdate(Long categoryId, CategoryUpdateReqDto categoryUpdateReqDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다.")
        );
        category.update(categoryUpdateReqDto.name());
    }

    @Transactional
    public void categoryDelete(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다.")
        );
        categoryRepository.deleteById(categoryId);
    }
}
