package textFormatter;

import java.util.ArrayList;
//textbranch

public class HTMLFormat implements IFormat{
    @Override
    public void format(ArrayList<String> data) {
        System.out.println("<html dir=\"ltr\" lang=\"en\">");
        System.out.println("<head>");
        for (String s : data) {
            System.out.println("<text>" + s + "</text>");
        }
        System.out.println("</head>");
        System.out.println("</html>");
    }
}
