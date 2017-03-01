package easytests.services;

import easytests.entities.PointInterface;
import easytests.entities.Solution;
import easytests.entities.SolutionInterface;
import easytests.mappers.SolutionMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SingularityA
 */
@Service
public class SolutionService {

    @Autowired
    private SolutionMapper solutionMapper;

    public List<SolutionInterface> findAll() {
        return this.map(this.solutionMapper.findAll());
    }

    public SolutionInterface find(Integer id) {
        return this.solutionMapper.find(id);
    }

    public List<SolutionInterface> findByPoint(PointInterface point) {
        return this.map(this.solutionMapper.findByPoint(point));
    }

    public void save(SolutionInterface solution) {
        if (solution.getId() != null) {
            this.solutionMapper.update(solution);
        } else {
            this.solutionMapper.insert(solution);
        }
    }

    public void delete(SolutionInterface solution) {
        this.solutionMapper.delete(solution);
    }

    private List<SolutionInterface> map(List<Solution> solutionsList) {
        final List<SolutionInterface> resultSolutionsList = new ArrayList(solutionsList.size());
        for (Solution solution: solutionsList) {
            resultSolutionsList.add(solution);
        }
        return resultSolutionsList;
    }
}
