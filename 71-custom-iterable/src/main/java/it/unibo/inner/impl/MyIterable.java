package it.unibo.inner.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

/**
 * {@inheritDoc}
 */
public class MyIterable<T> implements IterableWithPolicy<T> {

    private final T[] data;
    private Predicate<T> pred;

    public MyIterable(final T[] elems) {
        // can i use lamdas yet?
        // this(elems, T -> true);
        this(elems, new Predicate<T>(){
            @Override
            public boolean test(final T elem) {
                return true;
            }
        });
    }

    public MyIterable(final T[] elems, final Predicate<T> predicate) {
        data = Arrays.copyOf(elems, elems.length);
        pred = predicate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIterationPolicy(final Predicate<T> filter) {
        pred = filter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    private class InnerIterator implements Iterator<T> {

        /**
         * points to the element that will be returned by {@code next()}
         */
        private int pointer = 0;

        private void advancePointer() {
            while (pointer < data.length && !pred.test(data[pointer])) {
                pointer++;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            this.advancePointer();
            return this.pointer < data.length;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return data[pointer++];            
        }
    }   
}