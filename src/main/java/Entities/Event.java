package Entities;

import java.time.LocalDateTime;

public class Event {

    private String carPlate;
    private LocalDateTime horaEvento;
    private Place eventPlace;
    private Event nextEv;
    private Event lastEv;

    public Event(String carPlate, LocalDateTime horaEvento, Place eventPlace) {
        this.carPlate = carPlate;
        this.horaEvento = horaEvento;
        this.eventPlace = eventPlace;

    }

    public String getCarPlate() {
        return this.carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

}
