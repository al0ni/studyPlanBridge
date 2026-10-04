package kz.edu.aitu.studyplan;

public class MarkdownFormatter implements PlanFormatter {
    @Override
    public String formatTitle(String text) {
        return "# " + escape(text) + "\n\n";
    }

    @Override
    public String formatSection(String text) {
        return "\n## " + escape(text) + "\n\n";
    }

    @Override
    public String formatText(String text) {
        return escape(text) + "\n\n";
    }

    @Override
    public String formatListItem(String text) {
        return "- " + escape(text) + "\n";
    }

    private String escape(String text) {
        StringBuilder result = new StringBuilder();
        String specialCharacters = "\\`*_{}[]<>()#+-.!|>~";
        for (char character : text.toCharArray()) {
            if (specialCharacters.indexOf(character) >= 0) {
                result.append('\\');
            }
            if (character == '&') {
                result.append("&amp;");
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}
