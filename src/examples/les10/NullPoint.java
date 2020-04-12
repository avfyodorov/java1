package examples.les10;

import java.io.IOException;

public class NullPoint
{
  public static Integer sqr(Integer n)
  {
    try
    {
      return (n*n);
    }
    catch (NullPointerException e)
    {
      return -1;
    }
  }

  public String test(String filename) throws IOException
  {
    if (filename==null)
      throw new IOException("File not found");

    return "File processing";
  }

  public static void main(String[] args)
  {
    System.out.println(sqr(null));
  }
}
