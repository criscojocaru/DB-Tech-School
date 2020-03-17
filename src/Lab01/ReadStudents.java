package Lab01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ReadStudents implements Callable {
    private String file;

    public ReadStudents(String file){
        this.file = file;
    }

    @Override
    public List<Student> call() throws Exception {
        List<Student> students = new ArrayList<>();

        try {
            Stream<String> lines = Files.lines(Paths.get(this.file));
            List<String> content = lines.collect(Collectors.toList());

            for (String line : content) {
                String[] values = line.split(",");
                students.add(new Student(values[0], values[1], values[2],
                        Integer.valueOf(values[3]), Double.valueOf(values[4])));
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return students;
    }
}
