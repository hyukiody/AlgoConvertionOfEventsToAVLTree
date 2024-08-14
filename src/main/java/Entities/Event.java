package Entities;

import java.time.LocalDateTime;

public class Event {

    private String carPlate;
    private LocalDateTime horaEvento;
    private Location eventPlace;
    private Camera camera;


    public Event(String carPlate, LocalDateTime horaEvento, Location eventPlace, Camera camera) {
        this.carPlate = carPlate;
        this.horaEvento = LocalDateTime.now();
        this.eventPlace = eventPlace;
        this.camera = camera;
    }

    public String getCarPlate() {
        return this.carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public LocalDateTime getHoraEvento() {
        return this.horaEvento;
    }

    public void setHoraEvento(LocalDateTime horaEvento) {
        this.horaEvento = horaEvento;

    }

    public Location getEventPlace() {
        return this.eventPlace;
    }

    public void setEventPlace(Location eventPlace) {
        this.eventPlace = eventPlace;
    }

    public Camera getCamera() {
        return this.camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
    //to parse the event attributes from the prompt
    //formato HH:mm dd/MM/yyyy , latitude, longitude,
    //cameraID , carPlate
    public Event parseEventFromString(String eventString){
        String attributes[] = eventString.strip().split(",");
        LocalDateTime horaEvento = LocalDateTime.parse(attributes[0]);
        double
        double
        Camera camera =

    }

    @Override
    public String toString() {
        String body = "Detection of: " + this.carPlate + "; at " + this.eventPlace + "; \n Camera ID: " + this.camera.getId() + " Time: " + this.horaEvento;
        return body;
    }

}
