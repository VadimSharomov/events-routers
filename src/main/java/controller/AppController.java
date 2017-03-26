package controller;

import model.Event;
import model.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.RouterService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
@ComponentScan({"dao,model,service"})
public class AppController {
    static final int DEFAULT_EVENT_ID = -1;

    @Autowired
    private RouterService routerService;

    @RequestMapping("/")
    public String index(Model model) {
        List listEvents = routerService.listEvents();
        model.addAttribute("events", listEvents);
        model.addAttribute("routers", routerService.searchRouters(""));
        return "index";
    }

    @RequestMapping("/router_add_page")
    public String routerAddPage(Model model) {
        model.addAttribute("events", routerService.listEvents());
        return "router_add_page";
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
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("events", routerService.listEvents());
        model.addAttribute("routers", routerService.searchRouters(pattern));
        return "index";
    }

    @RequestMapping(value = "/router/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete, Model model) {
        if (toDelete != null)
            routerService.deleteRouter(toDelete);

        model.addAttribute("events", routerService.listEvents());
        model.addAttribute("routers", routerService.searchRouters(""));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value="/router/add", method = RequestMethod.POST)
    public String routerAdd(@RequestParam(value = "event") long eventId,
                            @RequestParam String routerName,
                             @RequestParam String apMac,
                             Model model)
    {
        Event event = (eventId != DEFAULT_EVENT_ID) ? routerService.findEvent(eventId) : null;

        Router router = new Router(apMac, routerName, event);
        routerService.addRouter(router);

        model.addAttribute("events", routerService.listEvents());
        model.addAttribute("routers", routerService.searchRouters(""));
        return "redirect:/";
    }

    @RequestMapping(value="/event/add", method = RequestMethod.POST)
    public String eventAdd(@RequestParam String name,
                           @RequestParam String location,
                           @ModelAttribute Date dateFrom,
                           @ModelAttribute Date dateTo,
                           Model model)
    {
        routerService.addEvent(new Event(name, location, dateFrom, dateTo));

        model.addAttribute("events", routerService.listEvents());
        model.addAttribute("routers", routerService.searchRouters(""));
        return "index";
    }
}
