package Main;

import Entities.*;
import Entities.mainStructure.AvlTree;
import Entities.registry.LocationRegistry;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
        /*
       arvorebinaria vai possuir as estruturas auxiliares abaixo:
       inicializar estruturas auxiliares: listas e conjuntos de armazenamento:
        hash do registro paras listas de pessoas> funcionarios e visitantes
        lista encadeada de veículos >> cada veiculo aponta para seu proprietario (funcionario ou visitante)

       arvore binaria tambem possui algoritmo responsavel por sua varredura
       de eventos que inicia e encerra visitas, atualizando as estruturas auxiliares


        EmployeeRegistry employeeRegistry = new EmployeeRegistry();
        GuestRegistry guestRegistry = new GuestRegistry();
        LocationRegistry locationRegistry = new LocationRegistry();
        ArrayList<Visit> visitHistory = new ArrayList<>(); // Lista de visitas

        Criar instâncias de Employee e Guest
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
         */
        AvlTree novaArvore = new AvlTree();

        Vehicle emp1Car = new Vehicle("ABC123", null);
        Vehicle emp2Car = new Vehicle("XYZ789", null);
        Employee emp1 = new Employee("funcionario1", "123.123.123-12", emp1Car);
        Employee emp2 = new Employee("funcionario2", "321.321.321-32", emp2Car);
        emp1Car.setOwner(emp1);
        emp2Car.setOwner(emp2);
        novaArvore.getVehicleRegistry().addVehicleToRegistry(emp1Car);
        novaArvore.getVehicleRegistry().addVehicleToRegistry(emp2Car);
        novaArvore.getVehicleRegistry().printVehiclesInList();

        novaArvore.getHashRegistry().addPerson(emp1);
        novaArvore.getHashRegistry().addPerson(emp2);
        System.out.println(novaArvore.getHashRegistry().toString());

        /*
        Vehicle iterateVehicle = novaArvore.getVehicleRegistry().getRaiz();
        while (iterateVehicle != null) {
            System.out.println(iterateVehicle);
            iterateVehicle = iterateVehicle.getNextInLine();
        }*/
        //input da entrada de 2 funcionarios e 2 visitantes
        String newInput1 = "2023-10-01T00:00:00,1,1,1,ABC123";
        String newInput12 = "2023-10-01T03:12:00,2,2,2,ABC123";
        String newInput13 = "2023-10-01T03:21:00,2,2,5,ABC123";
        String newInput14 = "2023-10-01T03:32:00,1,1,6,ABC123";

        String newInput2 = "2023-10-01T00:05:00,1,1,1,XYZ789";
        String newInput21 = "2023-10-01T03:35:00,2,2,2,XYZ789";
        String newInput22 = "2023-10-01T03:40:00,3,3,3,XYZ789";
        String newInput23 = "2023-10-01T03:45:00,3,3,4,XYZ789";
        String newInput24 = "2023-10-01T03:50:00,2,2,5,XYZ789";
        String newInput25 = "2023-10-01T03:55:00,1,1,6,XYZ789";

        String newInput3 = "2023-10-01T02:30:00,1,1,1,LMN456";
        String newInput31 = "2023-10-01T02:35:00,2,2,2,LMN456";
        String newInput32 = "2023-10-01T02:42:00,2,2,5,LMN456";
        String newInput33 = "2023-10-01T02:44:00,1,1,6,LMN456";

        String newInput4 = "2023-10-01T02:35:00,1,1,1,DEF321";
        String newInput41 = "2023-10-01T03:30:00,2,2,2,DEF321";
        String newInput42 = "2023-10-01T03:35:00,1,1,6,DEF321";

        novaArvore.newEventInTree(newInput2);
        novaArvore.newEventInTree(newInput21);
        novaArvore.newEventInTree(newInput22);
        novaArvore.newEventInTree(newInput23);
        novaArvore.newEventInTree(newInput24);
        novaArvore.newEventInTree(newInput25);

        novaArvore.newEventInTree(newInput3);
        novaArvore.newEventInTree(newInput31);
        novaArvore.newEventInTree(newInput32);
        novaArvore.newEventInTree(newInput33);

        novaArvore.newEventInTree(newInput4);
        novaArvore.newEventInTree(newInput41);
        novaArvore.newEventInTree(newInput42);

        novaArvore.varreduraVisitas(novaArvore.getRaiz());

        System.out.println("Exibindo visitas do registro");
        System.out.println(novaArvore.getVisitHistory());


        System.out.println("\n ---------------------- \n Exibindo em ordem:");
        novaArvore.exibirEmOrdem(novaArvore.getRaiz());
        System.out.println("\n------------\n exibindo nomes dos visitantes");
        for (Visit visit : novaArvore.getVisitHistory().getHistoryList()) {
            System.out.println(visit.getVisitor().getName());
        }

    }
}
