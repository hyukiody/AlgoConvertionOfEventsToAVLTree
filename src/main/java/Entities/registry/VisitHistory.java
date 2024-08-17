package Entities.registry;

import Entities.Visit;

import java.util.ArrayList;

public class VisitHistory {
    public ArrayList<Visit> visitHistory;

    public VisitHistory() {
        this.visitHistory = new ArrayList<Visit>();
    }

    public void addToHistory(Visit newVisit) {
        this.visitHistory.add(newVisit);
    }

    public ArrayList<Visit> getVisithistory(){
        return this.visitHistory;
    }

    public Visit getVisitByPlate(String eventPlate) {
        for (Visit visit : this.visitHistory) {
            if (visit.getVehicle().getPlate().equals(eventPlate)) {
                return visit;
            }
        }
        return null;
    }
}

