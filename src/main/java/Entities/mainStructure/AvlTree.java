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
    private int swipeCounter = 0;


    //não seria necessário inserir as estruturas de armazenamento de objetos na classe da arvore, mas faremos assim para exemplificar uma implementação integrada
    // poderiamos por exemplo, definir da mesma forma o uso destas nos parametros da arvore, mas instanciá-las na main e, na main, inserí-las como parametros da arvore, mas daria na mesma...
    public AvlTree() {
        this.hashRegistry = new HashRegistry(1);
        this.vehicleList = new VehicleRegistry();
        this.visitHistory = new VisitHistory();
        this.locationRegistry = new LocationRegistry();
        this.raiz = null;
    }

    private Nodo createNewNode(Event novoEvento) {
        Nodo novoNodo = new Nodo(null);
        novoNodo.setEvent(novoEvento);
        novoNodo.setAltd(0);
        novoNodo.setAlte(0);
        novoNodo.setEsq(null);
        novoNodo.setDir(null);
        return novoNodo;
    }

    public Nodo getRaiz() {
        return this.raiz;
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


    //função de varredura progressiva, avança pela arvore, realizando as verificações necessarias para tratamento dos registros
    public void progressiveSwipe(LocationRegistry locationRegistry, VehicleRegistry vehicleRegistry, HashRegistry hashRegistry, VisitHistory visitHistory, Nodo raizAtual) {
        swipeCounter++;
        System.out.println("Iniciando varredura:...");
        try {
            System.out.println("Numero de varreduras:  " + swipeCounter);
            if (raizAtual == null) {
                System.out.println("Raiz vazia;");
                return;
            } else if (raizAtual.getEvent() == null) {
                System.out.println("Evento vazio");
                return;
            }
            for (Visit visit : visitHistory.getVisitHistory()) {
                for (Event event : visit.getEventRoute()) {
                    if (raizAtual.getEvent() == event) {
                        progressiveSwipe(locationRegistry, vehicleRegistry, hashRegistry, visitHistory, raizAtual.getEsq());
                        progressiveSwipe(locationRegistry, vehicleRegistry, hashRegistry, visitHistory, raizAtual.getDir());
                    }
                }
            }
            if (raizAtual.getEvent().getCamera().getId() == 1 || raizAtual.getEvent().getCamera().getId() == 6) {
                if (raizAtual.getEvent().getCamera().getId() == 1) {
                    Event event = raizAtual.getEvent();
                    Vehicle newVehicle = vehicleRegistry.findVehicleByPlate(event.getCarPlate());
                    if (newVehicle == null) {
                        Guest newGuest = Guest.newGuest(null);
                        newVehicle = new Vehicle(raizAtual.getEvent().getCarPlate(), newGuest);
                        newGuest.setVehicle(newVehicle);
                        vehicleRegistry.addVehicleToRegistry(newVehicle);
                        Visit newVisit = new Visit(visitHistory.getVisitHistory(), newGuest, newVehicle, raizAtual.getEvent().getHoraEvento());
                        visitHistory.addToHistory(newVisit);
                        System.out.println("Adicionando nova visita ao registro...");
                        this.hashRegistry.addPerson(newGuest);
                        System.out.println("Adicionando visitante ao registro...");
                    } else {
                        if (newVehicle.getOwner() instanceof Employee) {
                            Visit newEmployeeVisit = new Visit(visitHistory.getVisitHistory(), newVehicle.getOwner(), newVehicle, raizAtual.getEvent().getHoraEvento());
                            visitHistory.addToHistory(newEmployeeVisit);
                            System.out.println("Nova visita de funcionario detectada...");
                        } else if (newVehicle.getOwner() instanceof Guest) {
                            Visit newGuestVisit = new Visit(visitHistory.getVisitHistory(), newVehicle.getOwner(), newVehicle, raizAtual.getEvent().getHoraEvento());
                            visitHistory.addToHistory(newGuestVisit);
                        }
                    }
                } else if (raizAtual.getEvent().getCamera().getId() == 6) {
                    Event event = raizAtual.getEvent();
                    Visit visitToBeClosed = visitHistory.getVisitByPlate(raizAtual.getEvent().getCarPlate());
                    visitToBeClosed.addToRoute(raizAtual.getEvent());
                    visitToBeClosed.setEndingTime(raizAtual.getEvent().getHoraEvento());
                }
            } else if (!(raizAtual.getEvent().getCamera().getId() == 1 || raizAtual.getEvent().getCamera().getId() == 6)) {
                Event event = raizAtual.getEvent();
                Visit visitToBeAddedTo = visitHistory.getVisitByPlate(event.getCarPlate());
                visitToBeAddedTo.addToRoute(raizAtual.getEvent());
            }
            while (raizAtual.getEsq() != null || raizAtual.getDir() != null) {
                progressiveSwipe(locationRegistry, vehicleRegistry, hashRegistry, visitHistory, raizAtual.getEsq());
                progressiveSwipe(locationRegistry, vehicleRegistry, hashRegistry, visitHistory, raizAtual.getDir());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSwipeCounter() {
        return swipeCounter;
    }


    //metodo de instancia da arvore pra receber a string e verificar o regitro de localidades, void pois ja adiciona o novo nodo criado a partir do evento criado da string inserida
    public void newEventInTree(String novoEventoEmString) {
        try {
            //um novo evento na árvore implica em possíveis novas visitas e novas pessoas (novos visitantes) no registro hash
            //então faz sentido que o registro de visitas seja iterado em conjunto na ordem: parse string >> new evento >> new nodo >> place nodo >> arvore

            Event novoEvento = new Event();
            novoEvento = novoEvento.parseEventFromString(this.locationRegistry, novoEventoEmString);
            inserirNaArvore(this.raiz, novoEvento);
            System.out.println("Novo nodo inserido na árvore:  \n" + novoEvento);
        }

        catch (NullPointerException e) {
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
    public void inserirNaArvore(Nodo raiz, Event novoEvento) {
        // no caso, o parametro raiz é a raiz da árvore
        try {
            if (raiz == null) {
                raiz = new Nodo(novoEvento);
            } else {
                if (novoEvento.getHoraEvento().isBefore(raiz.getEvent().getHoraEvento())) {
                    inserirNaArvore(raiz.getEsq(), novoEvento);
                    if (raiz.getEsq().getAlte() > raiz.getEsq().getAltd()) {
                        raiz.setAlte(raiz.getEsq().getAlte() + 1);
                    } else {
                        raiz.setAlte(raiz.getEsq().getAltd() + 1);
                    }
                } else {
                    inserirNaArvore(raiz.getDir(), novoEvento);
                    if (raiz.getDir().getAlte() > raiz.getDir().getAltd()) {
                        raiz.setAltd(raiz.getDir().getAlte() + 1);
                    } else {
                        raiz.setAltd(raiz.getDir().getAltd() + 1);
                    }
                }
            }

            balance(raiz);
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


    public void balance(Nodo raiz) {
        int balanceFactor = raiz.getAltd() - raiz.getAlte();
        if (balanceFactor == 2) {
            if (raiz.getDir().getAltd() >= raiz.getDir().getAlte()) {
                rotateLeft(raiz);
            } else {
                rotateRight(raiz.getDir());
                rotateLeft(raiz);
            }
        } else if (balanceFactor == -2) {
            if (raiz.getEsq().getAlte() <= raiz.getEsq().getAltd()) {
                 rotateRight(raiz);
            } else {
                rotateLeft(raiz.getEsq());
                 rotateRight(raiz);
            }
        }
    }

    public void rotateLeft(Nodo aux) {
        Nodo aux1 = aux.getDir();
        aux.setDir(aux1.getEsq());
        aux1.setEsq(aux);
        updateHeight(aux);
        updateHeight(aux1);
    }

    public void rotateRight(Nodo aux) {
        Nodo aux1 = aux.getEsq();
        aux.setEsq(aux1.getDir());
        aux1.setDir(aux);
        updateHeight(aux);
        updateHeight(aux1);
    }

    private void updateHeight(Nodo node) {
        if (node.getEsq() == null) {
            node.setAlte(0);
        } else {
            if (node.getEsq().getAlte() > node.getEsq().getAltd()) {
                node.setAlte(node.getEsq().getAlte() + 1);
            } else {
                node.setAlte(node.getEsq().getAltd() + 1);
            }
        }

        if (node.getDir() == null) {
            node.setAltd(0);
        } else {
            if (node.getDir().getAlte() > node.getDir().getAltd()) {
                node.setAltd(node.getDir().getAlte() + 1);
            } else {
                node.setAltd(node.getDir().getAltd() + 1);
            }
        }
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