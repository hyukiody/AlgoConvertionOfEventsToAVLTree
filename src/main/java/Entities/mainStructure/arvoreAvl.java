package Entities.mainStructure;

import Entities.Event;

public class arvoreAvl {
    private Nodo createNewNode(Event novoEvento) {
        Nodo novo = new Nodo();
        novo.setEvent(novoEvento);
        novo.setAltd(0);
        novo.setAlte(0);
        novo.setEsq(null);
        novo.setDir(null);
        return novo;
    }

    public Nodo inserir(Nodo raiz, Event novoEvento) {
        if (raiz == null) {
            return createNewNode(novoEvento);
        }

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

        return balance(raiz);
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

    public void displayInOrder(Nodo aux) {
        if (aux != null) {
            displayInOrder(aux.getEsq());
            System.out.print(aux.getEvent() + "  ");
            displayInOrder(aux.getDir());
        }
    }

    public void displayPreOrder(Nodo aux) {
        if (aux != null) {
            System.out.print(aux.getEvent() + ", ");
            displayPreOrder(aux.getEsq());
            displayPreOrder(aux.getDir());
        }
    }

    public void displayPostOrder(Nodo aux) {
        if (aux != null) {
            displayPostOrder(aux.getEsq());
            displayPostOrder(aux.getDir());
            System.out.print(aux.getEvent() + ", ");
        }
    }
}