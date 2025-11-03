package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class MyIterable<T> implements IterableWithPolicy<T> {

    public MyIterable(T... elems) {

    }

    public MyIterable(List<T> elems) {
        
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }   
}