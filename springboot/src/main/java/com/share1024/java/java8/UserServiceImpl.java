package com.share1024.java.java8;

import org.junit.Test;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/12/11
 */
public class UserServiceImpl implements UserService {

    private int i = 10;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
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

    @Override
    public void say() {

    }

    @Override
    public void player(PlayerAction playerAction) {
        System.out.println("i am player "+playerAction.player(this));
    }

    @Override
    public String test2() {
        return "test2";
    }


    public static void main(String[] args) {
        Optional<UserServiceImpl> userService = Optional.of(new UserServiceImpl());
        userService.get().setI(100);
        System.out.println(userService.get().getI());
    }
}
