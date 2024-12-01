package code_clone;

import java.util.Locale;

public class PorterStemmer {

    public String stem(String word) {
        if (word == null || word.length() < 3) return word.toLowerCase(Locale.getDefault());
        String stem = word.toLowerCase(Locale.getDefault());
        stem = applyStep1(stem);
        stem = applyStep2(stem);
        stem = applyStep3(stem);
        stem = applyStep4(stem);
        stem = applyStep5(stem);
        return stem;
    }

    private String applyStep1(String word) {
        // Apply step 1 rules
        word = step1a(word);
        word = step1b(word);
        word = step1c(word);
        return word;
    }

    private String applyStep2(String word) {
        // Apply step 2 rules
        return step2(word);
    }

    private String applyStep3(String word) {
        // Apply step 3 rules
        return step3(word);
    }

    private String applyStep4(String word) {
        // Apply step 4 rules
        return step4(word);
    }

    private String applyStep5(String word) {
        // Apply step 5 rules
        word = step5a(word);
        return step5b(word);
    }

    // Add previously defined methods for steps (step1a, step1b, step2, etc.) here.

}
