import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void processStudents(List<Student> students, Stream<String> lines) {
        List<String> content = lines.collect(Collectors.toList());

        for (String line : content) {
            String[] values = line.split(",");
            students.add(new Student(values[0], values[1], values[2],
                    Integer.valueOf(values[3]), Double.valueOf(values[4])));
        }

        content.forEach(x -> System.out.println(x));
    }

    public static void main(String[] args) {

        File folder = new File("F:\\DB Tech School\\Facultate");
        String[] files = folder.list();

        for (String file : files) {
            System.out.println(file);
        }

        System.out.println();

        List<Student> students = new ArrayList<>();

        try {
            Stream<String> lines = Files.lines(Paths.get("Facultate\\studenti-automatica.txt"));
            processStudents(students, lines);

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        try {
            Stream<String> lines = Files.lines(Paths.get("Facultate\\studenti-calculatoare.txt"));
            processStudents(students, lines);

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        System.out.println();

        Collections.sort(students, (firstStudent, secondStudent) -> {
            int GPAcompare = secondStudent.getGpa().compareTo(firstStudent.getGpa());
            int firstNameCompare = firstStudent.getFirstName().compareTo(secondStudent.getFirstName());
            int secondNameCompare = firstStudent.getSecondName().compareTo(secondStudent.getSecondName());

            if (GPAcompare == 0 && firstNameCompare == 0) return secondNameCompare;
            else if (GPAcompare == 0) return firstNameCompare;
            else return GPAcompare;
        });

        try {
            FileWriter myWriter = new FileWriter("studenti.txt");
            students.forEach(x -> {
                try {
                    myWriter.write(x.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
