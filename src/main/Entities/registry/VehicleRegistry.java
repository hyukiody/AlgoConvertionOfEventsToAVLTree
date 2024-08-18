package Entities.registry;

import Entities.Vehicle;

public class VehicleRegistry {
    private Vehicle raiz;


    public VehicleRegistry() {
        this.raiz = new Vehicle();
    }

    //recursive method to add a vehicle at the end of the linked list
    public void addVehicleToRegistry(Vehicle vehicle) {
        while (this.raiz.getNextInLine() != null) {
            addVehicleToRegistry(this.raiz.getNextInLine());
        }
        this.raiz.setNextInLine(vehicle);
    }

    public Vehicle findVehicleByPlate(String plate) {
        Vehicle aux = this.getRaiz();

        while (aux != null) {
            if (aux.getPlate().equals(plate)) {
                return aux;
            }
            aux = aux.getNextInLine();
        }

        return null; // Return null if not found
    }

    public Vehicle getRaiz() {
        return this.raiz;
    }

}
