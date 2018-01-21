package zad2;

import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;

class Reflex {
    Reflex(String classPackage, String classPath) throws Exception {
        List<Class> classes = getClassFromFile(classPackage, classPath);
        for (Class cl : classes) {
            getClassInfo(cl);
            testClass(cl);
            System.out.println("=========================================================================================");
        }
    }

    private static List<Class> getClassFromFile(String classPackage, String classPath) throws Exception {
        List<Class> tmp = new ArrayList<>();
        File[] files = new File(classPath).listFiles((dir, name) -> name.toLowerCase().endsWith(".class"));
        if (files != null)
            for (File f : files)
                tmp.add(new URLClassLoader(new URL[]{new URL("file:/" + classPath)})
                        .loadClass(classPackage + "." + (f.getName().substring(0, f.getName().lastIndexOf(".")).trim())));
        return tmp;
    }

    private static List<Class> getSuperClasses(Class cl) {
        List<Class> tmp = new ArrayList<>();
        Class klasa = cl.getSuperclass();
        while (klasa != null) {
            tmp.add(klasa);
            klasa = klasa.getSuperclass();
        }

        return tmp;
    }

    private static List<Constructor> getConstructors(Class cl) {
        List<Constructor> tmp = new ArrayList<>();
        for (Constructor constructor : cl.getConstructors())
            if (constructor.getParameterCount() > 0) tmp.add(constructor);
        return tmp;
    }

    private static List<Method> getMethods(Class cl) {
        List<Method> tmp = new ArrayList<>();
        for (Method m : cl.getDeclaredMethods())
            if (m.getParameterCount() > 0 && m.getModifiers() != Modifier.PRIVATE) tmp.add(m);
        return tmp;
    }

    private static List<Field> getFields(Class cl) {
        List<Field> tmp = new ArrayList<>();
        for (Field f : cl.getDeclaredFields())
            if (f.getModifiers() == Modifier.PUBLIC)
                tmp.add(f);
        return tmp;
    }

    private static List<Field> getInheritedFields(Class cl) {
        List<Field> tmp = new ArrayList<>();
        for (Field f : cl.getFields())
            for (Field sF : cl.getSuperclass().getDeclaredFields())
                if (f.equals(sF)) tmp.add(f);
        return tmp;
    }

    private static void getClassInfo(Class cl) {
        System.out.println("Class: " + cl.getName());

        System.out.println("a) Nadklasy:");
        for (Class c : getSuperClasses(cl)) System.out.println("    " + c.getName());

        System.out.println("b) Konstruktory:");
        for (Constructor c : getConstructors(cl)) System.out.println("    " + c);

        System.out.println("c) Nie-prywatni metody:");
        for (Method m : getMethods(cl)) System.out.println("    " + m);

        System.out.println("d) Publiczni pola:");
        for (Field f : getFields(cl)) System.out.println("    " + f);

        System.out.println("e) Pola zadeklarowane w (bezpośredniej) nadklasie:");
        for (Field f : getInheritedFields(cl)) System.out.println("    " + f);

        System.out.println("f) Test:");
    }

    private static void testClass(Class cl) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = null;
        for (Constructor c : getConstructors(cl))
            if (c.getParameterCount() == 1 && Arrays.asList(c.getParameterTypes()).contains(java.lang.String.class))
                constructor = c;

        if (constructor != null) {
            List<Method> methods = new ArrayList<>();
            for (Method m : getMethods(cl))
                if (m.getParameterCount() == 1 && Arrays.asList(m.getParameterTypes()).contains(java.lang.String.class))
                    methods.add(m);

            Object object = constructor.newInstance("LOL");
            System.out.println("Created object of " + cl);

            for (Method method : methods) {
                System.out.println("Invoking " + method + ":");
                System.out.println(method.invoke(object, "HEH"));
            }
        } else
            System.out.println("Niema konstruktora java.lang.String.class");
    }
}
