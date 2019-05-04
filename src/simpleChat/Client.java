/*
 *  Простой клиент
1. Run Server
2. Run Client
 */
package simpleChat;

import java.net.*;
import java.io.*;

import simpleChat.util.CodepagePrintStream;

/**
 *
 * @author vvm64
 */
public class Client {

    private static final int serverPort = 8154;//временно
    private static final String NAME = "CLIENT";

    public static void main(String[] args) throws IOException {

        try // Установка вывода консольных сообщений в нужной кодировке
        {
            String consoleEnc = System.getProperty("console.encoding", "utf-8");
            System.setOut(new CodepagePrintStream(System.out, consoleEnc));
            System.setErr(new CodepagePrintStream(System.err, consoleEnc));
        } catch (UnsupportedEncodingException e) {
            System.out.println("Unable to setup console codepage: " + e);
        }
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        //Scanner scanner = new Scanner(System.in, "utf-8"); //временно отключено

        System.out.println("Введите IP адрес");
        String address = keyboard.readLine();
        // int serverPort = 8154;//временно
        /*раскоментируйте если нужно вводить номер порта*/
        //System.out.println("Введите номер порта");
        //int serverPort = scanner.nextInt();

        try {
            Socket socket = null;
            InetAddress ipAddress = InetAddress.getByName(address);
            System.out.println("Подключение к  " + address + ":" + serverPort + "...");
            while (true) {
                try {
                    socket = new Socket(ipAddress, serverPort);
                    break;
                } catch (ConnectException ex) {
                }
            }
            // читаем данные из потока
            DataInputStream in;
            in = new DataInputStream(socket.getInputStream());
            //DataOutputStream пишем данные в поток через интерфейс DataOutput
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            System.out.println("Установлена связь между клиентом и сервером!");
            System.out.println();
            //vvm64 Недостатки такого подхода, бесконечные циклы
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.print(NAME + "  :");
                        System.out.println(in.readUTF());
                    } catch (IOException ex) {
                    }
                }
            }).start();
            String toServer;
            while (true) {
                //toServer ="To Server";
                toServer = keyboard.readLine(); // читаем строку с клавиатуры
                out.writeUTF(toServer); // пишем в поток в кодировке UTF-8
                out.flush();//очищаем любые выходные буферы, завершая операцию вывода.
            }
        } catch (IOException ex) {
        }
    }
}
