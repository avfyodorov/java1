package misc.sidnet1964.gc;

public class InvalidPointerException extends RuntimeException {
   public String commandName = "";

   public InvalidPointerException(String commandName, int commandLine) {
      super("Ошибка в строке # " + commandLine);
      this.commandName = commandName;
   }

   @Override
   public String getMessage() {
      return super.getMessage() + (commandName == null ? "" : " \"" + commandName + "\"");
   }
}
//  пример использования
//  throw new WrongCommand(" - неизвестный модификатор " + line0, numLine);