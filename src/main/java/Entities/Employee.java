package Entities;

public class Employee extends Person {

    private String workplace;

    public Employee(String name, String cpf, Vehicle owned) {
        super(name, cpf, owned);
    }



    //codigo retirado pois a função de varredura passou a ser propria da tabela Hash de pessoas
    //função de varredura a partir do primeiro da lista encadeada
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
        }*/

    }


