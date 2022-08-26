package zw.co.econet.enterprise.web.services.service.surveys.business.logic.api;

import java.util.Locale;
import org.springframework.data.domain.Pageable;
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.QuestionEditRequest;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.QuestionRequest;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.ServiceResponse;

public interface QuestionService {

     ServiceResponse save(QuestionRequest questionRequest, Locale locale, String username);

     ServiceResponse edit(QuestionEditRequest questionDto, Locale locale, String username);

     ServiceResponse findById(Long id, Locale locale);

     ServiceResponse findAll(Locale locale);

     ServiceResponse findByRecordStatus(RecordStatus recordStatus, Pageable pageable, Locale locale);

     ServiceResponse findAll(Pageable pageable, Locale locale);

     ServiceResponse findQuestionsBySurvey(String surveyId,Locale locale);

}
