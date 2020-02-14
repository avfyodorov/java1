package ru.progwards.java1.lessons.interfaces;

public interface CompareWeight
{
  CompareResult compareWeight(CompareWeight smthHasWeigt);

  public enum  CompareResult
  {
    LESS, EQUAL, GREATER,
  }

}

