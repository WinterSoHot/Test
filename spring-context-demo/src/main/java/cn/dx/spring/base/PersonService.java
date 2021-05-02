package cn.dx.spring.base;

import cn.dx.spring.entity.Person;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
@Service
public class PersonService {
    private static final Map<String, Person> map = new HashMap<>();

    public void save(Person person) {
        if (map.containsKey(person.getName())) {
            BeanUtils.copyProperties(person, map.get(person.getName()));
        } else {
            map.put(person.getName(), person);
        }
    }

    public List<Person> list() {
        return new ArrayList<>(map.values());
    }
}
