package ru.progwards.java2.lessons.annotation;
//В данном классе используются 3 аннотации: "@Before, @After, @Test
//Каждый тестовый метод должен возвращать результат тестирования boolean
public class CalculatorTest {
    Calculator calculator;
@Before
    void createObject(){
    calculator = new Calculator();
}
@After
    void deleteObject() {
    calculator = null;
}

@Test(priority = "2")
    boolean testSum1() {
    return calculator.sum(3,6) == 8;
}

@Test(priority = "1")
    boolean testSum2() {
    return calculator.diff(15,5) == 10;
}

    public static void main(String[] args) {
        CalculatorTest calculatorTest = new CalculatorTest();
        calculatorTest.createObject();
        System.out.println(calculatorTest.testSum1());
        System.out.println(calculatorTest.testSum2());
    }
}