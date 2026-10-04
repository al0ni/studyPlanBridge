package kz.edu.aitu.studyplan;

public class PlainTextFormatter implements PlanFormatter {
    @Override
    public String formatTitle(String text) {
        return text + "\n\n";
    }

    @Override
    public String formatSection(String text) {
        return "\n" + text + ":\n";
    }

    @Override
    public String formatText(String text) {
        return text + "\n";
    }

    @Override
    public String formatListItem(String text) {
        return "  " + text + "\n";
    }
}
