package model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by vadim on 3/26/17.
 */
@Entity
@Table(name = "event")
@Proxy(lazy=false)
public class Event implements Serializable {
    private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "date_from")
    private Date dateFrom;

    @Column(name = "date_to")
    private Date dateTo;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, orphanRemoval=true)
    private List<Router> routers = new ArrayList<>();

    public Event() {
    }

    public Event(String name, String location, Date dateFrom, Date dateTo) {
        this.name = name;
        this.location = location;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public String getDateFromFormat() {
        return formatter.format(dateFrom);
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public String getDateToFormat() {
        return formatter.format(dateTo);
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public List<Router> getRouters() {
        return routers;
    }

    public void setRouters(List<Router> routers) {
        this.routers = routers;
    }

    @Override
    public String toString() {
        return "{" + name + '\'' +
                ", loc:='" + location + '\'' +
                ", dateFrom=" + formatter.format(dateFrom) +
                ", dateTo=" + formatter.format(dateTo) +
                '}';
    }
}
