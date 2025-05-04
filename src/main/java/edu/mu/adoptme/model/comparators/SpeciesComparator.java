package edu.mu.adoptme.model.comparators;

import edu.mu.adoptme.model.Pet;
import java.util.Comparator;

/**
 * Comparator to sort pets by species (case-insensitive).
 */
public class SpeciesComparator implements Comparator<Pet> {

    @Override
    public int compare(Pet p1, Pet p2) {
        return p1.getSpecies().compareToIgnoreCase(p2.getSpecies());
    }
}