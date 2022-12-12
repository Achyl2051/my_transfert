package object;
import java.net.Socket;
import windows.*;

public class Client
{
    public static void main(String[] args)throws Exception
    {
        Socket socket =new Socket("localhost",2004);
        new Frame().sendFile(socket);
    }
}