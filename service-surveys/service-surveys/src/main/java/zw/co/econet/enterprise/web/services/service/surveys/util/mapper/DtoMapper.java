package zw.co.econet.enterprise.web.services.service.surveys.util.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import zw.co.econet.enterprise.web.services.service.surveys.domain.SurveyResponse;
import zw.co.econet.enterprise.web.services.service.surveys.util.dto.SurveyResponseDto;

@Mapper(componentModel = "spring")
public abstract class DtoMapper {

    public abstract SurveyResponseDto map(SurveyResponse surveyResponse);

    public abstract List<SurveyResponseDto> mapToSurveyResponseDtos(List<SurveyResponse> surveyResponses);

    public abstract SurveyResponse map(SurveyResponseDto surveyResponseDto);

    public abstract List<SurveyResponse> mapToSurveyResponses(List<SurveyResponseDto> surveyResponseDtos);
}



