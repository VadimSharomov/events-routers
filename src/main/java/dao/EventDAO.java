package dao;

import model.Event;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Vadim on 26.03.2017.
 */
@Service("eventDAO")
public interface EventDAO {
    void add(Event event);
    void delete(Event event);
    Event findOne(long id);
    List<Event> list();
}
