from DoubleLinkedList import DoublyLinkedList, Node


class QuickSort:
    def less(x: Node, y: Node) -> bool:
        return x.value < y.value

    def exch(x: Node, y: Node) -> None:
        # Nodes associated to Y
        yprev: Node = y.prev
        ynext: Node = y.next

        # Nodes associated to X
        xprev: Node = x.prev
        xnext: Node = x.next

        x.prev = yprev
        x.next = ynext

        y.prev = xprev
        y.next = xnext

        xprev.next = y
        xnext.prev = y

        yprev.next = x
        ynext.prev = x

    def partition(a: DoublyLinkedList, k: Node):
        
        pass
