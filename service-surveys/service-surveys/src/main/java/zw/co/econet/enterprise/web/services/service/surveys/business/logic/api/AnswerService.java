package zw.co.econet.enterprise.web.services.service.surveys.business.logic.api;

import zw.co.econet.enterprise.web.services.service.surveys.util.dto.AnswerDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.AnswersRequestDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.AnswersResponse;

public interface AnswerService {

    AnswersResponse create(AnswersRequestDto request, String username) throws Exception;

    AnswersResponse update(AnswerDto request, String username) throws Exception;

    AnswersResponse deleteAnswer(Long id, String username) throws Exception;

    AnswersResponse findById(Long id) throws Exception;

    AnswersResponse findAnswersByQuestionId(Long id);
}

