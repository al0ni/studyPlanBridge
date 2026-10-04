package kz.edu.aitu.studyplan;

public class DetailedStudyPlanView extends StudyPlanView {
    public DetailedStudyPlanView(StudyPlan plan, PlanFormatter formatter) {
        super(plan, formatter);
    }

    @Override
    public String render() {
        StringBuilder result = new StringBuilder(renderOverview());
        result.append(formatter.formatSection("Study sessions"));
        for (StudySession session : plan.getSessions()) {
            result.append(formatter.formatListItem(session.day() + ": "
                    + session.topic() + " (" + session.durationMinutes() + " minutes)"));
        }
        result.append(formatter.formatSection("Resources"));
        if (plan.getResources().isEmpty()) {
            result.append(formatter.formatText("No resources added"));
        } else {
            for (String resource : plan.getResources()) {
                result.append(formatter.formatListItem(resource));
            }
        }
        return result.toString();
    }
}
