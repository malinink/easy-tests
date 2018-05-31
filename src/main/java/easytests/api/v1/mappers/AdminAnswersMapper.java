package easytests.api.v1.mappers;

import easytests.api.v1.models.AdminAnswer;
import easytests.core.models.AnswerModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


/**
 * @author RisaMagpie
 */
@Service("AdminAnswersMapperV1")
public class AdminAnswersMapper extends ModelMapper {
    public AdminAnswersMapper() {
        super();
        this.createTypeMap(AdminAnswer.class, AnswerModel.class)
            .addMappings(
                mapper -> mapper.map(AdminAnswer::getId, AnswerModel::setId))
            .addMappings(
                mapper -> mapper.map(AdminAnswer::getText, AnswerModel::setTxt))
            .addMappings(
                mapper -> mapper.map(AdminAnswer::getNumber, AnswerModel::setSerialNumber))
            .addMappings(
                mapper -> mapper.map(AdminAnswer::getIsRight, AnswerModel::setRight)
            );
    }

}
