package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Visit {

    private ArrayList<Event> eventRoute;
    private Person visitor;
    private Vehicle visitorCar;
    private LocalDateTime startingTime;
    private LocalDateTime endingTime;

    // Construtor principal
    public Visit(Person visitor, Vehicle visitorCar, LocalDateTime startingTime) {
        this.eventRoute = new ArrayList<Event>();
        this.visitor = visitor;
        this.visitorCar = visitorCar;
        this.startingTime = startingTime;
        this.endingTime = null; // Inicia sem hora de término
    }

    // Adiciona um evento à rota
    public void addToRoute(Event event) {
        this.eventRoute.add(event);
    }

    // Getters e Setters
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

    public void setEventRoute(ArrayList<Event> eventRoute) {
        this.eventRoute = eventRoute;
    }

    // Método para verificar se há uma visita em aberto
    public static Visit newVisitOrGetOngoingVisit(ArrayList<Visit> visitHistory, Event chegada, Person visitor, Vehicle visitorCar, LocalDateTime startingTime) {
        for (Visit visit : visitHistory) {
            if (visit.getVisitor() == visitor && visit.getEndingTime() == null) {
                return visit;
            }
        }

        Visit newVisit = new Visit(visitor, visitorCar, startingTime);
        newVisit.addToRoute(chegada);
        visitHistory.add(newVisit);
        return newVisit;
    }
}
