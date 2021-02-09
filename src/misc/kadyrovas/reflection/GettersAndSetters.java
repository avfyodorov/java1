package misc.kadyrovas.reflection;

        import java.lang.reflect.Field;
        import java.lang.reflect.Method;
        import java.lang.reflect.Modifier;
        import java.lang.reflect.Parameter;
        import java.util.ArrayList;

/**
 * Определяет геттеры и сеттеры, необходимые для private полей
 */
public class GettersAndSetters {
   /**
    *
    * @param clazz Исследуемый класс
    * @throws ClassNotFoundException исследуемый класс не был найден
    */
   public static void check(String clazz) throws ClassNotFoundException {
      class Prms {
         String name; //имя
         String type; //тип
         public Prms(String name, String type) {
            this.name = name;
            this.type = type;
         }
      }

      Class insClass = Class.forName(clazz);
      Field[] fields = insClass.getDeclaredFields();
      ArrayList<Prms> listFieldsName = new ArrayList<>();
      String fieldModifiersLine;

      //Формируем listFieldNames с информацией обо всех private полях
      for (Field field : fields) {
         fieldModifiersLine = Modifier.toString(field.getModifiers());
         if (fieldModifiersLine.contains("final"))
            continue;
         if (fieldModifiersLine.contains("private"))
            listFieldsName.add(new Prms(field.getName(),
                    ClassInspector.parseType(field.getGenericType().toString())));
      }

      //Формируем listMethodsName с информацией обо всех public сеттерах и геттерах
      ArrayList<Prms> listMethodsName = new ArrayList<>();
      Method[] methods = insClass.getDeclaredMethods();
      for (Method method : methods) {
         fieldModifiersLine = Modifier.toString(method.getModifiers());
         if (!fieldModifiersLine.contains("public"))
            continue;
         if (fieldModifiersLine.contains("static"))
            continue;
         if (method.getName().substring(0,3).compareTo("set") == 0 && method.getParameterCount() == 1) {
            Parameter[] parameters = method.getParameters();
            listMethodsName.add(new Prms(method.getName(),
                    ClassInspector.parseType(parameters[0].getType().toString())));
         }
         else if (method.getName().substring(0,3).compareTo("get") == 0 && method.getParameterCount() == 0)
            listMethodsName.add(new Prms(method.getName(),
                    ClassInspector.parseType(method.getReturnType().toString())));
      }

      boolean setFound, getFound;
      String lineResult;
      for (Prms prmField: listFieldsName) {
         setFound = false;
         getFound = false;
         for (Prms prmMethod: listMethodsName)
            if (prmField.name.toUpperCase().compareTo(prmMethod.name.substring(3).toUpperCase()) == 0 &&
                    prmMethod.name.substring(0,3).compareTo("set") == 0 &&
                    prmField.type.compareTo(prmMethod.type) == 0) setFound = true;
            else if (prmField.name.toUpperCase().compareTo(prmMethod.name.substring(3).toUpperCase()) == 0 &&
                    prmMethod.name.substring(0,3).compareTo("get") == 0 &&
                    prmField.type.compareTo(prmMethod.type) == 0) getFound = true;
         if (!setFound) {
            lineResult = "public void set" + prmField.name.substring(0,1).toUpperCase() +
                    prmField.name.substring(1).toLowerCase() + "(" + prmField.type + " " +
                    prmField.name.toLowerCase() + ")";
            System.out.println(lineResult);
         }
         if (!getFound) {
            lineResult = "public " + prmField.type + " get" + prmField.name.substring(0,1).toUpperCase() +
                    prmField.name.substring(1).toLowerCase() + "()";
            System.out.println(lineResult);
         }
      }


   }

   public static void main(String[] args) throws ClassNotFoundException {
      check("misc.kadyrovas.reflection.Employee");
   }
}
