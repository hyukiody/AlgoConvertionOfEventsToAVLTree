package Entities;

import java.util.ArrayList;

public class Guest extends Person {

    public Guest(String name, String cpf, Vehicle owned) {
        super(name, cpf, owned);
    }

    public Guest newGuest(ArrayList<Guest> guestList, Vehicle detected) {
        String name = "Unknown ";
        String cpf = "Unknown";
        for (int i = 0; i < guestList.size(); i++) {
            if (guestList.get(i).getVehicle().getPlate()==(detected.getPlate())) {
                return guestList.get(i);

            }
        }
        name += guestList.size()+1;
                return new Guest(name, cpf, detected);

    }
}
