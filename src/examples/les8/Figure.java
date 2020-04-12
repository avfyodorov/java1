package examples.les8;

public abstract class Figure implements AreaCompare
{
  abstract double perimeter();

  public double area(){return 0d;}

  @Override
  public int compareArea(Figure figure)
  {
    return Double.compare(area(),figure.area());
  }

}

class Segment extends Figure
{
  double a;
  Segment(double a){this.a=a;}
  double perimeter(){return a;}

  public static void main(String[] args)
  {
    AreaCompare areaCompare=new Segment(5);
  }
}
