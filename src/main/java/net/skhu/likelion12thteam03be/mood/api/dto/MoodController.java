package net.skhu.likelion12thteam03be.mood.api.dto;

import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.mood.api.dto.response.MoodListResDto;
import net.skhu.likelion12thteam03be.mood.application.MoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moods")
public class MoodController {
    private final MoodService moodService;

    @GetMapping
    public ResponseEntity<MoodListResDto> moodFindAll() {
        MoodListResDto moodListResDto = moodService.moodFindAll();
        return new ResponseEntity<>(moodListResDto, HttpStatus.OK);
    }
}
