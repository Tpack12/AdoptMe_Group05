package edu.mu.adoptme.view;

import edu.mu.adoptme.model.Pet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Main GUI view for the pet adoption system.
 */
public class AdoptionView extends JFrame {

    private JTable petTable;
    private DefaultTableModel tableModel;
    private JButton addButton, removeButton, adoptButton, viewDetailsButton, saveButton;
    private JComboBox<String> sortComboBox;

    public AdoptionView() {
        setTitle("Adopt Me - Pet Adoption Center");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel: sorting
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Sort by:"));
        sortComboBox = new JComboBox<>(new String[] { "Name", "Age", "Species" });
        topPanel.add(sortComboBox);
        add(topPanel, BorderLayout.NORTH);

        // Center panel: table
        tableModel = new DefaultTableModel(new Object[] { "Name", "Species", "Age", "Type", "Adopted" }, 0);
        petTable = new JTable(tableModel);
        add(new JScrollPane(petTable), BorderLayout.CENTER);

        // Bottom panel: action buttons
        JPanel bottomPanel = new JPanel();
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        adoptButton = new JButton("Adopt");
        viewDetailsButton = new JButton("View Details");
        saveButton = new JButton("Save");

        bottomPanel.add(addButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(adoptButton);
        bottomPanel.add(viewDetailsButton);
        bottomPanel.add(saveButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Methods for controller access
    public void updatePetList(List<Pet> pets) {
        tableModel.setRowCount(0); // Clear existing rows
        for (Pet pet : pets) {
            tableModel.addRow(new Object[] {
                    pet.getName(),
                    pet.getSpecies(),
                    pet.getAge(),
                    pet.getType(),
                    pet.isAdopted() ? "Yes" : "No"
            });
        }
    }

    public JTable getPetTable() {
        return petTable;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getAdoptButton() {
        return adoptButton;
    }

    public JButton getViewDetailsButton() {
        return viewDetailsButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JComboBox<String> getSortComboBox() {
        return sortComboBox;
    }
}
