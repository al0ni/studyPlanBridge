package kz.edu.aitu.studyplan;

import java.util.List;
import java.util.Objects;

public final class StudyPlan {
    private final String title;
    private final String learningGoal;
    private final List<String> resources;
    private final List<StudySession> sessions;

    public StudyPlan(String title, String learningGoal,
                     List<String> resources, List<StudySession> sessions) {
        this.title = validateText(title, "Title");
        this.learningGoal = validateText(learningGoal, "Learning goal");
        Objects.requireNonNull(resources, "Resources cannot be null");
        Objects.requireNonNull(sessions, "Sessions cannot be null");
        if (sessions.isEmpty()) {
            throw new IllegalArgumentException("A study plan must contain at least one session");
        }
        for (String resource : resources) {
            validateText(resource, "Resource");
        }
        this.resources = List.copyOf(resources);
        this.sessions = List.copyOf(sessions);
    }

    private static String validateText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
        return value.strip();
    }

    public String getTitle() {
        return title;
    }

    public String getLearningGoal() {
        return learningGoal;
    }

    public List<String> getResources() {
        return resources;
    }

    public List<StudySession> getSessions() {
        return sessions;
    }

    public long getTotalStudyMinutes() {
        long totalMinutes = 0;
        for (StudySession session : sessions) {
            totalMinutes += session.durationMinutes();
        }
        return totalMinutes;
    }
}
