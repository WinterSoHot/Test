package cn.dx;

import java.util.LinkedList;
import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/3/18
 */
public class CatmullSpline {
    /**
     * Catmull-Rom Spline
     *
     * @param points
     * @param count
     * @return
     */
    public List<Point> catmull(List<Point> points, int count) {
        int len = points.size();
        if (len < 4) {
            return points;
        }
        List<Point> ans = new LinkedList<>();
        ans.add(points.get(0));
        for (int i = 1; i < len - 2; i++) {
            Point p0 = points.get(i - 1);
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            Point p3 = points.get(i + 2);
            for (int c = 1; c <= count; c++) {
                float t = c * (1.0f / count);
                float tt = t * t;
                float ttt = tt * t;
                Point newPoint = new Point();
                newPoint.x = (float) (0.5 * (2 * p1.x + (p2.x - p0.x) * t + (2 * p0.x - 5 * p1.x + 4 * p2.x - p3.x) * tt + (3 * p1.x - p0.x - 3 * p2.x + p3.x)
                        * ttt));
                newPoint.y = (float) (0.5 * (2 * p1.y + (p2.y - p0.y) * t + (2 * p0.y - 5 * p1.y + 4 * p2.y - p3.y) * tt + (3 * p1.y - p0.y - 3 * p2.y + p3.y)
                        * ttt));
                ans.add(newPoint);
            }
        }
        ans.add(points.get(len - 1));
        return ans;
    }

    class Point {
        float x;
        float y;

        public Point() {
        }

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }
    }
}
