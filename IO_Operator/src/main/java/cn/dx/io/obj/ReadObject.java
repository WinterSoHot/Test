package cn.dx.io.obj;

import cn.dx.io.dto.Person;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @ClassName ReadObject
 * @Description TODO
 * @Author dongxian
 * @Date 20-5-14 上午11:44
 * @Version 1.0
 **/
public class ReadObject {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person"));
        Person p = (Person) ois.readObject();
        System.out.println(ReflectionToStringBuilder.toString(p, ToStringStyle.MULTI_LINE_STYLE));
    }
}
