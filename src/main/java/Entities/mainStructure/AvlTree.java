package Entities.mainStructure;

import Entities.*;
import Entities.registry.LocationRegistry;
import Entities.registry.VehicleRegistry;
import Entities.registry.VisitHistory;
import Entities.registry.hashRegistry.HashRegistry;

public class AvlTree {

    private HashRegistry hashRegistry;
    private VehicleRegistry vehicleList;
    private VisitHistory visitHistory;
    private LocationRegistry locationRegistry;
    private Nodo raiz;
    private int contador = 0;

    //não seria necessário inserir as estruturas de armazenamento de objetos na classe da arvore, mas faremos assim para exemplificar uma implementação integrada
    // poderiamos por exemplo, definir da mesma forma o uso destas nos parametros da arvore, instanciá-las na main e, na main, inserí-las como parametros da arvore, mas daria na mesma...
    public AvlTree() {
        this.hashRegistry = new HashRegistry(1);
        this.vehicleList = new VehicleRegistry();
        this.visitHistory = new VisitHistory();
        this.locationRegistry = new LocationRegistry();
        this.raiz = new Nodo();
    }

    public Nodo getRaiz() {
        return this.raiz;
    }

    public void setRaiz(Nodo novoNodo) {
        this.raiz = novoNodo;
    }

    public HashRegistry getHashRegistry() {
        return this.hashRegistry;
    }

    public VehicleRegistry getVehicleList() {
        return this.vehicleList;
    }

    public VisitHistory getVisitHistory() {
        return this.visitHistory;
    }

    public LocationRegistry getLocationRegistry() {
        return this.locationRegistry;
    }

    public VehicleRegistry getVehicleRegistry() {
        return this.vehicleList;
    }

