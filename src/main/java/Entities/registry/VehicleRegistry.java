package Entities.registry;

import Entities.Vehicle;

public class VehicleRegistry {
    private Vehicle raiz;


    public VehicleRegistry() {
    }

    public void addVehicleToRegistry(Vehicle vehicle) {
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
    public Vehicle getRaiz(){
        return this.raiz;
    }

}
