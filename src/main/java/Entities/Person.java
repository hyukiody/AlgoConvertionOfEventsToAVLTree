package Entities;

public abstract class Person {

    private String name;
    private String cpf;
    private Vehicle vehicle;


    public Person(String name, String cpf, Vehicle owned) {
        this.name = name;
        this.cpf = cpf;
        this.vehicle = owned;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return this.cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the vehicle
     */
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    /**
     * @param vehicle the vehicle to set
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


    @Override
    public String toString() {
        return "Person{name='" + name + "', cpf='" + cpf + "', vehicle=" + vehicle + "}";
    }
}
