package code_clone;

import IO.ProjectReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CloneCheck {

    private List<String> projectFileNames1 = new ArrayList<>();
    private List<String> projectFileNames2 = new ArrayList<>();

    public void processProjects(String project1, String project2) throws IOException {
        String path1 = initializeFilePath(project1, projectFileNames1);
        String path2 = initializeFilePath(project2, projectFileNames2);

        performFileProcessing(project1, path1, projectFileNames1);
        performFileProcessing(project2, path2, projectFileNames2);

        TfIdfCalculate tfidf = new TfIdfCalculate();
        tfidf.calculateTFIDFVectors(path1, path2);

        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        List<List<Double>> similarities = cosineSimilarity.calculateSimilarity(projectFileNames1, projectFileNames2);

        new BoxAndWhiskerChart().display(similarities, projectFileNames1);
    }

    private String initializeFilePath(String projectName, List<String> fileNames) throws IOException {
        String basePath = "H:\\2-1\\project\\ProcessedFiles";
        String path = basePath + "\\" + projectName.replaceAll("[\\\\:]", "-");
        Path dirPath = Paths.get(path);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }
        fileNames.clear();
        return path;
    }

    private void performFileProcessing(String projectName, String path, List<String> fileNames) throws IOException {
        ProjectReader.fileRead(path, 0);
        for (var entry : ProjectReader.projectOne.entrySet()) {
            new PreProcessing().processFile(entry.getKey(), entry.getValue(), path);
        }
        ProjectReader.getFileList(projectName, path, fileNames);
    }
}
//dfjdkljd
