import java.util.ArrayList;
//textbranch

public class PlainTextFormat implements IFormat {
    @Override
    public void format(ArrayList<String> data) {
        // Check if the data is empty
        if (data == null || data.isEmpty()) {
            System.out.println("No content to display.");
            return;
        }

        // Print each line of content
        for (String s : data) {
            System.out.println(s);
        }
    }
}
