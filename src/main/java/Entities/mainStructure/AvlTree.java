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
        this.raiz = new Nodo();
    }

    private Nodo createNewNode(Event novoEvento) {
        Nodo novo = new Nodo();
        novo.setEvent(novoEvento);
        novo.setAltd(0);
        novo.setAlte(0);
        novo.setEsq(null);
        novo.setDir(null);
        return novo;
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
    public void progressiveSwipe(LocationRegistry locationRegistry, VehicleRegistry vehicleList, HashRegistry hashRegistry, VisitHistory visitList, Nodo raizAtual) {
        swipeCounter++;
        try {
            System.out.println(swipeCounter);
            if (raizAtual == null) {
                return;
            } else if (raizAtual.getEvent() == null) {
                return;
            }
            for (Visit visit : visitList.getVisitHistory()) {
                for (Event event : visit.getEventRoute()) {
                    if (raizAtual.getEvent() == event) {
                        progressiveSwipe(locationRegistry, vehicleList, hashRegistry, visitList, raizAtual.getEsq());
                        progressiveSwipe(locationRegistry, vehicleList, hashRegistry, visitList, raizAtual.getDir());
                    }
                }
            }
            if (raizAtual.getEvent().getCamera().getId() == 1 || raizAtual.getEvent().getCamera().getId() == 6) {
                if (raizAtual.getEvent().getCamera().getId() == 1) {
                    Event event = raizAtual.getEvent();
                    Vehicle newVehicle = vehicleList.findVehicleByPlate(event.getCarPlate());
                    if (newVehicle == null) {
                        Guest newGuest = Guest.newGuest(null);
                        newVehicle = new Vehicle(raizAtual.getEvent().getCarPlate(), newGuest);
                        newGuest.setVehicle(newVehicle);
                        vehicleList.addVehicleToRegistry(newVehicle);
                        Visit newVisit = new Visit(visitList.getVisitHistory(), newGuest, newVehicle, raizAtual.getEvent().getHoraEvento());
                        visitList.addToHistory(newVisit);
                        this.hashRegistry.addPerson(newGuest);
                    } else {
                        if (newVehicle.getOwner() instanceof Employee) {
                            Visit newEmployeeVisit = new Visit(visitList.getVisitHistory(), newVehicle.getOwner(), newVehicle, raizAtual.getEvent().getHoraEvento());
                            visitList.addToHistory(newEmployeeVisit);
                        } else if (newVehicle.getOwner() instanceof Guest) {
                            Visit newGuestVisit = new Visit(visitList.getVisitHistory(), newVehicle.getOwner(), newVehicle, raizAtual.getEvent().getHoraEvento());
                            visitList.addToHistory(newGuestVisit);
                        }
                    }
                } else if (raizAtual.getEvent().getCamera().getId() == 6) {
                    Event event = raizAtual.getEvent();
                    Visit visitToBeClosed = visitList.getVisitByPlate(raizAtual.getEvent().getCarPlate());
                    visitToBeClosed.addToRoute(raizAtual.getEvent());
                    visitToBeClosed.setEndingTime(raizAtual.getEvent().getHoraEvento());
                }
            } else if (!(raizAtual.getEvent().getCamera().getId() == 1 || raizAtual.getEvent().getCamera().getId() == 6)) {
                Event event = raizAtual.getEvent();
                Visit visitToBeAddedTo = visitList.getVisitByPlate(raizAtual.getEvent().getCarPlate());
                visitToBeAddedTo.addToRoute(raizAtual.getEvent());
            }
            while (raizAtual.getEsq() != null || raizAtual.getDir() != null) {
                progressiveSwipe(locationRegistry, vehicleList, hashRegistry, visitList, raizAtual.getEsq());
                progressiveSwipe(locationRegistry, vehicleList, hashRegistry, visitList, raizAtual.getDir());
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
            Event novoEvento = new Event().parseEventFromString(this.locationRegistry, novoEventoEmString);
            Nodo novoNodo = createNewNode(novoEvento);
            inserir(novoNodo, novoEvento);
            System.out.println("Novo nodo inserido na árvore: " + novoEvento);
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

    public Nodo inserir(Nodo raiz, Event novoEvento) {
    try {
        if (raiz == null) {
            raiz = createNewNode(novoEvento);
        } else {
            if (novoEvento.getHoraEvento().isBefore(raiz.getEvent().getHoraEvento())) {
                raiz.setEsq(inserir(raiz.getEsq(), novoEvento));
                if (raiz.getEsq().getAlte() > raiz.getEsq().getAltd()) {
                    raiz.setAlte(raiz.getEsq().getAlte() + 1);
                } else {
                    raiz.setAlte(raiz.getEsq().getAltd() + 1);
                }
            } else {
                raiz.setDir(inserir(raiz.getDir(), novoEvento));
                if (raiz.getDir().getAlte() > raiz.getDir().getAltd()) {
                    raiz.setAltd(raiz.getDir().getAlte() + 1);
                } else {
                    raiz.setAltd(raiz.getDir().getAltd() + 1);
                }
            }
        }

        return balance(raiz);
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
    return null;
}


    public Nodo balance(Nodo raiz) {
        int balanceFactor = raiz.getAltd() - raiz.getAlte();
        if (balanceFactor == 2) {
            if (raiz.getDir().getAltd() >= raiz.getDir().getAlte()) {
                return rotateLeft(raiz);
            } else {
                raiz.setDir(rotateRight(raiz.getDir()));
                return rotateLeft(raiz);
            }
        } else if (balanceFactor == -2) {
            if (raiz.getEsq().getAlte() <= raiz.getEsq().getAltd()) {
                return rotateRight(raiz);
            } else {
                raiz.setEsq(rotateLeft(raiz.getEsq()));
                return rotateRight(raiz);
            }
        }
        return raiz;
    }

    public Nodo rotateLeft(Nodo aux) {
        Nodo aux1 = aux.getDir();
        aux.setDir(aux1.getEsq());
        aux1.setEsq(aux);
        updateHeight(aux);
        updateHeight(aux1);
        return aux1;
    }

    public Nodo rotateRight(Nodo aux) {
        Nodo aux1 = aux.getEsq();
        aux.setEsq(aux1.getDir());
        aux1.setDir(aux);
        updateHeight(aux);
        updateHeight(aux1);
        return aux1;
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