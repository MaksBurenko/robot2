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
                robotConnectionManager.getConnection();  // устанавливаем соединение
                RobotConnection.moveRobotTo(toX, toY);   // двигаемся роботом в указанные координаты
                RobotConnection.close();                 // закрываем канал связи
                break;                                   // прерываем цикл, он успешно завершен
             } catch (RobotConnectionException e) {      // при возникновении исключения:
                 i++;                                    // раз возникло исключение, занчит попытка №1 провалена
                                                         // фиксируем попытку №2
                 RobotConnection.close();                // закрываем канал
                 if (i > 3) {                           // проверяем не выходит ли наша попытка связи
                                                         // за предел допустимых попыток
                     continue;                           // если выходит, закрываем канал и бросаем исключение
                     RobotConnection.close();
                     RobotConnectionException.super();
                 }
             }
        }while (true);                                    // если не выходит, то повторяем цикл
    }
}
