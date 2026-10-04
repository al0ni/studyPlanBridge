package kz.edu.aitu.studyplan;

import java.time.DayOfWeek;

public record StudySession(DayOfWeek day, String topic, int durationMinutes) {
    private static final int MINIMUM_SESSION_MINUTES = 15;
    private static final int MAXIMUM_SESSION_MINUTES = 240;

    public StudySession {
        if (day == null) {
            throw new IllegalArgumentException("Session day cannot be null");
        }
        if (topic == null || topic.isBlank()) {
            throw new IllegalArgumentException("Session topic cannot be blank");
        }
        if (durationMinutes < MINIMUM_SESSION_MINUTES
                || durationMinutes > MAXIMUM_SESSION_MINUTES) {
            throw new IllegalArgumentException(
                    "Session duration must be between " + MINIMUM_SESSION_MINUTES
                            + " and " + MAXIMUM_SESSION_MINUTES + " minutes"
            );
        }
        topic = topic.strip();
    }
}
