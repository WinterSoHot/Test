package cn.dx.io.obj;

import cn.dx.io.dto.Person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @ClassName WriteObject
 * @Description 对象序列化
 * @Author dongxian
 * @Date 20-5-14 上午11:38
 * @Version 1.0
 **/
public class WriteObject {

    public static void main(String[] args) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person"));
        Person p = new Person();
        p.setName("Moumou");
        oos.writeObject(p);
        oos.close();
    }
}
