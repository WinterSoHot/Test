package cn.dx.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/6/15
 */
public class GenericCollection {


    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        List<Cat> cats = new ArrayList<>();
        List<Garfield> garfields = new ArrayList<>();

        animals.add(new Animal());
        cats.add(new Cat());
        garfields.add(new Garfield());

        // 测试赋值操作
//        List<? extends Cat> extendsCatFromAnimal = animals;
        List<? super Cat> superCatFromAnimal = animals;

        List<? extends Cat> extendsCatFromCat = cats;
        List<? super Cat> superCatFromCat = cats;
        List<? extends Cat> extendsCatFromGarfield = garfields;
//        List<? super Cat> superCatFromGarfield = garfields;

//        extendsCatFromCat.add(new Animial());
//        extendsCatFromCat.add(new Cat());
//        extendsCatFromCat.add(new Garfield());

//        superCatFromCat.add(new Animal());
        superCatFromCat.add(new Cat());
        superCatFromCat.add(new Garfield());

        Object catExtends2 = extendsCatFromCat.get(0);
        Cat catExtendsl = extendsCatFromCat.get(0);
//        Garfield garfieldl = extendsCatFromGarfield.get(0);

    }
}
