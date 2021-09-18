package misc.kadyrovas.http;

import com.google.inject.internal.cglib.core.$ClassInfo;
import misc.kadyrovas.synchro.Account;
import misc.kadyrovas.synchro.AccountService;
import misc.kadyrovas.synchro.ConcurrentAccountService;
import misc.kadyrovas.synchro.FileStoreService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AtmClient implements AccountService, Runnable {
    private List<String>listLines = new ArrayList<>();

    private void connectToServer(String request) {
        //Обеспечивает связь с сервером и обмен сообщениями
        //Все принимаемые сообщения от сервера записываются в listLines
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 80);
            InputStream is = client.getInputStream();
            OutputStream os = client.getOutputStream();

        Scanner scanner = new Scanner(is);

        PrintWriter pw = new PrintWriter(os);
        pw.println(request);
        pw.flush();

        listLines.clear();
        while (scanner.hasNextLine()) {
            listLines.add(scanner.nextLine());
            if (listLines.get(listLines.size() - 1).compareTo("") == 0)
                break;
        }
        listLines.add(scanner.nextLine());
        client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    @Override
    public double balance(Account account)  {
        String request = "GET/GetBalance?id=" + account.getId() +
                "&pin=" + account.getPin() + " HTTP/1.1\n" +
                "hostname: localhost\n\n";
        connectToServer(request);

        if (listLines.get(0).endsWith("OK")){
            String line = listLines.get(listLines.size() - 1);
            line = line.replaceAll("Your balance is", "");
            line = line.replaceAll("rubles", "");
            line = line.trim();
            return Double.valueOf(line);
        }
        else
            return 0;

    }

    @Override
    public void deposit(Account account, double amount) {
        String request = "GET/Deposit?id=" + account.getId() +
                "&pin=" + account.getPin() + "&amount=" + amount + " HTTP/1.1\n" +
                "hostname: localhost\n\n";
        connectToServer(request);
    }

    @Override
    public void withdraw(Account account, double amount) {
        String request = "GET/Withdraw?id=" + account.getId() +
                "&pin=" + account.getPin() + "&amount=" + amount + " HTTP/1.1\n" +
                "hostname: localhost\n\n";
        connectToServer(request);
    }

    @Override
    public void transfer(Account from, Account to, double amount) {
        String request = "GET/Transfer?id=" + from.getId() +
                "&pin=" + from.getPin() + "&amount=" + amount +
                "&id2=" + to.getId() + " HTTP/1.1\n" +
                "hostname: localhost\n\n";
        connectToServer(request);
    }

    @Override
    public void run() {
        //Клиент запускается отдельным потоком
        AtmClient atmClient = new AtmClient();

        Account accountSidorov = new Account("Sidorov", 1007);

        System.out.println("Баланс Сидорова: " + atmClient.balance(accountSidorov));

        atmClient.deposit(accountSidorov, 500);
        System.out.println("баланс Сидорова увеличили на 500");
        System.out.println("Баланс Сидорова: " + atmClient.balance(accountSidorov));

        atmClient.withdraw(accountSidorov, 500);
        System.out.println("Баланс Сидорова уменьшили на 500");
        System.out.println("Баланс Сидорова: " + atmClient.balance(accountSidorov));

        Account accountIvanov = new Account("Ivanov", 1004);
        double balIvanov = atmClient.balance(accountIvanov);
        System.out.println("Баланс Иванова: " + balIvanov);

        atmClient.transfer(accountIvanov, accountSidorov, balIvanov);
        System.out.println("Деньги Иванова перевели Сидорову");

        System.out.println("Баланс Иванова: " + atmClient.balance(accountIvanov));
        System.out.println("Баланс Сидорова: " + atmClient.balance(accountSidorov));
    }
}
