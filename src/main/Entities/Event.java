package Entities;

import Entities.registry.LocationRegistry;

import java.time.LocalDateTime;

public class Event {

    private String carPlate;
    private LocalDateTime horaEvento;
    private Location eventPlace;
    private Camera camera;

    // Construtor padrão
    public Event() {}

    // Construtor completo
    public Event(String carPlate, LocalDateTime horaEvento, Location eventPlace, Camera camera) {
        this.carPlate = carPlate;
        this.horaEvento = horaEvento; // Corrigido para usar o valor passado
        this.eventPlace = eventPlace;
        this.camera = camera;
    }

    // Construtor sem câmera
    public Event(String carPlate, LocalDateTime horaEvento, Location eventPlace) {
        this.carPlate = carPlate;
        this.horaEvento = horaEvento;
        this.eventPlace = eventPlace;
        this.camera = null; // Define câmera como nula por padrão
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

    // Método para criar um evento a partir de uma string
    public Event parseEventFromString(LocationRegistry locationsList, String eventString) {
        String[] attributes = eventString.strip().split(",");
        LocalDateTime horaEvento = LocalDateTime.parse(attributes[0]);
        double latitude = Double.parseDouble(attributes[1]);
        double longitude = Double.parseDouble(attributes[2]);
        int cameraId = Integer.parseInt(attributes[3]);
        String carPlate = attributes[4];

        // Retorna a localidade cadastrada
        Location location = locationsList.getLocationByCoordinates(latitude, longitude);
        Camera camera = location.getCameraById(cameraId);
        return new Event(carPlate, horaEvento, location, camera);
    }

    @Override
    public String toString() {
        String body = "Detection of: " + this.carPlate + "; at " + this.eventPlace + "; \n Camera ID: " + (this.camera != null ? this.camera.getId() : "N/A") + " Time: " + this.horaEvento;
        return body;
    }
}
