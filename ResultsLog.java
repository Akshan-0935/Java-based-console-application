import java.util.ArrayList;
import java.util.List;

/** Stores a text record of every result so option 8 can show them all. */
public class ResultsLog {
    private final List<String> entries = new ArrayList<>();

    public void add(String entry) {
        entries.add(entry);
    }

    public void displayAll() {
        System.out.println("=============================================");
        System.out.println("ALL RESULTS");
        System.out.println("=============================================");
        if (entries.isEmpty()) {
            System.out.println("No results recorded yet. Run some searches or traversals first.");
        } else {
            for (int i = 0; i < entries.size(); i++) {
                System.out.println((i + 1) + ". " + entries.get(i));
            }
        }
        System.out.println("=============================================");
    }
}
