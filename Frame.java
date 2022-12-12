package windows;
import java.awt.*;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

public class Frame extends JFrame
{
    public void sendFile(Socket socket)
    {
        Panel jo=new Panel(this);
        this.add(jo.send(socket));
        this.setTitle("My Transfert");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public void receiveFile(ServerSocket serverSocket)
    {
        Panel jo=new Panel(this);
        this.add(jo.listFile(serverSocket));
        this.setTitle("My Transfert");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}