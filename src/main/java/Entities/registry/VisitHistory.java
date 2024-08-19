package Entities.registry;

import Entities.Visit;

import java.util.ArrayList;

public class VisitHistory {
    public ArrayList<Visit> visitHistory;

    public VisitHistory() {
        this.visitHistory = new ArrayList<>();
    }

    public void addToHistory(Visit newVisit) {
        this.visitHistory.add(newVisit);
    }

    public ArrayList<Visit> getVisitHistory(){
        return this.visitHistory;
    }

    //metodo de consulta do historico de visitas a partir da placa do carro detectada no evento
    public Visit getVisitByPlate(String eventPlate) {
        for (Visit visit : this.visitHistory) {
            //verificação dupla que impede o retorno de uma visita ja encerrada
            if (visit.getVehicle().getPlate().equals(eventPlate)&& visit.getEndingTime()==null) {
                return visit;
            }
        }
        return null;
    }
}

