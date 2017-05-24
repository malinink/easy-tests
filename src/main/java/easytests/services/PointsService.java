package easytests.services;

import easytests.entities.PointEntity;
import easytests.mappers.PointsMapper;
import easytests.models.PointModel;
import easytests.models.PointModelInterface;
import easytests.models.QuizModelInterface;
import easytests.options.PointsOptionsInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author nikitalpopov
 */
@Service
public class PointsService implements PointsServiceInterface {

    @Autowired
    private PointsMapper pointsMapper;

    @Override
    public void delete(PointModelInterface pointModel, PointsOptionsInterface pointsOptions) {

    }

    @Override
    public void delete(List<PointModelInterface> pointModels) {

    }

    @Override
    public void delete(List<PointModelInterface> pointModels, PointsOptionsInterface pointsOptions) {

    }

    @Override
    public void delete(PointModelInterface pointModel) {

        final PointEntity pointEntity = this.map(pointModel);
        if (pointEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.pointsMapper.delete(pointEntity);

    }

    @Override
    public void save(PointModelInterface pointModel, PointsOptionsInterface pointsOptions) {

    }

    @Override
    public void save(List<PointModelInterface> pointModels) {

    }

    @Override
    public void save(PointModelInterface pointModel) {

        final PointEntity pointEntity = this.map(pointModel);
        if (pointEntity.getId() == null) {
            this.pointsMapper.insert(pointEntity);
            pointModel.setId(pointEntity.getId());
            return;
        }
        this.pointsMapper.update(pointEntity);

    }

    @Override
    public void save(List<PointModelInterface> pointModels, PointsOptionsInterface pointsOptions) {

    }

    @Override
    public List<PointModelInterface> findByQuiz(QuizModelInterface quizModel) {
        return null;
    }

    @Override
    public List<PointModelInterface> findByQuiz(QuizModelInterface quizModel, PointsOptionsInterface pointsOptions) {
        return null;
    }

    public List<PointModelInterface> findAll() {
        return this.map(this.pointsMapper.findAll());
    }

    public PointModelInterface find(Integer id) {

        final PointEntity pointEntity = this.pointsMapper.find(id);
        if (pointEntity == null) {
            return null;
        }
        return this.map(pointEntity);

    }

    public PointModelInterface find(Integer id, PointsOptionsInterface pointsOptions) {
        return this.find(id);
    }

    private PointEntity map(PointModelInterface pointModel) {

        final PointEntity pointEntity = new PointEntity();
        pointEntity.map(pointModel);
        return pointEntity;

    }

    private PointModel map(PointEntity pointEntity) {

        final PointModel pointModel = new PointModel();
        pointModel.map(pointEntity);
        return pointModel;

    }

    private List<PointModelInterface> map(List<PointEntity> pointsList) {

        final List<PointModelInterface> resultPointList = new ArrayList<>(pointsList.size());
        for (PointEntity point: pointsList) {
            resultPointList.add(this.map(point));
        }
        return resultPointList;

    }
}
