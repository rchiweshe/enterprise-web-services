package zw.co.econet.enterprise.web.services.service.surveys.service.resource;

import io.swagger.annotations.ApiParam;
import java.util.Locale;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.econet.enterprise.web.services.common.utils.constants.Constants;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.domain.SurveyDirect;
import zw.co.econet.enterprise.web.services.service.surveys.domain.SurveyTracker;
import zw.co.econet.enterprise.web.services.service.surveys.repository.SurveyDirectRepository;
import zw.co.econet.enterprise.web.services.service.surveys.repository.SurveyTrackerRepository;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyDirectDto;

@RestController
@CrossOrigin
@RequestMapping(path = "/direct-surveys")
public class SurveyDirectResource {

    private SurveyDirectRepository surveyDirectRepository;
    private SurveyTrackerRepository surveyTrackerRepository;
    private ModelMapper modelMapper;

    public SurveyDirectResource(SurveyDirectRepository surveyDirectRepository,
                                SurveyTrackerRepository surveyTrackerRepository,
                                ModelMapper modelMapper) {
        this.surveyDirectRepository = surveyDirectRepository;
        this.surveyTrackerRepository = surveyTrackerRepository;
        this.modelMapper = modelMapper;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(Question.class);

    @PostMapping(value = "/ussd", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean createRecord(@RequestBody final SurveyDirectDto surveyDirectDto,
                                         @ApiParam(value = Constants.LOCALE_LANGUAGE_NARRATIVE)
                                    @RequestHeader(value = Constants.LOCALE_LANGUAGE,
                                                   defaultValue = Constants.DEFAULT_LOCALE) final Locale locale){
        try {
            LOGGER.info("Received request to create respose for survey question: {}", surveyDirectDto.toString());
            SurveyDirect surveyDirect = modelMapper.map(surveyDirectDto, SurveyDirect.class);

            Optional<SurveyDirect> surveyDirectRetrieved = surveyDirectRepository
                    .findByMsisdnAndAndSurveyNameAndQuestion(surveyDirectDto.getMsisdn(),
                            surveyDirectDto.getSurveyName().toLowerCase(), surveyDirectDto.getQuestion().toLowerCase());

            if(surveyDirectRetrieved.isPresent()) {
                LOGGER.info("survey response already exists : {}", surveyDirectDto);
            } else if (!surveyDirectRetrieved.isPresent()){
                SurveyDirect surveyDirectSaved = surveyDirectRepository.save(surveyDirect);
            }

            if (Boolean.parseBoolean(surveyDirectDto.getComplete())) {
                Optional<SurveyTracker> surveyTrackerRetrieved = surveyTrackerRepository.
                        findByMsisdnAndSurveyName(surveyDirectDto.getMsisdn(),surveyDirectDto.getSurveyName());
                if (surveyTrackerRetrieved.isPresent()) { return true; }
                SurveyTracker surveyTracker = new SurveyTracker();
                surveyTracker.setSurveyName(surveyDirectDto.getSurveyName());
                surveyTracker.setMsisdn(surveyDirectDto.getMsisdn());
                SurveyTracker surveyTrackerSaved = surveyTrackerRepository.save(surveyTracker);
            }
            LOGGER.info("successfully saved record {}", surveyDirectDto.toString());
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}


