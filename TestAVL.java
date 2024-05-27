package Lab8;

public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();
        
        try {
            // Caso 1: Rotación Simple a la Derecha (RSR)
            avl.insert(30);
            avl.printTree();
            System.out.println();
            avl.insert(20);
            avl.insert(10);
            avl.printTree();
            System.out.println();

            // Caso 2: Rotación Simple a la Izquierda (RSL)
            avl.insert(40);
            avl.insert(50);
            avl.printTree();
            System.out.println();

            // Caso 3: Rotación Doble Derecha-Izquierda (RDR)
            avl.insert(45);
            avl.insert(47);
            avl.insert(46);
            avl.printTree();
            System.out.println();

            // Caso 4: Rotación Doble Izquierda-Derecha (RDL)
            avl.insert(5);
            avl.insert(15);
            avl.insert(12);
            avl.printTree();
            System.out.println();

            // Caso 5: Rotación Simple a la Derecha (RSR)
            avl.insert(2);
            avl.printTree();
            System.out.println();

            // Caso 6: Rotación Simple a la Izquierda (RSL)
            avl.insert(60);
            avl.insert(70);
            avl.printTree();
            System.out.println();

            // Caso 7: Rotación Doble Derecha-Izquierda (RDR)
            avl.insert(65);
            avl.insert(67);
            avl.insert(66);
            avl.printTree();
            System.out.println();

            // Caso 8: Rotación Doble Izquierda-Derecha (RDL)
            avl.insert(1);
            avl.insert(4);
            avl.insert(3);
            avl.printTree();
            System.out.println();

            avl.printTree();

            System.out.println();
            System.out.println();

            //EJERCICIO 1

            BSTree<Integer> bst = new BSTree<>();
            bst.insert(20);
            bst.insert(10);
            bst.insert(30);
            bst.insert(5);
            bst.insert(15);
            bst.insert(25);
            bst.insert(35);
            bst.insert(40);
            bst.insert(45);
    
            System.out.println("Altura del árbol BST: " + bst.height());

            AVLTree<Integer> avl1 = new AVLTree<>();
            avl1.insert(20);
            avl1.insert(10);
            avl1.insert(30);
            avl1.insert(5);
            avl1.insert(15);
            avl1.insert(25);
            avl1.insert(35);
            avl1.insert(40);
            avl1.insert(45);

            System.out.println("Altura del árbol AVL: " + avl1.height());

            bst.search(35);

            avl1.search(35);

            //EJERCICIO 2

            // Pruebas de eliminación
            System.out.println("---- Eliminar nodos ----");

            avl.printTree();
            System.out.println();

            avl.remove(3);
            avl.printTree();
            System.out.println();

            avl.remove(4);
            avl.printTree();
            System.out.println();

            avl.remove(10);
            avl.printTree();
            System.out.println();

            avl.remove(30);
            avl.printTree();
            System.out.println();

            avl.remove(70);
            avl.printTree();
            System.out.println();

            avl.remove(2);
            avl.printTree();
            System.out.println();

            avl.remove(15);
            avl.printTree();
            System.out.println();

            avl.remove(60);
            avl.printTree();
            System.out.println();

            avl.remove(65);
            avl.printTree();
            System.out.println();

            //EJERCICIO 3

            AVLTree<Integer> avl2 = new AVLTree<>();

            avl2.insert(39);
            avl2.insert(27);
            avl2.insert(50);
            avl2.insert(18);
            avl2.insert(35);
            avl2.insert(46);
            avl2.insert(87);
            avl2.insert(7);
            avl2.insert(24);

            avl2.niveles();

        } catch (ItemDuplicated e) {
            System.out.println(e.getMessage());
        } catch (ItemNoFound e) {
            System.out.println( e.getMessage());
        }
    }
}