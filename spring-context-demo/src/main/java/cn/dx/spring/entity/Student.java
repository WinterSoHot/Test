package cn.dx.spring.entity;

import lombok.*;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/26
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    private int id;
    private String name;
    private int age;
    private String grade;
}
