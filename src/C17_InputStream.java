import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

public class C17_InputStream
{
  public static void main(String[] args) throws IOException
  {

    InputStream stream = new ByteArrayInputStream(new byte[]{0x33, 0x45, 0x01});
    System.out.println(checkSumOfStream(stream));
  }

  public static int checkSumOfStream(InputStream inputStream) throws IOException
  {

    // А тут сами пишите)))
    return 0;
  }

  static void codeS()
  {
    StringReader stream=new StringReader("Ы");

  }
}
