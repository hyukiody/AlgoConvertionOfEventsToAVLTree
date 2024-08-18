package main;

import Entities.*;
import Entities.registry.*;
import Entities.registry.hashRegistry.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main{

    public static void main(String[] args) {
        // Criar registros
        EmployeeRegistry employeeRegistry = new EmployeeRegistry();
        GuestRegistry guestRegistry = new GuestRegistry();
        LocationRegistry locationRegistry = new LocationRegistry();
        ArrayList<Visit> visitHistory = new ArrayList<>(); // Lista de visitas
        
        // Criar instâncias de Employee e Guest
        Employee emp1 = new Employee("John Doe", "12345", new Vehicle("ABC-1234", null));
        Guest guest1 = new Guest("Jane Doe", "67890", new Vehicle("XYZ-9876", null));
        
        // Adicionar Employees e Guests aos registros
        employeeRegistry.addEmployeeToRegistry(emp1);
        guestRegistry.addGuestToRegistry(guest1);
        
        // Criar instâncias de Location
        Location location1 = new Location(10.0f, 20.0f); // Ajuste para coordenadas
        Location location2 = new Location(15.0f, 25.0f); // Ajuste para coordenadas
        
        // Adicionar Locations ao registro
        locationRegistry.addLocation(location1);
        locationRegistry.addLocation(location2);
        
        // Criar instâncias de Event
        Event entryEvent1 = new Event("Entrada", LocalDateTime.of(2024, 8, 17, 10, 0), location1, null);
        Event exitEvent1 = new Event("Saída", LocalDateTime.of(2024, 8, 17, 18, 0), location1, null);
        Event entryEvent2 = new Event("Entrada", LocalDateTime.of(2024, 8, 17, 11, 0), location2, null);
        Event exitEvent2 = new Event("Saída", LocalDateTime.of(2024, 8, 17, 17, 0), location2, null);
        
        // Criar e adicionar visitas ao histórico de visitas
        Visit visit1 = new Visit(emp1, emp1.getVehicle(), LocalDateTime.of(2024, 8, 17, 10, 0));
        visit1.addToRoute(entryEvent1);
        visit1.addToRoute(exitEvent1);
        
        Visit visit2 = new Visit(guest1, guest1.getVehicle(), LocalDateTime.of(2024, 8, 17, 11, 0));
        visit2.addToRoute(entryEvent2);
        visit2.addToRoute(exitEvent2);
        
        // Adicionar visitas ao histórico
        visitHistory.add(visit1);
        visitHistory.add(visit2);

        // Exemplo de impressão para verificar os resultados
        for (Visit visit : visitHistory) {
            System.out.println("Visit for: " + visit.getVisitor().getName());
            for (Event event : visit.getEventRoute()) {
                System.out.println(event);
            }
        }
    }
}
