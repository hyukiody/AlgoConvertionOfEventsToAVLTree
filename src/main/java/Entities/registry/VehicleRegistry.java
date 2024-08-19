package Entities.registry;

import Entities.Vehicle;

public class VehicleRegistry {
    private Vehicle raiz;


    public VehicleRegistry() {
        this.raiz = new Vehicle();
    }

    public void printVehiclesInList(){
        Vehicle iterate=this.raiz;
        while(iterate !=null){
            System.out.println(iterate);
            iterate=iterate.getNextInLine();
        }
    }
    //recursive method to add a vehicle at the end of the linked list
    public void addVehicleToRegistry(Vehicle vehicle) {
        Vehicle iterate=this.getRaiz();
        while (iterate.getNextInLine() != null) {
            iterate=(iterate.getNextInLine());
        }
        iterate.setNextInLine(vehicle);
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
