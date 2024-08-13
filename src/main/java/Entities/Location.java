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

    public double getX() {
        return this.longitude;
    }

    public void setX(double x) {
        this.longitude = x;
    }

    public double getY() {
        return this.latitude;
    }

    public void setY(double y) {
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
        for (Camera camera1 : cameras) {
            if (camera1.getId() == camera.getId()) {
                System.out.println("Camera de mesmo ID já encontrada!");
                break;
            }
        }
        for (int i = 0; i <= this.cameras.length; i++) {
            if (this.cameras[i] == null) {
                this.cameras[i] = camera;
            } else if (i == this.cameras.length) {
                this.cameras = Arrays.copyOf(this.cameras, this.cameras.length + 1);
                this.cameras[i] = camera;
            }
        }

    }

    public Camera[] getCameras() {
        return Arrays.copyOf(cameras, getCameraCount());
    }

    public String LocationToString() {
        return "Location: Longitude " + this.longitude + " Latitude: " + this.latitude;
    }
}
