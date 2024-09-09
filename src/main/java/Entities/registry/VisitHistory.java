package Entities.registry;

import Entities.Visit;

import java.util.ArrayList;

public class VisitHistory {

    private ArrayList<Visit> historyList;

    public VisitHistory() {
        this.historyList = new ArrayList<Visit>();
    }

    public void addToHistory(Visit newVisit) {
        this.historyList.add(newVisit);
    }

    public ArrayList<Visit> getHistoryList() {
        return this.historyList;
    }

    //metodo de consulta do historico de visitas a partir da placa do carro detectada no evento
    public Visit getVisitInVisitListByPlate(String eventPlate) {
        for (Visit visit : getHistoryList()) {
            //verificação dupla que impede o retorno de uma visita ja encerrada
            if (visit.getVehicle().getPlate().equals(eventPlate) && visit.getEndingTime() == null) {
                return visit;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        int n = 1;
        StringBuilder body = new StringBuilder("Exibindo registro de visitas\n");
        for (Visit visit : getHistoryList()) {
            body.append("Visita nº").append(n).append(": ").append(visit).append("\n");
            n++;
        }
        return body.toString();
    }
}
