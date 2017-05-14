package easytests.services;

import easytests.entities.PointEntity;
import easytests.mappers.PointsMapper;
import easytests.models.PointModel;
import easytests.models.PointModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author nikitalpopov
 */
@Service
public class PointsService {

    @Autowired
    private PointsMapper pointsMapper;

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

    public void save(PointModelInterface pointModel) {

        final PointEntity pointEntity = this.map(pointModel);
        if (pointEntity.getId() == null) {
            this.pointsMapper.insert(pointEntity);
            pointModel.setId(pointEntity.getId());
            return;
        }
        this.pointsMapper.update(pointEntity);

    }

    public void delete(PointModelInterface pointModel) {

        final PointEntity pointEntity = this.map(pointModel);
        if (pointEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.pointsMapper.delete(pointEntity);

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