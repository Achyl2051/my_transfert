package windows;
import java.awt.*;
import java.io.DataInputStream;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Panel extends JPanel
{
    File file;
    Frame thisFrame;
    int x;
    int y;
    int height;
    int width;
    JButton[] button;
    JLabel body;

    public JLabel getBody() {
        return body;
    }
    public void setBody(JLabel body) {
        this.body = body;
    }
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public JButton[] getButton() {
        return button;
    }
    public void setButton(JButton[] button) {
        this.button = button;
    }
    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
    public void setX(int x)
    {
        this.x=x;
    }
    public void setY(int y)
    {
        this.y=y;
    }
    public int getHeight()
    {
        return this.height;
    }
    public int getWidth()
    {
        return this.width;
    }

    public Panel(Frame fr)
    {
        this.thisFrame=fr;
    }
    public JPanel send(Socket socket)
    {
        JPanel result=new JPanel();
        result.setLayout(null);
        this.x=10;
        this.y=10;
        this.width=100;
        this.height=50;
        JLabel title=new JLabel("Welcome to My Transfert");
        title.setBounds(width-width/4,y,width*2,height);
        result.add(title);
        y+=title.getHeight();
        body=new JLabel("Choose a file");
        body.setBounds(width+x,y,width*2,height);
        result.add(body);
        y+=body.getHeight();
        button=new JButton[2];
        button[0]=new JButton("send");
        button[0].setFocusable(false);
        button[0].setBounds(width/2,y,width,height-height/4);
        button[0].addActionListener(new AListner(thisFrame,button[0],this,socket));
        result.add(button[0]);
        button[1]=new JButton("choose");
        button[1].setFocusable(false);
        button[1].setBounds(width*2-x*3,y,width,height-height/4);
        button[1].addActionListener(new AListner(thisFrame,button[1],this,socket));
        result.add(button[1]);
        y+=button[0].getHeight();
        result.setPreferredSize(new Dimension(width*3+x,y/2+y/4+height));
        return result;
    }
    public JPanel listFile(ServerSocket serverSocket)
    {
        int fileId=0;
        JPanel result=new JPanel();
        result.setLayout(null);
        JScrollPane jScrollPane=new JScrollPane(result);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.x=10;
        this.y=10;
        this.width=100;
        this.height=50;
        JLabel title=new JLabel("My Transfert's receiver");
        title.setBounds(width-width/4,y,width*2,height);
        result.add(title);
        y+=title.getHeight();
        body=new JLabel("no file received");
        body.setFont(new Font("Arial",Font.BOLD,20));
        body.setBounds(width-width/4,y,width*2,height);
        y+=body.getHeight();
        button=new JButton[1];
        button[0]=new JButton("download");
        button[0].setFocusable(false);
        button[0].setBounds(width/2,y,width,height-height/4);
        button[0].addActionListener(new AListner(thisFrame,button[0],this));
        result.add(button[0]);
        y+=button[0].getHeight();
        result.add(body);
        result.setPreferredSize(new Dimension(width*3+x,y/2+y/4+height));
        return result;
    }
    public String getFileExtension(String file)
    {
        int i=file.lastIndexOf(".");
        if(i>0)
        {
            return file.substring(i+1);
        }
        else
        {
            return "no extension found...";
        }
    }
}