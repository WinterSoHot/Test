package cn.dx.io.obj;

import cn.dx.io.dto.Person;
import cn.dx.io.dto.Teacher;

import java.io.*;

/**
 * @ClassName WriteTeacher
 * @Description 对象引用序列化
 * @Author dongxian
 * @Date 20-5-15 上午10:21
 * @Version 1.0
 **/
public class WriteTeacher {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teacher"));
        Person p = new Person();
        p.setName("老孙");
        Teacher t1 = new Teacher("老王", p);
        Teacher t2 = new Teacher("老李", p);
        oos.writeObject(t1);
        oos.writeObject(t2);
        oos.writeObject(p);
        oos.writeObject(t2);

        oos.close();
    }
}
