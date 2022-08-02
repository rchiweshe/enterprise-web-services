package zw.co.econet.enterprise.web.services.service.surveys.service.processor.api;

import java.util.Locale;
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.QuestionEditRequest;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.QuestionRequest;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.QuestionResponse;

public interface QuestionProcessor {

     QuestionResponse save(QuestionRequest questionRequest, Locale locale, String username);

     QuestionResponse edit(QuestionEditRequest questionEditRequest, Locale locale, String username);

     QuestionResponse findById(Long id, Locale locale);

     QuestionResponse findAll(Locale locale);

     QuestionResponse findByRecordStatus(RecordStatus recordStatus, int page, int size, Locale locale);

     QuestionResponse findAll(int page, int size, Locale locale);

     QuestionResponse findQuestionsBySurveyId(String surveyId, Locale locale);
}
