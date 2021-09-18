package ru.progwards.java2.lessons.reflection;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;

public class ClassInspector {

    public static String getParams(Parameter[] parameters) {
        String params = "()";
        if (parameters.length != 0) {
            params = "(";
            for (Parameter p : parameters) {
                params += p.getParameterizedType().getTypeName() + " " + p.getName() + ", ";
            }
            params = params.substring(0, params.length() - 2);
            params += ")";
        }
        return params;
    }

    public static void inspect(String clazz) {
        try {
            Class thisClass = Class.forName(clazz);
            String className = thisClass.getSimpleName();
            Field[] fields = thisClass.getDeclaredFields();
            Constructor[] constructors = thisClass.getDeclaredConstructors();
            Method[] methods = thisClass.getDeclaredMethods();

            try (FileWriter writer = new FileWriter(className + ".java")) {
                writer.write(thisClass.toGenericString() + " {" + System.lineSeparator());

                for (Field f : fields) {
                    String type = f.getGenericType().toString();
                    int index = type.indexOf(" ");
                    if (index != -1) {
                        type = type.substring(index + 1).trim();
                    }
                    writer.write("\t" + Modifier.toString(f.getModifiers()) + " " +
                            type + " " + f.getName() + ";" + System.lineSeparator());
                }
                writer.write(System.lineSeparator());

                for (Constructor c : constructors) {
                    Parameter[] parameters = c.getParameters();
                    writer.write("\t" + Modifier.toString(c.getModifiers()) + " " +
                            className + getParams(parameters) + " {}" + System.lineSeparator());
                }
                writer.write(System.lineSeparator());

                for (Method m : methods) {
                    Parameter[] parameters = m.getParameters();
                    writer.write("\t" + Modifier.toString(m.getModifiers()) + " " +
                            m.getGenericReturnType().getTypeName() + " " +
                            m.getName() + getParams(parameters) + " {}" + System.lineSeparator());
                }
                writer.write("}");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        inspect("java.lang.String");
    }
}
