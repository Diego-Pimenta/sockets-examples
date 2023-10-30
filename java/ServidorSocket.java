import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
   public static void main(String[] args) {

      try {
         ServerSocket serverSocket = new ServerSocket(1234);
         System.out.println("Servidor iniciado. Aguardando conexões...");
         while (true) {
            Socket socket = serverSocket.accept();
            Thread thread = new Thread(new ClienteHandler(socket));
            thread.start();
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}