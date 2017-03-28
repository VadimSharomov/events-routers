package dao;

import model.Event;
import model.Router;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Vadim on 26.03.2017.
 */
@Service("routerDAO")
public interface RouterDAO {
    void add(Router router);
    void delete(Router router);
    void delete(long[] ids);
    List<Router> list(Event event);
    List<Router> list(String pattern);
    List<Router> list();
    Router findOne(long id);
}
