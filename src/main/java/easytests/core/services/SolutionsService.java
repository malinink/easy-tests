package easytests.core.services;

import easytests.core.entities.SolutionEntity;
import easytests.core.mappers.SolutionsMapper;
import easytests.core.models.PointModelInterface;
import easytests.core.models.SolutionModel;
import easytests.core.models.SolutionModelInterface;
import easytests.core.options.SolutionsOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author SingularityA
 * @author Loriens
 */
@Service
public class SolutionsService implements SolutionsServiceInterface {

    @Autowired
    private SolutionsMapper solutionsMapper;

    @Autowired
    private PointsService pointsService;

    @Override
    public List<SolutionModelInterface> findAll() {
        return this.map(this.solutionsMapper.findAll());
    }

    @Override
    public List<SolutionModelInterface> findAll(SolutionsOptionsInterface solutionsOptions) {
        return this.withServices(solutionsOptions).withRelations(this.findAll());
    }

    @Override
    public SolutionModelInterface find(Integer id) {
        final SolutionEntity solutionEntity = this.solutionsMapper.find(id);
        if (solutionEntity == null) {
            return null;
        }
        return this.map(solutionEntity);
    }

    @Override
    public SolutionModelInterface find(Integer id, SolutionsOptionsInterface solutionsOptions) {
        return this.withServices(solutionsOptions).withRelations(this.find(id));
    }

    public List<SolutionModelInterface> findByPoint(PointModelInterface pointModel) {
        return this.map(this.solutionsMapper.findByPointId(pointModel.getId()));
    }

    @Override
    public List<SolutionModelInterface> findByPoint(PointModelInterface pointModel,
                                             SolutionsOptionsInterface solutionsOptions) {
        return this.withServices(solutionsOptions).withRelations(this.findByPoint(pointModel));
    }

    @Override
    public void save(SolutionModelInterface solutionModel) {
        final SolutionEntity solutionEntity = this.map(solutionModel);
        if (solutionEntity.getId() != null) {
            this.solutionsMapper.update(solutionEntity);
            return;
        }
        this.solutionsMapper.insert(solutionEntity);
        solutionModel.setId(solutionEntity.getId());
    }

    @Override
    public void save(SolutionModelInterface solutionModel,
                                       SolutionsOptionsInterface solutionsOptions) {
        this.withServices(solutionsOptions).saveWithRelations(solutionModel);
    }

    @Override
    public void save(List<SolutionModelInterface> solutionModels) {
        for (SolutionModelInterface solutionModel: solutionModels) {
            this.save(solutionModel);
        }
    }

    @Override
    public void save(List<SolutionModelInterface> solutionModels, SolutionsOptionsInterface solutionsOptions) {
        for (SolutionModelInterface solutionModel: solutionModels) {
            this.save(solutionModel, solutionsOptions);
        }
    }

    @Override
    public void delete(SolutionModelInterface solutionModel) {
        final SolutionEntity solutionEntity = this.map(solutionModel);
        if (solutionEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.solutionsMapper.delete(solutionEntity);
    }

    @Override
    public void delete(List<SolutionModelInterface> solutionsModel) {
        for (SolutionModelInterface solutionModel: solutionsModel) {
            this.delete(solutionModel);
        }
    }

    @Override
    public void delete(SolutionModelInterface solutionModel, SolutionsOptionsInterface solutionsOptions) {
        this.withServices(solutionsOptions).deleteWithRelations(solutionModel);
    }

    @Override
    public void delete(List<SolutionModelInterface> solutionsModel, SolutionsOptionsInterface solutionsOptions) {
        for (SolutionModelInterface solutionModel: solutionsModel) {
            this.delete(solutionModel, solutionsOptions);
        }
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

    private List<SolutionModelInterface> map(List<SolutionEntity> solutionEntities) {
        final List<SolutionModelInterface> solutionModels = new ArrayList<>(solutionEntities.size());
        for (SolutionEntity solutionEntity: solutionEntities) {
            solutionModels.add(this.map(solutionEntity));
        }
        return solutionModels;
    }

    private SolutionsOptionsInterface withServices(SolutionsOptionsInterface solutionOptions) {
        solutionOptions.setSolutionsService(this);
        solutionOptions.setPointsService(this.pointsService);
        return solutionOptions;
    }
}
