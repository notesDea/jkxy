package com.jkxy.chapter13;

/**
 * Created by dea on 16-7-2.
 */
public class TestEdible {

    public static void main(String[] args) {
        Object[] objects = {new Chicken(), new Tiger(), new Apple(), new Oriange()};

        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof Edible) {
                System.out.println(((Edible) objects[i]).howToEat());
            }

            if (objects[i] instanceof Animal) {
                System.out.println(((Animal) objects[i]).sound());
            }
        }
        Fruit fruit = new Fruit() {
            @Override
            public String howToEat() {
                return null;
            }

        };

    }
}

abstract class  Animal {

    public abstract String sound();
}

class Chicken extends Animal implements Edible {

    @Override
    public String sound() {
        return "Chicken: cock-a-doodle-doo";
    }

    @Override
    public String howToEat() {
        return "Chicken: Fry it";
    }
}

class Tiger extends Animal {

    @Override
    public String sound() {
        return "RROOAARR";
    }

    //有动物吃不了，所以Animal不能implements Edible
}

abstract class Fruit implements Edible{                 //抽象类不需要重写接口的方法
    //ommited
}

class Apple extends Fruit {

    @Override
    public String howToEat() {
        return "Apple: Make apple cider";
    }
}

class Oriange extends Fruit {

    @Override
    public String howToEat() {
        return "Oriangle: Make oriange juice";
    }
}
