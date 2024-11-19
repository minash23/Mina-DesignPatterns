import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
//textbranch

public class DocumentEditorGUI extends JFrame {
    private JTextArea textArea;
    private JComboBox<String> formatComboBox;
    private Format documentFormat;
    private JButton saveButton;
    private JButton previewButton;

    public DocumentEditorGUI() {
        // Set up the main frame
        setTitle("Document Editor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize document format
        documentFormat = new Format();

        // Create components
        initComponents();

        // Layout components
        layoutComponents();
    }

    private void initComponents() {
        // Text area for document content
        textArea = new JTextArea(10, 40);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Format selection dropdown
        String[] formats = {"Plain Text", "HTML", "Markdown"};
        formatComboBox = new JComboBox<>(formats);
        formatComboBox.addActionListener(e -> updateFormat());

        // Buttons
        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveDocument());

        previewButton = new JButton("Preview");
        previewButton.addActionListener(e -> previewDocument());
    }

    private void layoutComponents() {
        // Use BorderLayout
        setLayout(new BorderLayout());

        // Top panel for format selection
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Format: "));
        topPanel.add(formatComboBox);

        // Bottom panel for buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(saveButton);
        bottomPanel.add(previewButton);

        // Add scroll pane for text area
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateFormat() {
        // Set the format based on user selection
        switch (formatComboBox.getSelectedIndex()) {
            case 0:
                documentFormat.setFormat(new PlainTextFormat());
                break;
            case 1:
                documentFormat.setFormat(new HTMLFormat());
                break;
            case 2:
                documentFormat.setFormat(new MarkdownFormat());
                break;
        }
    }

    private void saveDocument() {
        // Clear previous content
        Format.fileContent.clear();

        // Add current text to fileContent
        String text = textArea.getText().trim();
        if (!text.isEmpty()) {
            Format.fileContent.add(text);
        }

        // Determine file extension based on selected format
        String fileExtension = switch (formatComboBox.getSelectedIndex()) {
            case 0 -> ".txt";
            case 1 -> ".html";
            case 2 -> ".md";
            default -> ".txt";
        };

        // Save the document
        SaveFormat.saveDocument(Format.fileContent, documentFormat.getFormat(), fileExtension);
    }

    private void previewDocument() {
        // Clear previous content
        Format.fileContent.clear();

        // Add current text to fileContent
        String text = textArea.getText().trim();
        if (!text.isEmpty()) {
            Format.fileContent.add(text);
        }

        // Create a new window to show the preview
        JFrame previewFrame = new JFrame("Document Preview");
        JTextArea previewArea = new JTextArea();
        previewArea.setEditable(false);

        // Capture the formatted output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        // Format the document
        documentFormat.setFormat(getCurrentFormat());
        documentFormat.printFormatted();

        System.out.flush();
        System.setOut(old);

        // Set preview text
        previewArea.setText(baos.toString());

        // Show preview
        previewFrame.add(new JScrollPane(previewArea));
        previewFrame.setSize(400, 300);
        previewFrame.setVisible(true);
    }

    private IFormat getCurrentFormat() {
        // Get the current selected format
        return switch (formatComboBox.getSelectedIndex()) {
            case 0 -> new PlainTextFormat();
            case 1 -> new HTMLFormat();
            case 2 -> new MarkdownFormat();
            default -> new PlainTextFormat();
        };
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new DocumentEditorGUI().setVisible(true);
        });
    }
}