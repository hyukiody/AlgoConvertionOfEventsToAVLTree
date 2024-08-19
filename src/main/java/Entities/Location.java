package Entities;

import java.util.Arrays;

public class Location {

    private Camera[] cameras;
    private double longitude;
    private double latitude;

    public Location(float x, float y) {
        this.longitude = x;
        this.latitude = y;
        this.cameras = new Camera[0];
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
        int counter = 0;
        for (Camera camera : this.cameras) {
            counter++;
        }
        return counter;
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
        if (this.cameras == null) {
            this.cameras = new Camera[1];
            this.cameras[0] = camera;
            return;
        }

        // verifica se a camera ja existe na localidade
        for (Camera camera1 : this.cameras) {
            if (camera1 != null && camera1.getId() == camera.getId()) {
                throw new IllegalArgumentException("Camera de mesmo ID já encontrada!");
            }
        }

        // encontrar um espaço vazio no array ou o refaz com mais um espaço
        for (int i = 0; i < this.cameras.length; i++) {
            if (this.cameras[i] == null) {
                this.cameras[i] = camera;
                return;
            }
        }

        // Resize the array and add the new camera
        this.cameras = Arrays.copyOf(this.cameras, this.cameras.length + 1);
        this.cameras[this.cameras.length - 1] = camera;
    }

    public Camera[] getCameras() {
        return Arrays.copyOf(cameras, getCameraCount());
    }
    @Override
    public String toString() {
        return "Location: Longitude " + this.longitude + " Latitude: " + this.latitude;
    }
}
