package controller;

import model.Event;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.RouterService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/")
@ComponentScan({"dao,model,service"})
public class EventController {
    private final static Logger logger = getLogger(EventController.class);
    private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private static final int DEFAULT_EVENT_ID = -1;

    @Autowired
    private RouterService routerService;

    @RequestMapping("/events")
    public String events(Model model) {
        model.addAttribute("events", routerService.listEvents());
        return "events";
    }

    @RequestMapping("/event_add_page")
    public String eventAddPage() {
        return "event_add_page";
    }

    @RequestMapping("/event/{id}")
    public String listEvent(@PathVariable(value = "id") long eventId, Model model) {
        Event event = (eventId != DEFAULT_EVENT_ID) ? routerService.findEvent(eventId) : null;

        model.addAttribute("events", routerService.listEvents());
        model.addAttribute("currentEvent", event);
        model.addAttribute("routers", routerService.listRouters(event));
        model.addAttribute("selectedEvent", " of " + event.getName());
        return "index";
    }

    @RequestMapping(value = "/event/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {

        model.addAttribute("events", routerService.findEvents(pattern));
        if (!"".equals(pattern)) {
            model.addAttribute("messageHeadEvent", " searched by '" + pattern + "'");
        }
        return "events";
    }

    @RequestMapping(value = "/event/delete", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete, Model model) {
        if (toDelete != null) {
            routerService.deleteEvent(toDelete);
        }
        model.addAttribute("events", routerService.listEvents());
        return "redirect:/events";
    }

    @RequestMapping(value = "/event/edit_page", method = RequestMethod.POST)
    public String editEventPage(@RequestParam(value = "event_id", required = false) Long eventId,
                                Model model) {
        if (eventId != null) {
            Event event = routerService.findEvent(eventId);
            model.addAttribute("event", event);
            model.addAttribute("dateFrom", formatter.format(event.getDateFrom()));
            model.addAttribute("dateTo", formatter.format(event.getDateTo()));
            return "event_edit_page";
        }
        model.addAttribute("events", routerService.listEvents());
        return "redirect:/events";
    }

    @RequestMapping(value = "/event/edit", method = RequestMethod.POST)
    public String editEvent(@RequestParam(value = "id") long id,
                            @RequestParam String name,
                            @RequestParam String location,
                            @RequestParam String dateFrom,
                            @RequestParam String dateTo,
                            Model model) {
        try {
            Event event = routerService.findEvent(id);
            event.setName(name);
            event.setLocation(location);
            event.setDateFrom(formatter.parse(dateFrom));
            event.setDateTo(formatter.parse(dateTo));
            routerService.updateEvent(event);
        } catch (ParseException e) {
            logger.error("Error data format when edit event: " + name + ", " + location + ", " + dateFrom + ", " + dateTo, e.getMessage());
        }

        model.addAttribute("events", routerService.listEvents());
        return "redirect:/events";
    }

    @RequestMapping(value = "/event/add", method = RequestMethod.POST)
    public String eventAdd(@RequestParam String name,
                           @RequestParam String location,
                           @RequestParam String dateFrom,
                           @RequestParam String dateTo,
                           Model model) {

        try {
            routerService.addEvent(new Event(name, location, formatter.parse(dateFrom), formatter.parse(dateTo)));
        } catch (ParseException e) {
            logger.error("Error data format when added event: " + name + ", " + location + ", " + dateFrom + ", " + dateTo, e.getMessage());
        }

        model.addAttribute("events", routerService.listEvents());
        return "redirect:/events";
    }
}
