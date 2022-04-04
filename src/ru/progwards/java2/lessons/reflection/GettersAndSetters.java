package ru.progwards.java2.lessons.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class GettersAndSetters {

    public static boolean isHasGetter(String type, String name, Method[] methods) {
        String nameUpperCase = name.substring(0, 1).toUpperCase() + name.substring(1);
        for (Method m : methods) {
            Parameter[] parameters = m.getParameters();
            if (m.getName().equals("get" + nameUpperCase)) {
                if (!Modifier.toString(m.getModifiers()).contains("static")) {
                    if (parameters.length == 0) {
                        if (m.getGenericReturnType().getTypeName().equals(type)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isHasSetter(String type, String name, Method[] methods) {
        String nameUpperCase = name.substring(0, 1).toUpperCase() + name.substring(1);
        for (Method m : methods) {
            Parameter[] parameters = m.getParameters();
            if (m.getName().equals("set" + nameUpperCase)) {
                if (!Modifier.toString(m.getModifiers()).contains("static")) {
                    if (parameters.length == 1) {
                        if (parameters[0].getParameterizedType().getTypeName().equals(type)) {
                            return true;
                        }
                    }
                }
            }
        }
        return  false;
    }

    public static void check(String clazz) {
        try {
            Class thisClass = Class.forName(clazz);
            Field[] fields = thisClass.getDeclaredFields();
            Method[] methods = thisClass.getMethods();
            int count = 0;
            String[] types = new String[fields.length];
            String[] names = new String[fields.length];
            for (Field f : fields) {
                if (Modifier.toString(f.getModifiers()).contains("private")) {
                    types[count] = f.getGenericType().toString();
                    int index = types[count].indexOf(" ");
                    if (index != -1) {
                        types[count] = types[count].substring(index + 1).trim();
                    }
                    names[count] = f.getName();
                    count++;
                }
            }
            String out = "";
            for (int i = 0; i < count; i++) {
                String nameUpperCase = names[i].substring(0, 1).toUpperCase() + names[i].substring(1);
                if (!isHasGetter(types[i], names[i], methods)) {
                    out += "public " + types[i] + " get" + nameUpperCase + "() \n";
                }
                if (!isHasSetter(types[i], names[i], methods)) {
                    out += "public void set" + nameUpperCase + "(" + types[i] + " " + names[i] + ") \n";
                }
            }
            System.out.println(out);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        check("reflectiontest.Person");
    }
}
