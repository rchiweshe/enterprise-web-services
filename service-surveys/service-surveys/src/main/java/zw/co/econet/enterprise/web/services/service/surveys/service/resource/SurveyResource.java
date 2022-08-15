package zw.co.econet.enterprise.web.services.service.surveys.service.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Locale;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zw.co.econet.enterprise.web.services.common.utils.constants.Constants;
import zw.co.econet.enterprise.web.services.service.surveys.service.processor.api.SurveyProcessor;
import zw.co.econet.enterprise.web.services.service.surveys.util.SurveyStatus;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.CreateSurveyRequest;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.SurveyResponse;

@RestController
@CrossOrigin
@RequestMapping(path = "/surveys")
public class SurveyResource {

    private final SurveyProcessor surveyProcessor;

    public SurveyResource(SurveyProcessor surveyProcessor) {
        this.surveyProcessor = surveyProcessor;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource successfully created"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    @ApiOperation(value = "Create a Survey", response = SurveyResponse.class)
    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public SurveyResponse createSurvey(@RequestBody final CreateSurveyRequest createSurveyRequest,
                                           @RequestHeader("Authorization") final String authenticationToken,
                                           @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                      defaultValue = Constants.DEFAULT_LOCALE) final Locale locale){

        return surveyProcessor.save(createSurveyRequest, locale, authenticationToken);
    }

    @ApiOperation(value = "Edit a Survey", response = SurveyResponse.class)
    @PutMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public SurveyResponse editSurvey(@RequestBody final SurveyDto surveyDto,
                                       @RequestHeader("Authorization") final String authenticationToken,
                                       @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                               defaultValue = Constants.DEFAULT_LOCALE) final Locale locale){

        return surveyProcessor.edit(surveyDto, locale, authenticationToken);
    }

    @ApiOperation(value = "Find Survey By Id", response = SurveyResponse.class)
    @GetMapping(value = "/id/{id}")
    public SurveyResponse searchSurveyById(@PathVariable("id") final Long id,
                                         @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                 defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return surveyProcessor.findById(id, locale);
    }

    @ApiOperation(value = "Find Survey by Name", response = SurveyResponse.class)
    @GetMapping(value = "/name/{name}")
    public SurveyResponse searchSurveyByName(@PathVariable("name") final String name,
                                           @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                   defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return surveyProcessor.findBySurveyName(name, locale);
    }

    @ApiOperation(value = "Find Survey By Name Like given parameter", response = SurveyResponse.class)
    @GetMapping(value = "/name-match/{name}")
    public SurveyResponse searchSurveyByNane(@PathVariable("name") final String name,
                                         @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                 defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return surveyProcessor.findSurveysWithMatchingName(name, locale);
    }

    @ApiOperation(value = "Retrieve All Surveys And Return As Pages", response = SurveyResponse.class)
    @GetMapping(value = {""}, params = {"page", "size"})
    public  SurveyResponse retrieveAllSurveysAsPages(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestHeader("Authorization") String authenticationToken,
            @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE)
            @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                    defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return surveyProcessor.findAll(page, size, locale);
    }

    @ApiOperation(value = "Retrieve All Surveys by Status And Return As Pages", response = SurveyResponse.class)
    @GetMapping(value = {"/survey-status"}, params = {"page", "size"})
    public  SurveyResponse retrieveAllSurveysByStatusAsPages(
            @RequestParam(value = "status") final String status,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestHeader("Authorization") final String authenticationToken,
            @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE)
            @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                    defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {

        return surveyProcessor.findBySurveyStatus(SurveyStatus.valueOf(status),page, size, locale);
    }


    @ApiOperation(value = "Get all surveys by status and return as list", response = SurveyResponse.class)
    @GetMapping(value = "/list")
    public SurveyResponse getSurveyList(@ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
            defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        return surveyProcessor.findAll(locale);
    }


}
