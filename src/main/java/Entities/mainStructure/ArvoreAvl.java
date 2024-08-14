//adaptado do material fornecido do professor

package Entities.mainStructure;

import Entities.Event;
import Entities.Vehicle;
import Entities.registry.HashRegistry;
import Entities.registry.VehicleRegistry;

public class ArvoreAvl {
    private HashRegistry peopleHashRegistry;
    private VehicleRegistry vehicleRegistry;
    private Nodo raiz;

    public ArvoreAvl(){
        this.peopleHashRegistry=new HashRegistry(5);
        this.vehicleRegistry=new VehicleRegistry();
    }
    public void progressiveSwipe(){
        Nodo aux = this.raiz;

        if(aux==null){
        }else{
            Event evento = aux.getEvent();
            String plate = aux.getEvent().getCarPlate();
            if()
        }

    }

    // parametro Nodo raiz pois o algoritmo de varredura funciona a partir da raiz da arvore
    public Nodo inserir(Nodo raiz, Event novoEvento) {
        // o objeto novo é um objeto auxiliar
        Nodo novo;
        if (raiz == null) {
            novo = new Nodo();
            novo.setEvent(novoEvento);
            novo.setAltd(0);
            novo.setAlte(0);
            novo.setEsq(null);
            novo.setDir(null);
            raiz = novo;
        } else if (novoEvento.getHoraEvento().isBefore(raiz.getEvent().getHoraEvento())) {
            raiz.setEsq(inserir(raiz.getEsq(), novoEvento));
            if (raiz.getEsq().getAltd() > raiz.getEsq().getAlte()) {
                raiz.setAlte(raiz.getEsq().getAlte());
            } else {
                raiz.setAlte(raiz.getEsq().getAltd() + 1);
            }

        } else {
            raiz.setDir(inserir(raiz.getDir(), novoEvento));
            if (raiz.getDir().getAltd() > raiz.getDir().getAlte()) {
                raiz.setAltd(raiz.getDir().getAltd() + 1);
            } else {
                raiz.setAltd(raiz.getDir().getAlte() + 1);
            }
        }

        raiz = balance(raiz);

        return raiz;
    }

    //novamente, o parametro da função é a raiz, pois o método recursivo possui varredura inerente
    public Nodo balance(Nodo raiz) {
        int d, df;
        d = raiz.getAltd() - raiz.getAlte();
        if (d == 2) {
            df = raiz.getDir().getAltd() - raiz.getDir().getAlte();
            if (df >= 0) {
                raiz = rotacao_esquerda(raiz);
            } else {
                raiz.setDir(rotacao_direita(raiz.getDir()));
                raiz = rotacao_esquerda(raiz);
            }
        } else if (d == -2) {
            df = raiz.getEsq().getAltd() - raiz.getEsq().getAlte();
            if (df <= 0) {
                raiz = rotacao_direita(raiz);
            } else {
                raiz.setEsq(rotacao_esquerda(raiz.getEsq()));
                raiz = rotacao_direita(raiz);
            }
        }
        return raiz;
    }

    public Nodo rotacao_esquerda(Nodo aux) {

        Nodo aux1, aux2;
        aux1 = aux.getDir();
        aux2 = aux1.getEsq();
        aux.setDir(aux2);
        aux1.setEsq(aux);

        if (aux.getDir() == null) {
            aux.setAltd(0);
        } else if (aux.getDir().getAlte() > aux.getDir().getAltd()) {
            aux.setAltd(aux.getDir().getAlte() + 1);
        } else {
            aux.setAltd(aux.getDir().getAltd() + 1);
        }

        if (aux1.getEsq().getAlte() > aux1.getEsq().getAltd()) {
            aux1.setAlte(aux1.getEsq().getAlte() + 1);
        } else {
            aux1.setAlte(aux1.getEsq().getAltd() + 1);
        }
        return aux1;
    }

    public Nodo rotacao_direita(Nodo aux) {
        Nodo aux1, aux2;
        aux1 = aux.getEsq();
        aux2 = aux1.getDir();
        aux.setEsq(aux2);
        aux1.setDir(aux);
        if (aux.getEsq() == null) {
            aux.setAlte(0);
        } else if (aux.getEsq().getAlte() > aux.getEsq().getAltd()) {
            aux.setAlte(aux.getEsq().getAlte() + 1);
        } else {
            aux.setAlte(aux.getEsq().getAltd() + 1);
        }

        if (aux1.getDir().getAlte() > aux1.getDir().getAltd()) {
            aux1.setAltd(aux1.getDir().getAlte() + 1);
        } else {
            aux1.setAltd(aux1.getDir().getAltd() + 1);
        }
        return aux1;
    }

    public void exibiremordem(Nodo aux) {
        if (aux != null) {
            exibiremordem(aux.getEsq());
            System.out.print(aux.getEvent() + "  ");
            exibiremordem(aux.getDir());
        }
    }

    public void exibirpreordem(Nodo aux) {
        if (aux != null) {
            System.out.print(aux.getEvent() + ", ");
            exibirpreordem(aux.getEsq());
            exibirpreordem(aux.getDir());
        }
    }

    public void exibirposordem(Nodo aux) {
        if (aux != null) {
            exibirposordem(aux.getEsq());
            exibirposordem(aux.getDir());
            System.out.print(aux.getEvent() + ", ");
        }
    }

}
