package misc.sidnet1964.gc;

public class OutOfMemoryException extends RuntimeException {
   public String commandName = "";

   public OutOfMemoryException(String commandName, int size) {
      super("Ошибка при размещении " + size + " байт.");
      this.commandName = commandName;
   }

   @Override
   public String getMessage() {
      return super.getMessage() + (commandName == null ? "" : " \"" + commandName + "\"");
   }
}
//  пример использования
//  throw new WrongCommand(" - неизвестный модификатор " + line0, numLine);