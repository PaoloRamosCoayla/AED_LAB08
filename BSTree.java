package Lab8;

public class BSTree <E extends Comparable<E>>{
    protected class Node implements Comparable<Node>{
        protected E data;
        protected Node left, right;

        public Node(E data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
        public Node(){
            this.data = null;
            this.left = null;
            this.right = null;
        }

        public Node(E data,Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
        public E getData() {
            return data;
        }
        
        public int compareTo(Node nodo) {
            return this.data.compareTo(nodo.data);
        }
        
        public String toString(){
            return " "+getData()+" "+left+" "+right+" ";
        }
    }

    protected Node root;

    public BSTree(){
        this.root = null;
    }
    public boolean isEmpty(){ return this.root == null; }

    public void insert(E x) throws ItemDuplicated{
        Node nodito = new Node(x);

        if(isEmpty()){
            this.root = nodito;
        }
        else{
            Node current = root;
            Node ant = null;

            while (current != null) {
                ant = current;
                if(current.compareTo(nodito) > 0){
                    current = current.left;
                }
                else if(current.compareTo(nodito) == 0){
                    throw new ItemDuplicated("");
                }
                else{
                    current = current.right;
                }
            }
            if(ant.compareTo(nodito) > 0){
                ant.left = nodito;
            }
            else{
                ant.right = nodito;
            }
        }
    }

    public E search(E x) throws ItemNoFound{
        Node current = root;

        while (current != null) {
            int resp = current.data.compareTo(x);

            if (resp > 0) {
                current = current.left;
            } else if (resp < 0) {
                current = current.right;
            } else {
                return current.data;
            }
        }

        throw new ItemNoFound();
    }

    public void remove(E x) throws ItemNoFound{
        if(isEmpty()){
            System.out.println("El arbol no existe");
            return;
        }
        else{
            boolean isleftchild = true;
            Node p = null;
            Node c = root;
            while (c != null && c.data != x) {
                p = c;
                if(x.compareTo(c.data) > 0 ){
                    c = c.right;
                    isleftchild = false;
                }
                else{
                    c = c.left;
                    isleftchild = true;
                }
            }
            if(c == null){
                return;
            }
            if (c == root) {
                Node nodito = subBorrado(c);
                c.data = nodito.data;
                this.root = c;  
            }
            if(c.left == null && c.right == null){
                if(isleftchild){
                    p.left = null;
                }
                else{
                    p.right = null;
                }
            }
            if(c.left != null){
                if(isleftchild){
                    p.left = c.left;
                }
                else{
                    p.right = c.left;
                }                
            }
            if(c.left == null){
                if(isleftchild){
                    p.left = c.right;
                }
                else{
                    p.right = c.right;
                }
            }
            else{
                Node nodito = subBorrado(c);
                /*
                *c.data = nodito.data; 
                */
                
                nodito.left = c.left;
                nodito.right = c.right;

                if(isleftchild){
                    p.left = nodito;
                }
                else{
                    p.right = nodito;
                }
            }
        }
    }

    public Node subBorrado(Node nodito){
        Node p = nodito;
        Node c = nodito;
        c = c.right;
        if(c.left == null && c.right == null){
            return c;
        }
        while (c.left != null) {
            p = c;
            c = c.left;
        }
        p.left = c.right;
        return c;
    }

    public E minRemove() throws ItemNoFound{
        Node nodito = minRemoveReturn();
        return nodito.data;
    }

    private Node minRemoveReturn() throws ItemNoFound {
        if (isEmpty()){
            throw new ItemNoFound();
        }
        Node nodito = root;
        while (nodito.left != null){
            nodito = nodito.left;
        }
        remove(nodito.data);
        return nodito;
    }

    public int countNodes(int nivel) {
        if (isEmpty()) {
            return 0;
        }

        StackLink<Node> stack = new StackLink<>();
        stack.push(root);
        int count = 0;
    
        while (!stack.isEmpty() && nivel >= 0) {
            Node current = stack.pop();

            if (current.right != null) {
                if(nivel == 0){
                    count++;
                }else{
                    stack.push(current.right);
                }
            }

            if (current.left != null) {
                if(nivel == 0){
                    count++;
                }else{
                    stack.push(current.left);
                }
            }
            nivel --;
        }
    
        return count;
    }


    public int height() {
        return heightCont(root);
    }

    private int heightCont(Node nodo){
        if(nodo == null){
            return 0;
        }

        int alturaIzquierda = heightCont(nodo.left);
        int alturaDerecha = heightCont(nodo.right);

        if(alturaIzquierda > alturaDerecha){
            return alturaIzquierda + 1;
        } 
        else{
            return alturaDerecha + 1;
        }
    }

    // 2

    public int area(){
        return areaCont(root) * height();
    }
    private int areaCont(Node nodo){
        if(nodo == null) {
            return 0;
        }
        if(nodo.left == null && nodo.right == null){
            return 1;
        }
        return areaCont(nodo.left) + areaCont(nodo.right);
    }

    // 3

    public void iterativePreOrden() {
        if (isEmpty()) {
            System.out.println("El árbol está vacío");
            return;
        }

        StackLink<Node> stack = new StackLink<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                System.out.print(current.data + " ");
                stack.push(current);
                current = current.left;
            }
            if (!stack.isEmpty()) {
                current = stack.pop().right;
            }
        }
    }

    //EJERCICIO 4
    public int countNodesRecursivo(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 0;
        }
        return 1 + countNodesRecursivo(node.left) + countNodesRecursivo(node.right);
    }

    
    public String toString() {
        if (isEmpty()) {
            return "El árbol está vacío";
        }
        return root.toString();
    }
}