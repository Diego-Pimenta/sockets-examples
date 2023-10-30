import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteHandler implements Runnable {

   private Socket socket;

   public ClienteHandler(Socket socket) {
      this.socket = socket;
   }

   @Override
   public void run() {
      try {
         System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());
         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
         Scanner sc = new Scanner(System.in);
         boolean isFinish = true;

         while (isFinish) {
            String resposta = in.readLine();
            System.out.println("Mensagem do usuário: " + resposta);

            System.out.print("Digite sua mensagem: ");
            String msg = sc.next();
            out.println(msg);

            if (resposta.toUpperCase().equals("BYE")) {
               isFinish = false;
            }
         }
         in.close();
         out.close();
         sc.close();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            socket.close();
            System.out.println("Conexão encerrada com o cliente: " + socket.getInetAddress().getHostAddress());
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
}