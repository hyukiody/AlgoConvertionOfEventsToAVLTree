package Entities;

import java.time.LocalDateTime;

public class Event {

    private String carPlate;
    private LocalDateTime horaEvento;
    private Location eventPlace;
    private String imagemPath;
    private Camera camera;
    
    public Event(String carPlate, LocalDateTime horaEvento, Location eventPlace) {
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
