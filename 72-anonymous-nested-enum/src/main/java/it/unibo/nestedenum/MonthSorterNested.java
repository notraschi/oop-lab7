package it.unibo.nestedenum;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import it.unibo.functional.Transformers;
import it.unibo.functional.api.Function;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private enum Month {
        JANUARY("january", 31),
        FEBRUARY("february", 28),
        MARCH("march", 31),
        APRIL("april", 30),
        MAY("may", 31),
        JUNE("june", 30),
        JULY("july", 31),
        AUGUST("august", 31),
        SEPTEMBER("september", 30),
        OCTOBER("october", 31),
        NOVEMBER("november", 30),
        DECEMBER("december", 31);

        private final String name;
        private final int days;

        private Month(final String name, final int days) {
            this.name = name;
            this.days = days;
        }

        private String getName() {
            return this.name;
        }

        private int getDays() {
            return this.days;
        }

        private static Month fromString(final String name) {
            // i dont think we can use lamdas yet...
            final List<Month> candidates = Transformers.select(
                Arrays.asList(Month.values()),
                new Function<Month, Boolean>() {
                    @Override
                    public Boolean call(final Month input) {
                        return input.getName().startsWith(name.toLowerCase());
                    }
                }
            );
            if (candidates.size() != 1) {
                throw new IllegalArgumentException("Supplied name cannot be mapped to a unique valid month");
            } 
            return candidates.get(0);
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    private static class SortByMonthOrder implements Comparator<String> {
        @Override
        public int compare(final String t, final String t1) {
            return Integer.compare(
                Month.fromString(t).ordinal(),
                Month.fromString(t1).ordinal() 
            );
        }
    }

    private static class SortByDate implements Comparator<String> {
        @Override
        public int compare(final String t, final String t1) {
            return Integer.compare(
                Month.fromString(t).getDays(),
                Month.fromString(t1).getDays()
            );
        }
    }
}
