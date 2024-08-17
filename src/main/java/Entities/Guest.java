package Entities;


public class Guest extends Person {


    public Guest(String name, String cpf, Vehicle owned) {
        super(name, cpf, owned);
    }

    public static Guest newGuest(Vehicle newVehicle) {
        String name = "Unknown ";
        String cpf = "Unknown";

        return new Guest(name, cpf, newVehicle);

    }

    /**
     * return the nextInLine
     */
    /*
    //função de varredura a partir do primeiro da lista encadeada
    public Guest getGuestByCpf(String cpf) {
        Guest aux = this;

        if (this.getNextInLine().getCpf().equals(cpf)) {
            return this;
        } else {
            while (aux.getNextInLine() != null) {
                aux = (this.getNextInLine());
                aux.getGuestByCpf(cpf);
            }
            return null;
        }
    }

    public Guest getGuestByVehicle(Vehicle vehicle) {
        Guest aux = this;

        if (this.getNextInLine().getVehicle() == vehicle) {
            return this;
        } else {
            while (aux.getNextInLine() != null) {
                aux = this.getNextInLine();
                aux.getGuestByVehicle(vehicle);
            }
            return null;
        }

    }*/
    public void percorreRecursivo(Guest atual, Guest novo) {
    }

}
