package kz.edu.aitu.studyplan;

import java.util.Objects;

public abstract class StudyPlanView {
    protected final StudyPlan plan;
    protected PlanFormatter formatter;

    public StudyPlanView(StudyPlan plan, PlanFormatter formatter) {
        this.plan = Objects.requireNonNull(plan, "Study plan cannot be null");
        setFormatter(formatter);
    }

    public final void setFormatter(PlanFormatter formatter) {
        this.formatter = Objects.requireNonNull(formatter, "Formatter cannot be null");
    }

    protected String renderOverview() {
        return formatter.formatTitle(plan.getTitle())
                + formatter.formatText("Goal: " + plan.getLearningGoal())
                + formatter.formatText("Sessions: " + plan.getSessions().size())
                + formatter.formatText("Total study time: " + plan.getTotalStudyMinutes() + " minutes");
    }

    public abstract String render();
}
