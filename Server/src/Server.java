import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8000);) {

            System.out.println("Server started");

            while (true) {
                try (
                        Socket socket = server.accept();

                        OutputStreamWriter outputStream = new OutputStreamWriter(socket.getOutputStream());
                        BufferedWriter writer = new BufferedWriter(outputStream);

                        InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
                        BufferedReader reader = new BufferedReader(inputStream);
                ) {
                    new Thread(() -> {

                        try {
                            String request = reader.readLine();
                            System.out.println(request);
                            String response = (int) (Math.random() * 30 - 10) + "";
                            Thread.sleep(4000);
                            writer.write(response);
                            //StringBuffer buffer = new StringBuffer();
                            //buffer.append("HTTP/1.1 HELLO FROM SERVER\n");
                            //buffer.append("\n");
                            //writer.write(buffer.toString());

                            writer.newLine();                            //Перевод каретки
                            writer.flush();                              //Передает сообщение

                            //Закрывать не нужно, т.к. описываем в try(должны иметь интерфейс closable)
                            //writer.close();
                            //socket.close();
                            //server.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }).start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }

        //System.out.println("Server stopped");
    }
}
