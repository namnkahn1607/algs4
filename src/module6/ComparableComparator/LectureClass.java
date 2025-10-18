package module6.ComparableComparator;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LectureClass {
    public List<Student> studentList;

    public LectureClass() {
        studentList = new ArrayList<>();
    }

    public void addStudent(Student s) {
        if (s != null) {
            studentList.add(s);
        }
    }

    public void shuffleList() {
        Student[] students = toArray();
        StdRandom.shuffle(students);
        studentList = new ArrayList<>(Arrays.asList(students));
    }

    public void sortList() {
        Student[] students = toArray();
        Arrays.sort(students);
        studentList = new ArrayList<>(Arrays.asList(students));
    }

    public void sortList(Comparator<Student> cmp) {
        Student[] students = toArray();
        Arrays.sort(students, cmp);
        studentList = new ArrayList<>(Arrays.asList(students));
    }

    public Student[] toArray() {
        return studentList.toArray(new Student[0]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Students list:\n");

        for (Student s : studentList) {
            res.append(s.toString());
            res.append("\n");
        }

        return res.toString();
    }

    public static void main(String[] args) {
        final String[] names = {"Andrews", "Battle", "Chen", "Fox", "Furia", "Gazsi", "Kanaga", "Rohde"};
        final int[] section = {3, 4, 3, 3, 1, 4, 3, 2};
        final double[] gpa = {3.48, 2.18, 2.84, 3.14, 3.9, 3.78, 4.0, 2.21, 3.23};

        LectureClass dsaClass = new LectureClass();

        for (int i = 0; i < 8; ++i) {
            dsaClass.addStudent(new Student(names[i], section[i], gpa[i]));
        }

        dsaClass.shuffleList();

        dsaClass.sortList();
        System.out.println(dsaClass);

        dsaClass.shuffleList();

        dsaClass.sortList(Student.BY_NAME);
        System.out.println(dsaClass);

        dsaClass.sortList(Student.BY_SECTION);
        System.out.println(dsaClass);

        dsaClass.sortList(Student.BY_GPA);
        System.out.println(dsaClass);
    }
}