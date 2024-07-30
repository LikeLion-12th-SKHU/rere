package net.skhu.likelion12thteam03be.category.api;

import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.category.api.dto.response.CategoryListResDto;
import net.skhu.likelion12thteam03be.category.application.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<CategoryListResDto> categoryFindAll() {
        CategoryListResDto categoryListResDto = categoryService.categoryFindAll();
        return new ResponseEntity<>(categoryListResDto, HttpStatus.OK);
    }
}
