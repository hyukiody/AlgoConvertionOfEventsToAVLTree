package Entities;

public class Vehicle {

    private boolean registered;
    private String plate;
    private Person owner;
    private Vehicle nextInLine;

    public Vehicle(String plate, Person owner) {
        this.plate = plate;
        this.owner = owner;
    }

    public String getPlate() {
        return this.plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Person getOwner() {
        return this.owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Vehicle getNextInLine() {
        return this.nextInLine;

    }

    public void setNextInLine(Vehicle vehicle) {
        this.nextInLine = vehicle;
    }

    public Vehicle getVehicleByPlate(String plate) {
        Vehicle aux = this;

        if (aux.getPlate() == plate) {
            return aux;
        } else {
            while (aux.getNextInLine() != null) {
                aux = this.getNextInLine();
                aux.getVehicleByPlate(plate);
            }
        }return null;

    }
}
