package module6.ComparableComparator;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class Student implements Comparable<Student> {
    public static final Comparator<Student> BY_NAME = new ByName();
    public static final Comparator<Student> BY_SECTION = new ByAge();
    public static final Comparator<Student> BY_GPA = new ByGPA();

    private final String name;
    private final int section;
    private final double GPA;

    public Student(String name, int section, double GPA) {
        this.name = name;
        this.section = section;
        this.GPA = GPA;
    }

    public Student(Student other) {
        this.name = other.getName();
        this.section = other.getSection();
        this.GPA = other.getGPA();
    }

    @Override
    public int compareTo(@NotNull Student o) {
        return name.compareTo(o.getName());
    }

    private static class ByName implements Comparator<Student> {
        @Override
        public int compare(Student u, Student v) {
            return u.name.compareTo(v.name);
        }
    }

    private static class ByAge implements Comparator<Student> {
        @Override
        public int compare(Student u, Student v) {
            return Integer.compare(u.section, v.section);
        }
    }

    private static class ByGPA implements Comparator<Student> {
        @Override
        public int compare(Student u, Student v) {
            return Double.compare(u.GPA, v.GPA);
        }
    }

    public String getName() {
        return name;
    }

    public int getSection() {
        return section;
    }

    public double getGPA() {
        return GPA;
    }

    @Override
    public String toString() {
        return String.format("Student[name=%s, section=%d, GPA=%.2f)", getName(), getSection(), getGPA());
    }
}