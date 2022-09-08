package examples.les10;

import java.io.IOException;

public class NullPoint {
    public static Integer sqr(Integer n) {
        try {
            return (n * n);
        } catch (NullPointerException e) {
            return -1;
        }
    }

    public String test(String filename) throws IOException {
        if (filename == null)
            throw new IOException("File not found");

        return "File processing";
    }

    public static void testEx(){
        try {
            throw new ManagerSaveException("Not suppressed", false);
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
        try {
            throw new ManagerSaveException("Suppressed", true);
        } catch (ManagerSaveException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {//throws IOException {
//        System.out.println(new NullPoint().test2(null));
        testEx();
    }
}

class ManagerSaveException extends RuntimeException {

    private boolean suppress = false;

    public ManagerSaveException(String message, boolean suppress) {
        super(message, null, suppress, !suppress);
        this.suppress = suppress;
    }

    @Override
    public String toString() {
        if (suppress) {
            return getLocalizedMessage();
        } else {
            return super.toString();
        }
    }
}
