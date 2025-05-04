package edu.mu.adoptme.model.adapter;

import edu.mu.adoptme.model.Pet;

/**
 * Adapter class that makes ExoticAnimal compatible with the Pet abstract class.
 */
public class ExoticAnimalAdapter extends Pet {
    private ExoticAnimal exoticAnimal;

    /**
     * Constructs an adapter that wraps an ExoticAnimal and maps its fields to Pet.
     *
     * @param exoticAnimal the exotic animal to adapt
     */
    public ExoticAnimalAdapter(ExoticAnimal exoticAnimal) {
        super(exoticAnimal.getAnimalName(),
              exoticAnimal.getSubSpecies(),
              exoticAnimal.getYearsOld());
        this.exoticAnimal = exoticAnimal;
    }

    /**
     * Returns the exotic animal's category (e.g., Bird, Reptile, etc.)
     */
    @Override
    public String getType() {
        return exoticAnimal.getCategory();
    }
}