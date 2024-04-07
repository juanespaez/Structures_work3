from DoubleLinkedList import DoublyLinkedList, Node


class QuickSort:
    def less(x: Node, y: Node) -> bool:
        return x.value < y.value

    def partition(self, start: Node, end: Node) -> Node:
        pivot: Node = end
        pivot_value = pivot.value
        current: Node = start
        while current != pivot:
            if self.less(current.value, pivot_value):
                current = current.next
            else:
                current.value, pivot.value = pivot.value, current.value
            start = current
            return start
        
    def sort(self, start: Node, end: Node):
        print(start, end)
        if start is None or start == end:
            return

        pivot = self.partition(start, end)
        if pivot != start:
            self.sort(start, pivot.prev)
        if pivot != end:
            self.sort(pivot.next, end)
            
    
