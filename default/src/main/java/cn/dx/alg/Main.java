package cn.dx.alg;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/19
 */
public class Main {

    static List<Point> keyPoints = new ArrayList<>();

    static {
        keyPoints.add(new Point(10, 30));
        keyPoints.add(new Point(50, 60));
        keyPoints.add(new Point(80, 100));
        keyPoints.add(new Point(100, 120));
        keyPoints.add(new Point(180, 130));
        keyPoints.add(new Point(250, 30));
        keyPoints.add(new Point(300, 30));
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? " +
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(new MyPanel());
        f.setSize(500, 250);
        f.setVisible(true);
    }

    static class MyPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            g2.setStroke(new BasicStroke(2.0f));
            List<Point> points = CatmullRom.splinePoints(keyPoints, 100);
            GeneralPath path = new GeneralPath();
            path.moveTo(points.get(0).getX(), points.get(0).getY());
            for (int i = 1; i < points.size(); i++) {
                path.lineTo(points.get(i).getX(), points.get(i).getY());
            }
            g2.draw(path);

            g2.setPaint(Color.GREEN);
            List<Point> movePoints = CatmullRom.catmullRomInterpolating(keyPoints, 100, 90, 10);
            GeneralPath movePath = new GeneralPath();
            movePath.moveTo(movePoints.get(0).getX(), movePoints.get(0).getY());
            for (int i = 1; i < movePoints.size(); i++) {
                movePath.lineTo(movePoints.get(i).getX(), movePoints.get(i).getY());
            }
            g2.draw(movePath);

            g2.setPaint(Color.RED);
            for (Point keyPoint : keyPoints) {
                g2.fill(new Ellipse2D.Double(keyPoint.getX() - 5, keyPoint.getY() - 5, 10, 10));
            }
        }
    }
}
