package it.unibo.inner.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class MyIterable<T> implements IterableWithPolicy<T> {

    private List<T> data;

    @SuppressWarnings("unused")
    private MyIterable() {}

    public MyIterable(List<T> elems) {
        data = new LinkedList<>(elems);
    }

    public MyIterable(T[] elems) {
        data = new LinkedList<>(Arrays.asList(elems));
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    @SuppressWarnings("unused")
    private class InnerIterator implements Iterator<T> {

        /**
         * points to the element that will be returned by next()
         */
        private int pointer = 0;

        @Override
        public boolean hasNext() {
            return this.pointer < data.size();
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return data.get(pointer++);
        }
    }   
}