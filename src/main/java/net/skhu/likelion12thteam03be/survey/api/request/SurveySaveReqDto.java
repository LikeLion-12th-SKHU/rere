package net.skhu.likelion12thteam03be.survey.api.request;

import java.util.List;

public record SurveySaveReqDto(
        Long emotionId,
        List<Long> colorIds,
        int score
) {
}
