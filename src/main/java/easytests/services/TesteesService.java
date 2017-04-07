package easytests.services;

import easytests.entities.TesteeEntity;
import easytests.mappers.TesteesMapper;
import easytests.models.TesteeModel;
import easytests.models.TesteeModelInterface;
import easytests.options.TesteesOptionsInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DoZor-80
 */
@Service
public class TesteesService implements TesteesServiceInterface {
    @Autowired
    private TesteesMapper testeesMapper;

    @Override
    public List<TesteeModelInterface> findAll() {
        return this.map(this.testeesMapper.findAll());
    }

    @Override
    public List<TesteeModelInterface> findAll(TesteesOptionsInterface testeesOptions) {
        return this.withServices(testeesOptions).withRelations(this.findAll());
    }

    @Override
    public TesteeModelInterface find(Integer id) {
        return this.map(this.testeesMapper.find(id));
    }

    @Override
    public TesteeModelInterface find(Integer id, TesteesOptionsInterface testeesOptions) {
        return this.withServices(testeesOptions).withRelations(this.find(id));
    }

    @Override
    public void save(TesteeModelInterface testeeModel) {
        final TesteeEntity testeeEntity = this.map(testeeModel);
        if (testeeEntity.getId() == null) {
            this.testeesMapper.insert(testeeEntity);
            testeeModel.setId(testeeEntity.getId());
            return;
        }
        this.testeesMapper.update(testeeEntity);
    }

    @Override
    public void save(TesteeModelInterface testeeModel, TesteesOptionsInterface testeesOptions) {
        this.withServices(testeesOptions).saveWithRelations(testeeModel);
    }

    @Override
    public void delete(TesteeModelInterface testeeModel) {
        final TesteeEntity testeeEntity = this.map(testeeModel);
        if (testeeEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.testeesMapper.delete(testeeEntity);
    }

    @Override
    public void delete(TesteeModelInterface testeeModel, TesteesOptionsInterface testeesOptions) {
        this.withServices(testeesOptions).deleteWithRelations(testeeModel);
    }

    private TesteesOptionsInterface withServices(TesteesOptionsInterface testeesOptions) {
        testeesOptions.setTesteesService(this);
        return testeesOptions;
    }

    private TesteeModelInterface map(TesteeEntity testeeEntity) {
        if (testeeEntity == null) {
            return null;
        }
        final TesteeModelInterface testeeModel = new TesteeModel();
        testeeModel.map(testeeEntity);
        return testeeModel;
    }

    private TesteeEntity map(TesteeModelInterface testeeModel) {
        final TesteeEntity testeeEntity = new TesteeEntity();
        testeeEntity.map(testeeModel);
        return testeeEntity;
    }

    private List<TesteeModelInterface> map(List<TesteeEntity> testeesList) {
        final List<TesteeModelInterface> resultTesteeList = new ArrayList(testeesList.size());
        for (TesteeEntity testee: testeesList) {
            resultTesteeList.add(this.map(testee));
        }
        return resultTesteeList;
    }

}
