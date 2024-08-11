package Entities.registry;

import Entities.Guest;

import java.util.ArrayList;

public class GuestRegistry {

    private ArrayList<Guest> guestList;

    public GuestRegistry(ArrayList<Guest> guestList) {
        this.guestList = guestList;
    }

    public ArrayList<Guest> getGuestList() {
        return this.guestList;
    }

    public void addGuestToList(Guest guest) {
        this.guestList.add(guest);
    }

    public void removeGuestFromList(Guest guest) {
        this.guestList.remove(guest);
    }

    public Guest getGuestByIndex(int i) {
        return this.guestList.get(i);
    }

    public Guest getGuestByName(String name) {
        for (int i = 0; i < this.guestList.size(); i++) {
            if (this.guestList.get(i).getName().equals(name)) {
                return guestList.get(i);
            } else {
            }
        }
        return null;

    }
}
