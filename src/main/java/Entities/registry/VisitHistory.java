package Entities.registry;

import Entities.Visit;

import java.util.ArrayList;

public class VisitHistory {
    private ArrayList<Visit> visitHistory;

    public VisitHistory() {
        this.visitHistory = new ArrayList<Visit>();
    }

    public void addToHistory(Visit newVisit) {
        this.visitHistory.add(newVisit);
    }

    public ArrayList<Visit> getVisitHistory(){
        return this.visitHistory;
    }

    //metodo de consulta do historico de visitas a partir da placa do carro detectada no evento
    public Visit getVisitInVisitListByPlate(String eventPlate) {
        for (Visit visit : this.visitHistory) {
            //verificação dupla que impede o retorno de uma visita ja encerrada
            if (visit.getVehicle().getPlate().equals(eventPlate)&& visit.getEndingTime()==null) {
                return visit;
            }
        }
        return null;
    }
}

