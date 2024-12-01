package code_clone;

import java.util.List;

public class TfIdfCalculator {

    public double calculateTf(String[] fileContent, String term) {
        if (fileContent == null || term == null || fileContent.length == 0) {
            return 0.0;
        }
        double termCount = 0;
        for (String word : fileContent) {
            if (word.equalsIgnoreCase(term)) {
                termCount++;
            }
        }
        return termCount / fileContent.length;
    }

    public double calculateIdf(List<String[]> allFiles, String term) {
        if (allFiles == null || term == null || allFiles.isEmpty()) {
            return 0.0;
        }
        double documentCount = 0;

        for (String[] fileContent : allFiles) {
            if (fileContent == null) continue;
            for (String word : fileContent) {
                if (word.equalsIgnoreCase(term)) {
                    documentCount++;
                    break;
                }
            }
        }

        return 1 + Math.log(allFiles.size() / (documentCount > 0 ? documentCount : 1));
    }
}
