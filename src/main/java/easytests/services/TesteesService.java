package easytests.services;

import easytests.entities.TesteeEntity;
import easytests.mappers.TesteesMapper;
import easytests.models.TesteeModel;
import easytests.models.TesteeModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DoZor-80
 */
@Service
public class TesteesService {

    @Autowired
    private TesteesMapper testeesMapper;

    public List<TesteeModelInterface> findAll() {
        return this.map(this.testeesMapper.findAll());
    }

    public TesteeModelInterface find(Integer id) {
        final TesteeEntity testeeEntity = this.testeesMapper.find(id);
        if (testeeEntity == null) {
            return null;
        }
        return this.map(testeeEntity);
    }

    public void save(TesteeModelInterface testeeModel) {
        final TesteeEntity testeeEntity = this.map(testeeModel);
        if (testeeEntity.getId() == null) {
            this.testeesMapper.insert(testeeEntity);
            return;
        }
        this.testeesMapper.update(testeeEntity);
    }

    public void delete(TesteeModelInterface testeeModel) {
        final TesteeEntity testeeEntity = this.map(testeeModel);
        if (testeeEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.testeesMapper.delete(testeeEntity);
    }

    private TesteeModelInterface map(TesteeEntity testeeEntity) {
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
