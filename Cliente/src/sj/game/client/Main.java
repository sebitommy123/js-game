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
	public static Server server;

	static JFrame ventana;
	private static JButton register;
	private static JLabel title;
	private static JButton login;
	private static JPanel panel;
	private static JTextField userField;
	private static JTextField passField;
	private static JTextField rpassField;
	private static JTextField hintText;
	
	private static boolean logged = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ventana = new JFrame("JS-game");
		ventana.setVisible(true);
		ventana.setBackground(Color.BLACK);
		panel = new JPanel();
		ventana.setContentPane(panel);
		ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		panel.setVisible(true);
		
		title = new JLabel("Attempting to make connection...");
		
		title.setFont(new Font("Arial", Font.PLAIN, 50));
		title.setForeground(Color.BLACK);
		title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		panel.add(title);
		
		panel.validate();
		
		panel.setSize(ventana.getSize());
		
		int width = ventana.getFontMetrics(title.getFont()).stringWidth(title.getText());
		title.setLocation(calcPartW(50)-width/2, calcPartH(0));
		
		boolean foundServer = false; 

		while(!foundServer){
			//sent output to server
			try {
				System.out.println("Attempting connection at " + C.HOST + ":" + C.PORT);
				Socket s = new Socket(C.HOST, C.PORT);
				server = new Server(s);

				server.send(new ClientTextMessage("Hello my friend"));
				foundServer = true;
				//connected();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.err.println("[ERROR] No server found, trying again");
			}
		}
		
		
		panel.removeAll();
		
		
		
		title = new JLabel("JS-game");
		
		title.setFont(new Font("Arial", Font.PLAIN, 50));
		title.setForeground(Color.BLACK);
		title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		panel.add(title);
		
		
		
		
		
		
		
		
		
		userField = new JTextField();
		
		userField.setFont(new Font("Arial", Font.PLAIN, 30));
		userField.setForeground(Color.BLACK);
		userField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Username", SwingConstants.CENTER, SwingConstants.CENTER));
		
		panel.add(userField);	
		
		
		passField = new JPasswordField();
		
		passField.setFont(new Font("Arial", Font.PLAIN, 30));
		passField.setForeground(Color.BLACK);
		passField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Password", SwingConstants.CENTER, SwingConstants.CENTER));
		
		panel.add(passField);	
		
		
		
		
		rpassField = new JPasswordField();
		
		rpassField.setFont(new Font("Arial", Font.PLAIN, 30));
		rpassField.setForeground(Color.BLACK);
		rpassField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Repeat password", SwingConstants.CENTER, SwingConstants.CENTER));
		
		panel.add(rpassField);	
		
		
		hintText = new JTextField("Log in or register");
		
		hintText.setHorizontalAlignment(SwingConstants.CENTER);
		hintText.setFont(new Font("Arial", Font.PLAIN, 30));
		hintText.setForeground(Color.BLACK);
		hintText.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Result", SwingConstants.CENTER, SwingConstants.CENTER));
		hintText.setEditable(false);
		
		panel.add(hintText);	
		
		
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
				
				String user = userField.getText();
				String pass = passField.getText();
				String pass2 = rpassField.getText();
				if(pass.equals(pass2) || !user.equals("") || !pass.equals("")){
					if(register(user, pass)){
						setHintText("Registered successfuly");
					}else{
						setHintText("Register failed");
					}
				}
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
				String user = userField.getText();
				String pass = passField.getText();
				if(login(user,pass)){
					setHintText("Login successful");
					logged = true;
					GamePanel gamePanel = new GamePanel();
					gamePanel.setSize(ventana.getSize());
					ventana.setContentPane(gamePanel );
				}else{
					setHintText("Fail");
				}
			}
		});
		
		panel.add(login);	
		
		
		
		
		calculatePositions();

<<<<<<< HEAD
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
=======
		
>>>>>>> f7c8bc01c515505d357a1f432945c30b3953a4f2







	}

	private static void calculatePositions() {
		// TODO Auto-generated method stub
		panel.validate();
		
		panel.setSize(ventana.getSize());
		
		int width = ventana.getFontMetrics(title.getFont()).stringWidth(title.getText());
		title.setLocation(calcPartW(50)-width/2, calcPartH(0));
		
		int hintTextWidth = ventana.getFontMetrics(hintText.getFont()).stringWidth(hintText.getText());
		hintText.setLocation(calcPartW(50)-hintTextWidth/2, calcPartH(85));
		
		register.setLocation(calcPartW(25)-register.getWidth()/2,calcPartH(80)-register.getHeight()/2);
		login.setLocation(calcPartW(75)-login.getWidth()/2,calcPartH(80)-login.getHeight()/2);
		
		
		userField.setBounds(calcPartW(50)-300/2, calcPartH(40), 300, 50);
		passField.setBounds(calcPartW(50)-300/2, calcPartH(50), 300, 50);
		rpassField.setBounds(calcPartW(50)-300/2, calcPartH(60), 300, 50);
		
		
		
	}
	
	private static void setHintText(String text){
		hintText.setText(text);
		int hintTextWidth = ventana.getFontMetrics(hintText.getFont()).stringWidth(hintText.getText());
		hintText.setLocation(calcPartW(50)-hintTextWidth/2, calcPartH(85));
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
			
			
			
			if(System.currentTimeMillis() - sinceCurrentQuery > 3 * 1000 && !respondedToCurrentQuery){
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

			if(System.currentTimeMillis() - sinceCurrentQuery > 3 * 1000 && !respondedToCurrentQuery){
				System.out.println("Timeout");
				
				return false;

			}

		}

		return ((RegisterResponse) currentQueryResponse).getResult() == RegisterResponse.REGISTER_OK;
	}

<<<<<<< HEAD
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
			
			
			
=======
	

	public static void disconnected() {
		// TODO Auto-generated method stub
		if(!logged){
			hintText.setText("CONNECTION FAILED");
>>>>>>> f7c8bc01c515505d357a1f432945c30b3953a4f2
		}
	}

}
