package misc.kadyrovas.threads;

public class AccountServiceImpl {
    //Банковский счет
    int account;

    public AccountServiceImpl(int account) {
        this.account = account;
    }

    public AccountServiceImpl() {
        this.account = 0;
    }
    public int inc(int amountMoney){
        this.account += amountMoney;
        return this.account;
    }

    @Override
    public String toString() {
        return String.valueOf(account);
    }
}
