package ServerAndClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  //Listening for incoming connections and creating socket objects
  private ServerSocket serverSocket;

  //Server socket constructor
  public Server(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
  }

  //Method to start server
  public void startServer() {

    try{

      //Accept clients if socket is not closed
      while(!serverSocket.isClosed()) {

        Socket socket = serverSocket.accept();
        System.out.println("A new Client has connected!");
        ClientHandler handler = new ClientHandler(socket);

        Thread thread = new Thread(handler);
        thread.start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //Method to close Server socket
  public void closeServerSocket(){

    try{
      if(serverSocket != null){
        serverSocket.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //Main method
  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(1234);
    Server server = new Server(serverSocket);
    server.startServer();
  }

}
