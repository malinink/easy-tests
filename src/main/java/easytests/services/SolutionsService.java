package easytests.services;

import easytests.entities.SolutionEntity;
import easytests.mappers.SolutionsMapper;
import easytests.models.PointModelInterface;
import easytests.models.SolutionModel;
import easytests.models.SolutionModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SingularityA
 */
@Service
public class SolutionsService {

    @Autowired
    private SolutionsMapper solutionsMapper;

    public List<SolutionModelInterface> findAll() {
        return this.map(this.solutionsMapper.findAll());
    }

    public SolutionModelInterface find(Integer id) {
        final SolutionEntity solutionEntity = this.solutionsMapper.find(id);
        if (solutionEntity == null) {
            return null;
        }
        return this.map(solutionEntity);
    }

    public List<SolutionModelInterface> findByPoint(PointModelInterface point) {
        return this.map(this.solutionsMapper.findByPointId(point.getId()));
    }

    public void save(SolutionModelInterface solutionModel) {
        final SolutionEntity solutionEntity = this.map(solutionModel);
        if (solutionEntity.getId() != null) {
            this.solutionsMapper.update(solutionEntity);
            return;
        }
        this.solutionsMapper.insert(solutionEntity);
    }

    public void delete(SolutionModelInterface solutionModel) {
        final SolutionEntity solutionEntity = this.map(solutionModel);
        if (solutionEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.solutionsMapper.delete(solutionEntity);
    }

    private SolutionModelInterface map(SolutionEntity solutionEntity) {
        final SolutionModelInterface solutionModel = new SolutionModel();
        solutionModel.map(solutionEntity);
        return solutionModel;
    }

    private SolutionEntity map(SolutionModelInterface solutionModel) {
        final SolutionEntity solutionEntity = new SolutionEntity();
        solutionEntity.map(solutionModel);
        return solutionEntity;
    }

    private List<SolutionModelInterface> map(List<SolutionEntity> solutionsList) {
        final List<SolutionModelInterface> resultSolutionsList = new ArrayList(solutionsList.size());
        for (SolutionEntity solution: solutionsList) {
            resultSolutionsList.add(this.map(solution));
        }
        return resultSolutionsList;
    }
}
