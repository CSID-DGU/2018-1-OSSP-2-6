import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String arg[]) {
		
		Socket socket = null;	//client�� ����ϱ� ���� socket
		ServerSocket server_socket = null;	//���� ������ ���� ServerSocket
		BufferedReader in = null;	//Client�� ���� �����͸� �о���̱� ���� �Է� ��Ʈ��
		PrintWriter out = null;		//Clinet�� ���� �����͸� �������� ���� ��� ��Ʈ��
		
		try {
			server_socket = new ServerSocket(Ư�� ��Ʈ �Է�);
			
		}catch(IOException e){
			
			System.out.println("�ش� ��Ʈ�� �����ֽ��ϴ�.");
		}
		try {
			
			System.out.println("���� ����!!");
			socket = server_socket.accept();
			
			 
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));    //�Է½�Ʈ�� ����
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))); //��½�Ʈ�� ����
            
            String str = null;
            str = in.readLine();                //Client�κ��� �����͸� �о��
 
            System.out.println("Client�� ���� �� �޼��� : " + str);
            
            out.write(str);
            out.flush();
            socket.close();
        }catch(IOException e){}
    }
}