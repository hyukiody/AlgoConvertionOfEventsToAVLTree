package Entities;

import java.util.Arrays;

public class Location {

    private Camera[] cameras;
    private double longitude;
    private double latitude;

    public Location(float x, float y) {
        this.longitude = x;
        this.latitude = y;
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
    // Check if a camera with the same ID already exists
    for (Camera camera1 : cameras) {
        if (camera1.getId() == camera.getId()) {
            System.out.println("Camera de mesmo ID já encontrada!");
            return;
        }
    }

    // Find an empty spot or resize the array if necessary
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

    public String LocationToString() {
        return "Location: Longitude " + this.longitude + " Latitude: " + this.latitude;
    }
}
