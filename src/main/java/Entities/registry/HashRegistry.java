//modelando a partir das estruturas do professor Tiago

package Entities.registry;

import Entities.Person;
import java.util.LinkedList;

public class HashRegistry {

    private int size;
    private Person[] peopleList;
    private int qtdColisoes = 0;

    public HashRegistry(int size) {
        System.out.println("Estrutura Hash tamanho " + size);
        this.setSize(size);
        this.peopleList = new LinkedList[size];

        for (int i = 0; i < size; i++) {
            peopleList[i] = new LinkedList<>();
        }
    }

    // Adiciona uma pessoa para a tabela hash; aqui funcionarios e visitantes são armazenados juntos
    public void addPerson(Person person) {
        int index = calculateIndex(person.hashCode());

        // Verifica colisão
        if (!peopleList[index].isEmpty()) {
            qtdColisoes++;
        }

        // Adiciona na lista do indice
        peopleList[index].add(person);
    }

    // Searches for a person in the hash table by their hashcode
    public Person searchPerson(int personHashCode) {
        int index = calculateIndex(personHashCode);

        for (Person person : peopleList[index]) {
            if (person.hashCode() == personHashCode) {
                return person;
            }
        }
        return null; // Return null if not found
    }

    // Calculates the hash table index
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
            LinkedList<Person> list = peopleList[i];
            System.out.println("Number of elements: " + list.size());

            for (Person person : list) {
                System.out.println("Person: " + person); // Assuming Person has a toString method
            }
        }
    }

    public int getCollisionCount() {
        return qtdColisoes;
    }
}
