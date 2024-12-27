package org.main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Main {

    private JFrame frame;
    private JTable recipeTable;
    private DefaultTableModel tableModel;
    private ArrayList<Recipe> recipes;
    private final String DATA_FILE = "recipes.dat";

    public Main() {
        recipes = new ArrayList<>();
        loadRecipes();
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Aplikasi Resep Masakan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Recipe Table
        String[] columnNames = {"Nama Resep"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing
            }
        };
        recipeTable = new JTable(tableModel);
        recipeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScroll = new JScrollPane(recipeTable);

        // Add Recipe Button
        JButton addButton = new JButton("Tambah Resep");
        addButton.addActionListener(e -> openAddRecipeDialog());

        // View Recipe Button
        JButton viewButton = new JButton("Lihat Resep");
        viewButton.addActionListener(e -> viewSelectedRecipe());

        // Edit Recipe Button
        JButton editButton = new JButton("Edit Resep");
        editButton.addActionListener(e -> editSelectedRecipe());

        // Delete Recipe Button
        JButton deleteButton = new JButton("Hapus Resep");
        deleteButton.addActionListener(e -> deleteSelectedRecipe());

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Add Components to Main Panel
        mainPanel.add(tableScroll, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);

        updateTable();

        // Save recipes on close
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveRecipes();
            }
        });
    }

    private void openAddRecipeDialog() {
        JDialog dialog = new JDialog(frame, "Tambah Resep", true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(6, 2));

        // Form Fields
        JTextField nameField = new JTextField();
        JTextArea ingredientsArea = new JTextArea();
        JTextArea stepsArea = new JTextArea();
        JButton chooseImageButton = new JButton("Pilih Gambar");
        JLabel imagePathLabel = new JLabel("Tidak ada gambar");

        chooseImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(dialog);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imagePathLabel.setText(selectedFile.getAbsolutePath());
            }
        });

        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String ingredients = ingredientsArea.getText();
            String steps = stepsArea.getText();
            String imagePath = imagePathLabel.getText();

            if (!name.isEmpty()) {
                recipes.add(new Recipe(name, ingredients, steps, imagePath));
                updateTable();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Nama resep tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(new JLabel("Nama Resep:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Bahan:"));
        dialog.add(new JScrollPane(ingredientsArea));
        dialog.add(new JLabel("Langkah-langkah:"));
        dialog.add(new JScrollPane(stepsArea));
        dialog.add(chooseImageButton);
        dialog.add(imagePathLabel);
        dialog.add(saveButton);

        dialog.setVisible(true);
    }

    private void viewSelectedRecipe() {
        int selectedRow = recipeTable.getSelectedRow();
        if (selectedRow >= 0) {
            Recipe recipe = recipes.get(selectedRow);
            JDialog dialog = new JDialog(frame, recipe.getName(), true);
            dialog.setSize(400, 400);
            dialog.setLayout(new BorderLayout());

            JTextArea recipeDetails = new JTextArea();
            recipeDetails.setEditable(false);
            recipeDetails.setText("Bahan:\n" + recipe.getIngredients() + "\n\nLangkah-langkah:\n" + recipe.getSteps());

            JLabel imageLabel = new JLabel();
            if (recipe.getImagePath() != null && !recipe.getImagePath().isEmpty()) {
                ImageIcon icon = new ImageIcon(recipe.getImagePath());
                imageLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
            }

            dialog.add(imageLabel, BorderLayout.NORTH);
            dialog.add(new JScrollPane(recipeDetails), BorderLayout.CENTER);

            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih resep untuk dilihat.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void editSelectedRecipe() {
        int selectedRow = recipeTable.getSelectedRow();
        if (selectedRow >= 0) {
            Recipe recipe = recipes.get(selectedRow);

            JDialog dialog = new JDialog(frame, "Edit Resep", true);
            dialog.setSize(400, 400);
            dialog.setLayout(new GridLayout(6, 2));

            // Form Fields
            JTextField nameField = new JTextField(recipe.getName());
            JTextArea ingredientsArea = new JTextArea(recipe.getIngredients());
            JTextArea stepsArea = new JTextArea(recipe.getSteps());
            JButton chooseImageButton = new JButton("Pilih Gambar");
            JLabel imagePathLabel = new JLabel(recipe.getImagePath() == null ? "Tidak ada gambar" : recipe.getImagePath());

            chooseImageButton.addActionListener(e -> {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(dialog);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    imagePathLabel.setText(selectedFile.getAbsolutePath());
                }
            });

            JButton saveButton = new JButton("Simpan Perubahan");
            saveButton.addActionListener(e -> {
                String name = nameField.getText();
                String ingredients = ingredientsArea.getText();
                String steps = stepsArea.getText();
                String imagePath = imagePathLabel.getText();

                if (!name.isEmpty()) {
                    recipe.setName(name);
                    recipe.setIngredients(ingredients);
                    recipe.setSteps(steps);
                    recipe.setImagePath(imagePath);
                    updateTable();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Nama resep tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            dialog.add(new JLabel("Nama Resep:"));
            dialog.add(nameField);
            dialog.add(new JLabel("Bahan:"));
            dialog.add(new JScrollPane(ingredientsArea));
            dialog.add(new JLabel("Langkah-langkah:"));
            dialog.add(new JScrollPane(stepsArea));
            dialog.add(chooseImageButton);
            dialog.add(imagePathLabel);
            dialog.add(saveButton);

            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih resep untuk diedit.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteSelectedRecipe() {
        int selectedRow = recipeTable.getSelectedRow();
        if (selectedRow >= 0) {
            recipes.remove(selectedRow);
            updateTable();
        } else {
            JOptionPane.showMessageDialog(frame, "Pilih resep untuk dihapus.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Recipe recipe : recipes) {
            tableModel.addRow(new Object[]{recipe.getName()});
        }
    }

    private void saveRecipes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRecipes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            recipes = (ArrayList<Recipe>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            recipes = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}

