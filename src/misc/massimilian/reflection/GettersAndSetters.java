package misc.massimilian.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GettersAndSetters {
    private String result = "";
    public final static String lineSeparator = System.lineSeparator();
    private List<Method> clasMethods = new ArrayList<>();
    private List<Field> clasFields = new ArrayList<>();

    public GettersAndSetters(Class clas) {
        this.clasMethods = Arrays.asList(clas.getDeclaredMethods());
        this.clasFields = Arrays.asList(clas.getDeclaredFields());
    }

    public GettersAndSetters() {
    }

    public static void check(String clazz) throws ClassNotFoundException {
        GettersAndSetters gas = new GettersAndSetters(Class.forName(clazz));
        gas.check();
    }

    private void check() throws ClassNotFoundException {
        findMissingGettersAndSetters();
        System.out.println(result);
    }


    private void findMissingGettersAndSetters() throws ClassNotFoundException {
        for (int i = 0; i < clasFields.size(); i++) {
            findMethod(clasFields.get(i));
        }
    }

    private void findMethod(Field field) throws ClassNotFoundException {
        boolean hasGetter = false;
        boolean hasSetter = false;
        for (int i = 0; i < clasMethods.size(); i++) {
            Method method = clasMethods.get(i);
            if (isCorrectGetMethod(field, method)) {
                hasGetter = true;
            }
            if (isCorrectSetMethod(field, method)) {
                hasSetter = true;
            }
            if (hasGetter && hasGetter) {
                break;
            }
        }
        if (!hasGetter) {
            formGetterOrSetter(field, "get");
        }
        if (!hasSetter) {
            formGetterOrSetter(field, "set");
        }
    }

    private void formGetterOrSetter(Field field, String methodType) throws ClassNotFoundException {
        StringBuilder sb = new StringBuilder();
        String returnType = methodType.equals("set") ? "void" : prepareTypeName(field.getType().getSimpleName());
        String name = methodType + setBigFirstLetter(field.getName());
        sb.append("public ");
        sb.append(returnType).append(" ");
        sb.append(name);
        String argument = methodType.equals("set") ? prepareTypeName(field.getType().getSimpleName()) + " " + field.getName() : "";
        sb.append("(").append(argument).append(");").append(lineSeparator);
        result += sb.toString();
    }

    private String setBigFirstLetter(String name) {
        return String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
    }

    private boolean isCorrectSetMethod(Field field, Method method) throws ClassNotFoundException {
        String desiredName = getCorrectName(field, "set");
        String fieldType = prepareTypeName(field.getType().getSimpleName());
        String parameterName = prepareTypeName(method.getParameterTypes().length != 1 ? null : method.getParameterTypes()[0].getSimpleName());
        boolean correct = Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers()) &&
                method.getReturnType().getName().equals("void") && method.getName().equals(desiredName) &&
                method.getParameterCount() == 1 && fieldType.equals(parameterName);
        return correct;
    }

    private boolean isCorrectGetMethod(Field field, Method method) throws ClassNotFoundException {
        String desiredName = getCorrectName(field, "get");
        String methodType = prepareTypeName(method.getReturnType().getSimpleName());
        String fieldType = prepareTypeName(field.getType().getSimpleName());
        boolean correct = Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers())
                && method.getName().equals(desiredName) && methodType.equals(fieldType) && method.getParameterTypes().length == 0;
        return correct;
    }

    private String prepareTypeName(String name) throws ClassNotFoundException {
        if (name != null && name.startsWith("[")) {
            ClassInspector ci = new ClassInspector(null);
            name = ci.prepareTypeName(Class.forName(name));
        }
        return name;
    }

    private String getCorrectName(Field field, String type) {
        String requiredName = field.getName();
        return type + String.valueOf(requiredName.charAt(0)).toUpperCase() + requiredName.substring(1);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        GettersAndSetters.check("ru.progwards.java2.lessons.reflection.Tester");
    }
}
