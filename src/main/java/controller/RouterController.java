package controller;

import model.Event;
import model.Router;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.RouterService;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/")
@ComponentScan({"dao,model,service"})
public class RouterController {
    private final static Logger logger = getLogger(RouterController.class);
    private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat formatterDMY = new SimpleDateFormat("dd-MM-yyyy");
    private static final int DEFAULT_EVENT_ID = -1;

    @Autowired
    private RouterService routerService;

    @RequestMapping("/")
    public String index(Model model) {
        List listEvents = routerService.listEvents();
        model.addAttribute("events", listEvents);
        model.addAttribute("routers", routerService.findRouters(""));
        return "index";
    }


    @RequestMapping("/router_add_page")
    public String routerAddPage(Model model) {
        model.addAttribute("events", routerService.listEvents());
        return "router_add_page";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("events", routerService.listEvents());
        model.addAttribute("routers", routerService.findRouters(pattern));
        return "index";
    }

    @RequestMapping(value = "/router/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete, Model model) {
        if (toDelete != null)
            routerService.deleteRouter(toDelete);

        model.addAttribute("events", routerService.listEvents());
        model.addAttribute("routers", routerService.findRouters(""));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/router/edit", method = RequestMethod.POST)
    public String editRouterPage(@RequestParam(value = "router_id", required = false) Long router_id,
                                 Model model) {
        if (router_id != null) {
            Router router = routerService.findRouterById(router_id);

            model.addAttribute("events", routerService.listEvents());
            model.addAttribute("router", router);
            model.addAttribute("router_event", router.getEvent());
            return "router_edit_page";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/router/editRouter", method = RequestMethod.POST)
    public String editRouter(@RequestParam(value = "event") long eventId,
                             @RequestParam String routerName,
                             @RequestParam String apMac,
                             @RequestParam long id,
                             Model model) {
        Router router = new Router(apMac, routerName, routerService.findEvent(eventId));
        router.setId(id);
        routerService.updateRouter(router);


        model.addAttribute("events", routerService.listEvents());
        model.addAttribute("routers", routerService.findRouters(""));
        return "index";
    }

    @RequestMapping(value = "/router/add", method = RequestMethod.POST)
    public String routerAdd(@RequestParam(value = "event") long eventId,
                            @RequestParam String routerName,
                            @RequestParam String apMac,
                            Model model) {
        Event event = (eventId != DEFAULT_EVENT_ID) ? routerService.findEvent(eventId) : null;

        Router router = new Router(apMac, routerName, event);
        routerService.addRouter(router);

        model.addAttribute("events", routerService.listEvents());
        model.addAttribute("routers", routerService.findRouters(""));
        return "redirect:/";
    }
}
