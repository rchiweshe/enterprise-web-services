package zw.co.econet.enterprise.web.services.service.surveys.service.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Locale;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zw.co.econet.enterprise.web.services.common.utils.constants.Constants;
import zw.co.econet.enterprise.web.services.service.surveys.service.processor.api.SurveyResponseProcessor;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyResponseDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.SurveyResponse;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.SurveysResponse;

@RestController
@CrossOrigin
@RequestMapping(path = "/surveys-responses")
public class SurveyResponseResource {

    private final SurveyResponseProcessor surveyResponseProcessor;

    public SurveyResponseResource(SurveyResponseProcessor surveyResponseProcessor) {
        this.surveyResponseProcessor = surveyResponseProcessor;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource successfully created"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    @ApiOperation(value = "Save a survey question response", response = SurveyResponse.class)
    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public SurveysResponse createSurveyResponse(@RequestBody final SurveyResponseDto surveyResponseDto,
                                                @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                                @RequestHeader(value = Constants.LOCALE_LANGUAGE, defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return surveyResponseProcessor.save(surveyResponseDto, locale);
    }

    @ApiOperation(value = "Save many survey question response at once", response = SurveyResponse.class)
    @PostMapping(value = "saveAll", produces = {MediaType.APPLICATION_JSON_VALUE})
    public SurveysResponse saveAllSurveyResponses(@RequestBody final List<SurveyResponseDto> surveyResponseDtos,
                                                  @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                          defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return surveyResponseProcessor.saveAll( surveyResponseDtos, locale);
    }

    @ApiOperation(value = "Find Survey Response By Id", response = SurveyResponse.class)
    @GetMapping(value = "/id/{id}")
    public SurveysResponse searchSurveyResponseById(@PathVariable("id") final Long id,
                                                    @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                            defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return surveyResponseProcessor.findById(id, locale);
    }

    @ApiOperation(value = "Find Survey Response By survey Id", response = SurveyResponse.class)
    @GetMapping(value = "/survey-id/{surveyId}")
    public SurveysResponse searchSurveyResponsesBySurveyId(@PathVariable("surveyId") final String surveyId,
                                                           @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                                   defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return surveyResponseProcessor.findBySuveryId(surveyId, locale);
    }

    @ApiOperation(value = "Find Survey Response By msisdn", response = SurveyResponse.class)
    @GetMapping(value = "/msisdn/{msisdn}")
    public SurveysResponse searchSurveyResponsesByMsisdn(@PathVariable("msisdn") final String msisdn,
                                                         @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                                 defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return surveyResponseProcessor.findByMsisdn(msisdn, locale);
    }

    @ApiOperation(value = "Find Survey Response By question id", response = SurveyResponse.class)
    @GetMapping(value = "/question-id/{questionId}")
    public SurveysResponse searchSurveyResponsesByQuestionId(@PathVariable("questionId") final Long questionId,
                                                             @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                                     defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return surveyResponseProcessor.findByQuestion(questionId, locale);
    }

    @ApiOperation(value = "Find Survey Response By msisdn and question id", response = SurveyResponse.class)
    @GetMapping(value = "/question-id/msisdn/{questionId}/{msisdn}")
    public SurveysResponse searchSurveyResponsesByQuestionIdandMsisdn(@PathVariable("questionId") final String questionId,
                                                                      @PathVariable("msisdn") final String msisdn,
                                                                      @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                                              defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return surveyResponseProcessor.findByMsisdnAndQuestion(msisdn, questionId, locale);
    }

    @ApiOperation(value = "Find Survey Response By msisdn and survey Id", response = SurveyResponse.class)
    @GetMapping(value = "/survey-id/msisdn/{surveyId}/{msisdn}")
    public SurveysResponse searchSurveyResponsesBySurveyIdandMsisdn(@PathVariable("surveyId") final String surveyId,
                                                                    @PathVariable("msisdn") final String msisdn,
                                                                    @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                                            defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return surveyResponseProcessor.findByMsisdnAndSuveryId(msisdn, surveyId, locale);
    }

    @ApiOperation(value = "Retrieve All Surveys Responses And Return As Pages",
            response = SurveyResponse.class)
    @GetMapping(value = {""}, params = {"page", "size"})
    public  SurveysResponse retrieveAllSurveysByStatusAsPages(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestHeader("Authorization") final String authenticationToken,
            @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE)
            @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                    defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return surveyResponseProcessor.findAll(page, size, locale);
    }

}
