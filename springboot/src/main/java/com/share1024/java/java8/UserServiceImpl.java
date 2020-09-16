package com.share1024.java.java8;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/12/11
 */
public class UserServiceImpl implements UserService {

    public static void main(String[] args) {
        UserServiceImpl user = new UserServiceImpl();
        user.say();
        UserService.sayStatic();

    }

    @Test
    public void test(){
        Consumer c = System.out::println;
        c.accept("sdsdds");
    }


    @Test
    public void functionTest(){
        Function<Integer,Integer> f = s -> ++s;
        Function<Integer,Integer> g = s -> s * 2;
        System.out.println(f.compose(g).apply(1));

        System.out.println(f.andThen(g).apply(1));
    }


    @Test
    public void testPredicate(){
        Predicate<String> p = o -> o.equalsIgnoreCase("test");
        Predicate<String> p2 = o -> o.startsWith("t");
        System.out.println(p.negate().test("test"));
        System.out.println(p.and(p2).test("test"));

        List<String> test = new ArrayList<>();

    }
}
