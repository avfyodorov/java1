package examples.telebot;

// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

/*
        import com.google.common.collect.TreeMultimap;
        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;
        import org.telegram.telegrambots.ApiContextInitializer;
        import org.telegram.telegrambots.bots.TelegramLongPollingBot;
        import org.telegram.telegrambots.meta.TelegramBotsApi;
        import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
        import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
        import org.telegram.telegrambots.meta.api.objects.Message;
        import org.telegram.telegrambots.meta.api.objects.Update;
        import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
        import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class ProgwardsTelegramBot extends TelegramLongPollingBot {
   public String username;
   public String token;
   private List<ProgwardsTelegramBot.Association> associations = new ArrayList();
   private TreeMultimap<Integer, String> found;

   public ProgwardsTelegramBot() {
      ApiContextInitializer.init();
   }

   public TreeMultimap<Integer, String> getFound() {
      return this.found;
   }

   public void addTags(String name, String tags, String description) {
      this.associations.add(new ProgwardsTelegramBot.Association(name, tags, description));
   }

   public void checkTags(String text) {
      this.found = this.findAssociation(text);
   }

   public boolean checkLastFound(String text) {
      return this.getLastFound().toLowerCase().equals(text.toLowerCase());
   }

   public String getLastFound() {
      Object[] a = this.found.values().toArray();
      return a.length > 0 ? (String)a[a.length - 1] : "";
   }

   public int foundCount() {
      return this.found.size();
   }

   public Integer getLastWeight() {
      Object[] a = this.found.keys().toArray();
      return a.length > 0 ? (Integer)a[a.length - 1] : 0;
   }

   public String extract(int n) {
      Object[] vals = this.found.values().toArray();
      Object[] keys = this.found.keys().toArray();
      String res = "";
      int max = this.getLastWeight();

      for(int i = 0; i < vals.length; ++i) {
         if (max == 1 || (Integer)keys[i] > 1) {
            res = res + vals[i].toString() + "\n";
         }
      }

      return res;
   }

   public String extract() {
      return this.extract(this.found.size());
   }

   private int findAssociation(ProgwardsTelegramBot.Association ass, String text) {
      int weight = 0;
      if (text.toLowerCase().contains(ass.name.toLowerCase())) {
         weight += 10;
      }

      String[] tags = ass.tags.split(",");
      String[] var8 = tags;
      int var7 = tags.length;

      for(int var6 = 0; var6 < var7; ++var6) {
         String s = var8[var6];
         if (text.toLowerCase().contains(s.trim().toLowerCase())) {
            ++weight;
         }
      }

      return weight;
   }

   public TreeMultimap<Integer, String> findAssociation(String text) {
      TreeMultimap<Integer, String> result = TreeMultimap.create();
      Iterator var4 = this.associations.iterator();

      while(var4.hasNext()) {
         ProgwardsTelegramBot.Association ass = (ProgwardsTelegramBot.Association)var4.next();
         int weight = this.findAssociation(ass, text);
         if (weight > 0) {
            result.put(weight, ass.name);
         }
      }

      return result;
   }

   public String getBotToken() {
      return this.token;
   }

   public String getBotUsername() {
      return this.username;
   }

   public void onUpdateReceived(Update update) {
      try {
         if (update.hasMessage() && update.getMessage().hasText()) {
            Message inMessage = update.getMessage();
            String text = inMessage.getText();
            String name = "";
            String desc = "";
            for(int i=0; i< associations.size(); i++){
               String img = String.valueOf(associations.get(i).tags);
               if (text.equals(img)){
                  name = associations.get(i).name;
                  desc = associations.get(i).description;
               }
            }

            if(name.contains("https") || name.contains("http")){
               SendMessage outMessage = new SendMessage();
               outMessage.setChatId(inMessage.getChatId());
               SendPhoto photo = new SendPhoto();
               photo.setChatId(inMessage.getChatId());
               photo.setPhoto(name);
               outMessage.setText(desc);
               this.execute(photo);
               this.execute(outMessage);
            }
            else {
               SendMessage outMessage = new SendMessage();
               outMessage.setChatId(inMessage.getChatId());
               outMessage.setText(this.processMessage(text));
               this.execute(outMessage);
            }
         }
      } catch (TelegramApiException var5) {
         var5.printStackTrace();
      }
   }

   public String processMessage(String text) {
      return text;
   }

   public void start() {
      TelegramBotsApi botsApi = new TelegramBotsApi();

      try {
         botsApi.registerBot(this);
      } catch (TelegramApiRequestException var3) {
         var3.printStackTrace();
      }

   }

   static class Association {
      public String name;
      public String tags;
      public String description;

      public Association(String name, String tags, String description) {
         this.name = name;
         this.tags = tags;
         this.description = description;
      }
   }
}

*/