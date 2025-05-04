package edu.mu.adoptme.model.comparators;

import edu.mu.adoptme.model.Pet;
import java.util.Comparator;

/**
 * Comparator to sort pets by age in ascending order.
 */
public class AgeComparator implements Comparator<Pet> {

    @Override
    public int compare(Pet p1, Pet p2) {
        return Integer.compare(p1.getAge(), p2.getAge());
    }
}