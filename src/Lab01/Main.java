package Lab01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void listFilesFromFolder(String pathName) {
        File folder = new File(pathName);
        String[] files = folder.list();

        for (String file : files) {
            System.out.println(file);
        }
    }

    public static void processStudents(List<Student> students, Stream<String> lines) {
        List<String> content = lines.collect(Collectors.toList());

        for (String line : content) {
            String[] values = line.split(",");
            students.add(new Student(values[0], values[1], values[2],
                    Integer.valueOf(values[3]), Double.valueOf(values[4])));
        }

        content.forEach(x -> System.out.println(x));
    }

    public static void readFromFile(List<Student> students, String firstPath) {
        try {
            Stream<String> lines = Files.lines(Paths.get(firstPath));
            processStudents(students, lines);

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public static void sortStudents(List<Student> students) {
        Collections.sort(students, (firstStudent, secondStudent) -> {
            int GPACompare = secondStudent.getGpa().compareTo(firstStudent.getGpa());
            int firstNameCompare = firstStudent.getFirstName().compareTo(secondStudent.getFirstName());
            int secondNameCompare = firstStudent.getSecondName().compareTo(secondStudent.getSecondName());

            if (GPACompare == 0 && firstNameCompare == 0) return secondNameCompare;
            else if (GPACompare == 0) return firstNameCompare;
            else return GPACompare;
        });
    }

    public static void writeStudentsToFile(List<Student> students, String outputFile) {
        try {
            FileWriter myWriter = new FileWriter(outputFile);
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

    public static void main(String[] args) {
        listFilesFromFolder("Facultate\\");
        System.out.println();

        List<Student> students = new ArrayList<>();

        String firstPath = "Facultate\\studenti-automatica.txt";
        String secondPath = "Facultate\\studenti-calculatoare.txt";

        readFromFile(students, firstPath);
        readFromFile(students, secondPath);

        System.out.println();

        sortStudents(students);

        String outputFile = "studenti.txt";
        writeStudentsToFile(students, outputFile);
    }
}
