package misc.gesiod.maps;


        import java.math.BigDecimal;
        import java.util.Date;
        import java.util.Map;

public class FiboMapCache {
   private Map<Integer, BigDecimal> fiboCache;
   private boolean cacheOn;

   //При cacheOn = true кэш работает, при cacheOn = false - выключен
   public FiboMapCache(boolean cacheOn){
      this.cacheOn = cacheOn;
   }

   // Вычисляем число Фибоначчи
   private BigDecimal fibo(int n){
      if (n == 0){
         return new BigDecimal(0);
      }
      BigDecimal f0 = new BigDecimal(0);
      BigDecimal f1 = new BigDecimal(1);
      BigDecimal temp = new BigDecimal(0);
      for (int i = 0; i < n; i++) {
         temp = f1;
         f1 = f1.add(f0);
         f0 = temp;
      }
      return f1;
   }

   // проверить, находится ли вычисленное значение для n в кэше,
   // и если да - вернуть его из кэша, если нет - рассчитать и добавить в кэш.
   // Учитывать значение переменной cacheOn
   public BigDecimal fiboNumber(int n){
      if (cacheOn == true){
         BigDecimal bigDecimal = fiboCache.get(n);
         if (bigDecimal == null){
            BigDecimal hashValue = this.fibo(n);
            fiboCache.put(n, hashValue);
            return hashValue;
         }
         return bigDecimal;

      }
      else {
         return this.fibo(n);
      }
   }
   public void clearCahe(){
      fiboCache.clear();
   }
   // тест для расчета чисел Фибоначчи от n = 1 до 1000 включительно и замерить разницу во времени
   // с on = true и on = false, результат вывести на экран в формате "fiboNumber cacheOn=???
   // время выполнения ???" для cacheOn=true и cacheOn=false, вместо ??? вывести реальные значения в мсек.
   public static void test(){
      FiboMapCache fiboMapCacheOn = new FiboMapCache(true);
      var startTime = new Date().getTime();
      for (int i = 1; i <= 1000; i++) {
         fiboMapCacheOn.fiboNumber(i);
      }
      System. out.println( "fiboNumber cacheOn=true время выполнения " + (new Date().getTime() - startTime));
      FiboMapCache fiboMapCacheOff = new FiboMapCache(false);
      startTime = new Date().getTime();
      for (int i = 1; i <= 1000; i++) {
         fiboMapCacheOn.fiboNumber(i);
      }
      System. out.println( "fiboNumber cacheOn=false время выполнения " + (new Date().getTime() - startTime));
   }

   public static void main(String[] args) {
      test();


   }
}
