package net.skhu.likelion12thteam03be.survey.api;

import lombok.RequiredArgsConstructor;
import net.skhu.likelion12thteam03be.survey.api.request.SurveySaveReqDto;
import net.skhu.likelion12thteam03be.survey.api.response.SurveyResDto;
import net.skhu.likelion12thteam03be.survey.application.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/psy-test")
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;
    @PostMapping
    public ResponseEntity<SurveyResDto> createSurvey(@RequestBody SurveySaveReqDto reqDto, Principal principal) {
        SurveyResDto surveyResDto = surveyService.save(reqDto, principal);
        return new ResponseEntity<>(surveyResDto, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSurvey(Principal principal) {
        surveyService.delete(principal);
        return new ResponseEntity<>("설문조사 결과가 정상적으로 삭제되었습니다.", HttpStatus.OK);
    }

/*    @PatchMapping("/{surveyId}")
    public ResponseEntity<SurveyResDto> updateSurvey(
            @PathVariable Long surveyId,
            @RequestBody SurveySaveReqDto surveySaveReqDto,
            Principal principal) {
        SurveyResDto response = surveyService.update(surveyId, surveySaveReqDto, principal);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyResDto> findByIdSurvey(@PathVariable Long surveyId) {
        SurveyResDto response = surveyService.findById(surveyId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/

}