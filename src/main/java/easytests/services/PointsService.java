package easytests.services;

import easytests.entities.Point;
import easytests.entities.PointInterface;
import easytests.mappers.PointsMapper;
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

    public List<PointInterface> findAll() {
        return this.map(this.pointsMapper.findAll());
    }

    public PointInterface find(Integer id) {
        return this.pointsMapper.find(id);
    }

    public void save(PointInterface point) {
        if (point.getId() == null) {
            this.pointsMapper.insert(point);
            return;
        }
        this.pointsMapper.update(point);
    }

    public void delete(PointInterface point) {
        this.pointsMapper.delete(point);
    }

    private List<PointInterface> map(List<Point> pointsList) {
        final List<PointInterface> resultPointList = new ArrayList<>(pointsList.size());
        for (Point point: pointsList) {
            resultPointList.add(point);
        }
        return resultPointList;
    }
}
