package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class MyIterable<T> implements IterableWithPolicy<T> {

    private List<T> data;

    @SuppressWarnings("unused")
    private MyIterable() {}

    public MyIterable(List<T> elems) {
        data = new LinkedList<>(elems);
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIterator<>();
    }

    @SuppressWarnings("unused")
    private class InnerIterator<T> implements Iterator<T> {

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }   
}