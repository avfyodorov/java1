package misc.ansemal.classloader;

import java.util.*;

public class Profiler {
   public static long timeTemp = 0;   // - время работы вложенной секции
   private static Integer countVlozSec = 0;  // - счетчик работающих вложенных секций
   static boolean vlozInc;                      // вход в новую секцию?

   static TreeMap<String, StatisticInfo> profilMapResult = new TreeMap<>();
   static Map<String, Long> rabota = new TreeMap<>();
   static Map <String, LinkedList<Long>> vlozTempTime = new HashMap<>();


   public static void enterSection(String name) {
      countVlozSec++;
      if (countVlozSec > 1 && !vlozInc) {    // если при открытых секциях после закрытия не всех снова пошёл вход
         for (var entry: vlozTempTime.entrySet()) {           // сохраняем время работы вложенных секций до этого
            for (Long time: entry.getValue()) {
               time += timeTemp;
            }
         }
         timeTemp = 0;
      }
      LinkedList <Long> list = vlozTempTime.containsKey(name) ? vlozTempTime.get(name): new LinkedList<>();
      list.push(0L);
      vlozTempTime.put(name, list);
      rabota.put(name, System.nanoTime());
      vlozInc = true;
   }

   public static void exitSection(String name) {
      countVlozSec--;
      long fulltimeS = System.nanoTime()-rabota.get(name);
      long selftimeS = fulltimeS - timeTemp - vlozTempTime.get(name).pop();
      if (vlozTempTime.get(name).isEmpty())
         vlozTempTime.remove(name);
      timeTemp = countVlozSec != 0 ? fulltimeS : 0;

      StatisticInfo stillNot = profilMapResult.putIfAbsent(name, new StatisticInfo(name, fulltimeS, selftimeS, 1));
      if (stillNot != null) {
         profilMapResult.get(name).fullTime += fulltimeS;
         profilMapResult.get(name).selfTime += selftimeS;
         profilMapResult.get(name).count++;
      }
      vlozInc = false;
   }

   public static List<StatisticInfo> getStatisticInfo() {
      ArrayList<StatisticInfo> itog = new ArrayList<>();
      for (var entry: profilMapResult.entrySet())
         itog.add(entry.getValue());
      return itog;
   }
}

