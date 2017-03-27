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
    List<Event> list();
    List<Event> list(String pattern);
    Event findOne(long id);
    void delete(long[] ids);
}
