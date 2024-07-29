package net.skhu.likelion12thteam03be.category.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.category.api.dto.request.CategorySaveReqDto;
import net.skhu.likelion12thteam03be.category.api.dto.request.CategoryUpdateReqDto;
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

    @PostMapping
    public ResponseEntity<String> categorySave(@RequestBody @Valid CategorySaveReqDto categorySaveReqDto) {
        categoryService.categorySave(categorySaveReqDto);
        return new ResponseEntity<>("Successful Category Save!", HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<CategoryListResDto> categoryFindAll() {
        CategoryListResDto categoryListResDto = categoryService.categoryFindAll();
        return new ResponseEntity<>(categoryListResDto, HttpStatus.OK);
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<String> categoryUpdate(@PathVariable Long categoryId, @RequestBody @Valid CategoryUpdateReqDto categoryUpdateReqDto) {
        categoryService.categoryUpdate(categoryId, categoryUpdateReqDto);
        return new ResponseEntity<>("Successful Category Update! categoryId = " + categoryId, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> categoryDelete(@PathVariable Long categoryId) {
        categoryService.categoryDelete(categoryId);
        return new ResponseEntity<>("Successful Category Delete! categoryId = " + categoryId, HttpStatus.OK);
    }
}
