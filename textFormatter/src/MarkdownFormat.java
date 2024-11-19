import java.util.ArrayList;
import java.util.Random;
//textbranch

public class MarkdownFormat implements IFormat {
    @Override
    public void format(ArrayList<String> data) {
        // Check if the data is empty
        if (data == null || data.isEmpty()) {
            System.out.println("No content to display.");
            return;
        }

        // Print the first line as a header if it exists
        System.out.println("#" + data.get(0));

        // Print subsequent lines with random formatting
        String[] options = {"**", "***", "<sub>", "~~", "_"};
        Random rng = new Random();

        for(int i = 1; i < data.size(); i++) {
            int number = rng.nextInt(options.length);
            String option = options[number];
            System.out.println(option + data.get(i) + option);
        }
    }
}