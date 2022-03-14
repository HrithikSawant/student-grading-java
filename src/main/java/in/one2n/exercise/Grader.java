package in.one2n.exercise;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grader {

    public List<Student> parseCSV(String filepath) {
        // TODO: add your implementation here

        List<Student> students = new ArrayList<>();
        String currentLine;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            //to skip header
            br.readLine();
            while ((currentLine = br.readLine()) != null){
                String [] data = currentLine.split(",");
//                students.add(new Student(data[0],data[1],data[2],Double.parseDouble(data[3]),
//                        Double.parseDouble(data[4]),Double.parseDouble(data[5]),Double.parseDouble(data[6])));
                students.add(new Student(data[0],data[1],data[2],Double.valueOf(data[3]),
                        Double.valueOf(data[4]),Double.valueOf(data[5]),Double.valueOf(data[6])));
            }
            return students;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public List<Student> calculateGrade(List<Student> students) {
        // TODO: add your implementation here

        if(!students.isEmpty()) {
            for (Student student : students) {
                Double score = student.getFinalScore();
                if (score >= 70) {
                    student.setGrade(Grade.A);
                }
                if (score >= 50 && score < 70) {
                    student.setGrade(Grade.B);
                }
                if (score >= 35 && score < 50) {
                    student.setGrade(Grade.C);
                }
                if (score < 35) {
                    student.setGrade(Grade.F);
                }
            }
            return students;
        }

        return Collections.emptyList();
    }

    public Student findOverallTopper(List<Student> gradedStudents) {
        // TODO: add your implementation here

        gradedStudents.sort((s1, s2) -> (int) (s2.getFinalScore() - s1.getFinalScore()));

//        gradedStudents.sort((s1, s2) -> (int) (s2.getFinalScore() - s1.getFinalScore()));

        return gradedStudents.get(0);
    }

    public Map<String, Student> findTopperPerUniversity(List<Student> gradedStudents) {
        // TODO: add your implementation here


        //Compare by getUniversity name and then final Score name
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
