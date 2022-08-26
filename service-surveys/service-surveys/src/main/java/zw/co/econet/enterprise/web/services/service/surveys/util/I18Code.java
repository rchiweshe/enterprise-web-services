package zw.co.econet.enterprise.web.services.service.surveys.util;

public enum I18Code {

    MESSAGE_QUESTION_CREATION_SUCCESSFUL("message.question.created.successful"),
    MESSAGE_QUESTION_SEARCH_SUCCESS("message.question.search.successful"),
    MESSAGE_QUESTION_EDITED_SUCCESSFUL("message.question.edited.successful"),
    MESSAGE_QUESTION_EXISTS("message.question.exists"),
    MESSAGE_QUESTION_NOT_EDITED("message.question.not.edited"),
    MESSAGE_QUESTION_EXISTS_ALREADY("message.question.exists.already"),
    MESSAGE_QUESTION_NOT_FOUND("message.question.not.found"),
    MESSAGE_QUESTION_DELETE_SUCCESS("message.question.delete.success"),

    MESSAGE_SURVEY_INVALID_REQUEST("messages.survey.invalid.creation.request"),
    MESSAGE_SURVEY_WITH_NAME_ALREADY_EXISTS("messages.survey.name.already.exists"),
    MESSAGE_SURVEY_SUCCESSFULLY_CREATED("messages.survey.successfully.created"),
    MESSAGE_SURVEY_NOT_FOUND("messages.survey.not.found"),
    MESSAGE_SURVEY_SUCCESSFULLY_RETRIEVED("messages.survey.successfully.retrieved"),
    MESSAGE_SURVEY_INVALID_SEARCH_REQUEST("messages.survey.invalid.search.request"),
    MESSAGE_SURVEYS_SUCCESSFULLY_RETRIEVED("messages.surveys.successfully.retrieved"),
    MESSAGE_SURVEY_INVALID_EDIT_REQUEST("messages.invalid.survey.edit.request"),
    MESSAGE_SURVEY_SUCCESSFULLY_EDITED("message.survey.successfully.edited"),

    MESSAGE_SURVEY_RESPONSE_INVALID_CREATION_REQUEST("messages.invalid.survey.response.creation.request"),
    MESSAGE_INVALID_QUESTION_SELECTED("messages.invalid.question.selected"),
    MESSAGE_USER_RESPONSE_ALREADY_CAPTURED("messages.response.captured.already"),
    MESSAGE_INVALID_SURVEY_SELECTED("messages.invalid.survey.selected"),
    MESSAGE_RESPONSE_SAVED_SUCCESS("messages.answer.saved.successfull"),
    MESSAGE_SURVEY_RESPONSE_NOT_FOUND("messages.survey.response.not.found"),
    MESSAGE_SURVEY_RESPONSE_RETRIEVED_SUCCESS("messages.survey.response.retrieved.successfully"),
    MESSAGE_INVALID_SURVEY_RESPONSE_SELECTED("messages.invalid.survey.response.id"),
    MESSAGE_INVALID_SURVEY_RESPONSE_SEARCH_REQUEST("messages.invalid.survey.response.search.request"),
    MESSAGE_SURVEY_RESPONSES_RETRIEVED_SUCCESS("messages.surveys.response.retrieved.successfully"),
    MESSAGE_INVALID_MSISDN("messages.invalid.msisdn.supplied");

    private String code;

    I18Code(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}