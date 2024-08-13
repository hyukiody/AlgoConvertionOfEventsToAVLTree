package Entities.registry;

import Entities.Vehicle;

public class VehicleRegistry {
    private Vehicle raiz;
    
    
    public VehicleRegistry(){}
    
    public void addVehicleToRegistry(Vehicle vehicle){}
    
    public Vehicle findVehicleByPlate(int plate){
        Vehicle aux;
        
        if(this.raiz.getPlate()==plate){
            return this.raiz;
        }
        
        
    }
    
}
