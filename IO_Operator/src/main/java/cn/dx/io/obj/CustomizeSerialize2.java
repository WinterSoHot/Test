package cn.dx.io.obj;

import cn.dx.io.dto.Person;

import java.io.*;
import java.util.List;

/**
 * @ClassName CustomizeSerialize2
 * @Description 自定义序列化的第二、三种
 * @Author dongxian
 * @Date 20-5-15 上午10:49
 * @Version 1.0
 **/
public class CustomizeSerialize2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("customizeSerialize3"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("customizeSerialize3"));
        Person p = new Person("李大仙",200);
        oos.writeObject(p);

        List<Object> list = (List<Object>) ois.readObject();
        System.out.println(list);

        // 第四种序列化方式和第二种相似，将类继承ExternalSerializable，实现两个类似第二种的方法
        // 为了防止类的变化，可以提供    private final long serialVersionUID = 512L;作为版本标记
    }
}
