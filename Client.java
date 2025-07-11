import java.net.Socket;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
class Client extends JFrame implements Escape,WindowListener{
    Socket socket;
    BufferedReader br;
    PrintWriter ot;
    private JLabel heading = new JLabel("Client Area");
    private JTextArea Text = new JTextArea();
    private JTextField mesinput = new JTextField();
    private Font font = new Font("Tahoma",Font.PLAIN,20);
    public Client()
    { 
     try{
        System.out.println("Waiting for connection");
        socket = new Socket("192.168.7.131",7897);
        System.out.println("Connection is done");
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ot = new PrintWriter(socket.getOutputStream());
        GUI();
        handleEvents();
        startReading();
        startWriting();
     }
     catch(Exception e)
     {
        e.printStackTrace();
     }
     addWindowListener(this);
    }
    private void handleEvents(){
        mesinput.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        String getcontsend = mesinput.getText();
                        Text.append("Client : " + getcontsend + "\n");
                        ot.println(getcontsend);
                        if (getcontsend.equals("exit")) {
                                System.exit(0);
                        } 
                        ot.flush();
                        mesinput.setText("");
                        mesinput.requestFocus();
                    }
                }

            @Override
            public void keyTyped(KeyEvent e) {

            }

             @Override
            public void keyReleased(KeyEvent e) {
 
            }

        });
        
    }
    private void GUI()
    {
        this.setTitle("Client_Messenger");
        this.setSize(600, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        heading.setFont(font);
        Text.setFont(font);
        mesinput.setFont(font);
    
        // Load the image
        ImageIcon imageIcon = new ImageIcon("youv.png");
        Image image = imageIcon.getImage();
    
        // Resize the image to 60x69 pixels
        Image scaledImage = image.getScaledInstance(60, 69, Image.SCALE_SMOOTH);
    
        // Create a new ImageIcon with the resized image
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
    
        heading.setIcon(scaledIcon);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        Text.setEditable(false);
        mesinput.setHorizontalAlignment(SwingConstants.CENTER);
        this.setLayout(new BorderLayout());
        JScrollPane jscrollpane = new JScrollPane(Text);
        this.add(heading,BorderLayout.NORTH);
        this.add(jscrollpane,SwingConstants.CENTER);
        this.add(mesinput, BorderLayout.SOUTH);
        this.setVisible(true);
    }  

    public void startReading()
    {
        System.out.println("Reader initatiated");
        Runnable r1 = () -> {
            try{
              while(!socket.isClosed())
              {
            
                String msg = br.readLine();
                if(msg.equals("exit"))
                {
                    System.out.println("Server Terminated the chat");
                    JOptionPane.showMessageDialog(this,"Server Terminated the chat");
                    mesinput.setEditable(false);
                    Text.setEnabled(false);
                    socket.close();
                    break;
                }
                Text.append("Server :"+msg+"\n");
              }
            }
            catch(Exception e)
            {
                System.out.println("Connection is closed meet at 7:00am");
            }
        };
        new Thread(r1).start();
    }
    public void startWriting()
    {
        System.out.println("Writer initiated");
        Runnable r2 = () -> {
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
                        break;
                    }
                }
                catch(Exception e){
                    System.out.println("Connection is closed, meet at 7:00am");
                }
            }
        };
        new Thread(r2).start();
    }
    public static void main(String[] args)
    {
       new Client();
    
    }
    @Override
    public void windowOpened(WindowEvent e) {
 ;
    }
    @Override
    public void windowClosing(WindowEvent e) {
 
    }
    @Override
    public void windowClosed(WindowEvent e) {

    }
    @Override
    public void windowIconified(WindowEvent e) {

    }
    @Override
    public void windowDeiconified(WindowEvent e) {

    }
    @Override
    public void windowActivated(WindowEvent e) {

    }
    @Override
    public void windowDeactivated(WindowEvent e) {
 
    }
    
}
