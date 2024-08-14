//modelando a partir das estruturas do professor Tiago

package Entities.registry.hashRegistry;

import Entities.Person;


public class HashRegistry {

    private int size;
    private Person[] peopleLists;
    private int qtdColisoes = 0;

    public HashRegistry(int size) {
        System.out.println("Estrutura Hash tamanho " + size);
        this.setSize(size);
        this.peopleLists = new Person[size];

    }

    //metodo de inserir pessoa no registro, verifica se é funcionario ou visitante
    //o objetivo de salvar os visitantes, é o de ter seu historico de visitas atraves de seu veiculo
    public void addPerson(Person newPerson) {
        int index = calculateIndex(newPerson.hashCode());
        addPersonRecursive(newPerson, index);
    }

    private void addPersonRecursive(Person newPerson, int index) {
        if (peopleLists[index] == null) {
            peopleLists[index] = newPerson;
        } else {
            qtdColisoes++;
            if (peopleLists[index].getNextPerson() == null) {
                peopleLists[index].setNextPerson(newPerson);
            } else {
                addPersonRecursive(newPerson, index);
            }
        }
    }

    //buca recursivo pela pessoa na tabela hash pelo seu codigoHash
public Person searchPerson(Person searchingPerson) {
    int index = calculateIndex(searchingPerson.hashCode());

    Person current = peopleLists[index];
    while (current != null) {
        if (current.hashCode() == searchingPerson.hashCode()) {
            return current;
        }
        current = current.getNextPerson();
    }
    return null; // Return null if not found
}

// Busca recursivo pela pessoa pelo seu codigoHash na tabelaHash
public Person searchPersonRecursive(Person searchingPerson, int index) {
    return searchPersonRecursiveAux(searchingPerson, peopleLists[index]);
}

private Person searchPersonRecursiveAux(Person searchingPerson, Person current) {
    if (current == null) {
        return null; // Return null if not found
    }
    if (current.hashCode() == searchingPerson.hashCode()) {
        return current;
    }
    return searchPersonRecursiveAux(searchingPerson, current.getNextPerson());
}

    // função de calculo hash
    private int calculateIndex(int hashCode) {
        return Math.abs(hashCode) % getSize();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void printTable() {
    for (int i = 0; i < getSize(); i++) {
        System.out.println("Index: " + i);
        Person current = peopleLists[i];
        int count = 0;

        while (current != null) {
            count++;
            System.out.println("Person: " + current); // Assuming Person has a toString method
            current = current.getNextPerson();
        }

        System.out.println("Number of elements: " + count);
    }
}
    public int getCollisionCount() {
        return qtdColisoes;
    }
}
