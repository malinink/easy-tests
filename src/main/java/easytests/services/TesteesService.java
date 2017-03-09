package easytests.services;

import easytests.entities.Testee;
import easytests.entities.TesteeInterface;
import easytests.mappers.TesteesMapper;
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

    public List<TesteeInterface> findAll() {
        return this.map(this.testeesMapper.findAll());
    }

    public TesteeInterface find(Integer id) {
        return this.testeesMapper.find(id);
    }

    public void save(TesteeInterface testee) {
        if (testee.getId() == null) {
            this.testeesMapper.insert(testee);
            return;
        }
        this.testeesMapper.update(testee);
    }

    public void delete(TesteeInterface user) {
        this.testeesMapper.delete(user);
    }

    private List<TesteeInterface> map(List<Testee> testeesList) {
        final List<TesteeInterface> resultTesteeList = new ArrayList(testeesList.size());
        for (Testee testee: testeesList) {
            resultTesteeList.add(testee);
        }
        return resultTesteeList;
    }

}
