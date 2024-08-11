package Entities;

public class Vehicle {

    private int plate;
    private Person owner;

    public Vehicle(int plate, Employee owner) {
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
    public void setOwner(Person owner){
        this.owner = owner;}

}
