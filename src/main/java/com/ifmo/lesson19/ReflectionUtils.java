package com.ifmo.lesson19;

import com.ifmo.lesson7.Utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtils {
    private int var1 = 0;
    private String var2 = "weqweqwe";
    @Exclude
    private String var3 = "3333333";
    private List<String> list = Arrays.asList("111111", "222222", "333333");
    private TestRef ref = new TestRef();

    public static void main(String[] args) throws IllegalAccessException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException {
        ReflectionUtils utils = new ReflectionUtils();
        String string = toString(utils);
        System.out.println(string);

//        ReflectionUtils utils2 = (ReflectionUtils) deepClone(utils);
//        System.out.println(utils.var2 + ": " + utils.var2.hashCode() + ", " + utils.list.toString() + ": " + utils.list.hashCode() + ", " + utils.ref.toString() + ": " + utils.ref.hashCode());
//        System.out.println(utils2.var2 + ": " + utils2.var2.hashCode() + ", " + utils2.list.toString() + ": " + utils2.list.hashCode() + ", " + utils2.ref.toString() + ": " + utils2.ref.hashCode());
    }

    /**
     * Формирует строку аналогично переопределению {@code toString} предложенному IDEA.
     * если метод в классе не переопределен
     *
     * @param object
     * @return
     */

    public static String toString(Object object) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();

        Class<?> cls = object.getClass();

        for (Method method : cls.getDeclaredMethods()) {
            if ("toString".equals(method.getName()) && method.getParameterCount() == 0) return object.toString();
        }

        sb.append(cls.getSimpleName())
                .append("{");


        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (checkExclude(field)) continue;
            field.setAccessible(true);
            sb.append(field.getName())
                    .append("=");
            if (field.getType().isPrimitive()) {
                sb.append(field.get(object).toString());
            } else {
                sb.append(toString(field.get(object)));
            }
            sb.append((i < fields.length - 1) ? ", " : "");
        }

        sb.append("}");

        return sb.toString();
    }

    private static boolean checkExclude(Field field) {
        for (Annotation annotation : field.getAnnotations()) {
            if ("Exclude".equals(annotation.annotationType().getSimpleName())) return true;
        }
        return false;
    }

    /**
     * Клонирует глубоким методом включая List и массивы, при этом использует метод clone в случае,
     * если объект реализует Interface {@code Cloneable}
     *
     * @param object
     * @return
     */
    public static Object deepClone(Object object) throws NoSuchMethodException,
            InvocationTargetException,
            IllegalAccessException,
            InstantiationException {

        Class<?> cls = object.getClass();

        System.out.println(cls.getSimpleName());

        if (cls.isArray()) {
            System.out.println(cls.arrayType());
            return cls.getDeclaredMethod("clone").invoke(object);
        }

        for (Class<?> item : cls.getInterfaces()) {
            if ("Cloneable".equals(item.getSimpleName())) {
                for (Method method : cls.getDeclaredMethods()) {
                    if ("clone".equals(method.getName())) method.invoke(object);
                }
            }
        }

        final Constructor<?> constructor = cls.getConstructor();
        Object resultObj = constructor.newInstance();

        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.toString());
            if(Modifier.isStatic(field.getModifiers())) continue;
            if (field.getType().isPrimitive()) {
                field.set(resultObj, (field.get(object)));
            } else {
                field.set(resultObj, (deepClone(field.get(object))));
            }
        }

        return resultObj;
    }

    private static Object[] cloneArray(Object object) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> cls = object.getClass().arrayType();
        Object[] array = (Object[]) object;
        Object[] resultArray = new Object[array.length];
        int i = 0;
        for (Object obj : array) {
            if (obj.getClass().isPrimitive()) {
                resultArray[i++] = obj;
            } else {
                resultArray[i++] = deepClone(obj);
            }
        }
        return resultArray;
    }

    private Object cloneObject(Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<?> cls = obj.getClass();

        final Constructor<?> constructor = cls.getConstructor();
        Object resultObj = constructor.newInstance();

        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().isPrimitive()) {
                field.set(resultObj, (field.get(obj)));
            } else {
                field.set(resultObj, (deepClone(field.get(obj))));
            }
        }

        return resultObj;
    }


//    @Override
//    public String toString() {
//        return "ReflectionUtils{" +
//                "var1=" + var1 +
//                ", var2='" + var2 + '\'' +
//                '}';
//    }
}
