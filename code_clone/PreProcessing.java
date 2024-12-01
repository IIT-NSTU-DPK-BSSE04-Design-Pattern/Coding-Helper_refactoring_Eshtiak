package code_clone;

import IO.FileWriter;
import java.io.IOException;
import java.util.List;

public class PreProcessing {

    private PorterStemmer stemmer = new PorterStemmer();

    public String processFile(String fileName, String content, String outputPath) throws IOException {
        if (fileName == null || content == null || outputPath == null) {
            throw new IllegalArgumentException("File name, content, or output path cannot be null.");
        }

        String processedContent = removePunctuation(content);
        processedContent = removeKeywords(processedContent);
        processedContent = normalizeWhitespace(processedContent);
        processedContent = applyStemming(processedContent);

        FileWriter writer = new FileWriter();
        return writer.createProcessFile(fileName, processedContent, outputPath);
    }

    private String removePunctuation(String content) {
        return content.replaceAll("\\p{Punct}", " ");
    }

    private String normalizeWhitespace(String content) {
        return content.trim().replaceAll("\\s+", " ");
    }

    private String removeKeywords(String content) throws IOException {
        List<String> keywords = loadKeywords();
        StringBuilder filteredContent = new StringBuilder();

        for (String word : content.split("\\s+")) {
            if (!keywords.contains(word.trim())) {
                filteredContent.append(word).append(" ");
            }
        }

        return filteredContent.toString().trim();
    }

    private List<String> loadKeywords() throws IOException {
        // Replace with dynamic loading from configuration
        return List.of("class", "public", "static", "void"); // Example keywords
    }

    private String applyStemming(String content) {
        StringBuilder stemmedContent = new StringBuilder();

        for (String word : content.split("\\s+")) {
            stemmedContent.append(stemmer.stem(word)).append(" ");
        }

        return stemmedContent.toString().trim();
    }
}
