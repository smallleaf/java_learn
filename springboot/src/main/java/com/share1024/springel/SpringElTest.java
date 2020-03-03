package com.share1024.springel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * \* @Author: yesheng
 * \* Date: 2020/3/1 13:01
 * \* Description:
 * \
 */
public class SpringElTest {

    public static void main(String[] args) throws NoSuchMethodException {

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setUsername("yesheng");
        users.add(user);
        context.setVariable("users",users);
        context.setVariable("id","1");
        context.registerFunction("checkContain",
                SpringElTest.class.getDeclaredMethod("checkContain", new Class[] { List.class,String.class}));

        System.out.println(parser.parseExpression(
                "#checkContain(#{ #users },#{ #id })",new TemplateParserContext()).getValue(context));
    }

    public static String reverseString(String input) {
        StringBuilder backwards = new StringBuilder();
        for (int i = 0; i < input.length(); i++){
            backwards.append(input.charAt(input.length() - 1 - i));
         }
        return backwards.toString();
    }


    public static boolean checkContain(List list,String id){

        List<User> list1 = list;
        for (User user : list1) {
            if(user.getId() == Integer.valueOf(id)){
                return true;
            }
        }
        return false;
    }
}