package cn.dx.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 柱子
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/13
 */
public class Column {
    BufferedImage image;
    int x, y;
    int width, height;
    /**
     * 柱子之间得缝隙
     */
    int gap;

    /**
     * 柱子之间得距离
     */
    int distance;

    Random random = new Random();

    public Column(int n) throws IOException {
        image = ImageIO.read(getClass().getResource("/column.png"));
        width = image.getWidth();
        height = image.getHeight();
        gap = 144;
        distance = 245;
        x = 550 + (n - 1) * distance;
        y = random.nextInt(218) + 132;
    }

    public void step() {
        x--;
        if (x == -width / 2) {
            // 移动柱子
            x = distance * 2 - width / 2;
            y = random.nextInt(218) + 132;
        }
    }
}
