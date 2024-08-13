package Entities.registry;
// codigo do professor Tiago, ainda sem alterações

public class LinkedListStructure 
{
    
    private Nodo primeiro = null;
    private Nodo ultimo = null;
    private int qtd;

    Nodo x;
    /**
     * @return the qtd
     */
    public int getQtd() {
        return qtd;
    }

    /**
     * @param qtd the qtd to set
     */
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }


   

    public LinkedListStructure()
    {
        setQtd(0);
    }
            
    
    // ------------------------------- inserirLista
    public void inserirLista(int valor)
    {
        Nodo aux = new Nodo();
        Nodo tmp;
        aux.dado = valor;
        setQtd(getQtd() + 1);
        
        if (getPrimeiro() == null) 
        {
            primeiro = aux;
            ultimo = aux;
        }
        else 
        {
            ultimo.elo = aux;
            ultimo = aux;
        }      
    }
    
    public void inserirListaObj(Object valor){
        Nodo aux = new Nodo();
        Nodo tmp;
        aux.obj = valor;
        setQtd(getQtd() + 1);
        
        if (getPrimeiro() == null) 
        {
            primeiro = aux;
            ultimo = aux;
        }
        else 
        {
//            tmp = primeiro;
//            aux.elo = tmp;
//            primeiro = aux;
            
            ultimo.elo = aux;
            ultimo = aux;
        }   
    }
    public void inserirListaInicio(int valor)
    {
        Nodo novo = new Nodo();
        Nodo tmp;
        novo.dado = valor;
        setQtd(getQtd() + 1);
        
        if (getPrimeiro() == null) 
        {
            primeiro = novo;
            ultimo = novo;
        }
        else 
        {
            novo.elo = primeiro;
            primeiro = novo;
        }      
    }
    
    public void inserirListaOrdem(int valor)
    {
        Nodo aux = new Nodo();
        aux.dado = valor;
        
        Nodo anterior = null;
        Nodo atual = getPrimeiro();
        
        while (atual != null && aux.dado > atual.dado)
        {
            anterior = atual;
            atual = anterior.elo;            
        }
        
        if(anterior == null)
        {
            primeiro = aux;
            primeiro.elo = atual;
        }
        else
        {
            anterior.elo = aux;
            aux.elo = atual;
        }      
    }
    
    public void inserirListaOrdem1(int valor)
    {
        
        if(qtd == 0){
            inserirListaInicio(valor);                
        }
        else if(primeiro.dado > valor){
            inserirListaInicio(valor);            
        }
        else if(ultimo.dado < valor){
            inserirListaFim(valor);            
        }else{
                Nodo ant = null;
                Nodo aux = primeiro;
                while(aux != null)
                {
                    if(aux.dado < valor){
                        ant = aux;
                        aux = aux.elo;                        
                    }
                    else{
                        Nodo novo = new Nodo();
                        novo.dado = valor;
                           
                        ant.elo = novo;
                        novo.elo = aux;
                        
                        break;
                    }
                }
            }
    }
    
    public void inserirListaOrdemRec(int valor, Nodo anterior, Nodo atual)
    {
        if (atual != null && valor > atual.dado)
        {
            if(anterior != null)
                inserirListaOrdemRec(valor, atual, anterior.elo);
        }
        else{
            Nodo aux = new Nodo();
            aux.dado = valor;
            
            if(anterior == null)
            {
                primeiro = aux;
                primeiro.elo = atual;
            }
            else
            {
                anterior.elo = aux;
                aux.elo = atual;
            }    
        }
        
          
    }
    
    // ------------------------------- removerLista
    public int removerLista() 
    {        
        int dado;
        Nodo aux = getPrimeiro();
        
        if (aux != null) 
        {
            dado = getPrimeiro().dado;
            primeiro = getPrimeiro().elo;
            return(dado);
        }
        else
        return(0);
    }
    // ------------------------------- imprimirLista
    public void imprimirLista() 
    {
        Nodo aux = getPrimeiro();
        
        System.out.print("Lista Encadeada Simples: ");
        if (aux != null) 
        {
            while(aux != null) 
            {
                System.out.print(aux.dado + " ");
                aux = aux.elo;
            }
        }
        else 
        {    
            System.out.print("Vazia");
        }
         
        System.out.println();
    }
    
    
    public void imprimirListaRecAsc(Nodo aux) 
    {
        if (aux != null) 
        {
                System.out.print(aux.dado + " ");
                imprimirListaRecAsc(aux.elo);              
        }      
    }
    
    public void imprimirListaRecDesc(Nodo aux) 
    {
        if (aux != null) 
        {                
                imprimirListaRecDesc(aux.elo);    
                System.out.print(aux.dado + " ");
        }      
    }
    
    public Integer busca(int valor)
    {
        Nodo aux = getPrimeiro();
        Integer retorno = null;
        
        while(aux.dado != valor) 
        {
            aux = aux.elo;
            retorno = aux.dado;
        }
        
        return retorno;
    
    }
    
    public void remover(int valor)
    {
        Nodo anterior = null;
        Nodo atual = getPrimeiro();
        
        while (atual != null && valor != atual.dado)
        {
            anterior = atual;
            atual = anterior.elo;            
        }
        
        if(atual == null)
        {
            System.out.println("Lista vazia ou elemento não encontrado");
        }
        else
        {
            if(anterior != null){
                anterior.elo = atual.elo;   
            }
            else{
                primeiro = atual.elo;
            }
        }      
    }
    
    public void liberarLista()
    {
        Nodo aux = getPrimeiro();
        
        while(aux != null) 
        {
            aux = aux.elo;            
            primeiro = aux;
        }
            
        
    
    }
    
    
    public LinkedListStructure concatenaLista(LinkedListStructure l1, LinkedListStructure l2)
    {
        LinkedListStructure aux = new LinkedListStructure();
        
        if(l1 != null && l2 != null)
        {
            Nodo n = l1.primeiro;
            while(n != null)
            {
                aux.inserirLista(n.dado);
                n = n.elo;
            }
            
            n = l2.primeiro;
            while(n != null)
            {
                aux.inserirLista(n.dado);
                n = n.elo;
            }
        }
        
        return aux;
    
    }
    
    public LinkedListStructure concatenaListaRec(LinkedListStructure aux, Nodo n1, Nodo n2)
    {
            
            if(n1 != null)
            {
                LinkedListStructure l = concatenaListaRec(aux, n1.elo, n2);
                aux.inserirLista(n1.dado);
                
                return l;
            }
            else if(n2 != null)
            {
                
                LinkedListStructure l = concatenaListaRec(aux, n1, n2.elo);
                aux.inserirLista(n2.dado);
                
                return l;
            }
            else
            {
                return aux;
            }
        
        
            
    
    }
    
    public void ordernarLista()
    {         
        //o que há de diferente nesse método da bolha?
         Nodo ant = this.getPrimeiro();
         Nodo atual;
         
        while(ant != null) {

            atual = ant.elo;
            
            while(atual != null) {       
                
                if(ant.dado > atual.dado) {                        
                    trocaValores(ant, atual);                                            
                }
                
                atual = atual.elo;
            }                
            ant = ant.elo;               
        }
    }
    
    public void ordernarListaReferencia()
    {         
        boolean changed = true;
        Nodo p, q;
        Nodo top = new Nodo();
        top.elo = primeiro;
        
        while( changed ) {
            changed = false;
            q = top;            
            p = top.elo;
            while( p.elo != null ) {
                /* push bigger items down */
                if( p.dado > p.elo.dado ) {
                    q.elo = trocaReferencia( p, p.elo );
                    changed = true;
                }
                q = p;
                if( p.elo != null )
                    p = p.elo;
            }
        }        
        primeiro = top.elo;
    }
    
    public Nodo ordenaRecursivo(Nodo n)
    {   
        if( n == null ) return null;
        n.elo = ordenaRecursivo(n.elo);
        
        if( n.elo != null && n.dado > n.elo.dado ) {
            n = move(n);
        }
        return n;

    }
    
    public void ordenarRec()
    {
        Nodo top = new Nodo();
        top.elo = primeiro;
        
        ordenaRecursivo(top);
        
        primeiro = top.elo;
    }
    
    private Nodo move( Nodo x )
    {
        Nodo n, p, ret;

        p = x;
        n = x.elo;
        ret = n;
        
        while( n != null && x.dado > n.dado ) {
            p = n;
            n =  n.elo;
        }
        /* we now move the top item between p and n */
        p.elo = x;
        x.elo = n;
        
        return ret;
    }
    private Nodo trocaReferencia( Nodo l1, Nodo l2 )
    {
        l1.elo = l2.elo;
        l2.elo = l1;
        return l2;
    }
    
    private void inverteElementos (Nodo e, Nodo ant){
        if(e.elo!=null)
            inverteElementos(e.elo, e);
        e.elo = ant;
    }

    public void inverter (){
        inverteElementos(primeiro, null);

        //Inverte inicio com fim
        Nodo aux = primeiro;
        primeiro = ultimo;
        ultimo = aux;
    }
        /**
     * @return the primeiro
     */
    public Nodo getPrimeiro() {
        return primeiro;
    }

    /**
     * @return the ultimo
     */
    public Nodo getUltimo() {
        return ultimo;
    }
    
    private void trocaValores(Nodo ant, Nodo atual)
    {
        int valtemp = ant.dado;
        ant.dado = atual.dado;
        atual.dado = valtemp;
    }
    
    private void trocaReferencias(Nodo ant, Nodo atual, Nodo antant)
    {

        if(ant == primeiro)
        {
            primeiro = atual;
            Nodo temp = atual.elo;
            atual.elo = ant;
            ant.elo = temp;
        }
        else if(atual == ultimo)
        {
            ultimo = ant;
            antant.elo = atual;
            atual.elo = ant;
            ant.elo = null;
        }
        else
        {
            antant.elo = atual;
            Nodo temp = atual.elo;
            atual.elo = ant;
            ant.elo = temp;
        }
                        
    }
    
    public void primeiroUltimo(){
    
       
        ultimo.elo = primeiro;
        Nodo aux = primeiro;
        primeiro = primeiro.elo;
        aux.elo = null;
        ultimo = aux;
    
    }
    
    
     private void percorrer (Nodo e, Nodo ant, int i){
        if(e.elo != null)
            percorrer(e.elo, e, i+1);
        
        if(i >= this.getQtd() / 2){
            System.out.print((e.dado + x.dado)  + " ");
            x = x.elo;
        }
    }

    public void somaElementos (){
        this.x = primeiro;
        percorrer(primeiro, null, 0);
    }

    private void inserirListaFim(int valor) {
        Nodo aux = new Nodo();
        aux.dado = valor;
        
        setQtd(getQtd() + 1);
        
        if (getPrimeiro() == null) 
        {
            primeiro = aux;
            ultimo = aux;
        }
        else 
        {
            ultimo.elo = aux;
            ultimo = aux;
        }      
        
    }
}

