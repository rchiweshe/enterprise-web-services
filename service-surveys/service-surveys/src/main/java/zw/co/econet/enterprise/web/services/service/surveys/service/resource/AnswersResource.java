package zw.co.econet.enterprise.web.services.service.surveys.service.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.econet.enterprise.web.services.common.utils.constants.Constants;
import zw.co.econet.enterprise.web.services.service.surveys.business.logic.api.AnswerService;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.AnswerDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.request.AnswersRequestDto;
import zw.co.econet.enterprise.web.services.service.surveys.util.response.AnswersResponse;

@ApiResponses(value = {
        @ApiResponse(code = 201, message = "Resource successfully created"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
})

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/answers")
public class AnswersResource {

    private final AnswerService answerService;
    Logger log = LoggerFactory.getLogger(AnswersResource.class.getName());

    @Autowired
    public AnswersResource(AnswerService answerService) {
        this.answerService = answerService;
    }

    @ApiOperation(value = "Add Answer to specific Question", response = AnswersResponse.class)
    @PostMapping
    public ResponseEntity<AnswersResponse> createAnswer(@RequestBody final AnswersRequestDto request,
                                                        @ApiParam(value = Constants.SOURCE_NAME)
                                                        @RequestHeader(value = Constants.SOURCE_NAME) final String username) {
        try {
            return ResponseEntity.ok(answerService.create(request, username));
        } catch (Exception ex) {
            log.info(">>>Failed to create answer due to an exception -> {}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Edit Answer", response = AnswersResponse.class)
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AnswersResponse> updateAnswer(@PathVariable("id") final Long id,
                                                        @RequestBody final AnswerDto request,
                                                        @ApiParam(value = Constants.SOURCE_NAME)
                                                        @RequestHeader(value = Constants.SOURCE_NAME) final String username) {
        if (request.getId()==null)request.setId(id);
        try {
            return ResponseEntity.ok(answerService.update(request, username));
        } catch (Exception ex) {
            log.info(">>>Failed to update answer due to an exception -> {}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Delete Answer", response = AnswersResponse.class)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AnswersResponse> deleteAnswer(@PathVariable("id") final Long id,
                                                        @ApiParam(value = Constants.SOURCE_NAME)
                                                        @RequestHeader(value = Constants.SOURCE_NAME) final String username) {
        try {
            return ResponseEntity.ok(answerService.deleteAnswer(id, username));
        } catch (Exception ex) {
            log.info(">>>Failed to update answer due to an exception -> {}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Find Answer By Id", response = AnswersResponse.class)
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AnswersResponse> searchAnswerById(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(answerService.findById(id));
        } catch (Exception ex) {
            log.info(">>>Failed to update answer due to an exception -> {}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Find Answers by question ", response = AnswersResponse.class)
    @GetMapping(value = "/question/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AnswersResponse> findAnswersByQuestionId(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(answerService.findAnswersByQuestionId(id));
        } catch (Exception ex) {
            log.info(">>>Failed to update answer due to an exception -> {}", ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}

