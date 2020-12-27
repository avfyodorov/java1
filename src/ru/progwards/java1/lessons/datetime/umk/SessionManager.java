package ru.progwards.java1.lessons.datetime.umk;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class SessionManager {

   private ArrayList<UserSession1> sessions = new ArrayList<>();
   private int sessionValid;

   public SessionManager(int sessionValid) {
      this.sessionValid = sessionValid;
   }

   public void add(UserSession1 userSession) {
      sessions.add(userSession);
   }

   public UserSession1 find(String userName) {
      for (int i = 0; i < sessions.size(); i++) {
         UserSession1 us = sessions.get(i);
         LocalDateTime l = us.getLastAccess();
         long h = l.getHour();
         long m = l.getMinute();
         long s = l.getSecond();
         long s1 = (h * 3600) + (m * 60) + s;
         if (us.getUserName().equals(userName) && s1 < sessionValid) {
            us.updateLastAccess();
            return us;
         }

      }
      return null;
   }

   public UserSession1 get(int sessionHandle) {
      for (int i = 0; i < sessions.size(); i++) {
         UserSession1 us = sessions.get(i);
         LocalDateTime l = us.getLastAccess();
         long h = l.getHour();
         long m = l.getMinute();
         long s = l.getSecond();
         long s1 = (h * 3600) + (m * 60) + s;
         if (us.getSessionHandle() == sessionHandle && s1 < sessionValid) {
            us.updateLastAccess();
            return us;
         }

      }
      return null;
   }

   public void delete(int sessionHandle) {
      for (int i = 0; i < sessions.size(); i++) {
         UserSession1 us = sessions.get(i);
         if (us.getSessionHandle() == sessionHandle) {
            sessions.remove(us);
         }
      }
   }

   public void deleteExpired() {
      for (int i = 0; i < sessions.size(); i++) {
         UserSession1 us = sessions.get(i);
         LocalDateTime l = us.getLastAccess();
         long h = l.getHour();
         long m = l.getMinute();
         long s = l.getSecond();
         long s1 = (h * 3600) + (m * 60) + s;
         if (s1 < sessionValid) {
            sessions.remove(us);
         }

      }
   }

   public static void main(String[] args) throws InterruptedException {
      SessionManager sessionManager = new SessionManager(4);
      sessionManager.add(new UserSession1("s1"));
      Thread.sleep(2000);
      UserSession1 us = new UserSession1("s3");
      sessionManager.add(us);
      Thread.sleep(2000);
      System.out.println("added: " + sessionManager.sessions.size());

      sessionManager.deleteExpired();
      System.out.println("expired: " + sessionManager.sessions.size());

      sessionManager.delete(us.getSessionHandle());
      System.out.println("del by handle: " + sessionManager.sessions.size());

      us = new UserSession1("s0");
      if (sessionManager.find(us.getUserName()) == null) {
         sessionManager.add(us);
         for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("for i: " + i + " " + sessionManager.get(us.getSessionHandle()));
         }
         Thread.sleep(5000);
         System.out.println("after 5000 - " + sessionManager.get(us.getSessionHandle()));

         System.out.println("added 4: " + sessionManager.sessions.size());
         us = new UserSession1("s0");
         sessionManager.add(us);
         System.out.println("added 5: " + sessionManager.sessions.size());

      }

   }
}

class UserSession1 {


   private int sessionHandle;
   private String userName;
   private LocalDateTime lastAccess;

   public int getSessionHandle() {
      return sessionHandle;
   }

   public LocalDateTime getLastAccess() {
      return lastAccess;
   }

   public String getUserName() {
      return userName;
   }

   public void updateLastAccess() {
      lastAccess = LocalDateTime.now();
   }

   public UserSession1(String userName) {
      this.userName = userName;
      Random rand = new Random();
      this.sessionHandle = rand.nextInt(100);
      this.lastAccess = LocalDateTime.now();
   }

   @Override
   public String toString()
   {
      return "UserSession{" +
              "sessionHandle=" + sessionHandle +
              ", userName='" + userName + '\'' +
              ", lastAccess=" + lastAccess +
              '}';
   }

}

