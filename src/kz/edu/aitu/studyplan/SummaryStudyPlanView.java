package kz.edu.aitu.studyplan;

public class SummaryStudyPlanView extends StudyPlanView {
    public SummaryStudyPlanView(StudyPlan plan, PlanFormatter formatter) {
        super(plan, formatter);
    }

    @Override
    public String render() {
        return renderOverview();
    }
}
