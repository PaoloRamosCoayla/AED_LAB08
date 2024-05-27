package Lab8;

public class QueueLink <E>{
    private Nodo<E> last;
    protected Nodo<E> first;
    public QueueLink(){ 
        this.first = null;
        this.last = null;
    }
    public void enqueue(E x){ 
        Nodo<E> aux = new Nodo<E>(x);
        if (this.isEmpty()){
            this.first = aux;
            this.last = first;
        }
        else{
            this.last.setNext(aux);
            this.last = aux;
        }
    }
    public E dequeue() {
        if (isEmpty()) {
            System.out.println("esta vacio");
        }
        E result = first.getData();
        first = first.getNext();
        if (first == null) {
            last = null;
        }
        return result;
    }
    public E back() {
        if (isEmpty()) {
            System.out.println("esta vacio");
        }
        return last.getData();
    }
    public E front() {
        if (isEmpty()) {
            System.out.println("esta vacio");;
        }
        return this.first.getData();
    }
    public boolean isEmpty() {
        return this.first == null;
    }
    
}
