package cn.dx.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/19
 */
public class CatmullRom {
    private static Point interpolating(List<Point> keyPoints, double u) {
        double f0, f1, f2, f3;
        Point res = new Point();
        double uu = u * u, uuu = uu * u;
        f0 = 0.5 * (-1 * u + 2 * uu - uuu);
        f1 = 0.5 * (2 - 5 * uu + 3 * uuu);
        f2 = 0.5 * (u + 4 * uu - 3 * uuu);
        f3 = 0.5 * (-uu + uuu);
        res.setX(keyPoints.get(0).getX() * f0 + keyPoints.get(1).getX() * f1 + keyPoints.get(2).getX() * f2 + keyPoints.get(2).getX() * f3);
        res.setY(keyPoints.get(0).getY() * f0 + keyPoints.get(1).getY() * f1 + keyPoints.get(2).getY() * f2 + keyPoints.get(2).getY() * f3);
        return res;
    }

    public static List<Point> splinePoints(List<Point> controlPoints, int uNum) {
        List<Point> res = new LinkedList<>();
        int pointNum = controlPoints.size();
        res.add(new Point(controlPoints.get(0).getX(), controlPoints.get(0).getY()));
        List<Point> subControlPoints = new ArrayList<>();
        for (int i = 1; i < pointNum - 2; i++) {
            subControlPoints.add(controlPoints.get(i - 1));
            subControlPoints.add(controlPoints.get(i));
            subControlPoints.add(controlPoints.get(i + 1));
            subControlPoints.add(controlPoints.get(i + 2));

            double delatU = 1.0f / uNum;
            for (int j = 1; j <= uNum; j++) {
                res.add(interpolating(subControlPoints, j * delatU));
            }
            subControlPoints.clear();
        }
        res.add(new Point(controlPoints.get(controlPoints.size() - 1).getX(), controlPoints.get(controlPoints.size() - 1).getY()));
        return res;
    }

    public static List<Point> catmullRomInterpolating(List<Point> keyPoints, int num, int angle, int distance) {
        List<Point> points = CatmullRom.splinePoints(keyPoints, num);
        double sin = Math.sin(Math.toRadians(angle));
        double cos = Math.cos(Math.toRadians(angle));

        Point disPoint = new Point(Math.abs(cos) > 1E-9 ? distance * cos : 0, Math.abs(sin) > 1E-9 ? distance * sin : 0);
        points.forEach((point -> {
            // 根据坐标系设置
            point.setX(point.getX() + disPoint.getX());
            point.setY(point.getY() - disPoint.getY());
        }));
        return points;
    }
}
