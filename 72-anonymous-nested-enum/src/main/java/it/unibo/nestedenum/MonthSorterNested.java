package it.unibo.nestedenum;

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

        private Month(String name, int days) {
            this.name = name;
            this.days = days;
        }

        private String getName() {
            return this.name;
        }

        private static Month fromString(final String name) {
            List<Month> candidates = List.of(
                JANUARY, FEBRUARY, MARCH, 
                APRIL, MAY, JUNE, JULY, AUGUST, 
                SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER);

            // i dont think we can use lamdas yet...
            candidates = Transformers.select(candidates, new Function<Month, Boolean>() {
                @Override
                public Boolean call(Month input) {
                    return input.getName().startsWith(name.toLowerCase());
                }
            });

            if (candidates.size() != 1) {
                throw new IllegalArgumentException("Supplied name cannot be mapped to a unique valid month");
            } 
            return candidates.get(0);
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }


}
