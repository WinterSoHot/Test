package cn.dx.java8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Apple {
    private String name;
    private String color;
    private Integer weight;
}
