package cn.dx.io.obj;

import cn.dx.io.dto.Person;

import java.io.*;

/**
 * @ClassName SerializeMutable
 * @Description 当程序序列化对象只有第一次会输出字节码，后面只输出序列化编号，即使属性值变化。
 * @Author dongxian
 * @Date 20-5-15 上午10:38
 * @Version 1.0
 **/
public class SerializeMutable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mutable"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mutable"));

        Person p = new Person("小王", 23);
        oos.writeObject(p);
        p.setName("小王八");
        oos.writeObject(p);

        Person p2 = (Person) ois.readObject();
        Person p3 = (Person) ois.readObject();
        System.out.println(p2 == p3);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p3.getName());

    }
}
