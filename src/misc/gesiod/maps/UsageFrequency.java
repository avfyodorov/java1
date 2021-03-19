package misc.gesiod.maps;

        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.util.HashMap;
        import java.util.Map;
        import java.util.Scanner;
        import java.util.TreeMap;

//Реализовать класс, подсчитывающий частоту использования слов и букв в словах на основе текстов.
public class UsageFrequency {
   StringBuilder strForLetters = new StringBuilder();

   //загрузить содержимое файла
   public void processFile(String fileName) {
      try {
         FileReader fileReader = new FileReader(fileName);
         Scanner scanner = new Scanner(fileReader);
         while (scanner.hasNextLine()){
            strForLetters.append(scanner.nextLine());
         }
      } catch (FileNotFoundException e){
         System.out.println(e);
      }
   }

   //вернуть Map, который содержит все найденные буквы и цифры, и количество раз,
   // которое встретился каждый искомый символ. Знаки препинания, такие как “.,!? @” и др не учитывать.
   public Map<Character, Integer> getLetters() throws FileNotFoundException {
      Map<Character, Integer> mapForLetters = new TreeMap<>();
      String numbers = "0123456789";
      char[] letters = strForLetters.toString().toCharArray();
      for (int i = 0; i < letters.length; i++) {
         if (Character.isAlphabetic(letters[i]) || numbers.contains(String.valueOf(letters[i]))){
            char ch = letters[i];
            if (mapForLetters.get(ch) == null){
               mapForLetters.put(ch, 1);
            } else {
               int oldValue = mapForLetters.get(ch);
               int newValue = oldValue + 1;
               mapForLetters.replace(ch, oldValue, newValue);
            }
         }
      }
      return mapForLetters;
   }

   //вернуть Map, который содержит все найденные слова и количество раз, которое каждое слово встретилось.
   // Знаки препинания, такие как “.,!? @” и др являются разделителями.
   private static String[] convertStringToWordArray(StringBuilder stringBuilder) {
      String allText = stringBuilder.toString();
      return allText.split(" ");
   }

   private static String getWordWithoutEndingSymbols(String word) {
      String numbers = "0123456789";
      Integer amountOfSymbols = 0;
      char [] wordSymbols = word.toCharArray();
      for (int i = wordSymbols.length - 1; i > 0; i--) {
         if (!Character.isAlphabetic(wordSymbols[i]) ) {
            amountOfSymbols++;
         } else {
            break;
         }
      }
      return word.substring(0, wordSymbols.length - amountOfSymbols);
   }

   public Map<String, Integer> getWords(){
      Map<String, Integer> mapForWords = new TreeMap<>();
      String [] allWords = convertStringToWordArray(strForLetters);
      for (int i = 0; i < allWords.length; i++) {
//            String word = getWordWithoutEndingSymbols(allWords[i]);
         char[] wordWithSymbols = allWords[i].toCharArray();
         if (wordWithSymbols.length == 0 ||
                 !Character.isAlphabetic(wordWithSymbols[0]) ||
                 !Character.isAlphabetic(wordWithSymbols[wordWithSymbols.length-1])
         ){
            break;
         } else {
            if (mapForWords.get(allWords[i]) == null){
               mapForWords.put(allWords[i], 1);
            } else {
               int oldValue = mapForWords.get(allWords[i]);
               int newValue = oldValue + 1;
               mapForWords.replace(allWords[i], oldValue, newValue);
            }
         }
      }
      return mapForWords;
   }

   public static void main(String[] args) throws FileNotFoundException {
      UsageFrequency usageFrequency = new UsageFrequency();
      usageFrequency.processFile("wiki.test.tokens"); // wiki.train.tokens
//        System.out.println(usageFrequency.getLetters());
      System.out.println(usageFrequency.getWords());
   }
}

//{0=63, 1=8, 2=33, 3=1, 4=2, 5=3, 6=6, 7=2, 8=6, A=1, B=17, C=11, D=13, E=2, F=14, G=1, H=28, I=13, J=11, K=2, L=6, M=9, N=4, P=15, R=10, S=24, T=27, W=4, a=275, b=41, c=89, d=150, e=456, f=61, g=52, h=157, i=261, k=115, l=123, m=50, n=343, o=237, p=69, q=1, r=287, s=178, t=254, u=159, v=34, w=53, x=1, y=73, z=1}
//{2000=3, 2001=2, 2002=2, 2003=1, 2004=2, 2005=3, 2006=6, 2007=2, 2008=6, 2010=2, 2011=2, A=1, Ben=5, Bill=4, Blackburn=2, Borough=2, Burn=2, Bush=2, Career=1, Charles=1, Connor=1, Court=2, Craig=2, Curse=4, Daily=2, Darren=1, Dead=2, Derek=2, Doctors=2, Dominic=2, Drum=2, English=1, Evening=1, Factory=2, Firm=2, Fletcher=2, Fraser=3, Fulham=2, Fur=3, Guardian=1, Hall=2, Hamlet=1, Hands=1, Harry=2, He=15, Herald=1, Herons=2, How=4, I=2, In=9, Independent=1, It=1, Jacobi=2, Jason=1, Jimmy=1, John=4, Josh=1, Judge=2, Kent=2, London=4, Long=2, Mark=4, May=2, Mercury=3, National=1, Neil=1, November=2, Paris=4, Parry=1, Philip=2, Playing=1, Plymouth=2, Political=1, Price=1, Punch=3, Ridley=2, Robert=6, Royal=2, Safe=1, Scott=2, Sean=1, Shane=3, Simon=2, Something=1, Sophie=2, Spencer=1, Standard=1, Stanton=2, Steele=1, Stephens=2, Story=2, Strong=2, Sunday=1, Teddy=2, Telegraph=2, The=13, Theatre=6, This=1, Toby=1, Trevor=1, Tyler=1, Waking=2, William=2, a=27, about=1, acting=1, actor=2, actors=2, advisers=1, along=1, alongside=5, amid=1, an=3, and=21, appearance=2, appeared=3, applying=1, arc=2, are=2, as=17, at=8, bill=1, bizarre=1, brings=1, brother=2, but=1, by=17, called=1, cast=1, character=5, co=1, commented=1, confidence=1, critic=1, critical=1, described=1, different=2, difficulties=1, directed=8, doctor=1, doing=1, don=1, emergency=1, episode=8, episodes=3, experience=1, fantastic=1, favorable=1, favorite=1, featured=1, fellow=1, film=3, filmmaker=2, films=2, followed=3, for=2, from=2, giving=1, guest=5, guy=1, had=7, he=4, him=1, his=2, hits=1, horribly=1, identified=1, in=47, inherent=1, intense=1, interview=1, is=4, it=1, know=2, landed=2, like=1, look=1, loved=1, made=1, medical=1, menacing=1, my=1, named=1, noted=2, now=1, of=20, off=1, offer=1, on=14, one=1, part=3, performance=2, performances=1, performed=6, physician=2, play=7, played=1, portrayed=4, portraying=1, present=1, procedures=1, production=3, productions=2, quiet=1, re=2, received=2, recurring=3, review=3, reviews=1, role=12, s=4, series=15, set=1, stage=1, stand=1, starred=14, starring=3, stars=1, strange=1, t=1, taking=1, talking=1, television=16, ten=2, the=50, theatre=5, there=2, through=1, titled=2, to=6, touching=1, triple=1, two=5, unk=96, very=1, vulnerability=1, was=12, what=2, when=1, which=4, who=4, with=4, working=1, written=4, you=7}

