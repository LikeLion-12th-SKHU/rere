package net.skhu.likelion12thteam03be.mood.application;

import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.mood.api.dto.response.MoodInfoResDto;
import net.skhu.likelion12thteam03be.mood.api.dto.response.MoodListResDto;
import net.skhu.likelion12thteam03be.mood.domain.Mood;
import net.skhu.likelion12thteam03be.mood.domain.repository.MoodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MoodService {
    private final MoodRepository moodRepository;

    @Transactional
    public MoodListResDto moodFindAll() {
        List<Mood> moods = moodRepository.findAll();

        List<MoodInfoResDto> moodInfoResDtoList = moods.stream()
                .map(MoodInfoResDto::from)
                .toList();

        return MoodListResDto.from(moodInfoResDtoList);
    }
}
