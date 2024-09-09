package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Visit {

    private ArrayList<Event> eventRoute;
    private Person visitor;
    private Vehicle visitorCar;
    private long visitDuration;
    private LocalDateTime startingTime;
    private LocalDateTime endingTime;

    public Visit(ArrayList<Visit> historyList, Person visitor, Vehicle visitorCar, LocalDateTime startingTime) {
        this.eventRoute = new ArrayList<Event>();
        this.visitor = visitor;
        this.visitorCar = visitorCar;
        this.startingTime = startingTime;
        historyList.add(this);

    }

    /*
    //construtor com verificação de visita em aberto
    public Visit newVisitOrGetOngoingVisit(ArrayList<Visit> visitHistory, Event chegada, Person visitor, Vehicle visitorCar, LocalDateTime startingTime) {
        for (Visit visit : visitHistory) {
            if (visit.getVisitor() == visitor && visit.getEndingTime() == null) {
                return visit;


            }
        }

        ArrayList<Event> eventRoute = new ArrayList<Event>();
        eventRoute.add(chegada);
        Person newVisitor = visitor;
        Vehicle newVisitorCar = visitorCar;
        LocalDateTime newStartingTime = startingTime;

        return new Visit(visitHistory, eventRoute, newVisitor, newVisitorCar, newStartingTime);
    }*/
    public Person getVisitor() {
        return this.visitor;
    }

    public void setVisitor(Person visitor) {
        this.visitor = visitor;
    }

    public Vehicle getVehicle() {
        return this.visitorCar;
    }

    public void setVehicle(Vehicle visitorCar) {
        this.visitorCar = visitorCar;
    }

    public LocalDateTime getStartingTime() {
        return this.startingTime;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalDateTime getEndingTime() {
        return this.endingTime;
    }

    public void setEndingTime(LocalDateTime endingTime) {
        this.endingTime = endingTime;
    }

    public ArrayList<Event> getEventRoute() {
        return this.eventRoute;
    }

    public void addToRoute(Event event) {
        this.eventRoute.add(event);
    }

    @Override
    public String toString() {
        String body = "Visit info: \n" + "Start time: " + getStartingTime() + "\n Rota atual:";
        for (Event event : getEventRoute()) {
            body += event.toString();
        }
        body += ("Visitor: " + visitor);
        return body;
    }
}
