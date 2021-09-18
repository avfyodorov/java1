package misc.kadyrovas.synchro;

import java.io.IOException;

public interface AccountService {

    public double balance(Account account) throws IOException;
    public void deposit(Account account, double amount);
    public void withdraw(Account account, double amount);
    public void transfer(Account from, Account to, double amount);

}