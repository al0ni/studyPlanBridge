package kz.edu.aitu.studyplan;

public interface PlanFormatter {
    String formatTitle(String text);

    String formatSection(String text);

    String formatText(String text);

    String formatListItem(String text);
}
