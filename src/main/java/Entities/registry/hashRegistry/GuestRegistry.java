/*package Entities.registry.hashRegistry;

import Entities.Guest;

public class GuestRegistry {

    private Guest raiz;

    public GuestRegistry() {
    }

    public Guest getGuestRegistry() {
        return this.raiz;
    }
    public void addGuestToRegistry(Guest guest) {
        Guest aux=this.raiz;
        
        if (this.raiz == null) {
            this.raiz = guest;
        } else {
            while(this.raiz!=null)
                addGuestRecursivo(aux, guest);
                
        }

    }
    private void addGuestRecursivo(Guest atual, Guest novo) {
        if (atual.getNextInLine() == null) {
            atual.setNextInLine(novo);
        } else {
            addGuestRecursivo(atual.getNextInLine(), novo);
        }
    }

     public void removeFromRegistry(Guest guestRemover) {
        this.raiz = removeGuestRecursivo(this.raiz, guestRemover);
    }

    private Guest removeGuestRecursivo(Guest current, Guest guestRemover) {
        if (current == null) {
            return null;
        }

        if (current.equals(guestRemover)) {
            return current.getNextInLine();
        }

        current.setNextInLine(removeGuestRecursivo(current.getNextInLine(), guestRemover));
        return current;
    }
}

    

    

*/