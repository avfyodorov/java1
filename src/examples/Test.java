package examples;

   class A{
        int x = 2; //Поле родителя
        }

        class B extends A {
        int x = 3; //Поле которое должно перекрыть родительское
        int y = 5; //Поле, которого нет в родительском классе.
        }

public         class Test{
public static void main(String[] args) {
//        A4 ab = new B4(); //Восходящее преобразование
//        System.out.println("Int x = " + ab.x);
        }
        }