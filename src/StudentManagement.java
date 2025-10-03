import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.LinkedHashMap;

public class StudentManagement {
    Student[] students = new Student[100];
    int numberOfStudents = 0;

    public void addStudent(Student s) {
        students[numberOfStudents] = s;
        ++numberOfStudents;
    }

    public void removeStudent(String id) {
        int found = -1;

        for (int i = 0; i < numberOfStudents; ++i) {
            if (students[i].getId().equals(id)) {
                for (int j = i; j < numberOfStudents - 1; ++j)
                    students[j] = students[j + 1];

                --numberOfStudents;
                break;
            }
        }
    }

    public String studentsByGroup() {
        Map<String, List<Student>> map = new LinkedHashMap<>();

        for (int i = 0; i < numberOfStudents; ++i) {
            final Student curr = students[i];

            if (!map.containsKey(curr.getGroup()))
                map.put(curr.getGroup(), new ArrayList<>());

            map.get(curr.getGroup()).add(curr);
        }

        StringBuilder res = new StringBuilder();

        for (String key : map.keySet()) {
            res.append(key);
            res.append('\n');

            for (Student stu : map.get(key)) {
                res.append(stu.getInfo());
                res.append('\n');
            }
        }

        return res.toString();
    }

    public static boolean sameGroup(Student s1, Student s2) {
        return s1.getGroup().equals(s2.getGroup());
    }

    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student("Nguyen Le Nam Khanh", "24021530", "nlnk160706@gmail.com");
        Student s3 = new Student(s2);

        s2.setGroup("K69I-CS5");

        s1.setName("Nguyen Le Khanh Duy");
        s1.setId("27021530");
        s1.setGroup("N/A");
        s1.setEmail("nlkd191209@gmail.com");

        System.out.println(s1.getInfo());
        System.out.println(s2.getInfo());
        System.out.println(s3.getInfo());
    }
}
