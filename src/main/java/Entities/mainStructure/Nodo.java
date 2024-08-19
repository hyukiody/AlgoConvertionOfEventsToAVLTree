package Entities.mainStructure;

import Entities.Event;

public class Nodo {
    private Event event;
    private int num, altd, alte;
    private Nodo dir, esq;

    public Nodo() {
        this.event = new Event();
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getAltd() {
        return this.altd;
    }

    public void setAltd(int altd) {
        this.altd = altd;
    }

    public int getAlte() {
        return this.alte;
    }

    public void setAlte(int alte) {
        this.alte = alte;
    }

    public Nodo getDir() {
        return this.dir;
    }

    public void setDir(Nodo dir) {
        this.dir = dir;
    }

    public Nodo getEsq() {
        return this.esq;
    }

    public void setEsq(Nodo esq) {
        this.esq = esq;
    }

    @Override
    public String toString() {
        return this.getEvent().toString();
    }
}