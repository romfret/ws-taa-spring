package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatUI {

	private Commande unregisterCmd;
	private Commande postMessageCmd;
	
	
	private ChatUser u;
	
	private String title;

	private JFrame window;

	private JTextArea txtOutput;

	private JTextField txtMessage;

	private JButton btnSend;

	public ChatUI(){
		//aChatUI(0);
	}
	
	public void init() {

		//this.postMessageCmd = post;
		//this.unregisterCmd = unregister;
		JPanel panel = (JPanel) this.window.getContentPane();
		JScrollPane sclPane = new JScrollPane(txtOutput);
		panel.add(sclPane, BorderLayout.CENTER);
		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.add(this.txtMessage, BorderLayout.CENTER);
		southPanel.add(this.btnSend, BorderLayout.EAST);
		panel.add(southPanel, BorderLayout.SOUTH);

		// Gestion des évènements
		window.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				window_windowClosing(e);
			}
		});
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSend_actionPerformed(e);
			}
		});
		txtMessage.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent event) {
				if (event.getKeyChar() == '\n')
					btnSend_actionPerformed(null);
			}
		});

		// Initialisation des attributs
		this.txtOutput.setBackground(new Color(220, 220, 220));
		this.txtOutput.setEditable(false);
		this.window.setSize(500, 400);
		this.window.setVisible(true);
		this.txtMessage.requestFocus();
	}

	public void window_windowClosing(WindowEvent e) {
		try {
			unregisterCmd.execute();
		} catch (Exception exc) {
			System.err.println("Desinscription impossible");
		}
		 System.exit(-1);
	}

	public void btnSend_actionPerformed(ActionEvent e) {
		((CommandeServChatRoomPostMessage)postMessageCmd).setField(this.txtMessage);
		postMessageCmd.execute();
	}
	
    public void displayMessage(String message){
        this.txtOutput.append(message + "\n");
        this.txtOutput.moveCaretPosition(this.txtOutput.getText().length());
    }

    public String requestPseudo() {
         String pseudo = JOptionPane.showInputDialog(
                this.window, "Entrez votre pseudo : ",
                this.title,  JOptionPane.OK_OPTION
        );
        if (pseudo == null) System.exit(0);
        return pseudo;
    }

	public void setWindow(JFrame window) {
		this.window = window;
	}

	public void setU(ChatUser u) {
		this.u = u;
	}

	public void setTxtOutput(JTextArea txtOutput) {
		this.txtOutput = txtOutput;
	}

	public void setTxtMessage(JTextField txtMessage) {
		this.txtMessage = txtMessage;
	}

	public void setUnregisterCmd(Commande unregisterCmd) {
		this.unregisterCmd = unregisterCmd;
	}

	public void setPostMessageCmd(Commande postMessageCmd) {
		this.postMessageCmd = postMessageCmd;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBtnSend(JButton btnSend) {
		this.btnSend = btnSend;
	}
    

}
