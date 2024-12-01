package code_clone;

import java.io.*;
import java.util.*;

public class TfIdfCalculator {

    private List<String[]> project1FileWords = new ArrayList<>();
    private List<String[]> project2FileWords = new ArrayList<>();
    private List<double[]> project1TfIdfVectors = new ArrayList<>();
    private List<double[]> project2TfIdfVectors = new ArrayList<>();
    private List<String> combinedTerms = new ArrayList<>();
    private Map<String, Double> idfMap = new HashMap<>();

    public void readProjectFiles(String path, List<String[]> fileWordList) throws IOException {
        File directory = new File(path);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Invalid directory: " + path);
        }

        for (File file : Objects.requireNonNull(directory.listFiles((dir, name) -> name.endsWith(".txt")))) {
            String fileContent = readFileContent(file);
            fileWordList.add(fileContent.split("\\s+"));
        }
    }

    private String readFileContent(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(" ");
            }
            return content.toString().trim();
        }
    }

    public void calculateUniqueTerms(List<String[]> fileWordList, List<String> uniqueTerms) {
        for (String[] words : fileWordList) {
            for (String word : words) {
                if (!uniqueTerms.contains(word)) {
                    uniqueTerms.add(word);
                }
            }
        }
    }

    public void calculateIdf(List<String[]> allFiles) {
        TfIdfCalculatorHelper helper = new TfIdfCalculatorHelper();
        for (String term : combinedTerms) {
            double idf = helper.calculateIdf(allFiles, term);
            idfMap.put(term, idf);
        }
    }

    public void calculateTfIdfVectors(List<String[]> fileWords, List<double[]> tfIdfVectors) {
        TfIdfCalculatorHelper helper = new TfIdfCalculatorHelper();

        for (String[] fileWord : fileWords) {
            double[] tfIdfVector = new double[combinedTerms.size()];
            for (int i = 0; i < combinedTerms.size(); i++) {
                String term = combinedTerms.get(i);
                double tf = helper.calculateTf(fileWord, term);
                double idf = idfMap.getOrDefault(term, 0.0);
                tfIdfVector[i] = tf * idf;
            }
            tfIdfVectors.add(tfIdfVector);
        }
    }
}
