package net.skhu.likelion12thteam03be.survey.api.response;

import lombok.Builder;
import net.skhu.likelion12thteam03be.post.domain.Post;
import net.skhu.likelion12thteam03be.survey.domain.Survey;

import java.util.Collections;
import java.util.List;

@Builder
public record SurveyResDto(
        String animalPic,
        String type,
        List<String> colorComments, // request 받을 때: List<Long> colorIds,
        List<Post> postList
) {
    public static SurveyResDto from (Survey survey, List<String> colorComments) {
        List<Post> safePostList = Collections.emptyList();  // post는 잠시 비워둘 예정
        return SurveyResDto.builder()
                .animalPic(survey.getEmotion().getAnimalPic())
                .type(survey.getEmotion().getType())
                .colorComments(colorComments)
                .postList(safePostList)
                .build();
    }
}
