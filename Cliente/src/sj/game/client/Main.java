package sj.game.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sj.game.common.C;
import sj.game.common.ClientTextMessage;
import sj.game.common.LoginRequest;
import sj.game.common.LoginResponse;
import sj.game.common.RegisterRequest;
import sj.game.common.RegisterResponse;
import sj.game.common.ServerMessage;

public class Main{

	public static boolean respondedToCurrentQuery = false;
	public static long sinceCurrentQuery = 0;
	public static ServerMessage currentQueryResponse = null;
	private static Server server;

	static JFrame ventana;
	private static JButton register;
	private static JLabel title;
	private static JButton login;
	private static JPanel panel;
	private static JTextField userField;
	private static JTextField passField;
	private static JTextField rpassField;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ventana = new JFrame("JS-game");
		ventana.setVisible(true);
		ventana.setBackground(Color.CYAN);
		panel = new JPanel();
		ventana.setContentPane(panel);
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		panel.setVisible(true);
		
		title = new JLabel("JS-game");
		
		title.setFont(new Font("Arial", Font.PLAIN, 50));
		title.setForeground(Color.BLACK);
		title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		panel.add(title);
		
		
		
		
		
		
		
		
		
		userField = new JTextField("User: ");
		
		userField.setFont(new Font("Arial", Font.PLAIN, 30));
		userField.setForeground(Color.BLACK);
		userField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Username", SwingConstants.CENTER, SwingConstants.CENTER));
		
		panel.add(userField);	
		
		
		passField = new JPasswordField("Pass: ");
		
		passField.setFont(new Font("Arial", Font.PLAIN, 30));
		passField.setForeground(Color.BLACK);
		passField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Password", SwingConstants.CENTER, SwingConstants.CENTER));
		
		panel.add(passField);	
		
		
		
		
		rpassField = new JPasswordField("Repeat: ");
		
		rpassField.setFont(new Font("Arial", Font.PLAIN, 30));
		rpassField.setForeground(Color.BLACK);
		rpassField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Repeat password", SwingConstants.CENTER, SwingConstants.CENTER));
		
		panel.add(rpassField);	
		
		
		register = new JButton("Register");
		
		register.setFont(new Font("Arial", Font.PLAIN, 50));
		register.setForeground(Color.BLACK);
		register.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		panel.add(register);
		
		register.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		
		
		login = new JButton("Login");
		
		login.setFont(new Font("Arial", Font.PLAIN, 50));
		login.setForeground(Color.BLACK);
		login.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		login.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		panel.add(login);	
		
		
		
		
		calculatePositions();

		boolean foundServer = false; 

		while(!foundServer){
			//sent output to server
			try {
				System.out.println("Attempting connection at " + C.HOST + ":" + C.PORT);
				Socket s = new Socket(C.HOST, C.PORT);
				server = new Server(s);

				server.send(new ClientTextMessage("Hello my friend"));
				foundServer = true;
				connected();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.err.println("[ERROR] No server found, trying again");
			}
		}







	}

	private static void calculatePositions() {
		// TODO Auto-generated method stub
		panel.validate();
		
		panel.setSize(ventana.getSize());
		
		int width = ventana.getFontMetrics(title.getFont()).stringWidth(title.getText());
		title.setLocation(calcPartW(50)-width/2, calcPartH(0));
		
		register.setLocation(calcPartW(25)-register.getWidth()/2,calcPartH(80)-register.getHeight()/2);
		login.setLocation(calcPartW(75)-login.getWidth()/2,calcPartH(80)-login.getHeight()/2);
		
		
		userField.setLocation(calcPartW(50)-userField.getWidth()/2, calcPartH(40));
		passField.setLocation(calcPartW(50)-passField.getWidth()/2, calcPartH(50));
		rpassField.setBounds(calcPartW(50)-300/2, calcPartH(60), 300, 50);
		
		
		
	}

	private static int calcPartH(int i) {
		// TODO Auto-generated method stub
		return (int) (ventana.getHeight()*(((float) i)/100));
	}

	private static int calcPartW(int i) {
		// TODO Auto-generated method stub
		
		
		
		return (int) (ventana.getWidth()*(((float) i)/100));
	}

	private static boolean login(String user, String pass) {
		respondedToCurrentQuery = false;
		server.send(new LoginRequest(user, pass));


		sinceCurrentQuery = System.currentTimeMillis();

		while(!respondedToCurrentQuery){
			
			
			
			if(System.currentTimeMillis() - sinceCurrentQuery > 1 * 1000 && !respondedToCurrentQuery){
				System.out.println("Timeout");
				
				return false;

			}
			
			

		}



		return ((LoginResponse) currentQueryResponse).isAccepted();
	}

	private static boolean register(String user, String pass) {
		// TODO Auto-generated method stub
		respondedToCurrentQuery = false;
		server.send(new RegisterRequest(user, pass));


		sinceCurrentQuery = System.currentTimeMillis();

		while(!respondedToCurrentQuery){

			if(System.currentTimeMillis() - sinceCurrentQuery > 1 * 1000 && !respondedToCurrentQuery){
				System.out.println("Timeout");
				
				return false;

			}

		}

		return ((RegisterResponse) currentQueryResponse).getResult() == RegisterResponse.REGISTER_OK;
	}

	public static void connected(){
			

		String rl = JOptionPane.showInputDialog("(L) for login and (R) for register");
		if(rl == null) System.exit(0);
		if(rl.equals("L")){
			String user = JOptionPane.showInputDialog("Username: ");
			String pass = JOptionPane.showInputDialog("Password: ");
			if(user == null) connected();
			if(pass == null) connected();
			if(login(user,pass)){
				System.out.println("Logged");

			}else{
				System.out.println("USERNAME INCORRECT");
				connected();return;
				
			}
		}else{
			String user = JOptionPane.showInputDialog("Username: ");
			String pass = JOptionPane.showInputDialog("Password: ");
			String pass2 = JOptionPane.showInputDialog("Confirm password: ");
			if(user == null) connected();
			if(pass == null) connected();
			if(pass2 == null) connected();
			if(pass.equals(pass2) || !user.equals("") || !pass.equals("")){
				if(register(user, pass)){
					System.out.println("Registered success");
					connected();return;
					
				}else{
					System.out.println("Register failed");
					connected();return;
				}
			}
		}


		System.out.println("Server found! Starting input connections");



		//create thread to wait for server inputs


		System.out.println("Server found! Starting game mechanics");

		//run game

		boolean playing = true;
		
		while(playing){
			
			
			
		}

	}

}
