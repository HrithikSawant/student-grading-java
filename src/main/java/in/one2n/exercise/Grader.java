package in.one2n.exercise;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grader {
    private static final String SEPARATOR = ",";

    public List<Student> parseCSV(String filepath) {
        // TODO: add your implementation here

        Path path = Paths.get(filepath);
        try {
            if (!Files.exists(path)) {
                throw new FileNotFoundException();
            } else {
                List<Student> students;
                try (Stream<String> stream = Files.lines(path)) {
                    students = stream.skip(1)
                            .map(s -> s.split(SEPARATOR))
                            .map(this::createStudent)
                            .collect(Collectors.toList());
                    return students;
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private Student createStudent(String[] data) {
        //                students.add(new Student(data[0],data[1],data[2],Double.parseDouble(data[3]),
        //                        Double.parseDouble(data[4]),Double.parseDouble(data[5]),Double.parseDouble(data[6])));
        return new Student(data[0], data[1], data[2],Double.valueOf(data[3]),
                Double.valueOf(data[4]),Double.valueOf(data[5]),Double.valueOf(data[6]));
    }

    public List<Student> calculateGrade(List<Student> students) {
        // TODO: add your implementation here

        if(!students.isEmpty()) {
            for (Student student : students) {
                Double score = student.getFinalScore();
                if (score >= 70) {
                    student.setGrade(Grade.A);
                    continue;
                }
                if (score >= 50) {
                    student.setGrade(Grade.B);
                    continue;
                }
                if (score >= 35) {
                    student.setGrade(Grade.C);
                    continue;
                }
                student.setGrade(Grade.F);
            }
            return students;
        }

        return Collections.emptyList();
    }

    public Student findOverallTopper(List<Student> gradedStudents) {
        // TODO: add your implementation here

        gradedStudents.sort(Comparator.comparing(Student::getFinalScore));
        return gradedStudents.get(0);
    }

    public Map<String, Student> findTopperPerUniversity(List<Student> gradedStudents) {
        // TODO: add your implementation here

        //Compare by getUniversity name and then finalScore
        Comparator<Student> compareBy = Comparator
                .comparing(Student::getUniversity)
                .thenComparing(Student::getFinalScore);

        Collections.sort(gradedStudents, compareBy);

        HashMap<String,Student> map = new HashMap<>();
        for (Student s : gradedStudents) {
            map.put(s.getUniversity(),s);
        }

        if(!map.isEmpty()){
            return map;
        }
        return new HashMap<>();
    }
}
