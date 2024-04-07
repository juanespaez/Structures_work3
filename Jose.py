class Node:
    def __init__(self, value: int, next=None, prev=None):
        self.value = value
        self.next = next
        self.prev = prev

    def set_next_node(self, next):
        self.next = next

    def get_next_node(self):
        return self.next

    def set_prev_node(self, prev):
        self.prev = prev

    def get_prev_node(self):
        return self.prev

    def get_value(self):
        return self.value

    def less(self, y):
        return self.value < y.value


class DoublyLinkedList:
    def __init__(self):
        self.head_node = None
        self.tail_node = None

    def print_list(self):
        current = self.head_node
        while current:
            print(current.value, end=" <-> ")
            current = current.next
        print("None")

    def add_to_head(self, new_value):
        new_head = Node(new_value)
        current_head = self.head_node

        if current_head != None:
            current_head.set_prev_node(new_head)
            new_head.set_next_node(current_head)

        self.head_node = new_head

        if self.tail_node == None:
            self.tail_node = new_head

    def add_to_tail(self, new_value):
        new_tail = Node(new_value)
        current_tail = self.tail_node

        if current_tail != None:
            current_tail.set_next_node(new_tail)
            new_tail.set_prev_node(current_tail)

        self.tail_node = new_tail

        if self.head_node == None:
            self.head_node = new_tail

    def remove_head(self):
        removed_head = self.head_node

        if removed_head == None:
            return None

        self.head_node = removed_head.get_next_node()

        if self.head_node != None:
            self.head_node.set_prev_node(None)

        if removed_head == self.tail_node:
            self.remove_tail()

        return removed_head.get_value()

    def remove_tail(self):
        removed_tail = self.tail_node

        if removed_tail == None:
            return None

        self.tail_node = removed_tail.get_prev_node()

        if self.tail_node != None:
            self.tail_node.set_next_node(None)

        if removed_tail == self.head_node:
            self.remove_head()

        return removed_tail.get_value()

    def remove_by_value(self, value_to_remove):
        node_to_remove = None
        current_node = self.head_node

        while current_node != None:
            if current_node.get_value() == value_to_remove:
                node_to_remove = current_node
                break

            current_node = current_node.get_next_node()

        if node_to_remove == None:
            return None

        if node_to_remove == self.head_node:
            self.remove_head()
        elif node_to_remove == self.tail_node:
            self.remove_tail()
        else:
            next_node = node_to_remove.get_next_node()
            prev_node = node_to_remove.get_prev_node()
            next_node.set_prev_node(prev_node)
            prev_node.set_next_node(next_node)

        return node_to_remove


class Quicksort():
    def partition(self, head_node, tail_node):
        pivot = tail_node
        iNode = head_node
        while iNode != pivot:
            if iNode.value <= pivot.value:
                iNode = iNode.next
            else:
                iNode.value, pivot.value = pivot.value, iNode.value
                # pivot = iNode
                return pivot

    def quicksort(self, head_node, tail_node):
        print(head_node, tail_node)
        if head_node is None or head_node == tail_node:
            return

        pivot = self.partition(head_node, tail_node)
        if pivot != head_node:
            self.quicksort(head_node, pivot)
        if pivot != tail_node:
            self.quicksort(pivot.next, head_node)

    def print_list(self):
        current = self.head_node
        while current:
            print(current.value, end=" <-> ")
            current = current.next
        print("None")


if __name__ == "__main__":
    list = DoublyLinkedList()
    sorter = Quicksort()
    data_list = [3, 6, 1, 9, 4, 2]
    for data in data_list:
        list.add_to_head(data)
    print("Original List:")
    list.print_list()
    sorter.quicksort(list.head_node, list.tail_node)
    print("\nSorted List:")
    list.print_list()
