package net.skhu.likelion12thteam03be.survey.application;

import lombok.RequiredArgsConstructor;

import net.skhu.likelion12thteam03be.color.domian.Color;
import net.skhu.likelion12thteam03be.color.domian.repository.ColorRepository;
import net.skhu.likelion12thteam03be.emotion.domain.Emotion;
import net.skhu.likelion12thteam03be.emotion.domain.repository.EmotionRepository;
import net.skhu.likelion12thteam03be.survey.api.request.SurveySaveReqDto;
import net.skhu.likelion12thteam03be.survey.api.response.SurveyResDto;
import net.skhu.likelion12thteam03be.survey.domain.Survey;
import net.skhu.likelion12thteam03be.survey.domain.repository.SurveyRepository;
import net.skhu.likelion12thteam03be.survey.exception.SurveyAlreadyExistsException;
import net.skhu.likelion12thteam03be.user.domain.User;
import net.skhu.likelion12thteam03be.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;
    private final EmotionRepository emotionRepository;
    private final ColorRepository colorRepository;

    @Transactional
    public SurveyResDto save(SurveySaveReqDto surveySaveReqDto, Principal principal) {
        String loginId = principal.getName();
        if (surveyRepository.existsByUserLoginId(loginId)){
            throw new SurveyAlreadyExistsException("이미 설문조사 결과가 존재합니다. 유저 아이디 = " + loginId);
        }
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("유저 정보를 찾을 수 없습니다: " + loginId));
        Emotion emotion = emotionRepository.findById(surveySaveReqDto.emotionId())
                .orElseThrow(() -> new IllegalArgumentException("해당 감정을 찾을 수 없습니다: " + surveySaveReqDto.emotionId()));
        List<Color> colors = colorRepository.findAllById(surveySaveReqDto.colorIds());

        Survey survey = Survey.builder()
                .score(surveySaveReqDto.score())
                .emotion(emotion)
                .colors(colors)
                .user(user)
                .build();
        surveyRepository.save(survey);

        List<String> colorComments = colors.stream()
                .map(Color::getComment)
                .toList();

        return SurveyResDto.from(survey, colorComments);
    }

    @Transactional
    public void delete(Principal principal) {
        String loginId = principal.getName();
        Survey survey = surveyRepository.findByUserLoginId(loginId);
        if (survey == null) {
            throw new IllegalArgumentException("삭제할 설문조사가 존재하지 않습니다: " + loginId);
        }
        surveyRepository.delete(survey);
    }

/*    @Transactional
    public SurveyResDto update(Long surveyId, SurveySaveReqDto surveySaveReqDto, Principal principal) {
        String loginId = principal.getName();
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("설문조사 결과를 찾을 수 없습니다: " + surveyId));

        if (!survey.getUser().getLoginId().equals(loginId)) {
            throw new IllegalArgumentException("해당 설문조사를 수정할 권한이 없습니다.");
        }

        Emotion emotion = emotionRepository.findById(surveySaveReqDto.emotionId())
                .orElseThrow(() -> new IllegalArgumentException("해당 감정을 찾을 수 없습니다: " + surveySaveReqDto.emotionId()));

        List<Color> colors = colorRepository.findAllById(surveySaveReqDto.colorIds());


        survey.update(surveySaveReqDto.score(), emotion, colors);

        surveyRepository.save(survey);

        List<String> colorComments = colors.stream()
                .map(Color::getComment)
                .toList();
        return SurveyResDto.from(survey, colorComments);
    }

    public SurveyResDto findById(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("설문조사 결과를 찾을 수 없습니다: " + surveyId));

        List<Color> colors = survey.getColors();
        List<String> colorComments = colors.stream()
                .map(Color::getComment)
                .toList();

        return SurveyResDto.from(survey, colorComments);
    }*/
}
