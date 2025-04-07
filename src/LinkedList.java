import java.util.ArrayList;
import java.util.List;

public class LinkedList<T> {
    // Узел списка
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private int size;

    // Конструктор
    public LinkedList() {
        head = null;
        size = 0;
    }

    // MAKENULL - создание пустого списка
//    public void makeNull() {
//        head = null;
//        size = 0;
//    }

    // INS - вставка элемента x в позицию p
    public void insert(T x, int p) {
        if (p < 0 || p > size) {
            System.out.println("Неверная позиция для вставки");
            return;
        }

        Node<T> newNode = new Node<>(x);

        if (p == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> prev = head;
            for (int i = 0; i < p - 1; i++) {
                prev = prev.next;
            }
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }

    // RET - возврат значения элемента в позиции p
    public T retrieve(int p) {
        if (p < 0 || p >= size) {
            System.out.println("Неверная позиция");
            return null;
        }

        Node<T> current = head;
        for (int i = 0; i < p; i++) {
            current = current.next;
        }
        return current.data;
    }

    // DEL - удаление элемента из позиции p
//    public void delete(int p) {
//        if (p < 0 || p >= size) {
//            System.out.println("Неверная позиция для удаления");
//            return;
//        }
//
//        if (p == 0) {
//            head = head.next;
//        } else {
//            Node<T> prev = head;
//            for (int i = 0; i < p - 1; i++) {
//                prev = prev.next;
//            }
//            prev.next = prev.next.next;
//        }
//        size--;
//    }

    // LOCATE - возврат позиции элемента x
//    public int locate(T x) {
//        Node<T> current = head;
//        int pos = 0;
//        while (current != null) {
//            if (current.data.equals(x)) {
//                return pos;
//            }
//            current = current.next;
//            pos++;
//        }
//        return -1; // Элемент не найден
//    }

    // NEXT - возврат позиции следующего элемента после p
    public int next(int p) {
        if (p < 0 || p >= size - 1) {
            return -1; // Нет следующего элемента
        }
        return p + 1;
    }

    // PREV - возврат позиции предыдущего элемента перед p
//    public int previous(int p) {
//        if (p <= 0 || p >= size) {
//            return -1; // Нет предыдущего элемента
//        }
//        return p - 1;
//    }

    // FIRST - возврат позиции первого элемента
    public int first() {
        return (head == null) ? -1 : 0;
    }

    // LAST - возврат позиции последнего элемента
//    public int last() {
//        return size - 1;
//    }

    // Поиск всех вхождений элемента с использованием операторов
    public void findOccurrences(T x) {
        List<Integer> positions = new ArrayList<>();
        int count = 0;

        // Начинаем с первого элемента
        int currentPos = first();

        while (currentPos != -1) {
            // Получаем элемент в текущей позиции
            T currentElement = retrieve(currentPos);

            // Сравниваем с искомым элементом
            if (currentElement != null && currentElement.equals(x)) {
                count++;
                positions.add(currentPos);
            }

            // Переходим к следующей позиции
            currentPos = next(currentPos);
        }

        System.out.println("Элемент " + x + " встречается " + count + " раз(а)");
        if (count > 0) {
            System.out.print("Позиции: ");
            for (int p : positions) {
                System.out.print(p + " ");
            }
            System.out.println();
        }
    }

    // Печать списка
    public void print() {
        int currentPos = first();
        while (currentPos != -1) {
            System.out.print(retrieve(currentPos) + " ");
            currentPos = next(currentPos);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        // Пример использования
        list.insert(10, 0);
        list.insert(20, 1);
        list.insert(10, 2);
        list.insert(30, 3);
        list.insert(10, 4);
        list.insert(40, 5);

        System.out.print("Список: ");
        list.print();

        // Поиск вхождений числа 10 с использованием операторов
        list.findOccurrences(10);

        // Пример использования других методов
//        System.out.println("Первый элемент: " + list.retrieve(list.first()));
//        System.out.println("Последний элемент: " + list.retrieve(list.last()));
//        System.out.println("Следующий после позиции 2: " + list.retrieve(list.next(2)));
//        System.out.println("Предыдущий перед позицией 2: " + list.retrieve(list.previous(2)));
    }
}