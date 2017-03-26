package model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * Created by vadim on 3/26/17.
 */
@Entity
@Table(name = "router")
@Proxy(lazy=false)
public class Router implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "router_id")
    private long id;

    @Column(name = "apMac")
    private String apMac;

    @Column(name = "router_name")
    private String routerName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="event_id")
    private Event event;

    public Router() {
    }

    public Router(String apMac, String routerName) {
        this.apMac = apMac;
        this.routerName = routerName;
    }

    public Router(String apMac, String routerName, Event event) {
        this.apMac = apMac;
        this.routerName = routerName;
        this.event = event;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApMac() {
        return apMac;
    }

    public void setApMac(String apMac) {
        this.apMac = apMac;
    }

    public String getName() {
        return routerName;
    }

    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "{" +
                "apMac='" + apMac + '\'' +
                ", name='" + routerName + '\'' +
                ", event=" + event +
                '}';
    }
}
