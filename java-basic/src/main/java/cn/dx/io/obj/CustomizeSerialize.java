package cn.dx.io.obj;

import cn.dx.io.dto.Person;

import java.io.*;

/**
 * @ClassName CustomizeSerialize
 * @Description 自定义序列化，如果某些变量不序列化，并且不产生 NoSerializableException
 * @Author dongxian
 * @Date 20-5-15 上午10:44
 * @Version 1.0
 **/
public class CustomizeSerialize {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("customizeSerialize"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("customizeSerialize"));
        Person p = new Person("孙",123);
        p.setAddress("哈哈哈");//地址不会序列化
        oos.writeObject(p);

        Person p1 = (Person) ois.readObject();
        System.out.println(p1.getAddress());
    }
}
