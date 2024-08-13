package Entities;

public class Vehicle {

    private boolean registered;
    private int plate;
    private Person owner;
    private Vehicle nextInLine;

    public Vehicle(int plate, Person owner) {
        this.plate = plate;
        this.owner = owner;
    }

    public int getPlate() {
        return this.plate;
    }

    public void setPlate(int plate) {
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

    public Vehicle getVehicleByPlate(int plate) {
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
