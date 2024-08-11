package Entities;

public class Employee extends Person {

    private String workplace;

    public Employee(String name, String cpf, Vehicle owned) {
        super(name, cpf, owned);
        this.workplace = workplace;
    }
    
    public String getWorkplace(){
        return this.workplace;
    }
    public void setWorkplace(String worplace){
        this.workplace=workplace;
    }
}
