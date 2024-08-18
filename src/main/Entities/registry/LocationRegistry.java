package Entities.registry;

import Entities.Camera;
import Entities.Location;

import java.util.ArrayList;

public class LocationRegistry {

    private ArrayList<Location> locationsList;

    // Construtor de teste
    public LocationRegistry() {
        this.locationsList = new ArrayList<>();
        Camera camera1 = new Camera(1, "entrada", "entrada.jpg");
        Camera camera2 = new Camera(2, "entrada", "entrada2.jpg");
        Camera camera3 = new Camera(3, "entrada", "entrada3.jpg");
        Camera camera4 = new Camera(4, "saida", "saida4.jpg");
        Camera camera5 = new Camera(5, "saida", "saida5.jpg");
        Camera camera6 = new Camera(6, "saida", "saida6.jpg");

        Location localEntrada = new Location(1, 1);
        localEntrada.addCamera(camera1);
        localEntrada.addCamera(camera6);
        Location localMeio = new Location(2, 2);
        localMeio.addCamera(camera2);
        localMeio.addCamera(camera5);
        Location localFim = new Location(3, 3);
        localFim.addCamera(camera3);
        localFim.addCamera(camera4);

        this.locationsList.add(localEntrada);
        this.locationsList.add(localMeio);
        this.locationsList.add(localFim);
    }

    public ArrayList<Location> getLocationsList() {
        return this.locationsList;
    }

    public Location getLocationByCoordinates(double searchLatitude, double searchLongitude) {
        for (Location thisLocation : this.locationsList) {
            if (thisLocation.getLatitude() == searchLatitude && thisLocation.getLongitude() == searchLongitude) {
                return thisLocation;
            }
        }
        return null;
    }

    // Método para adicionar uma Location à lista
    public void addLocation(Location location) {
        this.locationsList.add(location);
    }
}
