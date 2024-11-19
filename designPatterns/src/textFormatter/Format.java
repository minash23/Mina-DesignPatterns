package textFormatter;

import java.util.ArrayList;
//textbranch

public class Format {
    static ArrayList<String> fileContent;
    private IFormat format;

    public Format() {
        fileContent = new ArrayList<>();
    }

    public void setFormat(IFormat format) {
        this.format = format;
    }

    // Add this method to return the current format
    public IFormat getFormat() {
        return this.format;
    }

    public void printFormatted() {
        format.format(fileContent);
    }
}