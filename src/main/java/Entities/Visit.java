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

    //construtor com verificação de visita em andamento
    public Visit newVisitOrGetOngoingVisit(ArrayList<Visit> visitHistory, Event chegada, Person visitor, Vehicle visitorCar, LocalDateTime startingTime) {
        for (Visit visit : visitHistory) {
            if (visit.getVisitor() == visitor && visit.getEndingTime() == null) {
                return visit;


            }
        }

        this.eventRoute = new ArrayList<Event>;
        this.eventRoute.add(chegada);
        this.visitor = visitor;
        this.visitorCar = visitorCar;
        this.startingTime = startingTime;
    }


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

}
