package com.annotation;

import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * @author Y
 */
public class MethodAnnotations {

     @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    static @interface QueryParam {
        String value();
    }

    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    static @interface DefaultValue {
        String value() default "";
    }

    public void hello(@QueryParam("action") String action, @QueryParam("sort") @DefaultValue("asc") String sort){
        // ...
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<?> cls = MethodAnnotations.class;
        // 获取hello这个方法
        Method method = cls.getMethod("hello",new Class[]{String.class,String.class});
        // 获取参数上的注解信息
        Annotation[][] annts = method.getParameterAnnotations();
        for (int i = 0; i < annts.length; i++) {
            System.out.println("annotation for paramter ：" + (i+1));
            Annotation[] anntArr  = annts[i];
            for (Annotation annotation : anntArr) {
                if (annotation instanceof QueryParam) {
                    QueryParam qp = (QueryParam)annotation;
                    System.out.println(qp.annotationType().getSimpleName() + ": " + qp.value());
                } else if (annotation instanceof DefaultValue) {
                    DefaultValue dv = (DefaultValue)annotation;
                    System.out.println(dv.annotationType().getSimpleName() + ": " + dv.value());
                }
            }
        }
    }
    /* 输出
    annotation for paramter ：1
    QueryParam: action
    annotation for paramter ：2
    QueryParam: sort
    DefaultValue: asc
    */

}