//{0=63, 1=8, 2=33, 3=1, 4=2, 5=3, 6=6, 7=2, 8=6, A=1, B=17, C=11, D=13, E=2, F=14, G=1, H=28, I=13, J=11, K=2, L=6, M=9, N=4, P=15, R=10, S=24, T=27, W=4, a=275, b=41, c=89, d=150, e=456, f=61, g=52, h=157, i=261, k=115, l=123, m=50, n=343, o=237, p=69, q=1, r=287, s=178, t=254, u=159, v=34, w=53, x=1, y=73, z=1}
//{=18, Fulham=2, Stanton=2, about=1, episode=8, theatre=5, offer=1, character=5, Daily=2, –=2, starred=14, National=1, Simon=2, Toby=1, Bush=2, ten=2, Story=2, Parry=1, you=7, landed=2, "=58, horribly=1, Herald=1, (=1, )=1, bill=1, received=2, vulnerability=1, ,=43, an=3, .=43, Steele=1, /=2, actors=2, How=4, 2=31, as=17, at=8, triple=1, Bill=4, London=4, Sean=1, guest=5, :=3, ;=3, Political=1, ==18, brother=2, Trevor=1, @=5, A=1, role=12, Jacobi=2, I=2, talking=1, giving=1, two=5, The=13, Royal=2, arc=2, are=2, by=17, procedures=1, Josh=1, quiet=1, a=27, Sophie=2, Telegraph=2, set=1, one=1, Ben=5, amid=1, co=1, actor=2, the=50, stage=1, difficulties=1, series=15, Hall=2, to=6, interview=1, Career=1, Firm=2, Waking=2, Scott=2, but=1, through=1, featured=1, medical=1, emergency=1, had=7, Connor=1, Strong=2, Court=2, Darren=1, brings=1, This=1, menacing=1, 're=2, written=4, Jason=1, inherent=1, which=4, films=2, Harry=2, Dead=2, Fraser=3, William=2, look=1, filmmaker=2, strange=1, advisers=1, Long=2, know=2, Judge=2, doing=1, production=3, for=2, Mark=4, Factory=2, Standard=1, Blackburn=2, now=1, performances=1, November=2, directed=8, Kent=2, was=12, stars=1, noted=2, with=4, what=2, there=2, Fletcher=2, titled=2, he=4, Mercury=3, taking=1, play=7, very=1, named=1, called=1, Teddy=2, John=4, favorable=1, when=1, cast=1, appeared=3, identified=1, critic=1, loved=1, review=3, portrayed=4, Guardian=1, He=15, Neil=1, in=47, made=1, Plymouth=2, is=4, <unk=96, it=1, 's=4, Spencer=1, 't=1, Doctors=2, favorite=1, acting=1, Hamlet=1, don=1, May=2, Evening=1, In=9, portraying=1, guy=1, It=1, Borough=2, physician=2, stand=1, Jimmy=1, Fur=3, alongside=5, touching=1, television=16, Stephens=2, performed=6, off=1, hits=1, appearance=2, fellow=1, Shane=3, bizarre=1, Safe=1, Tyler=1, experience=1, him=1, Punch=3, his=2, reviews=1, Robert=6, Curse=4, from=2, different=2, Something=1, episodes=3, described=1, intense=1, Charles=1, like=1, recurring=3, film=3, my=1, Craig=2, Price=1, Sunday=1, Theatre=6, Derek=2, present=1, who=4, Playing=1, critical=1, part=3, Philip=2, along=1, and=21, of=20, working=1, commented=1, on=14, Hands=1, applying=1, Dominic=2, Drum=2, confidence=1, followed=3, played=1, Burn=2, doctor=1, English=1, Independent=1, performance=2, Ridley=2, starring=3, fantastic=1, productions=2, Herons=2, Paris=4}

