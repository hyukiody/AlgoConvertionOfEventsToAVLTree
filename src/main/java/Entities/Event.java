package Entities;

import java.time.LocalDateTime;

public class Event {

    private String carPlate;
    private LocalDateTime horaEvento;
    private Location eventPlace;
    private Camera camera;
    
    public Event(String carPlate, LocalDateTime horaEvento, Location eventPlace) {
        this.carPlate = carPlate;
        this.horaEvento = LocalDateTime.now();
        this.eventPlace = eventPlace;

    }

    public String getCarPlate() {
        return this.carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public LocalDateTime getHoraEvento(){
        return this.horaEvento;
    }
    @Override
    public String toString(){
         String body = "Detection of: " + this.carPlate + "; at "
                 + this.eventPlace + "; \n Camera ID: " + this.camera.getId() +
                 " Time: " + this.horaEvento;
         return body;
    }
}
