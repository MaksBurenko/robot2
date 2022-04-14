package robot2;
public class Main {
    public static void main(String[] args) {
    }

    public interface RobotConnectionManager {
        RobotConnection getConnection();
    }


    public interface RobotConnection extends AutoCloseable {
        void moveRobotTo(int x, int y);
        @Override
        void close();
    }


    public class RobotConnectionException extends RuntimeException {
        public RobotConnectionException(String message) {
            super(message);
        }
        public RobotConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }



    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {

        int i =1;
        do{
             try {
                 robotConnectionManager.getConnection().moveRobotTo(toX, toY);
                 robotConnectionManager.getConnection().close();
                break;
             } catch (RobotConnectionException e) {
                 i++;
                 robotConnectionManager.getConnection().close();
                 if (i > 3) {
                     continue;
                     robotConnectionManager.getConnection().close();
                     RobotConnectionException.super;
                 }
             }
        }while (true);
    }
}
