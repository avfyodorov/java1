package misc.kadyrovas.threads;


import java.util.Arrays;

public class StoreServiceImpl implements Runnable {
    final static int COUNTACCOUNT = 5;
    AccountServiceImpl[] account;
    int accountNumber;
    int amountMoney;
    public StoreServiceImpl(AccountServiceImpl[] account, int accountNumber, int amountMoney){
        this.account = account;
        this.accountNumber = accountNumber;
        this.amountMoney = amountMoney;
    }
    @Override
    //банковская транзакция
     public void run() {
        synchronized (account) {
            account[accountNumber].inc(amountMoney);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AccountServiceImpl[] account = new AccountServiceImpl[COUNTACCOUNT];

        Thread[] threads = new Thread[COUNTACCOUNT];
        for (int i = 0; i < COUNTACCOUNT; i++)
            account[i] = new AccountServiceImpl(i * COUNTACCOUNT + 1);

        for (int i = 0; i < COUNTACCOUNT; i ++) {
            int amountMoney = (int) Math.pow((-1),i) * i;
            threads[i] = new Thread(new StoreServiceImpl(account, i, amountMoney));
            threads[i].start();
        }
        for (int i = 0; i < COUNTACCOUNT; i++) {
            threads[i].join();
        }

    }
}
