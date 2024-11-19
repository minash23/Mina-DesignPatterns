package textFormatter;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
//textbranch

// New class to handle saving documents in different formats
public class SaveFormat {
    public static void saveDocument(ArrayList<String> content, IFormat format, String fileExtension) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Document");

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // Ensure the file has the correct extension
            if (!fileToSave.getName().toLowerCase().endsWith(fileExtension)) {
                fileToSave = new File(fileToSave.getAbsolutePath() + fileExtension);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                // Use the selected format to generate the content
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PrintStream ps = new PrintStream(baos);
                PrintStream old = System.out;
                System.setOut(ps);

                format.format(content);

                System.out.flush();
                System.setOut(old);

                // Write the formatted content to the file
                writer.write(baos.toString());

                JOptionPane.showMessageDialog(null, "Document saved successfully!",
                        "Save Successful", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving document: " + ex.getMessage(),
                        "Save Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}