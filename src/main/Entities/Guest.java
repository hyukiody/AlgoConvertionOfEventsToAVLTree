package Entities;

public class Guest extends Person {

    private Guest nextInLine; // Referência para o próximo Guest na lista encadeada

    public Guest(String name, String cpf, Vehicle owned) {
        super(name, cpf, owned);
        this.nextInLine = null;
    }

    public Guest getNextInLine() {
        return nextInLine;
    }

    public void setNextInLine(Guest nextInLine) {
        this.nextInLine = nextInLine;
    }

    // Método estático para criar um novo Guest com veículo
    public static Guest newGuest(Vehicle newVehicle) {
        String name = "Unknown";
        String cpf = "Unknown";
        return new Guest(name, cpf, newVehicle);
    }

    // Função de varredura a partir do primeiro da lista encadeada
    public Guest getGuestByCpf(String cpf) {
        Guest current = this;

        while (current != null) {
            if (current.getCpf().equals(cpf)) {
                return current;
            }
            current = current.getNextInLine();
        }
        return null;
    }

    public Guest getGuestByVehicle(Vehicle vehicle) {
        Guest current = this;

        while (current != null) {
            if (current.getVehicle().equals(vehicle)) {
                return current;
            }
            current = current.getNextInLine();
        }
        return null;
    }

    // Método para percorrer a lista encadeada recursivamente
    public void percorreRecursivo(Guest atual, Operation operation) {
        if (atual == null) {
            return;
        }

        // Executa a operação no Guest atual
        operation.perform(atual);

        // Chama o método recursivamente para o próximo Guest na lista
        percorreRecursivo(atual.getNextInLine(), operation);
    }

    // Interface para representar a operação a ser realizada
    public interface Operation {
        void perform(Guest guest);
    }
}

