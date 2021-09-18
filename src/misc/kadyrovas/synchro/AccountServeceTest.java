package misc.kadyrovas.synchro;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class ThreadTest extends Thread {
    ConcurrentAccountService accountService;

    @Override
    public void run() {
        this.accountService = new ConcurrentAccountService();
        Collection<Account> collection = FileStoreService.INSTANCE.get();

        for (int i = 0; i < 10; i++)
            for (Account value : collection) {
                accountService.deposit(value, 500);
            }
    }
}

public class AccountServeceTest {
    public static void main(String[] args) throws InvalidPointerException {
        Thread[] myThread = new Thread[10];
        for (int i = 0; i < 1; i++) {
            myThread[i] = new ThreadTest();
            myThread[i].start();
        }
        Collection<Account> list = FileStoreService.INSTANCE.get();
        Account lastAccount = null;
        for (Account account : list) {
            lastAccount = account;
        }
        System.out.println(lastAccount.getId());
        FileStoreService.INSTANCE.delete(lastAccount.getId());
    }
}
