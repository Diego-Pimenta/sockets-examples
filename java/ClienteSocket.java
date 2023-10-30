import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSocket {
   public static void main(String[] args) {

      try {
         Socket socket = new Socket("localhost", 1234);
         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
         Scanner sc = new Scanner(System.in);
         boolean isFinish = true;

         while (isFinish) {
            System.out.print("Digite sua mensagem: ");
            String msg = sc.next();
            out.println(msg);

            String resposta = in.readLine();
            System.out.println("Mensagem do servidor: " + resposta);

            if (resposta.toUpperCase().equals("BYE")) {
               isFinish = false;
            }
         }
         in.close();
         out.close();
         sc.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}