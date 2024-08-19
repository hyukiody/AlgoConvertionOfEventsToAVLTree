//modelando a partir das estruturas do professor Tiago

package Entities.registry.hashRegistry;

import Entities.Person;
import Entities.Vehicle;


public class HashRegistry {

    private final int qtdColisoes = 0;
    private Person[] peopleTable;
    private int size;

    public HashRegistry(int size) {
        System.out.println("Estrutura Hash tamanho " + size);
        this.setSize(size);
        this.peopleTable = new Person[size];

    }

    public void setPeopleTable(Person[] peopleTable) {
        this.peopleTable = peopleTable;
    }

    //metodo de calculo do
    private int calculateIndex(int hashCode) {
        return Math.abs(hashCode) % this.getSize();
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    //metodo de inserir pessoas no registro hash; se o indice estiver ocupado, armazena no proximo disponivel
    //o objetivo de salvar os visitantes, é o de ter seu historico de visitas atraves de seu veiculo, sendo necessario a instancia de um visitante pois um veiculo implica em um motorista, e este num visitante

    public void addPerson(Person newPerson) {
        int index = calculateIndex(newPerson.hashCode());
        while (index < this.peopleTable.length && this.peopleTable[index] != null) {
            index++;
        }
        if (index == this.peopleTable.length) {
            Person[] newSizedTable = new Person[this.peopleTable.length * 2];
            for (int i = 0; i < this.peopleTable.length; i++) {
                newSizedTable[i] = this.peopleTable[i];
            }
            this.peopleTable = newSizedTable;
            index = this.peopleTable.length / 2; // Reset index to the first new slot
        }
        this.peopleTable[index] = newPerson;
    }


    public Person searchPersonByCarPlate(String searchPlate) {
        for (Person person : peopleTable) {
            if (person.getVehicle().getPlate().equals(searchPlate)) {
                return person;
            }
        }
        return null;
    }

    public Person searchPersonByVehicle(Vehicle searchVehicle) {
        for (Person person : peopleTable) {
            if (person.getVehicle().equals(searchVehicle)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder body = new StringBuilder();
        body.append("HashRegistry:\n");
        body.append("    size=").append(size);
        body.append("\n    qtdColisoes=").append(qtdColisoes);
        body.append("\n    peopleTable=\n");
        for (Person person : peopleTable) {
            if (person != null) {
                body.append(person.toString()).append(", \n");
            }
        }
        if (peopleTable.length > 0) {
            body.setLength(body.length() - 2); // Remove the last comma and space
        }
        body.append("\n");
        return body.toString();
    }

    public int getCollisionCount() {
        return qtdColisoes;
    }
}
