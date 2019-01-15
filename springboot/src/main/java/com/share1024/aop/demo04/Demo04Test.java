package com.share1024.aop.demo04;

import com.share1024.aop.demo03.PersonService;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/15
 */
@Component
public class Demo04Test {

//    @Autowired
//    private PersonService personService;

    @PostConstruct
    public void init(){

        PersonServiceImpl personService = new PersonServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory(personService);
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("heiheihei before");
            }
        });
        PersonServiceImpl myPerson = (PersonServiceImpl) proxyFactory.getProxy();
        myPerson.say();




    }
}
