package edu.mu.adoptme.controller;

import edu.mu.adoptme.model.*;
import edu.mu.adoptme.model.adapter.ExoticAnimalAdapter;
import edu.mu.adoptme.util.JsonUtil;
import edu.mu.adoptme.view.AdoptionView;
import edu.mu.adoptme.view.dialogs.AddPetDialog;
import edu.mu.adoptme.view.dialogs.PetDetailsDialog;
import edu.mu.adoptme.model.comparators.AgeComparator;
import edu.mu.adoptme.model.comparators.SpeciesComparator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller that connects the view to the model and handles user actions.
 */
public class AdoptionController {

    private final AdoptionView view;
    private final Shelter<Pet> shelter;

    public AdoptionController() {
        this.view = new AdoptionView();
        this.shelter = new Shelter<>();

        // Load pets
        List<Pet> allPets = new ArrayList<>();
        allPets.addAll(JsonUtil.loadRegularPets("pets.json"));
        allPets.addAll(JsonUtil.loadExoticPets("exotic_animals.json"));

        for (Pet pet : allPets) {
            shelter.addPet(pet);
        }

        view.updatePetList(shelter.getAllPets());

        setupListeners();
        view.setVisible(true);
    }

    private void setupListeners() {
        view.getAddButton().addActionListener(e -> showAddDialog());
        view.getRemoveButton().addActionListener(e -> removeSelectedPet());
        view.getAdoptButton().addActionListener(e -> adoptSelectedPet());
        view.getViewDetailsButton().addActionListener(e -> showPetDetails());
        view.getSaveButton().addActionListener(e -> JsonUtil.savePetsToJson(shelter.getAllPets()));
        view.getSortComboBox().addActionListener(e -> sortPets());
    }

    private void showAddDialog() {
        AddPetDialog dialog = new AddPetDialog(view);
        dialog.setVisible(true);

        if (dialog.isConfirmed()) {
            try {
                String name = dialog.getNameInput();
                String species = dialog.getSpeciesInput();
                int age = dialog.getAgeInput();
                String type = dialog.getTypeInput();

                Pet pet = switch (type) {
                    case "Dog" -> new Dog(name, species, age);
                    case "Cat" -> new Cat(name, species, age);
                    case "Rabbit" -> new Rabbit(name, species, age);
                    default -> null;
                };

                if (pet != null) {
                    shelter.addPet(pet);
                    view.updatePetList(shelter.getAllPets());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Please enter a valid number for age.");
            }
        }
    }

    private void removeSelectedPet() {
        int selected = view.getPetTable().getSelectedRow();
        if (selected >= 0) {
            Pet pet = shelter.getAllPets().get(selected);
            shelter.removePet(pet);
            view.updatePetList(shelter.getAllPets());
        } else {
            JOptionPane.showMessageDialog(view, "Please select a pet to remove.");
        }
    }

    private void adoptSelectedPet() {
        int selected = view.getPetTable().getSelectedRow();
        if (selected >= 0) {
            Pet pet = shelter.getAllPets().get(selected);
            if (pet.isAdopted()) {
                JOptionPane.showMessageDialog(view, "This pet has already been adopted!");
            } else {
                pet.adopt();
                view.updatePetList(shelter.getAllPets());
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select a pet to adopt.");
        }
    }

    private void showPetDetails() {
        int selected = view.getPetTable().getSelectedRow();
        if (selected >= 0) {
            Pet pet = shelter.getAllPets().get(selected);
            PetDetailsDialog dialog = new PetDetailsDialog(view, pet);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(view, "Please select a pet to view details.");
        }
    }

    private void sortPets() {
        String option = (String) view.getSortComboBox().getSelectedItem();
        if (option == null) return;

        switch (option) {
            case "Name" -> shelter.sortByName();
            case "Age" -> shelter.sortByComparator(new AgeComparator());
            case "Species" -> shelter.sortByComparator(new SpeciesComparator());
        }

        view.updatePetList(shelter.getAllPets());
    }
}