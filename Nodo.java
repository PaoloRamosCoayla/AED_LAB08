package Lab8;

public class Nodo<T> {
	private T data;
	private Nodo<T> next;
	
	Nodo(T data){
		this(data,null);
	}
	Nodo(T d, Nodo<T> n){
		this.data = d;
		this.next = n;
	}
	
	public T getData() {return this.data;}
	public Nodo<T> getNext(){return this.next;}
	public void setData(T data) {this.data = data;}
	public void setNext(Nodo<T> n) {this.next = n;}
	
	@Override
	public String toString() {
		return " "+getData();
	}
}
