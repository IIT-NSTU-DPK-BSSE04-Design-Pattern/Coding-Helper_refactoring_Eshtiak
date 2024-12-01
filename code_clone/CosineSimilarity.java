package code_clone;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CosineSimilarity {

    public List<List<Double>> calculateSimilarity(List<String> fileNames1, List<String> fileNames2) {
        List<List<Double>> similarityMatrix = new ArrayList<>();

        for (int i = 0; i < fileNames1.size(); i++) {
            List<Double> similarities = new ArrayList<>();
            for (int j = 0; j < fileNames2.size(); j++) {
                double similarity = cosineSimilarity(TfIdfCalculate.tfidfvectorProject1.get(i),
                        TfIdfCalculate.tfidfvectorProject2.get(j));
                BigDecimal bd = new BigDecimal(similarity).setScale(2, RoundingMode.HALF_UP);
                similarities.add(bd.doubleValue());
            }
            similarityMatrix.add(similarities);
        }

        return similarityMatrix;
    }

    private double cosineSimilarity(double[] vector1, double[] vector2) {
        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;

        for (int i = 0; i < vector2.length; i++) {
            dotProduct += vector1[i] * vector2[i];
            magnitude1 += Math.pow(vector1[i], 2);
            magnitude2 += Math.pow(vector2[i], 2);
        }

        magnitude1 = Math.sqrt(magnitude1);
        magnitude2 = Math.sqrt(magnitude2);

        if (magnitude1 == 0 || magnitude2 == 0) {
            return 0.0;
        }

        return (dotProduct / (magnitude1 * magnitude2)) * 100;
    }
}
