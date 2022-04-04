package misc.massimilian.reflection;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

public abstract class Tester<Integer> {

    public static String tester;
    @NotNull
    @Future
    private int intTest = 0;
    protected double doubleTest;
    private final Tester TEST_TESTER = null;
    private Integer[] objects;
    String string;

    public Tester(int intTest, double doubleTest) {
        this.intTest = intTest;
        this.doubleTest = doubleTest;
    }

    public Tester(int intTest) {
        this.intTest = intTest;
    }

    public Character[] getObjects() {
        return (Character[]) objects;
    }

    @Deprecated
    public void setObjects(Character[] objects) {
        this.objects = (Integer[]) objects;
    }

    private int getIntTest() {
        return 0;
    }

    public void setIntTest(int intTest) {
        this.intTest = intTest;
    }

    public double getDoubleTest() {
        return doubleTest;
    }

    public void setDoubleTest(double doubleTest) {
        this.doubleTest = doubleTest;
    }

    public Tester getTEST_TESTER() {
        return TEST_TESTER;
    }

    public void setString(String string) {
        this.string = string;
    }

    public static String getInfornation() {
        return "No information";
    }

    public String getString() {
        return getInfornation();
    }

    @Past
    public void testMethodOne(int argOne, String argTwo) {
    }

    public int testMethodTwo(Object[] objects) {
        return 0;
    }

    public String testMethodThree(String[] string) {
        return null;
    }

    public short[] testMethodFour () {
        return new short[1];
    }

}
