package edu.mu.adoptme.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A generic shelter that stores adoptable pets.
 *
 * @param <T> any subclass of Pet
 */
public class Shelter<T extends Pet> {
    private final List<T> pets;

    public Shelter() {
        this.pets = new ArrayList<>();
    }

    public void addPet(T pet) {
        pets.add(pet);
    }

    public void removePet(T pet) {
        pets.remove(pet);
    }

    public List<T> getAllPets() {
        return new ArrayList<>(pets);
    }

    public void sortByName() {
        Collections.sort(pets);
    }

    public void sortByComparator(Comparator<T> comparator) {
        pets.sort(comparator);
    }

    public boolean adoptPet(T pet) {
        if (pet.isAdopted()) {
            return false;
        } else {
            pet.adopt();
            return true;
        }
    }
}

