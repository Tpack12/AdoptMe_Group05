package edu.mu.adoptme.view.dialogs;

import edu.mu.adoptme.model.Pet;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog to display details of a selected pet.
 */
public class PetDetailsDialog extends JDialog {

    public PetDetailsDialog(JFrame parent, Pet pet) {
        super(parent, "Pet Details", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 1, 10, 10));

        add(new JLabel("Name: " + pet.getName()));
        add(new JLabel("Species: " + pet.getSpecies()));
        add(new JLabel("Age: " + pet.getAge()));
        add(new JLabel("Type: " + pet.getType()));
        add(new JLabel("Adopted: " + (pet.isAdopted() ? "Yes" : "No")));
    }
}