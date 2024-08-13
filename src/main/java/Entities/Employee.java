package Entities;

public class Employee extends Person {

    private String workplace;
    private Employee nextInLine;

    public Employee(String name, String cpf, Vehicle owned) {
        super(name, cpf, owned);
        this.workplace = workplace;
    }

    public String getWorkplace() {
        return this.workplace;
    }

    public void setWorkplace(String worplace) {
        this.workplace = workplace;
    }

    public Employee getNextInLine() {
        return nextInLine;
    }

    /**
     * @param nextInLine the nextInLine to set
     */
    public void setNextInLine(Employee nextInLine) {
        this.nextInLine = nextInLine;
    }

    //função de varredura a partir do primeiro da lista encadeada
    public Employee getEmployeeByCpf(String cpf) {
        Employee aux = this;

        if (aux.getCpf().equals(cpf)) {
            return this;
        } else {
            while (aux.getNextInLine() != null) {
                aux = (this.getNextInLine());
                aux.getEmployeeByCpf(cpf);
            }
            return null;
        }
    }

    //função de varredura a partir do primeiro da lista encadeada
    public Employee getEmployeeByVehicle(Vehicle vehicle) {
        Employee aux = this;

        if (this.getNextInLine().getVehicle() == vehicle) {
            return this;
        } else {
            while (aux.getNextInLine() != null) {
                aux=this.getNextInLine();
                aux.getEmployeeByVehicle(vehicle);
            }
            return null;
        }

    }

}
