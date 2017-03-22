package easytests.models.empty;

import easytests.models.exceptions.CallMethodOnEmptyModelsListException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;


/**
 * @author malinink
 */
public class ModelsListEmpty implements List {
    public ModelsListEmpty() {
        super();
    }

    @Override
    public void replaceAll(UnaryOperator unaryOperator) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public void sort(Comparator comparator) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public Spliterator spliterator() {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public int size() {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public boolean isEmpty() {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public boolean contains(Object o) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public Iterator iterator() {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public Object[] toArray() {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public Object[] toArray(Object[] objects) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public boolean add(Object o) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public void add(int i, Object o) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public boolean remove(Object o) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public Object remove(int i) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public boolean containsAll(Collection collection) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public boolean addAll(Collection collection) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public boolean addAll(int i, Collection collection) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public boolean removeAll(Collection collection) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public boolean retainAll(Collection collection) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public void clear() {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public Object get(int i) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public Object set(int i, Object o) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public int indexOf(Object o) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public ListIterator listIterator() {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public ListIterator listIterator(int i) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public List subList(int i, int i1) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public boolean removeIf(Predicate predicate) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public Stream stream() {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public Stream parallelStream() {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public void forEach(Consumer consumer) {
        throw new CallMethodOnEmptyModelsListException();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ModelsListEmpty;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
