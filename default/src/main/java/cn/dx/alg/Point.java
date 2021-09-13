package cn.dx.alg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point implements Cloneable {
    private double x, y;
}
