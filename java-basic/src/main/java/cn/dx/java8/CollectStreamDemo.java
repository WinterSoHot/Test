package cn.dx.java8;

import cn.dx.java8.entity.Dish;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/6/6
 */
@Slf4j
public class CollectStreamDemo {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        // 计数
        long howManyDish = menu.size();
        log.info("howManyDish = {}", howManyDish);

        // 比较
        Comparator<Dish> dishCaloriesComparator =
                comparingInt(Dish::getCalories);

        Optional<Dish> mostCalorieDish = menu.stream().max(dishCaloriesComparator);
        mostCalorieDish.ifPresent(dish -> log.info("mostCalorieDish = {}", dish));

        // 求和
        int totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();
        log.info("totalCalories = {}", totalCalories);

        // 均值
        double avgCalories = menu.stream().collect(averagingDouble(Dish::getCalories));
        log.info("avgCalories = {}", avgCalories);

        // 统计
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        log.info("menuStatistics = {}", menuStatistics);

        // 字符串
        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        log.info("shortMenu = {}", shortMenu);

        shortMenu = menu.stream().map(Dish::getName).collect(joining(","));
        log.info("shortMenu = {}", shortMenu);

        // 广义的归纳汇总
        // reducing 起始值， 转换函数， 操作
        totalCalories = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        log.info("totalCalories = {}", totalCalories);

        mostCalorieDish = menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
        mostCalorieDish.ifPresent(dish -> log.info("mostCalorieDish = {}", dish));

        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
        // reduce() 不可变的归约
        // collect() 适合可变归约
        List<Integer> numbers = stream.reduce(new ArrayList<>(), (List<Integer> l, Integer e) -> {
            l.add(e);
            return l;
        }, (List<Integer> l1, List<Integer> l2) -> {
            l1.addAll(l2);
            return l1;
        });
        log.info("numbers = {}", numbers);

        /*
         * 使用collect reducing对流求和
         * totalCalories = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (t1, t2) -> t1 + t2));
         */
        log.info("totalCalories = {}", totalCalories);

        // 分组
        Map<Dish.Type, List<Dish>> dishByType = menu.stream().collect(groupingBy(Dish::getType));
        log.info("dishByType = {} ", dishByType);

        Map<CaloricLevel, List<Dish>> dishByCaloric = menu.stream().collect(groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));

        log.info("dishByCaloric = {} ", dishByCaloric);

        // 多级分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(groupingBy(Dish::getType, groupingBy(dish -> {
            // 二级分组
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        })));
        log.info("dishesByTypeCaloricLevel = {} ", dishesByTypeCaloricLevel);

        // 第二个分组可以是其他类型
        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
        log.info("typesCount = {} ", typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                maxBy(comparingInt(Dish::getCalories))));
        log.info("mostCaloricByType = {} ", mostCaloricByType);

        Map<Dish.Type, Dish> mostCaloriesByType2 = menu.stream()
                .collect(groupingBy(Dish::getType,  //  ←─分类函数
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),  //  ←─包装后的收集器
                                Optional::get)));   // ←─转换函数
        log.info("mostCaloriesByType2 = {} ", mostCaloriesByType2);

        Map<Dish.Type, Integer> totalCaloriesByType = menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        log.info("totalCaloriesByType = {} ", totalCaloriesByType);

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> {
                                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                },
                                toSet())));

        log.info("caloricLevelsByType = {} ", caloricLevelsByType);

        //分区是分组的特殊情况：由一个谓词（返回一个布尔值的函数）作为分类函数，它称分区函数
        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(partitioningBy(Dish::isVegetarian));    // ←─分区函数
        log.info("partitionedMenu = {} ", partitionedMenu);

        // 返回所有素食
        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        log.info("vegetarianDishes = {} ", vegetarianDishes);
        // 分区的好处在于保留了分区函数返回true或false的两套流元素列表
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        log.info("vegetarianDishesByType = {} ", vegetarianDishesByType);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream()
                .collect(partitioningBy(Dish::isVegetarian, collectingAndThen(
                        maxBy(comparingInt(Dish::getCalories)),
                        Optional::get
                )));
        log.info("mostCaloricPartitionedByVegetarian = {} ", mostCaloricPartitionedByVegetarian);

        Map<Boolean, Set<Integer>> numberByPrime = IntStream.rangeClosed(0, 100).boxed().collect(partitioningBy(CollectStreamDemo::isPrime, toSet()));
        log.info("numberByPrime = {} ", numberByPrime);


    }

    /**
     * 判断是否为质数
     *
     * @param candidate 待判断数组
     * @return 是否
     */
    public static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
    }

    public enum CaloricLevel {DIET, NORMAL, FAT}
}
