package ru.progwards.java2.lessons.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class JTest {

    class MethodAtributes {
        Method method;
        String annotationName;
        int priority;

        public MethodAtributes(Method method) {
            this.method = method;
            if (method.isAnnotationPresent(Before.class)) {
                this.annotationName = "Before";
                this.priority = -2;
            } else if (method.isAnnotationPresent(After.class)) {
                this.annotationName = "After";
                this.priority = -1;
            } else if (method.isAnnotationPresent(Test.class)) {
                Test annotation = method.getAnnotation(Test.class);
                this.annotationName = "Test";
                this.priority = Integer.valueOf(annotation.priority());
            } else {
                this.annotationName = "";
                this.priority = 0;
            }
        }

        @Override
        public String toString() {
            return this.method.getName() + " " + this.annotationName + " " +
                    (this.priority == 0 ? "" : String.valueOf(this.priority));
        }
    }

    ArrayList<MethodAtributes> listMethods = new ArrayList<>();

    //Метод run через Reflection API получает доступ к классу name и выполняет следующие методы этого класса:
    //1. Метод с аннотацией @Before
    //2. Все методы с аннотацией Test в порядке возрастания приоритета
    //3. Метод с аннотацией After

    void run(String name) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class testClass = Class.forName(name);
        Constructor constructor = testClass.getConstructor();
        Object object = constructor.newInstance();

        int sumBefore = 0;
        int sumAfter = 0;

        MethodAtributes methodAtributes;
        for (Method method : testClass.getDeclaredMethods()) {
            methodAtributes = new MethodAtributes(method);
            if (methodAtributes.priority == 0)
                continue;
            listMethods.add(methodAtributes);
            if (methodAtributes.annotationName.compareTo("Before") == 0)
                sumBefore++;
            else if (methodAtributes.annotationName.compareTo("After") == 0)
                sumAfter++;
        }

        if (sumAfter > 1)
            throw new RuntimeException("В классе обнаружено более одного метода, проаннотированного After");
        else if (sumBefore > 1)
            throw new RuntimeException("В классе обнаружено более одного метода, проаннотированного Before");
        else if (sumAfter == 0)
            throw new RuntimeException("В классе не обнаружено ни одного метода, проаннотированного After");
        else if (sumBefore == 0)
            throw new RuntimeException("В классе не обнаружено ни одного метода, проаннотированного Before");

        //Сортировка listMethods в порядке возрастания приоритетов
        listMethods.sort(Comparator.comparingInt(o -> o.priority));

//        listMethods.forEach(mtd-> System.out.println(mtd));

        Method method = listMethods.get(0).method;
        method.invoke(object);

        //Выполнение методов тестируемого класса с аннотацией @Test
        boolean result;
        for (int i = 2; i < listMethods.size(); i++) {
            method = listMethods.get(i).method;
            result = (Boolean) method.invoke(object);
            if (result)
                System.out.println(method.getName() + "-" + "Ok");
            else
                System.out.println(method.getName() + "-" + "Error");
        }

        method = listMethods.get(1).method;
        method.invoke(object);
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        JTest jTest = new JTest();
        jTest.run("ru.progwards.java2.lessons.annotation.CalculatorTest");
    }
}
