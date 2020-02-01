import java.util.Objects;

public class Exam
{
  public static void main(String[] args)
  {
    Pair<Integer, String> pair = Pair.of(1, "hello");
    Integer i = pair.getFirst(); // 1
    String s = pair.getSecond(); // "hello"
    System.out.println(i);
    System.out.println(s);

    Pair<Integer, String> pair2 = Pair.of(1, "hello");
    boolean mustBeTrue = pair.equals(pair2); // true!
    boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!
    System.out.println(mustBeTrue);
    System.out.println(mustAlsoBeTrue);



/*    P<String> s=new P("qyqy");
    System.out.println(s.getVal());

    P<Integer> i=new P(123);
    System.out.println(i.getVal());
*/
  }


}

final class Pair<I, S>
{
  private final I i;
  private final S s;
  private Pair(I i,S s)
  {
    this.i=i;
    this.s=s;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pair<?, ?> pair = (Pair<?, ?>) o;
    return Objects.equals(i, pair.i) &&
            Objects.equals(s, pair.s);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(i, s);
  }

  public static <I,S> Pair<I,S> of(I i7, S s7)
  {
    return new Pair<>(i7, s7);
  }
  public I getFirst()
  {
    return i;
  }
  public S getSecond()
  {
    return s;
  }

}
