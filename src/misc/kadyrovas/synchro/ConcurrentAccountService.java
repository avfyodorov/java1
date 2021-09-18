package misc.kadyrovas.synchro;

import java.util.concurrent.locks.ReadWriteLock;

public class ConcurrentAccountService implements AccountService{
    public Account get(String id){
        return FileStoreService.INSTANCE.get(id);
    }

    @Override
    public  double balance(Account account) {
        return FileStoreService.INSTANCE.get(account.getId()).getAmount();
    }

    @Override
    public void deposit(Account account, double amount) {
        ReadWriteLock lock = FileStoreService.INSTANCE.getLock(account);
        lock.writeLock().lock();
            Account accountFound = FileStoreService.INSTANCE.get(account.getId());
            if (accountFound == null) {
                //аккуунт не найден
                lock.writeLock().unlock();
                return;
            }
            double ammountFound = accountFound.getAmount() + amount;
            accountFound.setAmount(ammountFound);
            FileStoreService.INSTANCE.update(accountFound);
        lock.writeLock().unlock();
        FileStoreService.INSTANCE.rewrite();
    }

    @Override
    public void withdraw(Account account, double amount)  {
        ReadWriteLock lock = FileStoreService.INSTANCE.getLock(account);
        lock.writeLock().lock();
            Account accountFound = FileStoreService.INSTANCE.get(account.getId());
            if (accountFound == null) {
                //аккаунт не найден
                lock.writeLock().unlock();
                return;
            }

            double ammountFound = accountFound.getAmount() - amount;
            accountFound.setAmount(ammountFound);
            FileStoreService.INSTANCE.update(accountFound);
        lock.writeLock().unlock();
        FileStoreService.INSTANCE.rewrite();
    }

    @Override
    public void transfer(Account from, Account to, double amount)  {
        ReadWriteLock lockFrom = FileStoreService.INSTANCE.getLock(from);
        ReadWriteLock lockTo = FileStoreService.INSTANCE.getLock(to);
        lockFrom.writeLock().lock();
        lockTo.writeLock().lock();
            Account accountFrom = FileStoreService.INSTANCE.get(from.getId());
            Account accountTo = FileStoreService.INSTANCE.get(to.getId());
            if (accountFrom == null || accountTo == null) {
                //не наден один из аккаунтов
                lockFrom.writeLock().unlock();
                lockTo.writeLock().unlock();
                return;
            }
            double ammountFrom = accountFrom.getAmount() - amount;
            accountFrom.setAmount(ammountFrom);
            double ammountTo = accountTo.getAmount() + amount;
            accountTo.setAmount(ammountTo);
            FileStoreService.INSTANCE.update(accountFrom);
            FileStoreService.INSTANCE.update(accountTo);
        lockFrom.writeLock().unlock();
        lockTo.writeLock().unlock();
        FileStoreService.INSTANCE.rewrite();
    }

}
