package kz.edu.aitu.studyplan;

import java.time.DayOfWeek;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudyPlan plan = new StudyPlan(
                "Java Study Plan",
                "Understand the Bridge pattern",
                List.of("Lecture 4 slides", "Java practice tasks"),
                List.of(
                        new StudySession(DayOfWeek.MONDAY, "Interfaces and abstract classes", 60),
                        new StudySession(DayOfWeek.WEDNESDAY, "Bridge pattern practice", 90),
                        new StudySession(DayOfWeek.FRIDAY, "Defense preparation", 45)
                )
        );

        PlanFormatter plainText = new PlainTextFormatter();
        PlanFormatter markdown = new MarkdownFormatter();

        StudyPlanView summary = new SummaryStudyPlanView(plan, plainText);
        printView("Summary in plain text", summary);
        summary.setFormatter(markdown);
        printView("Same summary after switching to Markdown", summary);

        StudyPlanView detailed = new DetailedStudyPlanView(plan, plainText);
        printView("Detailed plan in plain text", detailed);
        detailed.setFormatter(markdown);
        printView("Same detailed plan after switching to Markdown", detailed);
    }

    private static void printView(String label, StudyPlanView view) {
        System.out.println("=== " + label + " ===");
        System.out.println(view.render());
    }
}
