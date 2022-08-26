package zw.co.econet.enterprise.web.services.service.surveys.service.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Locale;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.econet.enterprise.web.services.common.utils.constants.Constants;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.domain.SurveyTracker;
import zw.co.econet.enterprise.web.services.service.surveys.repository.SurveyTrackerRepository;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.SurveyTrackerRequest;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.SurveyResponse;

@RestController
@CrossOrigin
@RequestMapping("/survey-tracker")
public class SurveyTrackerResource {

    private final SurveyTrackerRepository surveyTrackerRepository;
    private final ModelMapper modelMapper;

    public SurveyTrackerResource(SurveyTrackerRepository surveyTrackerRepository,
                                 ModelMapper modelMapper) {
        this.surveyTrackerRepository = surveyTrackerRepository;
        this.modelMapper = modelMapper;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(Question.class);

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void createRecord(@RequestBody final SurveyTrackerRequest surveyTrackerRequest,
                                @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                               defaultValue = Constants.DEFAULT_LOCALE) final Locale locale){
        try {
            LOGGER.info("Received request to create tracker for survey for msisdnn: {}", surveyTrackerRequest.getMsisdn());
            SurveyTracker surveyTracker = modelMapper.map(surveyTrackerRequest, SurveyTracker.class);

            Optional<SurveyTracker> surveyTrackerRetrieved = surveyTrackerRepository
                    .findByMsisdnAndSurveyName(surveyTrackerRequest.getMsisdn(),
                            surveyTrackerRequest.getSurveyName().toLowerCase());
            if(surveyTrackerRetrieved.isPresent()) {
                LOGGER.info("survey tracker for msisdn {} already exists : {}", surveyTrackerRequest.getMsisdn());
            }

            SurveyTracker surveyTrackerSaved = surveyTrackerRepository.save(surveyTracker);

            LOGGER.info("successfully saved record {}", surveyTracker.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @ApiOperation(value = "Find Survey Tracker Record", response = SurveyResponse.class)
    @GetMapping(value = "/find-by-msisdn-and-survey-name/msisdn/{msisdn}/survey-name/{surveyName}")
    public Boolean searchSurveyResponseById(@PathVariable("msisdn") final String msisdn,
                                                    @PathVariable("surveyName") final String surveyName,
                                                    @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                                        @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                                       defaultValue = Constants.DEFAULT_LOCALE) final Locale locale) {
        Optional<SurveyTracker> surveyTracker = surveyTrackerRepository.findByMsisdnAndSurveyName(msisdn, surveyName);
        if (surveyTracker.isPresent()) {
            return true;
        }
        return false;
    }
}
