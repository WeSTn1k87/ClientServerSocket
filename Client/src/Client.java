import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("127.0.0.1",8000);
            OutputStreamWriter outputStream = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter writer = new BufferedWriter(outputStream);

            InputStreamReader inputStream = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(inputStream);
         ) {

            System.out.println("Connected to server");
            String  request = "Hello";

            writer.write(request);
            writer.newLine();                            //Перевод каретки
            writer.flush();                              //Передает сообщение

            String response = reader.readLine();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
