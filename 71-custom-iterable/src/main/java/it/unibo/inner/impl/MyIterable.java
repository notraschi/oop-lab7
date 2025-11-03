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
    private Predicate<T> pred;

    @SuppressWarnings("unused")
    private MyIterable() {}

    public MyIterable(T[] elems) {
        // can i use lamdas yet?
        // this(elems, T -> true);
        this(elems, new Predicate<T>(){
            @Override
            public boolean test(T elem) {
                return true;
            }
        });
    }

    public MyIterable(T[] elems, Predicate<T> predicate) {
        data = new LinkedList<>(Arrays.asList(elems));
        pred = predicate;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        pred = filter;
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

        private void advancePointer() {
            while (pointer < data.size() && !pred.test(data.get(pointer))) {
                pointer++;
            }
        }

        @Override
        public boolean hasNext() {
            this.advancePointer();
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