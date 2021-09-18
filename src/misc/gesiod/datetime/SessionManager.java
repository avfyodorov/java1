package misc.gesiod.datetime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SessionManager {
   private List<UserSession> sessions = new ArrayList<>();
   private int sessionValid;

   //создает экземпляр SessionManager и сохраняет sessionValid - период валидности сессии в секундах
   public SessionManager(int sessionValid){
      this.sessionValid = sessionValid;
   }

   //добавляет новую сессию пользователя
   public void add(UserSession userSession){
      sessions.add(userSession);
   }
   //проверяет наличие существующей сессии по userName. Если срок валидности истек,
   // или такой  сессии нет, возвращает null. В противном случае возвращает сессию, обновив ее дату доступа
   public UserSession find(String userName){
      UserSession userSession = null;
      for (int i = 0; i < sessions.size(); i++ ) {
         if (sessions.get(i).getUserName().equals(userName)){
            userSession = sessions.get(i);
            break;
         }
      }
      if (userSession != null && Duration.between(userSession.getLastAccess(), LocalDateTime.now()).toSeconds() < sessionValid){
         userSession.updateLastAccess();
         return userSession;
      }
      return null;
   }
   //проверяет наличие существующей сессии по хендлу. Если срок валидности истек,
   // или такой  сессии нет, возвращает null. В противном случае возвращает сессию, обновив ее дату доступа.
   public UserSession get(int sessionHandle){
      deleteExpired();
      UserSession userSession = null;
      for (int i = 0; i < sessions.size(); i++ ) {
         if (sessions.get(i).getSessionHandle() == sessionHandle){
            userSession = sessions.get(i);
            break;
         }
      }
      if (userSession != null && Duration.between(userSession.getLastAccess(), LocalDateTime.now()).toSeconds() < sessionValid ){
         userSession.updateLastAccess();
         return userSession;
      }
      return null;
   }
   //удаляет указанную сессию пользователя
   public void delete(int sessionHandle){
      for (int i = 0; i < sessions.size(); i++) {
         if (sessions.get(i).getSessionHandle() == sessionHandle){
            sessions.remove(sessions.get(i));
         }
      }
   }
   //удаляет все сессии с истекшим сроком годности
   public void deleteExpired(){
      for (int i = 0; i < sessions.size(); i++) {
         if (Duration.between(sessions.get(i).getLastAccess(), LocalDateTime.now()).toSeconds() >= sessionValid){
            sessions.remove(sessions.get(i--));
         }
      }
   }

   public static void main(String[] args) throws InterruptedException {

      SessionManager sessionManager = new SessionManager(4);
      sessionManager.add(new UserSession("s1"));
      Thread.sleep(2000);
      UserSession us = new UserSession("s3");
      sessionManager.add(us);
      Thread.sleep(2000);
      System.out.println("added: " + sessionManager.sessions.size());

      sessionManager.deleteExpired();
      System.out.println("expired: " + sessionManager.sessions.size());

      sessionManager.delete(us.getSessionHandle());
      System.out.println("del by handle: " + sessionManager.sessions.size());

      us = new UserSession("s0");
      if (sessionManager.find(us.getUserName()) == null) {
         sessionManager.add(us);
         for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("for i: " + i + " " + sessionManager.get(us.getSessionHandle()));
         }
         Thread.sleep(5000);
         System.out.println("after 5000 - " + sessionManager.get(us.getSessionHandle()));

         System.out.println("added 4: " + sessionManager.sessions.size());
         us = new UserSession("s0");
         sessionManager.add(us);
         System.out.println("added 5: " + sessionManager.sessions.size());
      }
   }
}

class UserSession{
   private int sessionHandle;
   private String userName;
   private LocalDateTime lastAccess;

   public int getSessionHandle() {
      return sessionHandle;
   }

   public String getUserName() {
      return userName;
   }

   public LocalDateTime getLastAccess() {
      return lastAccess;
   }

   //обновляет время доступа к сессии
   void updateLastAccess(){
      lastAccess = LocalDateTime.now();
   }

   //сессия пользователя. Внутри автоматически сгенерировать sessionHanle,
   // для примера использовать просто случайное число через класс Random, а так же установить текущее время доступа.
   public UserSession(String userName){
      this.userName = userName;
      this.sessionHandle = (int) (Math.random() * 100000);
      updateLastAccess();
   }

   @Override
   public String toString() {
      return "UserSession{" +
              "sessionHandle=" + sessionHandle +
              ", userName='" + userName + '\'' +
              ", lastAccess=" + lastAccess +
              '}';
   }
}

/*
added: 2
expired: 1
del by handle: 0
for i: 0 UserSession{sessionHandle=791187117, userName='s0', lastAccess=2021-06-01T20:59:50.847332400}
for i: 1 UserSession{sessionHandle=791187117, userName='s0', lastAccess=2021-06-01T20:59:51.869561400}
for i: 2 UserSession{sessionHandle=791187117, userName='s0', lastAccess=2021-06-01T20:59:52.873112}
for i: 3 UserSession{sessionHandle=791187117, userName='s0', lastAccess=2021-06-01T20:59:53.876515700}
for i: 4 UserSession{sessionHandle=791187117, userName='s0', lastAccess=2021-06-01T20:59:54.879884400}
after 5000 - null
added 4: 0
added 5: 1
 */
