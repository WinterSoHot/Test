package cn.dx.spring.custom;

import cn.dx.spring.annotation.CustomService;
import cn.dx.spring.entity.Student;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/26
 */

@CustomService
public class StudentService {
    private static final Map<String, Student> map = new HashMap<>();

    public void save(Student student) {
        if (map.containsKey(student.getName())) {
            BeanUtils.copyProperties(student, map.get(student.getName()));
        } else {
            map.put(student.getName(), student);
        }
    }

    public List<Student> list() {
        return new ArrayList<>(map.values());
    }
}
