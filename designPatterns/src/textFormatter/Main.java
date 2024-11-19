package textFormatter;

public class Main {
    //textbranch
    public static void main(String[] args) {
        // Create a new textFormatter.Format instance
        Format format = new Format();

        // Add sample content
        format.fileContent.add("There are many variations of passages of Lorem Ipsum available, but the " +
                "\nmajority have suffered alteration in some form, by injected humour, or randomised words " +
                "\nwhich don't look even slightly believable.");
        format.fileContent.add("If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't " +
                "\nanything embarrassing hidden in the middle of text.");
        format.fileContent.add("All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, " +
                "\nmaking this the first true generator on the Internet.");

        // Demonstrate different formatting styles
        System.out.println("Plain Text textFormatter.Format:");
        format.setFormat(new PlainTextFormat());
        format.printFormatted();

        System.out.println("\nHTML textFormatter.Format:");
        format.setFormat(new HTMLFormat());
        format.printFormatted();

        System.out.println("\nMarkdown textFormatter.Format:");
        format.setFormat(new MarkdownFormat());
        format.printFormatted();

        // Launch the GUI
        DocumentEditorGUI.main(args);
    }
}