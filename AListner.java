package windows;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.io.ObjectInputStream.GetField;

import javax.swing.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

public class AListner implements ActionListener
{
    JButton b;
    Panel p;
    Frame f;
    Socket socket;
    
    public AListner(Frame f,JButton b,Panel p)
    {
        this.b=b;
        this.p=p;
        this.f=f;
    }
    public AListner(Frame f,JButton b,Panel p,Socket socket)
    {
        this.b=b;
        this.p=p;
        this.f=f;
        this.socket=socket;
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b)
        {
            if(b.getText().equals("choose"))
            {
                JFileChooser fileChooser=new JFileChooser();
                fileChooser.setDialogTitle("Choose a file to send");
                if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
                {
                    p.setFile(fileChooser.getSelectedFile());
                    p.getBody().setText("filename: "+p.getFile().getName());
                }
            }
            else if(b.getText().equals("send"))
            {
                if(p.getFile()==null)
                {
                    p.getBody().setText("choose file first...");
                }
                else
                {
                    try 
                    {
                        FileInputStream fileInputStream=new FileInputStream(p.getFile().getAbsolutePath());

                        DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
                        String filename=p.getFile().getName();
                        byte[] filenameBytes=filename.getBytes();
                        byte[] fileContentBytes=new byte[(int)p.getFile().length()];
                        fileContentBytes = Files.readAllBytes(p.getFile().toPath());
                        dataOutputStream.writeInt(filenameBytes.length);
                        dataOutputStream.write(filenameBytes);

                        dataOutputStream.writeInt(fileContentBytes.length);
                        dataOutputStream.write(fileContentBytes);

                        fileInputStream.close();
                        dataOutputStream.close();
                    } catch (Exception exc) 
                    {
                        exc.printStackTrace();
                    }
                }
            }
        }
    }
}