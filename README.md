# Разбор кода односвязного списка на Java

Этот код реализует структуру данных "односвязный список" (singly linked list) с основными операциями. Давайте разберем его подробно, начиная с основ Java.

## Основные концепции Java в этом коде

1. **Классы и объекты**: Код определяет класс `LinkedList`, который можно использовать для создания объектов-списков.
2. **Generics (`<T>`)** - обобщения: Позволяют создавать типобезопасные коллекции для любых типов данных.
3. **Вложенные классы**: Класс `Node` определен внутри `LinkedList`.
4. **Инкапсуляция**: Поля класса (`head`, `size`) объявлены как `private`.
5. **Методы**: Класс предоставляет публичные методы для работы со списком.

## Структура кода

### 1. Вложенный класс Node

```java
private static class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}
```

- `Node` - это строительный блок списка.
- `data` - хранит значение элемента.
- `next` - ссылка на следующий узел.
- Конструктор инициализирует `data` и устанавливает `next` в `null`.

### 2. Основные поля класса LinkedList

```java
private Node<T> head;  // ссылка на первый узел
private int size;      // количество элементов в списке
```

### 3. Конструктор LinkedList

```java
public LinkedList() {
    head = null;
    size = 0;
}
```

Инициализирует пустой список.

## Основные методы

### 1. Вставка элемента (insert)

```java
public void insert(T x, int p) {
    if (p < 0 || p > size) {
        System.out.println("Неверная позиция для вставки");
        return;
    }

    Node<T> newNode = new Node<>(x);

    if (p == 0) {  // вставка в начало
        newNode.next = head;
        head = newNode;
    } else {  // вставка в середину или конец
        Node<T> prev = head;
        for (int i = 0; i < p - 1; i++) {
            prev = prev.next;
        }
        newNode.next = prev.next;
        prev.next = newNode;
    }
    size++;
}
```

Алгоритм:
1. Проверка корректности позиции
2. Создание нового узла
3. Если вставка в начало - обновляем `head`
4. Иначе ищем узел перед позицией вставки и перестраиваем ссылки

### 2. Получение элемента (retrieve)

```java
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
```

Проходит по списку до нужной позиции и возвращает данные.

### 3. Получение следующей позиции (next)

```java
public int next(int p) {
    if (p < 0 || p >= size - 1) {
        return -1; // Нет следующего элемента
    }
    return p + 1;
}
```

Просто возвращает p+1, если это допустимо.

### 4. Получение первой позиции (first)

```java
public int first() {
    return (head == null) ? -1 : 0;
}
```

Возвращает 0, если список не пуст, иначе -1.

### 5. Поиск вхождений (findOccurrences)

```java
public void findOccurrences(T x) {
    List<Integer> positions = new ArrayList<>();
    int count = 0;

    int currentPos = first();

    while (currentPos != -1) {
        T currentElement = retrieve(currentPos);

        if (currentElement != null && currentElement.equals(x)) {
            count++;
            positions.add(currentPos);
        }

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
```

Использует другие методы (`first`, `retrieve`, `next`) для последовательного прохода по списку и поиска всех вхождений элемента.

### 6. Печать списка (print)

```java
public void print() {
    int currentPos = first();
    while (currentPos != -1) {
        System.out.print(retrieve(currentPos) + " ");
        currentPos = next(currentPos);
    }
    System.out.println();
}
```

Также использует базовые методы для последовательного вывода всех элементов.

## Пример использования (main)

```java
public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<>();

    list.insert(10, 0);
    list.insert(20, 1);
    list.insert(10, 2);
    list.insert(30, 3);
    list.insert(10, 4);
    list.insert(40, 5);

    System.out.print("Список: ");
    list.print();

    list.findOccurrences(10);
}
```

Создает список, добавляет элементы, печатает его и ищет вхождения числа 10.

## Принцип работы односвязного списка

Односвязный список - это цепочка узлов, где каждый узел содержит:
1. Данные
2. Ссылку на следующий узел

Последний узел имеет `next = null`.

Преимущества:
- Динамический размер
- Быстрая вставка/удаление в начало

Недостатки:
- Медленный доступ по индексу (O(n))
- Требует дополнительной памяти для хранения ссылок

Этот код демонстрирует хороший стиль ООП, инкапсуляцию и повторное использование кода через базовые методы.
