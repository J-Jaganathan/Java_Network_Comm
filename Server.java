import java.net.*;
import java.io.*;
class Server
{
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter ot;
    public Server()
    {
        try{
            server = new ServerSocket(7897);
            System.out.println("The server is ready");
            System.out.println("Waiting for connection....");
            socket=server.accept();
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ot = new PrintWriter(socket.getOutputStream());
            startReading();
            startWriting();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void startReading()
    {
       Runnable r1 = () -> {
            System.out.println("Reader started");
            try{
                 while(!socket.isClosed())
                 {
                
                    String msg = br.readLine();
                    if(msg.equals("exit"))
                    {
                        System.out.println("Client terminated the chat");
                        socket.close();
                        System.exit(0);
                        break;
                    }
                    System.out.println("Client:"+msg);
                 }
            }
            catch(Exception e)
            {
                  System.out.println("Client terminated the chat");
            }
       };
       new Thread(r1).start();
    }
    public void startWriting()
    {   System.out.println("Writer Started");
        Runnable r2= () -> {
          while(!socket.isClosed())
          {
            try{
                BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                String content = br1.readLine();
                ot.println(content);
                ot.flush();
                if(content.equals("exit"))
                {
                    socket.close();
                    System.exit(0);
                    break;
                }
           }
           catch(Exception e)
           {
             System.out.println("Client terminated the chat");
           }
          } 

        };
        new Thread(r2).start();

    }
    public static void main(String[] args)
    {
        System.out.println("I am Server, got prepared");
        new Server();
    }


}