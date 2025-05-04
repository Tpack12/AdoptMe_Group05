package edu.mu.adoptme.model.adapter;

/**
 * Simulates a third-party ExoticAnimal class with a different structure.
 * Used as the adaptee in the Adapter Pattern.
 */
public class ExoticAnimal {
    private String uniqueId;
    private String animalName;
    private String category;     // Like type
    private String subSpecies;   // Like species
    private int yearsOld;        // Like age

    // Required for Gson deserialization
    public ExoticAnimal() {}

    public String getUniqueId() {
        return uniqueId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getCategory() {
        return category;
    }

    public String getSubSpecies() {
        return subSpecies;
    }

    public int getYearsOld() {
        return yearsOld;
    }
}