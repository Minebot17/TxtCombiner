import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter root folder path:");
        Path rootPath = Paths.get(scanner.nextLine());
        List<Path> txtFiles = new ArrayList<>();

        try {
            Files.walk(rootPath)
                    .filter(p -> p.toString().toLowerCase().endsWith(".txt"))
                    .forEach(txtFiles::add);
        }
        catch (IOException e){
            System.out.println("This folder does not exist");
            throw e;
        }

        txtFiles.sort(Comparator.comparing(p -> p.getFileName().toString()));

        Path resultPath = Paths.get("./result.txt");
        Files.deleteIfExists(resultPath);
        PrintWriter writer = new PrintWriter(resultPath.toFile(), StandardCharsets.UTF_8);

        for (Path txtFile : txtFiles){
            Scanner txtScanner = new Scanner(txtFile);

            while (txtScanner.hasNextLine()){
                writer.println(txtScanner.nextLine());
            }

            txtScanner.close();
        }

        writer.close();
        System.out.println("Done! Output file is result.txt");
    }
}
