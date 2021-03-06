package service;

import dao.EventDAO;
import dao.RouterDAO;
import model.Event;
import model.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

/**
 *
 * Created by Vadim on 26.03.2017.
 */
@Service("routerService")
public class RouterService {
    @Autowired
    private RouterDAO routerDAO;
    @Autowired
    private EventDAO eventDAO;

    @Transactional
    public void addRouter(Router router) {
        routerDAO.add(router);
    }

    @Transactional
    public void deleteRouter(long[] ids) {
        routerDAO.delete(ids);
    }

    @Transactional
    public List<Router> listRouters() {
        return sortRouters(routerDAO.list());
    }

    @Transactional
    public List<Router> listRouters(Event event) {
        return sortRouters(routerDAO.list(event));
    }

    @Transactional
    public List<Router> findRouters(String pattern) {
        List<Router> routers = routerDAO.list(pattern);
        return sortRouters(routers);
    }

    @Transactional
    public Router findRouterById(long id) {
        return routerDAO.findOne(id);
    }

    @Transactional
    public void updateRouter(Router router) {
        routerDAO.add(router);
    }

    @Transactional
    public void updateEvent(Event event) {
        eventDAO.add(event);
    }

    @Transactional
    private List<Router> sortRouters(List<Router> routers) {
        routers.sort(new Comparator<Router>() {
            @Override
            public int compare(Router o1, Router o2) {
                int resCompare = String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
                return (resCompare != 0) ? resCompare : o1.getName().compareTo(o2.getName());
            }
        });
        return routers;
    }
}
