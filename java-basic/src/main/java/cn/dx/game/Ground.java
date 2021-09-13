package cn.dx.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/13
 */
public class Ground {
    BufferedImage image;
    /**
     * 位置
     */
    int x, y;
    int width, height;

    public Ground() throws IOException {
        image = ImageIO.read(getClass().getResource("/ground.png"));
        width = image.getWidth();
        height = image.getHeight();

        x = 0;
        y = 500;
    }

    /**
     * 地面向左移动
     */
    public void step() {
        x--;
        if (x == -100) {
            x = 0;
        }
    }
}
