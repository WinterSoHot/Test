package cn.dx.io.dto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Person
 * @Description TODO
 * @Author dongxian
 * @Date 20-5-14 上午11:41
 * @Version 1.0
 **/
public class Person implements Serializable {
    private String name;
    private String sex;
    private Integer age;
    private transient String address; //不希望这个字段序列化

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Person setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Person setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Person setAddress(String address) {
        this.address = address;
        return this;
    }

    // 实现自定义序列化的另一种当时 .下面两个方法一一对应
    /*
    private void writeObject(ObjectOutputStream out) throws IOException {
        // 处理写入对象，读出也要处理，这样有利于保护信息。
        out.writeObject(new StringBuffer(this.name).reverse());
        out.writeInt(this.age);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.name = ((StringBuffer) in.readObject()).reverse().toString();
        this.age = in.readInt();
    }*/

    // 第三种自定义序列化写入，写入对象时候，调用这个方法，将写入对象替换成其他
    private Object writeReplace() throws ObjectStreamException {
        List<Object> list = new ArrayList<>();
        list.add(this.name);
        list.add(this.age);
        return list;
    }
    private final long serialVersionUID = 512L;
}
