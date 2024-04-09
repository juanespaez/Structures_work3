class QuickSort {
    Node head;

    static class Node {
        private String data;
        private Node next;
        private Node prev;

        Node(String value) {
            data = value;
            next = null;
            prev = null;
        }
    }

    // Metod para obtener el ultimo nodo de la lista
    Node lastNode(Node node) {
        while (node.next != null)
            node = node.next;
        return node;
    }

    // Metodo para agregar un nodo al inicio de la lista
    void push(String value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            return;
        }

        newNode.next = head;
        head.prev = newNode;
        newNode.prev = null;
        head = newNode;
    }

    // Metodo para comparar dos nodos
    boolean less(QuickSort.Node x, QuickSort.Node y) {
        // Se toma como menor o igual por practicidad para su uso en el metodo partition
        return x.data.compareTo(y.data) <= 0;
    }

    // METODO DE PARTICION
    Node partition(Node lo, Node hi) {
        // Pivote
        Node x = hi;

        Node i = lo.prev;

        for (Node j = lo; j != hi; j = j.next) {
            // Si el valor a la izquierda del pivote es menor o igual al del indice actual,
            // aumentamos la i y hacemos el intercambio de los nodos i y j,
            // cuando esto no pase, la j va a seguir el loop hasta hallar un valor
            // mas grande que el pivote e intercambiarlo con el indice i

            if (less(j, x)) {
                // Pasamos al siguiente nodo i
                if (i == null)
                    i = lo;
                else
                    i = i.next;

                // Intercambiamos los valores de los nodos i y j
                String temp = i.data;
                i.data = j.data;
                j.data = temp;
            }
        }

        // Cuando terminamos de hacer todos los intercambios necesarios, aumentamos la i
        if (i == null)
            i = lo;
        else
            i = i.next;

        // Intercambiamos el valor de i con el del pivote y retornamos i (que ahora es
        // el pivote en su posicion final en la lista)
        String temp = i.data;
        i.data = hi.data;
        hi.data = temp;
        return i;

        // Al retornar el pivote estamos retornando las dos listas (menores a la
        // izquierda y mayores a la derecha)
        // para la lista con los menores al pivote accedemos a i.prev
        // para la lista con los mayores al pivote accedemos a i.next
    }

    /** METODO RECURSIVO QUICKSORT */
    // !----------------- NOTA IMPORTANTE AL PROFESOR ----------------!
    // Como en este metodo estamos haciendo los intercambios de los valores de los
    // nodos y no de sus referencias (prev y next) realmente no es necesario hacer
    // concatenaciones, puesto que en ningun momento estamos moviendo nodos, solo
    // estamos cambiando sus valores.
    void quickSort(Node lo, Node hi) {
        if (hi != null && lo != hi && lo != hi.next) {
            Node temp = partition(lo, hi);
            quickSort(lo, temp.prev);
            quickSort(temp.next, hi);
        }
    }

    public void sort(Node node) {
        Node head = lastNode(node);
        quickSort(node, head);
    }

    // Metodo creado para facilitar la visualizacion de la lista, no tiene
    // relevancia en el algoritmo
    public void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + "<->");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        QuickSort list = new QuickSort();
        NameGenerator nameGenerator = new NameGenerator(10);

        // Se crean n nombres aleatorios
        int n = 10;
        for (int i = 0; i < n; i++) {
            list.push(nameGenerator.getName());
        }

        list.push("Juan Esteban Paez");
        list.push("Cristian Perez Arango");
        list.push("Juan Jose Rios");

        System.out.println("Lista Original:" + "\n");
        list.printList(list.head);

        System.out.println("\n" + "Lista final ordenada:");
        list.sort(list.head);
        list.printList(list.head);
    }
}