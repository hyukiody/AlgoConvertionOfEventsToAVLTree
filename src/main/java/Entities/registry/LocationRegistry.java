package Entities.registry;

import Entities.Location;

import java.util.ArrayList;

public class LocationRegistry {

    private ArrayList<Location> locationsList;

    public LocationRegistry(){
        this.locationsList = new ArrayList<Location>();
    }
    public ArrayList<Location> getLocationsList(){
        return this.locationsList;
    }

    public Location getLocationByCoordinates(double searchLatitude, double searchLongitude){
        for(Location thisLocation : this.locationsList){
            if(thisLocation.getLatitude()==searchLatitude && thisLocation.getLongitude()==searchLongitude){
                return thisLocation;
            }
        }return null;

    }
}
