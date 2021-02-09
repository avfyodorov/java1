package misc.kadyrovas.reflection;

        import java.util.Objects;

/**
 * Вспомогательный класс для тестирования ClassInspector и GettersAndSetters
 */
public class Employee implements Comparable<Employee> {

   private String name;
   private int age;
   public int salary;
   public static int count;
   public static final String LABEL = "EMPLOYEE";

   /**
    *
    */
   public Employee() {

   }

   /**
    *
    * @param name Имя работника
    * @param age Возраст работника
    */
   private Employee(String name, int age) {
      this.name = name;
      this.age = age;
   }

   /**
    * Конструктор
    * @param name Имя работника
    * @param age Возраст работника
    * @param salary Заработная плата
    */
   public Employee(String name, int age, int salary) {
      this.name = name;
      this.age = age;
      this.salary = salary;
   }

   /**
    *
    * @return Возраст
    */
   public int getAge(){
      return this.age;
   }

   /**
    *
    * @param c Количество работников
    */
   private static void setCount(int c){
      count=count+c;
   }

   /**
    *
    * @return Метка
    */
   public static String printLabel(){
      return LABEL;
   }

   /**
    *
    * @return Имя работника
    */
   public String getName(){
      return name;
   }

   /**
    *
    * @param name Имя работника
    */
   public void setName(String name){
      this.name = name;
   }

   /**
    *
    * @param salary Увеличение заработной платы на salary
    */
   public void increaseSalary(int salary){
      this.salary = this.salary + salary;
   }

   /**
    * Увеличение заработной платы на 100
    */
   public void increaseSalary(){
      this.salary = this.salary + 100;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Employee employee = (Employee) o;
      return age == employee.age &&
              salary == employee.salary &&
              Objects.equals(name, employee.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name, age, salary);
   }

   @Override
   public String toString() {
      return "Employee{" +
              "name='" + name + '\'' +
              ", age=" + age +
              ", salary=" + salary +
              '}';
   }

   @Override
   public int compareTo(Employee o) {
      return name.compareTo(o.name);
   }
}
