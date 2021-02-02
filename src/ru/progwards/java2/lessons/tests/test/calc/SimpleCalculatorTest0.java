package ru.progwards.java2.lessons.tests.test.calc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.progwards.java2.lessons.tests.calc.SimpleCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SimpleCalculatorTest0
{
  @Parameterized.Parameter(0)
public int v1;
  @Parameterized.Parameter(1)
public int v2;
  @Parameterized.Parameter(2)
public int exp;

//public SimpleCalculatorTest(int v1,int v2, int exp)
//{
//  this.v1=v1;
//  this.v2=v2;
//  this.exp=exp;
//}

@Parameterized.Parameters(name = "tst {index}:({0})+({1})={2}")
  public static Iterable<Integer[]> makeData()
{
  return Arrays.asList(new Integer[][]{{1,2,3},{4,5,9}});
}


  SimpleCalculator calc;
  @Before
  public void create()
  {
    calc=new SimpleCalculator();
  }

  @Test
  public void sum()
  {
    Assert.assertEquals (exp,calc.sum(v1,v2));
  }

//  @org.junit.Test
//  public void diff()
//  {
//  }
//
//  @org.junit.Test
//  public void mult()
//  {
//  }
//
//  @org.junit.Test
//  public void div()
//  {
//  }
}