package dao;

import model.Event;
import model.Router;
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
@Repository("routerDao")
public class RouterDAOImpl implements RouterDAO {
    private final static Logger logger = getLogger(RouterDAOImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Router router) {
        entityManager.merge(router);
        logger.info("Added router: '" + router + "'");
    }

    @Override
    public void delete(Router router) {
        entityManager.remove(router);
        logger.info("Deleted router: '" + router + "'");
    }

    @Override
    public void delete(long[] ids) {
        Router router;
        for (long id : ids) {
            router = entityManager.getReference(Router.class, id);
            entityManager.remove(router);
            logger.info("Deleted router: '" + router + "'");
        }
    }

    @Override
    public List<Router> list(Event event) {
        Query query;

        if (event != null) {
            query = entityManager.createQuery("SELECT r FROM Router r WHERE r.event = :event", Router.class);
            query.setParameter("event", event);
        } else {
            query = entityManager.createQuery("SELECT c FROM Event c", Router.class);
        }

        return (List<Router>) query.getResultList();
    }

    @Override
    public List<Router> list(String pattern) {
        Query query = entityManager.createQuery("SELECT r FROM Router r WHERE r.routerName LIKE :pattern", Router.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return (List<Router>) query.getResultList();
    }

    @Override
    public List<Router> list() {
        Query query = entityManager.createQuery("SELECT r FROM Router r", Router.class);
        return (List<Router>) query.getResultList();
    }

    @Override
    public Router findOne(long id) {
        return entityManager.getReference(Router.class, id);
    }
}
