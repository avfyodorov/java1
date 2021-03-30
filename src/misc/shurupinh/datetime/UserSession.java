package misc.shurupinh.datetime;

import java.time.Instant;

import java.util.Random;

public class UserSession {
    private int sessionHandle;
    private String userName;
    private Instant lastAccess;

    public UserSession(String userName) {
        this.userName = userName;
        sessionHandle = new Random().nextInt(1000000);
        lastAccess = Instant.now();
    }


    public int getSessionHandle() {
        return sessionHandle;
    }

    public String getUserName() {
        return userName;
    }

    public Instant getLastAccess() {
        return lastAccess;
    }

    public void updateLastAccess() {
        lastAccess = Instant.now();
    }


    @Override
    public String toString() {
        return "UserSession{" +
                "sessionHandle=" + sessionHandle +
                ", userName='" + userName + '\'' +
                ", lastAccess=" + lastAccess +
                '}';
    }


    public static void main(String[] args) {
        UserSession uS = new UserSession("user1");
        System.out.println(uS.toString());
    }
}