    //função de varredura progressiva, avança pela arvore, realizando as verificações necessarias para tratamento dos registros
    public void varreduraVisitas(Nodo raizAtual) {
        contador++;
        System.out.println("Iniciando varredura:...");
        try {
            System.out.println("Numero de varreduras:  " + contador);
            if (raizAtual == null) {
                System.out.println("Raiz vazia;");
                return;
            } else if (raizAtual.getEvent() == null) {
                System.out.println("Evento vazio");
                return;
            }
            for (Visit visit : getVisitHistory().getHistoryList()) {
                for (Event event : visit.getEventRoute()) {
                    if (raizAtual.getEvent() == event) {
                        System.out.println("-Evento já tratado, avançando na árvore...");
                        this.varreduraVisitas(raizAtual.getEsq());
                        this.varreduraVisitas(raizAtual.getDir());
                    }
                }
            }
            if (raizAtual.getEvent().getCamera().getId() == 1 || raizAtual.getEvent().getCamera().getId() == 6) {
                if (raizAtual.getEvent().getCamera().getId() == 1) {
                    Event event = raizAtual.getEvent();
                    Vehicle newVehicle = getVehicleRegistry().findVehicleByPlate(event.getCarPlate());
                    if (newVehicle == null) {
                        Guest newGuest = Guest.newGuest(event.getCarPlate());
                        newVehicle = newGuest.getVehicle();
                        newGuest.setVehicle(newVehicle);
                        getVehicleRegistry().addVehicleToRegistry(newVehicle);
                        Visit newVisit = new Visit(getVisitHistory().getHistoryList(), newGuest, newVehicle, event.getEventTime());
                        getVisitHistory().addToHistory(newVisit);
                        System.out.println("Adicionando nova visita ao registro...");
                        getHashRegistry().addPerson(newGuest);
                        System.out.println("Adicionando visitante ao registro...");
                    } else {
                        if (newVehicle.getOwner() instanceof Employee) {
                            Visit newEmployeeVisit = new Visit(getVisitHistory().getHistoryList(), newVehicle.getOwner(), newVehicle, event.getEventTime());
                            getVisitHistory().addToHistory(newEmployeeVisit);
                            System.out.println("Nova visita de funcionario detectada...");
                        } else if (newVehicle.getOwner() instanceof Guest) {
                            Visit newGuestVisit = new Visit(getVisitHistory().getHistoryList(), newVehicle.getOwner(), newVehicle, event.getEventTime());
                            getVisitHistory().addToHistory(newGuestVisit);
                        }
                    }
                } else {
                    Event event = raizAtual.getEvent();
                    Visit visitToBeClosed = getVisitHistory().getVisitInVisitListByPlate(event.getCarPlate());
                    visitToBeClosed.addToRoute(event);
                    visitToBeClosed.setEndingTime(event.getEventTime());
                }
            } else {
                boolean found = false;
                Event event = raizAtual.getEvent();
                for (Visit visitIerator : getVisitHistory().getHistoryList()) {
                    if (visitIerator.getVehicle().getPlate().equals(event.getCarPlate())) {
                        Visit visitToBeAddedTo = visitIerator;
                        visitToBeAddedTo.addToRoute(event);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    Vehicle newVehicle = new Vehicle(event.getCarPlate(), Guest.newGuest(event.getCarPlate()));
                    Visit newVisit = new Visit(getVisitHistory().getHistoryList(), newVehicle.getOwner(), newVehicle, event.getEventTime());
                    getVisitHistory().addToHistory(newVisit);
                }
            }
            if (raizAtual.getEsq() != null) {
                varreduraVisitas(raizAtual.getEsq());
            }
            if (raizAtual.getDir() != null) {
                varreduraVisitas(raizAtual.getDir());
            }
            contador = 0;
        } catch (NullPointerException e) {
            System.err.println("NullPointerException caught in varredura: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("IllegalArgumentException caught in varredura: " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public int getContador() {
        return contador;
    }

    private Nodo createNewNode(Event novoEvento) {
        Nodo novoNodo = new Nodo(novoEvento);
        novoNodo.setAltd(0);
        novoNodo.setAlte(0);
        novoNodo.setEsq(null);
        novoNodo.setDir(null);
        return novoNodo;
    }

    //metodo de instancia da arvore pra receber a string e verificar o regitro de localidades, void pois ja adiciona o novo nodo criado a partir do evento criado da string inserida
    public void newEventInTree(String novoEventoEmString) {
        try {
            //um novo evento na árvore implica em possíveis novas visitas e novas pessoas (novos visitantes) no registro hash
            //então faz sentido que o registro de visitas seja iterado em conjunto na ordem: parse string->>new evento->>new nodo->>place nodo->>arvore

            Event novoEvento = new Event();
            novoEvento = novoEvento.parseEventFromString(getLocationRegistry(), novoEventoEmString);
            this.setRaiz(inserirNaArvore(getRaiz(), novoEvento));

            System.out.println("Novo nodo inserido na árvore:  \n" + novoEvento);
        } catch (NullPointerException e) {
            System.err.println("NullPointerException caught: " + e.getMessage());
            e.printStackTrace();

        } catch (IllegalArgumentException e) {
            System.err.println("IllegalArgumentException caught: " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
            e.printStackTrace();

        }
    }

    //
    public Nodo inserirNaArvore(Nodo raiz, Event novoEvento) {
        // no caso, o parametro raiz é a raiz da árvore
        try {

            if (raiz == null || raiz.getEvent() == null) {
                System.out.println("nó vazio detectado; inserindo novo nodo.");

                return createNewNode(novoEvento);
            } else if (novoEvento.getEventTime().isBefore(raiz.getEvent().getEventTime())) {
                raiz.setEsq(inserirNaArvore(raiz.getEsq(), novoEvento));
                raiz.setAlte(Math.max(raiz.getEsq().getAlte(), raiz.getEsq().getAltd()) + 1);
            } else {
                raiz.setDir(inserirNaArvore(raiz.getDir(), novoEvento));
                raiz.setAltd(Math.max(raiz.getDir().getAlte(), raiz.getDir().getAltd()) + 1);
            }

        } catch (NullPointerException e) {
            System.err.println("NullPointerException caught: " + e.getMessage());
            e.printStackTrace();

        } catch (IllegalArgumentException e) {
            System.err.println("IllegalArgumentException caught: " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
            e.printStackTrace();

        }
        return balance(raiz);
    }

    public Nodo balance(Nodo raiz) {
        int balanceFactor = raiz.getAltd() - raiz.getAlte();

        if (balanceFactor == 2) {
            if (raiz.getDir().getAltd() >= raiz.getDir().getAlte()) {
                raiz = rotateLeft(raiz); // Update raiz after rotation
            } else {
                raiz.setDir(rotateRight(raiz.getDir())); // Update right child first
                raiz = rotateLeft(raiz); // Update raiz after rotation
            }
        } else if (balanceFactor == -2) {
            if (raiz.getEsq().getAlte() >= raiz.getEsq().getAltd()) {
                raiz = rotateRight(raiz); // Update raiz after rotation
            } else {
                raiz.setEsq(rotateLeft(raiz.getEsq())); // Update left child first
                raiz = rotateRight(raiz); // Update raiz after rotation
            }
        }

        return raiz;
    }

    public Nodo rotateLeft(Nodo aux) {
        if (aux == null || aux.getDir() == null) {
            return aux; // Return original node if rotation isn't possible
        }
        Nodo aux1 = aux.getDir();
        aux.setDir(aux1.getEsq());
        aux1.setEsq(aux);
        updateHeight(aux);
        updateHeight(aux1);
        return aux1;
    }

    public Nodo rotateRight(Nodo aux) {
        if (aux == null || aux.getEsq() == null) {
            return aux; // Return original node if rotation isn't possible
        }
        Nodo aux1 = aux.getEsq();
        aux.setEsq(aux1.getDir());
        aux1.setDir(aux);
        updateHeight(aux);
        updateHeight(aux1);
        return aux1;
    }

    private void updateHeight(Nodo node) {
        if (node == null) {
            return;
        }
        node.setAlte(node.getEsq() != null ? Math.max(node.getEsq().getAlte(), node.getEsq().getAltd()) + 1 : 0);
        node.setAltd(node.getDir() != null ? Math.max(node.getDir().getAlte(), node.getDir().getAltd()) + 1 : 0);
    }

    public void exibirEmOrdem(Nodo raiz) {
        if (raiz != null) {
            exibirEmOrdem(raiz.getEsq());
            System.out.print(raiz.getEvent() + "  ");
            exibirEmOrdem(raiz.getDir());
        }
    }

    public void exibirPreOrdem(Nodo raiz) {
        if (raiz != null) {
            System.out.print(raiz.getEvent() + ", ");
            exibirPreOrdem(raiz.getEsq());
            exibirPreOrdem(raiz.getDir());
        }
    }

    public void exibirPosOrdem(Nodo raiz) {
        if (raiz != null) {
            exibirPosOrdem(raiz.getEsq());
            exibirPosOrdem(raiz.getDir());
            System.out.print(raiz.getEvent() + ", ");
        }
    }
}
