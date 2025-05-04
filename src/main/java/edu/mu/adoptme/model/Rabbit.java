package edu.mu.adoptme.model;

/**
 * Represents a Rabbit available for adoption.
 */
public class Rabbit extends Pet {

    /**
     * Constructs a Rabbit object with the given name, species, and age.
     *
     * @param name    the name of the rabbit
     * @param species the breed or species (e.g., Dutch)
     * @param age     the age of the rabbit
     */
    public Rabbit(String name, String species, int age) {
        super(name, species, age);
    }

    /**
     * Returns the type of the pet.
     *
     * @return "Rabbit"
     */
    @Override
    public String getType() {
        return "Rabbit";
    }
}