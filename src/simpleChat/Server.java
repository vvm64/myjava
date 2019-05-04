package simpleChat;

/*
 Простой сервер
1. Run Server
2. Run Client

Достоинство: простота 

Недостатки, недоработки:
бесконечные циклы, ввод пользователя никак не обрабатывается,
не обрабатывается разрыв соединения, связь никак не шифруется
 */
import java.net.*;
import java.io.*;

/**
 *
 * @author vvm64
 */
public class Server {
    private static final String NAME="SERVER";
    private static final int port = 8154;
    public static void main(String[] args) {

        //int port = 8154;//временно номер порта клиента и сервера такой
        try {
             
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Ждем когда клиент соеденится...");

            Socket socket = serverSocket.accept();
            System.out.println("Связь клиент-сервер установлена!");
            System.out.println();

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            //vvm64 Недостатки такого подхода, бесконечные циклы
            new Thread(() -> {
                while (true) {
                    try {
                        
                        System.out.println(in.readUTF());

                    } catch (IOException e) {
                    }
                }
            }).start();
            String toClient;//для отправки клиенту
            while (true) {
                //System.out.print(NAME+"  : ");
                 
                toClient = keyboard.readLine();
                out.writeUTF(toClient);
                out.flush();
            }
        } catch (IOException x) {
        }
    }
}
