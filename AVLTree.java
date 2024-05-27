package Lab8;

import java.util.LinkedList;

public class AVLTree <E extends Comparable<E>> extends BSTree<E>{
    class NodeAVL extends Node {
        public int bf;
        public NodeAVL(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
        public String toString() {
            return "Dato: "+ data + " Factor de equilibrio:" + bf ;
        }
    }

    private boolean height;

    public void insert(E x) throws ItemDuplicated {
        this.height = false;
        this.root = insert(x, (NodeAVL)this.root);
    }

    protected Node insert(E x, NodeAVL node)throws ItemDuplicated{ 
        NodeAVL fat = node;
        if (node == null){ 
            this.height = true;
            fat = new NodeAVL (x);
        }
        else {
            int resC = node.data.compareTo(x);
            if(resC == 0)throw new ItemDuplicated(x+" ya se encuentra en el arbol...");
            if(resC < 0) {
                fat.right = insert(x, (NodeAVL)node.right);
                if(this.height) switch(fat.bf) {
                    case -1: 
                        fat.bf = 0;
                        this.height = false;
                        break;
                    case 0: 
                        fat.bf = 1;
                        this.height = true;
                        break;
                    case 1: //bf = 2
                        fat = balanceToLeft(fat);
                        this.height = false;
                        break;
                }
            }
            else {
                fat.left = insert(x, (NodeAVL) node.left);
                if (this.height) switch (fat.bf) {
                    case 1:
                        fat.bf = 0;
                        this.height = false;
                        break;
                    case 0:
                        fat.bf = -1;
                        this.height = true;
                        break;
                    case -1: // bf = -2, desequilibrio hacia la izquierda
                        fat = balanceToRight(fat);
                        this.height = false;
                        break;
                }
            }
        }
        return fat;
    }

    private NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.right;
        if (hijo == null) return node;
    
        switch (hijo.bf) {
            case 1:
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSL(node);
                break;
            case -1:
                NodeAVL nieto = (NodeAVL) hijo.left;
                if (nieto == null) break;
    
                switch (nieto.bf) {
                    case -1:
                        node.bf = 0;
                        hijo.bf = 1;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case 1:
                        node.bf = 1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.right = rotateSR(hijo);
                node = rotateSL(node);
                break;
        }
        return node;
    }
    
    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.left;
        if (hijo == null) return node;
    
        switch (hijo.bf) {
            case -1:
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSR(node);
                break;
            case 1:
                NodeAVL nieto = (NodeAVL) hijo.right;
                if (nieto == null) break;
    
                switch (nieto.bf) {
                    case 1:
                        node.bf = 0;
                        hijo.bf = -1;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case -1:
                        node.bf = -1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.left = rotateSL(hijo);
                node = rotateSR(node);
                break;
        }
        return node;
    }

    private NodeAVL rotateSL(NodeAVL node){
        NodeAVL p = (NodeAVL)node.right;
        node.right = p.left;
        p.left = node;
        node = p;
        return node;
    }

    private NodeAVL rotateSR(NodeAVL node){
        NodeAVL p = (NodeAVL) node.left;
        node.left = p.right;
        p.right = node; 
        node = p;
        return node;
    }

    public void printTree() {
        printTree((NodeAVL) this.root, "", true);
    }

    private void printTree(NodeAVL node, String indent, boolean last) {
        if (node != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R---- ");
                indent += "   ";
            } else {
                System.out.print("L---- ");
                indent += "|  ";
            }
            System.out.println(node.data + " (BF: " + node.bf + ")");
            printTree((NodeAVL) node.left, indent, false);
            printTree((NodeAVL) node.right, indent, true);
        }
    }

    // EJERCICIO 1
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

    // EJERCICO 2
    
    public void remove(E x) throws ItemNoFound {
        this.height = false;
        this.root = remove(x, (NodeAVL) this.root);
    }
    
    protected Node remove(E x, NodeAVL node) throws ItemNoFound {
        if (node == null) {
            throw new ItemNoFound();
        }
    
        int resC = node.data.compareTo(x);
        if (resC > 0) {
            node.left = remove(x, (NodeAVL) node.left);
            if (this.height) {
                node = balanceToLeft(node);
                this.height = node.bf != 0;
            }
        } else if (resC < 0) {
            node.right = remove(x, (NodeAVL) node.right);
            if (this.height) {
                node = balanceToRight(node);
                this.height = node.bf != 0;
            }
        } else { // Node found
            if (node.left == null || node.right == null) {
                node = (NodeAVL) (node.left != null ? node.left : node.right);
                this.height = true;
            } else {
                NodeAVL successor = (NodeAVL) node.right;
                while (successor.left != null) {
                    successor = (NodeAVL) successor.left;
                }
                node.data = successor.data;
                node.right = remove(successor.data, (NodeAVL) node.right);
                if (this.height) {
                    node = balanceToRight(node);
                    this.height = node.bf != 0;
                }
            }
        }
        return node;
    }

    // EJERCICIO 3
    
    public void niveles() {
        if (isEmpty()) {
            System.out.println("El árbol está vacío");
            return;
        }

        QueueLink<NodeAVL> queue = new QueueLink<>();
        queue.enqueue((NodeAVL) root);

        while (!queue.isEmpty()) {
            NodeAVL node = queue.dequeue();
            System.out.print(node.data + " ");

            if (node.left != null) {
                queue.enqueue((NodeAVL) node.left);
            }

            if (node.right != null) {
                queue.enqueue((NodeAVL) node.right);
            }
        }
    }
} 