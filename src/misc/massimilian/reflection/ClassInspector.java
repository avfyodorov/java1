package misc.massimilian.reflection;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassInspector {
    private final String threeSpaces = "   ";
    private final String clazz;

    public ClassInspector(String clazz) {
        this.clazz = clazz;
    }

    public static void inspect(String clazz) throws ClassNotFoundException, IOException {
        ClassInspector cl = new ClassInspector(clazz);
        cl.inspect();
    }

    private void inspect() throws ClassNotFoundException, IOException {
        String info = getInfo(this.clazz);
        createFile(info);
    }

    private void createFile(String info) throws IOException {
        Path path = Paths.get("src/reflectiontest/"+clazz + "_ms.java");
        Files.deleteIfExists(path);
        Files.createFile(path);
        Files.writeString(path, info);
    }

    private String getInfo(String clazz) throws ClassNotFoundException {
        return putInfoIntoString(Class.forName(clazz));
    }

    private String putInfoIntoString(Class cl) throws ClassNotFoundException {
        StringBuilder sb = new StringBuilder();
        String modifier = checkMod(Modifier.toString(cl.getModifiers()), true);
        String ext = checkExtends(cl.getSuperclass().getSimpleName());
        String impl = checkImplements(cl.getInterfaces());
        sb.append("package ").append(cl.getPackageName()).append(";");
        sb.append(GettersAndSetters.lineSeparator).append(GettersAndSetters.lineSeparator);
        sb.append(modifier).append(cl.getSimpleName()).append(ext).append(impl).append("{").
                append(GettersAndSetters.lineSeparator).append(GettersAndSetters.lineSeparator);
        setEntryInfo(cl, sb);
        sb.append("}");
        return sb.toString();
    }

    private String checkImplements(Class[] interfaces) {
        String result = "";
        if (interfaces.length > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("implements ");
            for (Class anInterface : interfaces) {
                sb.append(anInterface.getSimpleName()).append(", ");
            }
            result = sb.toString();
            if (interfaces.length == 1) {
                result = result.substring(0, result.length() - 2) + " ";
            }
        }
        return result;
    }

    private String checkExtends(String simpleName) {
        if (simpleName.equals("Object")) {
            return "";
        } else {
            return " extends " + simpleName + " ";
        }
    }

    private String checkMod(String modifier, boolean isClass) {
        String result;
        if (!modifier.equals("public abstract interface") && isClass) {
            result = modifier + " class ";
        } else {
            result = modifier + " ";
        }
        return result;
    }

    private void setEntryInfo(Class cl, StringBuilder sb) throws ClassNotFoundException {
        setVariables(cl.getDeclaredFields(), sb);
        setExecutables(cl.getDeclaredConstructors(), sb);
        sb.append(GettersAndSetters.lineSeparator);
        setExecutables(cl.getDeclaredMethods(), sb);
    }

    private void setVariables(Field[] fields, StringBuilder sb) {
        for (int i = 0; i < fields.length; i++) {
            setVariable(fields[i], sb);
            sb.append(GettersAndSetters.lineSeparator);
        }
        sb.append(GettersAndSetters.lineSeparator);
    }

    private void test(Field field) {
        System.out.println(field.getType().getSimpleName());
    }

    private void setVariable(Field field, StringBuilder sb) {
        StringBuilder sField = new StringBuilder();
        Annotation[] annotations = field.getDeclaredAnnotations();
        setAnnotations(sField, annotations);
        sField.append(checkMod(Modifier.toString(field.getModifiers()), false));
        System.out.println(field.getType().getSimpleName());
        sField.append(field.getType().getSimpleName()).append(" ");
        sField.append(field.getName()).append(";").append(GettersAndSetters.lineSeparator);
        if (sField.charAt(0) == ' ') {
            sField.deleteCharAt(0);
        }
        sb.append(threeSpaces).append(sField);
    }

    private void setAnnotations(StringBuilder sField, Annotation[] annotations) {
        for (int i = 0; i < annotations.length; i++) {
            Class annotation = annotations[i].annotationType();
            sField.append("@").append(annotation.getSimpleName()).append(GettersAndSetters.lineSeparator).append(threeSpaces);
        }
    }

    private void setExecutables(Executable[] exec, StringBuilder sb) throws ClassNotFoundException {
        for (int i = 0; i < exec.length; i++) {
            setExecutable(exec[i], sb);
            sb.append(GettersAndSetters.lineSeparator);
        }
    }

    private void setExecutable(Executable exec, StringBuilder sb) throws ClassNotFoundException {
        StringBuilder sMeth = new StringBuilder();
        Annotation[] annotations = exec.getDeclaredAnnotations();
        setAnnotations(sMeth, annotations);
        String modificator = checkMod(Modifier.toString(exec.getModifiers()), false);
        String type = null;
        if (exec instanceof Method) {
            type = String.valueOf(((Method) exec).getReturnType());
            if (type.startsWith("class ")) {
                type = prepareTypeName(((Method) exec).getReturnType());
            }
        }
        String name = prepareName(exec);
        sMeth.append(modificator);
        if (type != null) {
            sMeth.append(type).append(" ");
        }
        sMeth.append(name);
        Parameter[] params = exec.getParameters();
        Class[] classParams = exec.getParameterTypes();
        sMeth.append("(");
        for (int i = 0; i < params.length; i++) {
            sMeth.append(classParams[i].getSimpleName()).append(" ").append(params[i].getName()).append(", ");
        }
        if (params.length > 0) {
            sMeth.delete(sMeth.length() - 2, sMeth.length());
        }
        sMeth.append(");");
        sb.append(threeSpaces).append(sMeth).append(GettersAndSetters.lineSeparator);
    }

    public String prepareTypeName(Class<?> clas) throws ClassNotFoundException {
        StringBuilder classBuilder = new StringBuilder();
        String typeOfClass = clas.getName();
        if (typeOfClass.startsWith(("["))) {
            if (typeOfClass.startsWith("[L")) {
                String className = clas.getName().substring(2);
                className = className.substring(0, className.length() - 1);
                Class cl = Class.forName(className);
                classBuilder.append(cl.getSimpleName()).append("[]");
            } else {
                switch (typeOfClass) {
                    case "[I":
                        classBuilder.append("int[]");
                        break;
                    case "[B":
                        classBuilder.append("byte[]");
                        break;
                    case "[Z":
                        classBuilder.append("boolean[]");
                        break;
                    case "[C":
                        classBuilder.append("char[]");
                        break;
                    case "[F":
                        classBuilder.append("float[]");
                        break;
                    case "[D":
                        classBuilder.append("double[]");
                        break;
                    case "[L":
                        classBuilder.append("long[]");
                        break;
                    case "[S":
                        classBuilder.append("long[]");
                        break;
                    default:
                        classBuilder.append("UNKNOWN_TYPE[]");
                }
            }
        } else {
            classBuilder.append(typeOfClass.substring(typeOfClass.lastIndexOf(".") + 1));
        }
        return classBuilder.toString();
    }

    private String prepareName(Executable exec) {
        String result = exec.getName();
        if (exec.getName().contains(".")) {
            result = result.substring(result.lastIndexOf("."));
        }
        if (result.startsWith(".")) {
            result = result.substring(1);
        }
        return result;
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
//        ClassInspector.inspect("ru.progwards.java2.lessons.reflection.Tester");
        inspect("Person");
        inspect("Tester");

    }
}
