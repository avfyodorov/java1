package misc.kadyrovas.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestLines {
    //Парсит принимаемый в конструкторе List inputLines и формирует MAP commands,
    //где key - параметр (id, pin, amount, id2), а value - значение параметра
    //command - команда (GetBalance, Transfer, Deposit, Withdraw)
    //code - код выполнения parse. 0 - ok, 400 - что-то пошло не так
    public int code = 0;
    public Map<String, String> commands = new HashMap<>();
    public String command;
    private List<String> inputLines;
    public RequestLines(List<String> inputLines) {
        this.inputLines = inputLines;
        parseCommandLine();
    }

    private void parseCommandLine(){
        String[] requesLines = inputLines.get(0).split("/");
        if (requesLines.length != 3){
            this.code = 400;
            return;
        }

        if (requesLines[0].compareTo("GET") != 0 ||
                requesLines[2].compareTo("1.1") != 0 ||
                !requesLines[1].endsWith("HTTP") ||
                this.inputLines.get(2).compareTo("") != 0) {
            code = 400;
            return;
        }

        String[] commandLine = requesLines[1].split("\\?");
        command = commandLine[0];
        commandLine[1] = commandLine[1].substring(0, commandLine[1].length() - 4);
        String[] parameters = commandLine[1].split("&");
        for (int i = 0; i < parameters.length; i ++){
            String[] args = parameters[i].split("=");
            if (args.length != 2){
                code = 400;
                return;
            }

            args[0] = args[0].trim();
            args[1] = args[1].trim();
            commands.put(args[0], args[1]);
        }
    }
}
