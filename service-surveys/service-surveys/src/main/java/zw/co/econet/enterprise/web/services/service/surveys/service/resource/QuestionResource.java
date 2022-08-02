package zw.co.econet.enterprise.web.services.service.surveys.service.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.service.processor.api.QuestionProcessor;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.QuestionEditRequest;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.QuestionRequest;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.QuestionResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value ="/questions")
public class QuestionResource {

    private QuestionProcessor questionProcessor;

    public QuestionResource(QuestionProcessor questionProcessor) {
        this.questionProcessor = questionProcessor;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(Question.class);

    @ApiOperation(value = "Create Question record", response = QuestionResponse.class)
    @PostMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public QuestionResponse questionCreation(@RequestBody final QuestionRequest questionRequest,
                                             @RequestHeader(value = Constants.SOURCE_NAME) final String username,
                                             @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                                                                                   defaultValue = Constants.DEFAULT_LOCALE) final Locale locale){

        LOGGER.info("Received user: {}", questionRequest.toString());

        return questionProcessor.save(questionRequest, locale, username);

    }

    @ApiOperation(value = "search Question by id", response = QuestionResponse.class)
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public QuestionResponse searchQuestionById(@PathVariable("id") final Long id,
                                               @ApiParam(value = Constants.SOURCE_TYPE_NARRATIVE) @RequestHeader(value = Constants.SOURCE_NAME) final String username,
                                               @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                       defaultValue = Constants.DEFAULT_LOCALE) final Locale locale){

        return questionProcessor.findById(id,locale);

    }

    @ApiOperation(value = "search Question by survey id", response = QuestionResponse.class)
    @GetMapping(value = "/survey-id/{survey-id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public QuestionResponse searchQuestionById(@PathVariable("survey-id") final String surveyId,
                                               @ApiParam(value = Constants.SOURCE_TYPE_NARRATIVE) @RequestHeader(value = Constants.SOURCE_NAME) final String username,
                                               @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                       defaultValue = Constants.DEFAULT_LOCALE) final Locale locale){

        return questionProcessor.findQuestionsBySurveyId(surveyId,locale);

    }

    @ApiOperation(value = "Update Question",response = QuestionResponse.class)
    @PutMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public QuestionResponse updateQuestion(@RequestBody final QuestionEditRequest questionEditRequest,
                                           @RequestHeader(value = Constants.SOURCE_NAME) final String username,
                                           @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                   defaultValue = Constants.DEFAULT_LOCALE) final Locale locale){


        return questionProcessor.edit(questionEditRequest, locale, username);

    }

    @ApiOperation(value = "search Question by status", response = QuestionResponse.class)
    @GetMapping(value = {"/question-status/{question-status}"}, params = {"page", "size"},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public QuestionResponse findQuestionByStatus(@PathVariable("status") final String status,
                                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                                 @ApiParam(value = Constants.SOURCE_TYPE_NARRATIVE) @RequestHeader(value =  Constants.SOURCE_NAME) final String username,
                                                 @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                         defaultValue = Constants.DEFAULT_LOCALE) final Locale locale){

        RecordStatus recordStatus =  RecordStatus.valueOf(status.toUpperCase());

        return questionProcessor.findByRecordStatus(recordStatus,page,size, locale);

    }

    @ApiOperation(value = "Retrieve All Questions", response = QuestionResponse.class)
    @GetMapping(value = "/",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public QuestionResponse FindAllQuestions(
            @ApiParam(value = Constants.SOURCE_TYPE_NARRATIVE) @RequestHeader(value =  Constants.SOURCE_NAME) final String username,
            @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                    defaultValue = Constants.DEFAULT_LOCALE) final Locale locale){

        return questionProcessor.findAll(locale);

    }

    @ApiOperation(value = "Retrieve All Questions  with paging ", response = QuestionResponse.class)
    @GetMapping(value = "/paged", params = {"page", "size"},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public QuestionResponse FindAllPagedQuestions(
            @ApiParam(value = Constants.SOURCE_TYPE_NARRATIVE) @RequestHeader(value =  Constants.SOURCE_NAME) final String username,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE) @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                    defaultValue = Constants.DEFAULT_LOCALE) final Locale locale){

        return questionProcessor.findAll(page,size,locale);

    }
}
