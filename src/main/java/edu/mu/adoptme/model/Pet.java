package edu.mu.adoptme.model;

public abstract class Pet implements Comparable<Pet> {
    protected String name;
    protected String species;
    protected int age;
    protected boolean adopted;

    /**
     * Constructs a Pet with the given name, species, and age.
     * Initially, the pet is not adopted.
     */
    public Pet(String name, String species, int age) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.adopted = false;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void adopt() {
        this.adopted = true;
    }

    /**
     * Default comparison is by name (case-insensitive).
     */
    @Override
    public int compareTo(Pet other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    /**
     * Returns the type of the pet (e.g., Dog, Cat, Rabbit, etc.)
     */
    public abstract String getType();
}