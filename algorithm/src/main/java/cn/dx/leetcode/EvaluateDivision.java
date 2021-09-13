package cn.dx.leetcode;

import java.util.*;

/**
 * 399. 除法求值
 * <p>
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * <p>
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * <p>
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 * <p>
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * <p>
 * level: medium
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/evaluate-division
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/1/6
 */
public class EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 构造图结构
        // Map<头，Map<尾，值>>
        Map<String, Map<String, Double>> graph = new HashMap<>();
        Set<String> params = new HashSet<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String key = equation.get(0);
            String value = equation.get(1);
            // 当前公式用到的字符串
            params.addAll(equation);

            // 正方向
            Map<String, Double> val = graph.getOrDefault(key, new HashMap<>());
            val.put(value, values[i]);
            graph.put(key, val);

            // 反方向
            val = graph.getOrDefault(value, new HashMap<>());
            val.put(key, 1 / values[i]);
            graph.put(value, val);
        }
        double[] ret = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String start = query.get(0);
            String end = query.get(1);
            if (!params.contains(start) || !params.contains(end)) {
                ret[i] = -1;
                continue;
            }
            ret[i] = findResult(graph, start, end);
        }
        return ret;
    }

    private double findResult(Map<String, Map<String, Double>> graph, String start, String end) {
        // 记录路径。避免死循环
        Set<String> path = new HashSet<>();
        // 深度优先搜索
        return deepFirstSearch(graph, start, end, 1f, path);
    }

    private double deepFirstSearch(Map<String, Map<String, Double>> graph, String start, String end, double i, Set<String> path) {
        // 找到节点，直接返回i值
        if (start.equals(end)) {
            return i;
        }
        // 重复查找，直接返回
        if (path.contains(start)) {
            return -1;
        }
        // 记录路径
        path.add(start);
        if (graph.containsKey(start)) {
            Map<String, Double> val = graph.get(start);
            // 当前当前节点连接的节点
            for (Map.Entry<String, Double> entry : val.entrySet()) {
                String nextStart = entry.getKey();
                // 值变化
                double v = deepFirstSearch(graph, nextStart, end, i * entry.getValue(), path);
                // 判断是否得到结果
                if (v != -1) {
                    return v;
                }
            }
        }
        // 删除路径
        path.remove(start);
        return -1;
    }

    public static void main(String[] args) {
        EvaluateDivision ed = new EvaluateDivision();
        List<List<String>> equations = new LinkedList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));
        double[] values = new double[]{2.0, 3.0};
        List<List<String>> queries = new LinkedList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));
        double[] ret = ed.calcEquation(equations, values, queries);
        for (double v : ret) {
            System.out.print(v + " ");
        }
    }
}
