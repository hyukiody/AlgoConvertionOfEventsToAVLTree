package Entities;

public class Employee extends Person {

    private String workplace;
    private Employee nextInLine; // Adicione este atributo

    public Employee(String name, String cpf, Vehicle owned) {
        super(name, cpf, owned);
        this.workplace = workplace;
        this.nextInLine = null; // Inicialize o próximo empregado como nulo
    }

    public String getWorkplace() {
        return this.workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    // Adicione o método getNextInLine()
    public Employee getNextInLine() {
        return this.nextInLine;
    }

    // Adicione o método setNextInLine()
    public void setNextInLine(Employee nextInLine) {
        this.nextInLine = nextInLine;
    }

    // Código comentado anteriormente
    // Código removido pois a função de varredura passou a ser própria da tabela Hash de pessoas

    // Função de varredura a partir do primeiro da lista encadeada
    /*public Employee getEmployeeByCpf(String cpf) {
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

    // Função de varredura a partir do primeiro da lista encadeada
    public Employee getEmployeeByVehicle(Vehicle vehicle) {
        Employee aux = this;

        if (this.getNextInLine().getVehicle() == vehicle) {
            return this;
        } else {
            while (aux.getNextInLine() != null) {
                aux = this.getNextInLine();
                aux.getEmployeeByVehicle(vehicle);
            }
            return null;
        }*/
}
