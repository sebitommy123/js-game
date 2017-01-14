package sj.game.server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.EncryptedPrivateKeyInfo;
import javax.xml.crypto.dsig.DigestMethod;

import sj.game.common.C;
import sj.game.common.UserData;

public class Server {
	private ServerSocket ss;
	static ArrayList<UserData> users = new ArrayList<UserData>();
	public static UserData getByUsername(String user){
		for(UserData ud : users){
			if(ud.getUsername().equals(user)){
				return ud;
			}
		}
		return null;
	}
	public Server() {
		try {
			System.out.println("[INFO] Initializing server at port "+C.PORT+"...");
			File users = new File(C.USERS_PATH);
			if(!users.exists() || !users.isDirectory()){
				users.mkdirs();
			}
			setSs(new ServerSocket(C.PORT));
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("[INFO] Listening for clients...");
					while(!ss.isClosed()){
						try {
							Socket client = ss.accept();
							System.out.println("[INFO] Client connected from "+client.getInetAddress()+":"+client.getLocalPort()+"! Assigning listener...");
							listen(client);
						} catch (IOException e) {
							System.err.println("[ERROR] Server Socket is closed!");			
						}
					}
					
				}
			});
			t.start();
		} catch (IOException e) {
			System.err.println("[ERROR] Couldn't initialize ServerSocket!");			

		}
		
		
		
	}
	public static String byteArrayToHexString(byte[] b) {
		  String result = "";
		  for (int i=0; i < b.length; i++) {
		    result +=
		          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		  }
		  return result;
		}
	public static String sha1(String data){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return byteArrayToHexString(md.digest(data.getBytes()));
	}
	public static String encrypt(String data){
		return sha1(sha1("dawdawdawpoesfusuoifhoi")+data+sha1("awdhawydgaygwdauyodg"))+"wwywehweo2";
	}
	public static void saveUsers(){
		
		for(UserData user : users){
			String pass = user.getPassword();
			String username = user.getUsername();
		}
	}
	protected void listen(Socket client) {
		Client cml = new Client(client);
		cml.listen();
		System.out.println("[INFO] Listener assigned for "+client.getInetAddress()+":"+client.getLocalPort());			
	}

	public ServerSocket getSs() {
		return ss;
	}

	public void setSs(ServerSocket ss) {
		this.ss = ss;
	}
}
