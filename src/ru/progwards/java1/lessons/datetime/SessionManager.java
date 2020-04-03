package ru.progwards.java1.lessons.datetime;

//Реализовать класс для хранения пользовательских сессий для сервера,
// который проверяет аутентификацию пользователей. Менеджер работает
// по следующему принципу: при логине (считаем что проверка логин-пароль уже прошла)
// данные о сессии пользователя заносятся в список и возвращается хэндл сессии.
// Затем пользователи запрашивают информацию используя хэндл, авторизация идет по хендлу сессии,
// который валиден определенное время, с момента крайнего запроса. Проверка сессии по хендлу должна
// работать максимально быстро. У каждого пользователя может быть только одна сессия.

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class SessionManager
{
  private int sessionValid;

  public SessionManager(int sessionValid)
// и сохраняет sessionValid - период валидности сессии в секундах.
  {
    this.sessionValid = sessionValid;
  }
 
  private Map<Integer, UserSession> sessions = new TreeMap<>();

  private boolean isValid(UserSession userSession)
  {
    Duration duration = Duration.between(userSession.getLastAccess(), LocalDateTime.now());
    return duration.compareTo(Duration.ofSeconds(sessionValid)) < 0;
  }

  public void add(UserSession userSession)
//        - добавляет новую сессию пользователя
  {
    sessions.put(userSession.getSessionHandle(), userSession);
  }

  public UserSession find(String userName)
//        - проверяет наличие существующей сессии
//        по userName. Если срок валидности истек, или такой  сессии нет, возвращает null.
//        В противном случае возвращает сессию, обновив ее дату доступа.
  {
    Collection<UserSession> list = sessions.values();
    for (UserSession us : list)
    {
      if (userName.compareTo(us.getUserName()) == 0)
      {
        if (isValid(us))
        {
          us.updateLastAccess();
          return us;
        } else
          return null;
      }
    }
    return null;
  }

  public UserSession get(int sessionHandle)
//        - проверяет наличие существующей сессии
//        по хендлу. Если срок валидности истек, или такой  сессии нет, возвращает null.
//        В противном случае возвращает сессию, обновив ее дату доступа.
  {
    if (!sessions.containsKey(sessionHandle))
      return null;

    UserSession us = sessions.get(sessionHandle);
    if (!isValid(us))
    {
      delete(sessionHandle);
      return null;
    }

    us.updateLastAccess();
    return us;
  }

  public void delete(int sessionHandle)
  //- удаляет указанную сессию пользователя
  {
    sessions.remove(sessionHandle);
  }

  public void deleteExpired()
  //- удаляет все сессии с истекшим сроком годности.
  {
    Collection<UserSession> list = sessions.values();
    sessions = new TreeMap<>();

    for (UserSession us : list)
    {
      if (isValid(us))
        add(us);
    }

  }

//        Протестировать следующим образом:
//
//        Создать сессию по userName, для этого
//        - сделать find,
//        - убедиться что вернется null
//        - создать новую сессию
//        - добавить используя add
//
//        Вызвать несколько раз get
//
//        Подождать (Thread.sleep) время, большее, чем время валидности
//
//        Проверить что сессии нет через метод get
//-----------------------------------------------------
//        Создать еще одну сессию
//
//        Подождать половину времени валидности сессии
//
//        Создать еще одну сессию
//
//        Подождать еще раз половину времени валидности сессии
//
//        Вызвать deleteExpired()
//
//        Убедиться, что одна сессия удалена, вторая нет
//
//        Удалить оставшуюся через метод delete
//
//        Убедиться что удаление прошло

  public static void main(String[] args) throws InterruptedException
  {
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
    if (sessionManager.find(us.getUserName()) == null)
    {
      sessionManager.add(us);
      for (int i = 0; i < 5; i++)
      {
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

class UserSession
{
  private int sessionHandle;
  private String userName;
  private LocalDateTime lastAccess;

  public int getSessionHandle()
  {
    return sessionHandle;
  }

  public String getUserName()
  {
    return userName;
  }

  public LocalDateTime getLastAccess()
  {
    return lastAccess;
  }

  void updateLastAccess()
  {
    lastAccess = LocalDateTime.now();
  }

  public UserSession(String userName)
//     создать сессию пользователя.Внутри автоматически сгенерировать sessionHanle, для
//     примера использовать просто случайное число через класс Random, а так же
//     установить текущее  время доступа.
  {
    this.userName = userName;
    updateLastAccess();
    Random random = new Random();
    sessionHandle = random.nextInt();
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

