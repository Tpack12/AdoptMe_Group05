package edu.mu.adoptme.model;

/**
 * Represents a Dog available for adoption.
 */
public class Dog extends Pet {

    /**
     * Constructs a Dog object with the given name, species, and age.
     *
     * @param name    the name of the dog
     * @param species the breed or species (e.g., Golden Retriever)
     * @param age     the age of the dog
     */
    public Dog(String name, String species, int age) {
        super(name, species, age);
    }

    /**
     * Returns the type of the pet.
     *
     * @return "Dog"
     */
    @Override
    public String getType() {
        return "Dog";
    }
}
