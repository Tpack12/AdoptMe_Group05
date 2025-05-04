package edu.mu.adoptme.view.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog to input a new pet's details.
 */
public class AddPetDialog extends JDialog {

    private JTextField nameField;
    private JTextField speciesField;
    private JTextField ageField;
    private JComboBox<String> typeComboBox;
    private boolean confirmed = false;

    public AddPetDialog(JFrame parent) {
        super(parent, "Add New Pet", true);
        setSize(350, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Species:"));
        speciesField = new JTextField();
        add(speciesField);

        add(new JLabel("Age:"));
        ageField = new JTextField();
        add(ageField);

        add(new JLabel("Type:"));
        typeComboBox = new JComboBox<>(new String[] { "Dog", "Cat", "Rabbit" });
        add(typeComboBox);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            confirmed = true;
            setVisible(false);
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> setVisible(false));

        add(addButton);
        add(cancelButton);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getNameInput() {
        return nameField.getText().trim();
    }

    public String getSpeciesInput() {
        return speciesField.getText().trim();
    }

    public int getAgeInput() throws NumberFormatException {
        return Integer.parseInt(ageField.getText().trim());
    }

    public String getTypeInput() {
        return (String) typeComboBox.getSelectedItem();
    }
}

