package cn.dx.io.obj;

import cn.dx.io.dto.Person;
import cn.dx.io.dto.Teacher;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @ClassName ReadTeacher
 * @Description TODO
 * @Author dongxian
 * @Date 20-5-15 上午10:26
 * @Version 1.0
 **/
public class ReadTeacher {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("teacher"));
        Teacher t1 = (Teacher) ois.readObject();
        Teacher t2 = (Teacher) ois.readObject();
        Person p = (Person) ois.readObject();
        Teacher t3 = (Teacher) ois.readObject();

        System.out.println(ReflectionToStringBuilder.toString(t1, ToStringStyle.DEFAULT_STYLE));
        System.out.println(ReflectionToStringBuilder.toString(t2, ToStringStyle.DEFAULT_STYLE));
        System.out.println(ReflectionToStringBuilder.toString(p, ToStringStyle.DEFAULT_STYLE));
        System.out.println(ReflectionToStringBuilder.toString(t3, ToStringStyle.DEFAULT_STYLE));
        System.out.println("判断两个Person是否相同：" + (t1.getStudent() == t2.getStudent()));
        System.out.println("判断两个Teacher是否相同：" + (t2.getStudent() == t3.getStudent()));
    }
}
