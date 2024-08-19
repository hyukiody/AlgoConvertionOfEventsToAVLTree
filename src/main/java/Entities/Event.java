package Entities;

import Entities.registry.LocationRegistry;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event {

    private String carPlate;
    private LocalDateTime horaEvento;
    private Location eventPlace;
    private Camera camera;

    // Placeholder constructor
public Event() {
    this.carPlate = "UNKNOWN";
    this.horaEvento = LocalDateTime.parse("2012-01-01T00:00:00");
    this.eventPlace = new Location(0, 0);
    this.camera = new Camera(0, "none", "none");
}

    public Event(String carPlate, LocalDateTime horaEvento, Location eventPlace, Camera camera) {
        this.carPlate = carPlate;
        this.horaEvento = horaEvento;
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
    public Event parseEventFromString(LocationRegistry locationsList, String eventString) {
        try {
            if (eventString == null || eventString.isEmpty()) {
                throw new IllegalArgumentException("Event string cannot be null or empty");
            }
            {
                String[] attributes = eventString.strip().split(",");
                if (attributes.length==5) {
                    LocalDateTime horaEvento = LocalDateTime.parse(attributes[0]);
                    double latitude = Double.parseDouble(attributes[1]);
                    double longitude = Double.parseDouble(attributes[2]);
                    int cameraId = Integer.parseInt(attributes[3]);
                    String carPlate = attributes[4];

                    // retorna a localidade cadastrada
                    Location location = locationsList.getLocationByCoordinates(latitude, longitude);
                    return new Event(carPlate, horaEvento, location, location.getCameraById(cameraId));
                }else{
                    throw new IllegalArgumentException("String is not well formatted");
                }
            }
        } catch (IllegalArgumentException e) {
            System.err.println("IllegalArgumentException caught: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        String body = "Detection of: Plate:   " + this.carPlate + "; at " + this.eventPlace + "; \n Camera ID: " + this.camera.getId() + " Time: " + this.horaEvento + "\n";
        return body;
    }

}
