package edu.mu.adoptme.model;

/**
 * Represents a Cat available for adoption.
 */
public class Cat extends Pet {

    /**
     * Constructs a Cat object with the given name, species, and age.
     *
     * @param name    the name of the cat
     * @param species the breed or species (e.g., Siamese)
     * @param age     the age of the cat
     */
    public Cat(String name, String species, int age) {
        super(name, species, age);
    }

    /**
     * Returns the type of the pet.
     *
     * @return "Cat"
     */
    @Override
    public String getType() {
        return "Cat";
    }
}
