package cn.dx.spring;

import cn.dx.spring.annotation.CustomScan;
import cn.dx.spring.base.PersonService;
import cn.dx.spring.custom.StudentService;
import cn.dx.spring.entity.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/26
 */
@CustomScan(basePackage = {"cn.dx.spring.custom"})
public class MyMainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyMainApp.class);
        PersonService personService = context.getBean(PersonService.class);
        System.out.println(personService.list());
        personService.save(new Person(1, "张三", 22));
        System.out.println(personService.list());
        StudentService studentService = context.getBean(StudentService.class);
        System.out.println(studentService.list());
    }
}
