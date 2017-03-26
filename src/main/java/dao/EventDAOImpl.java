package dao;

import model.Event;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


/**
 *
 * Created by Vadim on 26.03.2017.
 */
@Repository("eventDao")
public class EventDAOImpl implements EventDAO {
    private final static Logger logger = getLogger(EventDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Event event) {
        entityManager.merge(event);
        logger.info("Added event: '" + event + "'");
    }

    @Override
    public void delete(Event event) {
        entityManager.remove(event);
        logger.info("Deleted event: '" + event + "'");
    }

    @Override
    public Event findOne(long id) {
        return entityManager.getReference(Event.class, id);
    }

    @Override
    public List<Event> list() {
        Query query = entityManager.createQuery("SELECT e FROM Event e", Event.class);
        return (List<Event>) query.getResultList();
    }
}
