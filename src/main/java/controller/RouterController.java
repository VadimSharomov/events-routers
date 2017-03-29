package controller;

import model.Event;
import model.Router;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.RouterService;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/")
@ComponentScan({"dao,model,service"})
public class RouterController {
    private final static Logger logger = getLogger(RouterController.class);

    @Autowired
    private RouterService routerService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("events", routerService.listEvents());
        model.addAttribute("routers", routerService.listRouters());
        model.addAttribute("selectedEvent", "of all events");
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
        if ("".equals(pattern)) {
            model.addAttribute("selectedEvent", "of all events");
        } else {
            model.addAttribute("selectedEvent", "searched by '" + pattern + "'");
        }
        return "index";
    }

    @RequestMapping(value = "/router/delete", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete, Model model) {
        if (toDelete != null)
            routerService.deleteRouter(toDelete);

        model.addAttribute("events", routerService.listEvents());
        model.addAttribute("routers", routerService.listRouters());
        model.addAttribute("selectedEvent", "of all events");
        return "redirect:/";
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
        Router router = routerService.findRouterById(id);
        router.setRouterName(routerName);
        router.setApMac(apMac);
        router.setEvent(routerService.findEvent(eventId));
        routerService.updateRouter(router);


        model.addAttribute("events", routerService.listEvents());
        model.addAttribute("routers", routerService.listRouters());
        model.addAttribute("selectedEvent", "of all events");
        return "redirect:/";
    }

    @RequestMapping(value = "/router/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String routerAdd(@RequestParam(value = "event") long eventId,
                            @RequestParam String routerName,
                            @RequestParam String apMac,
                            Model model) {
        Event event = routerService.findEvent(eventId);
//        routerName = new String(routerName.getBytes("iso-8859-1"),"UTF-8");
//        apMac = new String(apMac.getBytes("iso-8859-1"), "UTF-8");
        Router router = new Router(apMac, routerName, event);
        routerService.addRouter(router);

        model.addAttribute("events", routerService.listEvents());
        model.addAttribute("routers", routerService.listRouters());
        model.addAttribute("selectedEvent", "of all events");
        return "redirect:/";
    }
}
