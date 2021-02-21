package cn.dx.java.collection;

import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/12
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Person, String> pdata = new TreeMap<>();
        pdata.put(new Person("张三", 30), "zhangsan");
        pdata.put(new Person("李四", 20), "lisi");
        pdata.put(new Person("王五", 10), "wangwu");
        pdata.put(new Person("小红", 5), "xiaohong");
        System.out.println(pdata.firstKey());
        Set<Person> keys = pdata.keySet();
        for (Person key : keys) {
            System.out.println(key.getAge() + "-" + key.getName());
        }
    }


    static class Person implements Comparable<Person> {
        private String name;
        private int age;

        public Person(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Person person = (Person) o;

            if (age != person.age) {
                return false;
            }
            return Objects.equals(name, person.name);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + age;
            return result;
        }

        /**
         * T重写compareTo方法实现按年龄来排序
         */
        @Override
        public int compareTo(Person o) {
            if (this.age > o.getAge()) {
                return 1;
            }
            if (this.age < o.getAge()) {
                return -1;
            }
            return 0;
        }
    }
}
