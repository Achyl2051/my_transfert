package object;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    ServerSocket serverSocket;
    public void startServer()
    {
        while(!serverSocket.isClosed())
        {
            try
            {
                Socket socket=serverSocket.accept();
                DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
                int fileNameLength = dataInputStream.readInt();
                byte[] fileNameBytes = new byte[fileNameLength];
                dataInputStream.readFully(fileNameBytes,0,fileNameBytes.length);
                String filename = new String(fileNameBytes);
                int fileContentLength = dataInputStream.readInt();
                if (fileContentLength > 0)
                {
                    byte[] fileContentBytes = new byte[fileContentLength];
                    dataInputStream.readFully(fileContentBytes,0,fileContentLength);
                    File fileToDownload=new File(filename);
                    FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
                    fileOutputStream.write(fileContentBytes);
                    fileOutputStream.close();
                }
            }
            catch(Exception e)
            {}
        }
    }
    public static void main(String[] args)throws Exception
    {
        ServerSocket serverSocket=new ServerSocket(2004);
        Server server=new Server(serverSocket);
        server.startServer();
    }
}
