package Entities;


public class Guest extends Person {

    private Guest nextInLine;

    public Guest(String name, String cpf, Vehicle owned) {
        super(name, cpf, owned);
    }

    public Guest newGuest(Vehicle detected) {
        String name = "Unknown ";
        String cpf = "Unknown";
        
        return new Guest(name, cpf, detected);

    }

    /**
     * @return the nextInLine
     */
    public Guest getNextInLine() {
        return nextInLine;
    }

    /**
     * @param nextInLine the nextInLine to set
     */
    public void setNextInLine(Guest nextInLine) {
        this.nextInLine = nextInLine;
    }

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
                aux=this.getNextInLine();
                aux.getGuestByVehicle(vehicle);
            }
            return null;
        }

    }
    public void percorreRecursivo(Guest atual, Guest novo){}

}
