package Entities.mainStructure;

import Entities.Event;

public class Nodo 
{
        private Event event;
        private int num, altd, alte;
        private Nodo dir, esq;   

        
    public Event getEvent(){
        return this.event;
    }
    public void setEvent(Event event){
        this.event=event;
    }
    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return the altd
     */
    public int getAltd() {
        return altd;
    }

    /**
     * @param altd the altd to set
     */
    public void setAltd(int altd) {
        this.altd = altd;
    }

    /**
     * @return the alte
     */
    public int getAlte() {
        return alte;
    }

    /**
     * @param alte the alte to set
     */
    public void setAlte(int alte) {
        this.alte = alte;
    }

    /**
     * @return the dir
     */
    public Nodo getDir() {
        return dir;
    }

    /**
     * @param dir the dir to set
     */
    public void setDir(Nodo dir) {
        this.dir = dir;
    }

    /**
     * @return the esq
     */
    public Nodo getEsq() {
        return esq;
    }

    /**
     * @param esq the esq to set
     */
    public void setEsq(Nodo esq) {
        this.esq = esq;
    }
        
        
}
