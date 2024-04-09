// A Java program to sort a linked list using Quicksort   

import java.util.Scanner;

class QuickSort {
    Node head;

    /* a node of the doubly linked list */
    static class Node {
        private int data;
        private Node next;
        private Node prev;

        Node(int value) {
            data = value;
            next = null;
            prev = null;
        }
    }

    // A utility function to find last node of linked list
    Node lastNode(Node node) {
        while (node.next != null)
            node = node.next;
        return node;
    }

    /*
     * Considers the last element as pivot, places the pivot element at its
     * correct position in a sorted array, and places all smaller (smaller than
     * pivot) to the left of the pivot and all greater elements to right of pivot
     */
    Node partition(Node l, Node h) {
        // set pivot as h element
        int x = h.data;

        // similar to i = l-1 for array implementation
        Node i = l.prev;

        // Similar to "for (int j = l; j <= h- 1; j++)"
        for (Node j = l; j != h; j = j.next) {
            if (j.data <= x) {
                // Similar to i++ for array
                i = (i == null) ? l : i.next;
                int temp = i.data;
                i.data = j.data;
                j.data = temp;
            }
        }
        i = (i == null) ? l : i.next; // Similar to i++
        int temp = i.data;
        i.data = h.data;
        h.data = temp;
        return i;
    }

    /* A recursive implementation of quicksort for linked list */
    void _quickSort(Node l, Node h) {
        if (h != null && l != h && l != h.next) {
            Node temp = partition(l, h);
            _quickSort(l, temp.prev);
            _quickSort(temp.next, h);
        }
    }

    // The main function is to sort a linked list. It mainly calls _quickSort()
    public void quickSort(Node node) {
        // Find last node
        Node head = lastNode(node);

        // Call the recursive QuickSort
        _quickSort(node, head);
    }

    // A utility function to print contents of arr
    public void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    /* Function to insert a node at the beginning of the Doubly Linked List */
    void push(int new_Data) {
        Node new_Node = new Node(new_Data); /* allocate node */

        // if head is null, head = new_Node
        if (head == null) {
            head = new_Node;
            return;
        }

        /* link the old list off the new node */
        new_Node.next = head;

        /* change prev of head node to new node */
        head.prev = new_Node;

        /* since we are adding at the begining, prev is always NULL */
        new_Node.prev = null;

        /* move the head to point to the new node */
        head = new_Node;
    }

    /* Driver program to test above written function */
    public static void main(String[] args) {
        QuickSort list = new QuickSort();
        list.push(6);
        list.push(3);
        list.push(5);
        list.push(9);
        list.push(1);
        list.push(4);

        System.out.println("Original Doubly Linked List::" + "\n");
        list.printList(list.head);
        
        System.out.println("\n" + "Sorted Doubly Linked List::");
        list.quickSort(list.head);
        list.printList(list.head);
    }
}