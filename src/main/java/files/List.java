package files;

import java.util.Iterator;

/**
 * The List class represents a dynamic array-like collection that can store and manage objects.
 * It includes methods for adding, removing, and accessing elements, as well as checking
 * if the list is empty or finding the index of an element.
 *
 * This implementation grows dynamically as elements are added.
 * It also implements the Iterable interface to allow for easy iteration over the list.
 *
 * @param <E> The type of elements stored in the list.
 * @author Amani Islam
 */
public class List<E> implements Iterable<E> {

    private E[] objects;  // Array to store the elements in the list
    private int size;     // The current size of the list
    private static final int NOT_FOUND = -1;  // Constant to indicate an element was not found

    /**
     * Default constructor that initializes the list with an initial capacity of 4.
     */
    public List() {
        objects = (E[]) new Object[4];
    }

    /**
     * Helper method to find the index of a given element.
     *
     * @param e The element to find in the list.
     * @return The index of the element, or -1 if not found.
     */
    private int find(E e) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(e)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Expands the internal array by a factor of 4 to accommodate more elements.
     */
    private void grow() {
        E[] newObj = (E[]) new Object[objects.length + 4];
        for (int i = 0; i < size; i++) {
            newObj[i] = objects[i];
        }
        objects = newObj;
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param e The element to check for in the list.
     * @return True if the element is in the list, otherwise false.
     */
    public boolean contains(E e) {
        return find(e) != NOT_FOUND;
    }

    /**
     * Adds an element to the list. If the list is full, the internal array will grow.
     *
     * @param e The element to add to the list.
     */
    public void add(E e) {
        if (objects.length == size) {
            grow();
        }
        objects[size] = e;
        size++;
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     *
     * @param e The element to remove.
     */
    public void remove(E e) {
        for (int i = find(e); i < size - 1; i++) {
            objects[i] = objects[i + 1];
        }
        size--;
    }

    /**
     * Checks if the list is empty.
     *
     * @return True if the list is empty, otherwise false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the current size of the list.
     *
     * @return The number of elements in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Returns an iterator for the list, allowing iteration over the elements.
     *
     * @return An iterator for the list.
     */
    public Iterator<E> iterator() {
        return new ListIterator<>();
    }

    /**
     * Retrieves the element at the specified index.
     *
     * @param index The index of the element to retrieve.
     * @return The element at the specified index.
     */
    public E get(int index) {
        return objects[index];
    }

    /**
     * Replaces the element at the specified index with the given element.
     *
     * @param index The index where the element should be replaced.
     * @param e The new element to insert at the specified index.
     */
    public void set(int index, E e) {
        objects[index] = e;
    }

    /**
     * Returns the index of the specified element, or -1 if the element is not found.
     *
     * @param e The element to find in the list.
     * @return The index of the element, or -1 if it is not found.
     */
    public int indexOf(E e) {
        return find(e);
    }

    /**
     * Inner class that implements the Iterator interface for the List class,
     * allowing iteration over the elements in the list.
     *
     * @param <E> The type of elements in the list.
     */
    private class ListIterator<E> implements Iterator<E> {

        private int pos = 0;

        /**
         * Checks if there are more elements to iterate over.
         *
         * @return True if there are more elements, otherwise false.
         */
        public boolean hasNext() {
            return pos < size;
        }

        /**
         * Returns the next element in the list.
         *
         * @return The next element in the list.
         */
        @Override
        public E next() {
            if (!hasNext()) {
                return null;
            }
            return (E) objects[pos++];
        }
    }
}
