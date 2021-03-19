package examples.les05;

public interface CompareWeight {
   CompareResult compareWeight(CompareWeight smthHasWeigt);
   double getWeight();

   enum  CompareResult
   {
      LESS, EQUAL, GREATER,
   }

}
