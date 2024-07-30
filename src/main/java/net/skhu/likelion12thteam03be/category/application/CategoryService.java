package net.skhu.likelion12thteam03be.category.application;

import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.category.api.dto.response.CategoryInfoResDto;
import net.skhu.likelion12thteam03be.category.api.dto.response.CategoryListResDto;
import net.skhu.likelion12thteam03be.category.domain.Category;
import net.skhu.likelion12thteam03be.category.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryListResDto categoryFindAll() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryInfoResDto> categoryInfoResDtoList = categories.stream()
                .map(CategoryInfoResDto::from)
                .toList();

        return CategoryListResDto.from(categoryInfoResDtoList);
    }
}
