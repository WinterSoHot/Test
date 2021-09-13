package cn.dx;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/4/7
 */
public class Main11 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        Map<String, String> parent = new HashMap<>(n);
        Map<String, String> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            String s = in.nextLine();
            String[] con = s.split(" ");
            parent.put(con[0], con[0]);
            map.put(con[0], con[1]);
        }
        int count = n;
        for (Map.Entry<String, String> item : map.entrySet()) {
            boolean res = union(parent, item.getKey(), item.getValue());
            if (res) {
                count--;
            }
        }
        System.out.println(count);
    }

    public static boolean union(Map<String, String> parent, String p, String q) {
        String py = find(parent, p);
        String qy = find(parent, q);
        if (!py.equals(qy)) {
            parent.put(py, qy);
            return true;
        }
        return false;
    }

    public static String find(Map<String, String> parent, String p) {
        String py = parent.getOrDefault(p, p);
        if (!py.equals(p)) {
            parent.put(p, find(parent, py));
        }
        return parent.getOrDefault(p, p);
    }
}
