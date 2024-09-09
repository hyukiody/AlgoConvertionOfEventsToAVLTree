package Main;

import Entities.*;
import Entities.mainStructure.AvlTree;
import Entities.registry.LocationRegistry;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) {

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

        novaArvore.newEventInTree(newInput1);
        novaArvore.newEventInTree(newInput12);
        novaArvore.newEventInTree(newInput13);
        novaArvore.newEventInTree(newInput14);

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

        System.out.println("Exibindo pre ordem");
        novaArvore.exibirPreOrdem(novaArvore.getRaiz());
        System.out.println("\n------------\n exibindo nomes dos visitantes");
        for (Visit visit : novaArvore.getVisitHistory().getHistoryList()) {
            System.out.println(visit.getVisitor().getName());
        }

    }
}
