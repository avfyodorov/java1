package misc.kadyrovas.http;

import misc.kadyrovas.synchro.Account;
import misc.kadyrovas.synchro.ConcurrentAccountService;
import misc.kadyrovas.synchro.FileStoreService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AtmMachine {
    //Путь к файлу с клиентской базой
    private static final String FILEPATH = "d:/java/account.csv";

    public static void main(String[] args) {
        //Клиент запускается отдельным потоком
        new Thread(new AtmClient()).start();

        ReadWriteLock lock = new ReentrantReadWriteLock();
        Socket server;
        try (ServerSocket serverSocket = new ServerSocket(80)){
            serverSocket.setSoTimeout(2000);
            while (true){
                try {
                    server = serverSocket.accept();
                } catch (SocketTimeoutException e){
                    break;
                }
                new Thread(new RequestProcessing(server, lock)).start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

class RequestProcessing implements Runnable {
    Socket server;
    ReadWriteLock lock;
    ConcurrentAccountService service;
    public RequestProcessing(Socket server, ReadWriteLock lock){
        this.server = server;
        this.lock = lock;
        this.service = new ConcurrentAccountService();
    }

    @Override
    public void run() {
        String responseLine;
        List<String> listLine = new ArrayList<>();
        try(
            InputStream is = server.getInputStream();
            OutputStream os = server.getOutputStream();
        ){
            Scanner sc = new Scanner(is);
            while (sc.hasNextLine()){
                 listLine.add(sc.nextLine());
                 if (listLine.get(listLine.size()-1).compareTo("") == 0)
                     break;
            }

            //Авторизация клиента
            //Проверяется наличие id в базе и соответствие pin
            //Также проверяется структура запроса
            RequestLines requestLines = new RequestLines(listLine);
            if (requestLines.code != 0) {
                responseLine = "The request structure is not correct";
                fResponse(os, requestLines.code, responseLine);
                return;
            }

            String id = requestLines.commands.get("id");
            Account localAccont = this.service.get(id);
            if (localAccont == null) {
                responseLine = "The client with the id = " + id + " is not in the database";
                fResponse(os, 401, responseLine);
                return;
            }

            String pin = requestLines.commands.get("pin");
            int pinInt = Integer.valueOf(pin);
            if (localAccont.getPin() != pinInt){
                responseLine = "Error entering pin";
                fResponse(os, 401, responseLine);
                return;
            }
            //Методы:
            //GetBalance - Получения информации по балансу
            //Transfer - Перевод на другую карту
            //Deposit - Пополнение счета
            //Withdraw - Снятие наличных
            //Параметры: param1 = id
            //           param2 = pin
            //           param3 = amount
            //           param4 = id2 (При выполнении transfer)


            if (requestLines.command.compareTo("GetBalance") == 0){
                //Получение баланса
                responseLine = "Your balance is " + localAccont.getAmount() + "  rubles";
                fResponse(os, 200, responseLine);
            }
            else if (requestLines.command.compareTo("Transfer") == 0){
                //Перевод на другую карту
                String id2 = requestLines.commands.get("id2");
                if (id2 == null) {
                    responseLine ="The client with the id = " + id2 + " is not in the database";
                    fResponse(os, 404, responseLine);
                }
                String amountStr = requestLines.commands.get("amount");
                if (amountStr == null){
                    responseLine = "The transfer amount is not specified";
                    fResponse(os, 400, responseLine);
                    return;
                }
                Account toAccount = service.get(id2);
                if (toAccount == null) {
                    responseLine = "The transfer amount is not specified";
                    fResponse(os, 400, responseLine);
                    return;
                }
                double amountDbl = Double.valueOf(amountStr);
                service.transfer(localAccont, toAccount, amountDbl);
                responseLine = "transfer in the amount of "+ amountStr + " rubles id " +
                               id2 + " completed";
                fResponse(os, 200, responseLine);
            }
            else if (requestLines.command.compareTo("Deposit") == 0){
                //Пополнение счета
                String amountStr = requestLines.commands.get("amount");
                if (amountStr == null){
                    responseLine = "The transfer amount is not specified";
                    fResponse(os, 400, responseLine);
                    return;
                }
                double amountDbl = Double.valueOf(amountStr);
                service.deposit(localAccont, amountDbl);
                responseLine = "Your account has been replenished by " + amountStr + " rubles";
                fResponse(os, 200, responseLine);
            }
            else if (requestLines.command.compareTo("Withdraw") == 0){
                //Снятие наличных
                String amountStr = requestLines.commands.get("amount");
                if (amountStr == null){
                    responseLine = "The transfer amount is not specified";
                    fResponse(os, 400, responseLine);
                    return;
                }
                double amountDbl = Double.valueOf(amountStr);
                service.withdraw(localAccont, amountDbl);
                responseLine = "Account balance reduced by " + amountStr + " rubles";
                fResponse(os, 200, responseLine);
            }
            else {
                responseLine = "The request structure is not correct";
                fResponse(os, 400, responseLine);
                return;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void fResponse(OutputStream os, int code, String line){
        //Отправляет ответ клиенту
        String lineResponse = "HTTP/1.1 " + code +
                              (code == 200 ? " OK" : " Bad Request") + "\n" +
                              "Content-Type: text/html; charset=utf-8\n" +
                              "Content-Length: " + line.length() +"\n\n" + line;
        PrintWriter pr = new PrintWriter(os);
        pr.println(lineResponse);
        pr.flush();
    }
}