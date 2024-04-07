class Node:
    def __init__(self, data):
        self.data = data
        self.next = None
        self.prev = None


def partition(start, end):
    pivot = end
    pivot_value = pivot.data
    current = start
    while current != pivot:
        if current.data <= pivot_value:
            current = current.next
        else:
            current.data, pivot.data = pivot.data, current.data
        start = current
        return start


def quicksort(start, end):
    print(start, end)
    if start is None or start == end:
        return

    pivot = partition(start, end)
    if pivot != start:
        quicksort(start, pivot.prev)
    if pivot != end:
        quicksort(pivot.next, end)


def print_list(head):
    current = head
    while current:
        print(current.data, end=" <-> ")
        current = current.next
    print("None")


def append_node(head, data):
    new_node = Node(data)
    if head is None:
        return new_node
    current = head
    while current.next:
        current = current.next
    current.next = new_node
    new_node.prev = current
    return head


if __name__ == "__main__":
    head = None
    data_list = [3, 6, 1, 9, 4, 2]
    for data in data_list:
        head = append_node(head, data)
    print("Original List:")
    print_list(head)
    quicksort(head, None)
    print("\nSorted List:")
    print_list(head)
