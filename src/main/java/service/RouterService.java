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
    public void addEvent(Event event) {
        eventDAO.add(event);
    }

    @Transactional
    public void deleteRouter(long[] ids) {
        routerDAO.delete(ids);
    }

    @Transactional
    public void deleteEvent(Event event) {
        eventDAO.delete(event);
    }

    @Transactional
    public void deleteEvent(long[] ids) {
        eventDAO.delete(ids);
    }

    @Transactional
    public List<Event> listEvents() {
        List<Event> events = eventDAO.list();
        return sortEvents(events);
    }

    @Transactional
    public List<Router> listRouters(Event event) {
        return sortRouters(routerDAO.list(event));
    }

    @Transactional
    public Event findEvent(long id) {
        return eventDAO.findOne(id);
    }

    public List<Router> findRouters(String pattern) {
        List<Router> routers = routerDAO.list(pattern);
        return sortRouters(routers);
    }

    public List<Event> findEvents(String pattern) {
        List<Event> events = eventDAO.list(pattern);
        return sortEvents(events);
    }

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

    private List<Event> sortEvents(List<Event> events) {
        events.sort(new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                int resCompare = String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
                return (resCompare != 0) ? resCompare : o1.getName().compareTo(o2.getName());
            }
        });
        return events;
    }
}
