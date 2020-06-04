package cn.dx.io.dto;

import java.io.Serializable;

/**
 * @ClassName Teacher
 * @Description TODO
 * @Author dongxian
 * @Date 20-5-15 上午10:22
 * @Version 1.0
 **/
public class Teacher implements Serializable {
    private String name;
    private Person student;

    public String getName() {
        return name;
    }

    public Teacher setName(String name) {
        this.name = name;
        return this;
    }

    public Person getStudent() {
        return student;
    }

    public Teacher setStudent(Person student) {
        this.student = student;
        return this;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", student=" + student +
                '}';
    }

    public Teacher(String name, Person student) {
        this.name = name;
        this.student = student;
    }
}
