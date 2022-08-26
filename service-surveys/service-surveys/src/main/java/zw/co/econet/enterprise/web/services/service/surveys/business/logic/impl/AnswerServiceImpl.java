package zw.co.econet.enterprise.web.services.service.surveys.business.logic.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import zw.co.econet.enterprise.web.services.common.utils.enums.RecordStatus;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.AnswerService;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Answers;
import zw.co.econet.enterprise.web.services.service.surveys.domain.Question;
import zw.co.econet.enterprise.web.services.service.surveys.repository.AnswersRepository;
import zw.co.econet.enterprise.web.services.service.surveys.repository.QuestionRepository;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.AnswerDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.exceptions.NotFoundException;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.AnswersRequestDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.AnswersResponse;

public class AnswerServiceImpl implements AnswerService {

    private final AnswersRepository answersRepository;
    private final QuestionRepository questionRepository;

    private final ModelMapper mapper;

    @Autowired
    public AnswerServiceImpl(AnswersRepository answersRepository, QuestionRepository questionRepository, ModelMapper mapper) {
        this.answersRepository = answersRepository;
        this.questionRepository = questionRepository;
        this.mapper = mapper;
    }

    @Override
    public AnswersResponse create(AnswersRequestDto request, String username) throws Exception {
        Question question = questionRepository.findById(request.getQuestion()).orElseThrow(() -> new NotFoundException("Question not found"));
        Answers answers = mapper.map(request, Answers.class);
        answers.setQuestion(question);
        Answers saved = answersRepository.save(answers);
        AnswerDto dto = mapper.map(saved, AnswerDto.class);
        dto.setQuestion(question.getId());
        return populateResponse(true, "Answer created successfully", dto);
    }

    @Override
    public AnswersResponse update(AnswerDto request, String username) throws Exception {
        Answers fetchedAnswer = answersRepository.findById(request.getId()).orElseThrow(() -> new NotFoundException("Answer not found"));
        if (request.getDescription() != null) {
            fetchedAnswer.setDescription(request.getDescription());
        }
        if (request.getAnswerNumber() != null) {
            fetchedAnswer.setAnswerNumber(request.getAnswerNumber());
        }
        if (request.getStatus() != null) {
            fetchedAnswer.setStatus(RecordStatus.valueOf(request.getStatus().toUpperCase()));
        }
        if (request.getQuestion() != null) {
            Question question = questionRepository.findById(request.getQuestion()).orElseThrow(() -> new NotFoundException("Question not found"));
            fetchedAnswer.setQuestion(question);
        }
        Answers answer = answersRepository.save(fetchedAnswer);
        AnswerDto dto = mapper.map(answer, AnswerDto.class);
        dto.setQuestion(answer.getQuestion().getId());
        return populateResponse(true, "Answer edited successfully", dto);
    }

    @Override
    public AnswersResponse deleteAnswer(Long id, String username) throws Exception {
        Answers fetchedAnswer = answersRepository.findById(id).orElseThrow(() -> new NotFoundException("Answer not found"));
        fetchedAnswer.setStatus(RecordStatus.DELETED);
        Answers answer = answersRepository.save(fetchedAnswer);
        AnswerDto dto = mapper.map(answer, AnswerDto.class);
        dto.setQuestion(fetchedAnswer.getQuestion().getId());
        return populateResponse(true, "Answer deleted Successfully", dto);
    }

    @Override
    public AnswersResponse findById(Long id) throws Exception {
        Answers fetchedAnswer = answersRepository.findById(id).orElseThrow(() -> new NotFoundException("Answer not found"));
        AnswerDto dto = mapper.map(fetchedAnswer, AnswerDto.class);
        dto.setQuestion(fetchedAnswer.getQuestion().getId());
        return populateResponse(true, "Answer retrieved successfully", dto);
    }

    @Override
    public AnswersResponse findAnswersByQuestionId(Long id) {
        List<Answers> answers = answersRepository.findAnswersByQuestionIdAndStatus(id, RecordStatus.ACTIVE);
        List<AnswerDto> dtos = answers
                .stream()
                .map(entity -> {
                    AnswerDto dto = mapper.map(entity, AnswerDto.class);
                    dto.setQuestion(entity.getQuestion().getId());
                    return dto;
                }).collect(Collectors.toList());
        return populateResponse(true, "Answers retrieved successfully", dtos);
    }

    public AnswersResponse populateResponse(boolean success, String message, Object data) {
        AnswersResponse response = new AnswersResponse<>();
        response.setSuccess(success);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
