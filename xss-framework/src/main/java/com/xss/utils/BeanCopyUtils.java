package com.xss.utils;

import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils(){}

    /**
     * @param source 传入的要转化的类
     * @param clazz 返回的结果的类型
     *  BeanUtils.copyProperties是springboot中转化类的类型的方法要求两个转化的类的类型的属性必须都相同
     */
    public static <V> V copyBean(Object source, Class<V> clazz) {
        V result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result);
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     *
     * @param list 传入的要转化的集合
     * @param clazz 转化后每个集合中的对象的类型
     * 该方法使用steam流中map方法将list集合中每一个对象使用copyBean方法转化为clazz
     */
    public static <V> List<V> copyBeanList(List<?> list, Class<V> clazz) {
        return list.stream()
//                .map(new Function<Object, V>() {
//                    @Override
//                    public V apply(Object o) {
//                        return copyBean(o,clazz);
//                    }
//                })
                .map(o -> copyBean(o,clazz))
                .collect(Collectors.toList());
    }
}
