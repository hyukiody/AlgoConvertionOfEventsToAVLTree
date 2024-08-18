package Entities;

import java.util.ArrayList; // Importa ArrayList

public class Location {

    private ArrayList<Camera> cameras; // Usa ArrayList para armazenar câmeras
    private double longitude;
    private double latitude;

    public Location(double x, double y) {
        this.longitude = x;
        this.latitude = y;
        this.cameras = new ArrayList<>(); // Inicializa a lista de câmeras
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double x) {
        this.longitude = x;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double y) {
        this.latitude = y;
    }

    public int getCameraCount() {
        return this.cameras.size(); // Usa o tamanho do ArrayList
    }

    public Camera getCameraById(int id) {
        for (Camera camera : this.cameras) {
            if (camera.getId() == id) {
                return camera;
            }
        }
        return null;
    }

    public void addCamera(Camera camera) {
        if (camera == null) {
            System.out.println("Camera é nula!");
            return;
        }

        // Verifica se a câmera já existe
        for (Camera camera1 : cameras) {
            if (camera1.getId() == camera.getId()) {
                System.out.println("Camera de mesmo ID já encontrada!");
                return;
            }
        }

        // Adiciona a nova câmera
        this.cameras.add(camera);
    }

    public ArrayList<Camera> getCameras() {
        return new ArrayList<>(cameras); // Retorna uma cópia da lista de câmeras
    }

    public String LocationToString() {
        return "Location: Longitude " + this.longitude + " Latitude: " + this.latitude;
    }
}
