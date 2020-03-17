package Lab01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void listFilesFromFolder(String pathName) {
        File folder = new File(pathName);
        String[] files = folder.list();

        for (String file : files) {
            System.out.println(file);
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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        listFilesFromFolder("Facultate\\");
        System.out.println();

        List<Student> students = new ArrayList<>();

        String firstPath = "Facultate\\studenti-automatica.txt";
        String secondPath = "Facultate\\studenti-calculatoare.txt";

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<List<Student>>> result = new ArrayList<>();

        result.add(executorService.submit(new ReadStudents(firstPath)));
        result.add(executorService.submit(new ReadStudents(secondPath)));

        for (Future<List<Student>> listFuture : result) {
            students.addAll(listFuture.get());
        }

        executorService.shutdown();

        sortStudents(students);

        String outputFile = "studenti.txt";
        writeStudentsToFile(students, outputFile);

    }
}
