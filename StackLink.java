package Lab8;

public class StackLink<E> {
	private Nodo<E> top;
	
	public StackLink() {
		this.top = null;
	}
	
	public void push(E x) {
		Nodo<E> newNodo = new Nodo<>(x);
		newNodo.setNext(top);
		top = newNodo;
	}

	public E pop() {
        if (isEmpty()) {
            return null;
        } else {
            E data = top.getData();
            top = top.getNext();
            return data;
        }
    }

    public E top() {
        if (isEmpty()) {
            return null;
        }
        return top.getData();
    }

	public boolean isEmpty() {
		return top == null;
	}
	
	public String toString() {
	    String result = "[";
	    Nodo<E> current = top;
	    while (current != null) {
	        result += current.getData();
	        if (current.getNext() != null) {
	            result += ", ";
	        }
	        current = current.getNext();
	    }
	    result += "]";
	    return result;
	}
}
