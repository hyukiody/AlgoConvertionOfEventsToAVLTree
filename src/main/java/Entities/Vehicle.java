package Entities;

public class Vehicle {

    private String plate;
    private Person owner;
    private Vehicle nextInLine;

    //contrutor padrao pra facilitar a semantica
    public Vehicle(String plate, Person owner) {
        this.plate = plate;
        this.owner = owner;
    }//construtor vazio do veiculo raiz da lista encadeada
    public Vehicle(){}

    public Vehicle vehicleFromEvent(Event event){
        return new Vehicle(event.getCarPlate(),null);

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
