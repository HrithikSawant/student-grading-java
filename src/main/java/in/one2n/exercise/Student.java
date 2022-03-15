package in.one2n.exercise;

import java.util.Comparator;

public class Student  {

    private String firstname;
    private String lastname;
    private String university;
    private Double test1Score;
    private Double test2Score;
    private Double test3Score;
    private Double test4Score;

    // computed fields
    private Double finalScore;
    private Grade grade;


    public String getUniversity() {
        return university;
    }


    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Student(String firstname, String lastname, String university) {
        // TODO: add your implementation here
        this.firstname = firstname;
        this.lastname = lastname;
        this.university = university;
    }

    public Student(String firstname, String lastname, String university, Double test1Score, Double test2Score, Double test3Score, Double test4Score) {
        // TODO: add your implementation here
        this.firstname = firstname;
        this.lastname = lastname;
        this.university = university;
        this.test1Score = test1Score;
        this.test2Score = test2Score;
        this.test3Score = test3Score;
        this.test4Score = test4Score;

    }

    public Double getFinalScore() {
        // TODO: add your implementation here
        double sum = this.test1Score + this.test2Score + this.test3Score + this.test4Score;
        this.finalScore = sum / 4;
        return finalScore;
    }


    public Grade getGrade() {
        // TODO: add your implementation here
        return this.grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", university='" + university + '\'' +
                ", test1Score=" + test1Score +
                ", test2Score=" + test2Score +
                ", test3Score=" + test3Score +
                ", test4Score=" + test4Score +
                ", finalScore=" + finalScore +
                ", grade=" + grade +
                '}';
    }


    @Override
    public boolean equals(Object obj) {

        if(obj == null){
            return false;
        }

        if(obj.getClass() != this.getClass()){
            return false;
        }

        // type casting of the argument.
        Student student = (Student) obj;

        return this.university!=null ? student.university != null : this.university.equals(student.university)
                && this.lastname != null ? student.lastname != null : this.lastname.equals(student.lastname)
                && this.firstname != null ? student.firstname != null : this.firstname.equals(student.firstname);
    }

}


