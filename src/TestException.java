
public class TestException {
  public static void main(String[] args) {
    System.out.println(getCallerClassAndMethodName());
    anotherMethod();
  }

  private static void anotherMethod() {
    System.out.println(getCallerClassAndMethodName());
  }

  public static String getCallerClassAndMethodName() {
    StackTraceElement[] s= new Throwable().getStackTrace();
    if(s.length<3) return null;
    return s[2].getClassName()+"#"+s[2].getMethodName();// ...
  }
}