package misc.ansemal.classloader;

public class StatisticInfo {
   public String sectionName; // - имя секции
   public long fullTime; // - полное время выполнения секции в наносекундах.
   public long selfTime; // - чистое время выполнения секции в наносекундах. Для вложенных секций, из времени выполнения внешней секции нужно вычесть времена выполнения вложенных секций.
   public int count;

   @Override
   public String toString() {
      return "StatisticInfo{" +
              "sectionName='" + sectionName + '\'' +
              ", fullTime=" + fullTime/1000000 +         // для перевода в милисекунды
              ", selfTime=" + selfTime/1000000 +         // для перевода в милисекунды
              ", count=" + count +
              '}';
   }

   public StatisticInfo(String sectionName, long fullTime, long selfTime, int count) {
      this.sectionName = sectionName;
      this.fullTime = fullTime;
      this.selfTime = selfTime;
      this.count = count;
   }
}

