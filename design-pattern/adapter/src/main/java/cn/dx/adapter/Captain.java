package cn.dx.adapter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Captain {
    private RowingBoat rowingBoat;

    void row() {
        this.rowingBoat.row();
    }
}
