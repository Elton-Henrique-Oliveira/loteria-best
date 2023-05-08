package br.com.loteriaBest.service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import br.com.loteriaBest.connection.connectionBank;
import br.com.loteriaBest.entities.userEntity;
import br.com.loteriaBest.userCase.userUseCase;

@SuppressWarnings("serial")
public class validateUserService extends JFrame {

	private JPanel contentPane;
	private JTextField imput_usuario;
	public JPasswordField imput_senha;
	static validateUserService frame;
	private JLabel lblNewLabel;
	private int conectado = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new validateUserService();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public validateUserService() {

		formWindowActivated();

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(validateUserService.class.getResource("/br/com/loteriaBest/imgs/pessoal.png")));
		setTitle("Seja bem vindo");
		setBackground(SystemColor.controlDkShadow);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 296);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel painel_principal = new JPanel();
		painel_principal.setBackground(Color.LIGHT_GRAY);
		contentPane.add(painel_principal, BorderLayout.CENTER);
		painel_principal.setLayout(null);

		JLabel lbl_usuario = new JLabel("Usuario");
		lbl_usuario.setForeground(Color.BLACK);
		lbl_usuario.setFont(new Font("Candara Light", Font.BOLD, 15));
		lbl_usuario.setBounds(38, 50, 86, 33);
		painel_principal.add(lbl_usuario);

		JLabel lbl_senha = new JLabel("Senha");
		lbl_senha.setForeground(Color.BLACK);
		lbl_senha.setFont(new Font("Candara Light", Font.BOLD, 15));
		lbl_senha.setBounds(38, 94, 86, 33);
		painel_principal.add(lbl_senha);

		imput_usuario = new JTextField();
		imput_usuario.setBackground(Color.WHITE);
		imput_usuario.setForeground(Color.BLACK);
		imput_usuario.setFont(new Font("Arial", Font.PLAIN, 15));
		imput_usuario.setBounds(134, 50, 265, 33);
		painel_principal.add(imput_usuario);
		imput_usuario.setColumns(10);

		JButton btn_logar = new JButton("");
		btn_logar.setIcon(new ImageIcon(validateUserService.class.getResource("/br/com/loteriaBest/imgs/login.png")));
		btn_logar.addActionListener(new ActionListener() {
			@SuppressWarnings({ "static-access" })
			public void actionPerformed(ActionEvent e) {

				userEntity usuarioBusca = new userEntity();
				userUseCase userUseCase = new userUseCase();

				if (conectado == 1) {
					String senha = new String(imput_senha.getPassword());
					usuarioBusca = userUseCase.verificaLogin(1, imput_usuario.getText(), senha);
					if (usuarioBusca.getMsg_erro().trim().equals("") == true) {
						mainService mainService = new mainService(usuarioBusca);
						mainService.frame.setVisible(true);
						mainService.frame.setLocationRelativeTo(null);
						frame.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, usuarioBusca.getMsg_erro());
					}
				} else {
					JOptionPane.showMessageDialog(null, "Impossível fazer login, servidor inativo!");
				}
			}
		});
		btn_logar.setForeground(SystemColor.inactiveCaptionText);
		btn_logar.setFont(new Font("Candara Light", Font.BOLD, 15));
		btn_logar.setBounds(253, 157, 66, 57);
		painel_principal.add(btn_logar);

		imput_senha = new JPasswordField();
		imput_senha.setEchoChar('*');
		imput_senha.addKeyListener(new KeyAdapter() {
			@SuppressWarnings({ "static-access" })
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					userEntity usuarioBusca = new userEntity();
					userUseCase userUseCase = new userUseCase();

					if (conectado == 1) {
						String senha = new String(imput_senha.getPassword());
						usuarioBusca = userUseCase.verificaLogin(1, imput_usuario.getText(), senha);
						if (usuarioBusca.getMsg_erro().trim().equals("") == true) {
							mainService mainService = new mainService(usuarioBusca);
							mainService.frame.setVisible(true);
							mainService.frame.setLocationRelativeTo(null);
							frame.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, usuarioBusca.getMsg_erro());
						}
					} else {
						JOptionPane.showMessageDialog(null, "Impossível fazer login, servidor inativo!");
					}
				}
			}
		});
		imput_senha.setBackground(Color.WHITE);
		imput_senha.setForeground(Color.BLACK);
		imput_senha.setFont(new Font("Arial", Font.PLAIN, 15));
		imput_senha.setBounds(134, 94, 220, 33);
		painel_principal.add(imput_senha);

		lblNewLabel = new JLabel("");
		if (conectado == 1) {
			lblNewLabel.setIcon(
					new ImageIcon(validateUserService.class.getResource("/br/com/loteriaBest/imgs/dataBaseOn.png")));
		} else {
			lblNewLabel.setIcon(
					new ImageIcon(validateUserService.class.getResource("/br/com/loteriaBest/imgs/dataBaseOff.png")));
		}
		lblNewLabel.setBounds(137, 157, 56, 57);
		painel_principal.add(lblNewLabel);

		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manipulaSenha();
			}
		});
		btnNewButton.setIcon(
				new ImageIcon(validateUserService.class.getResource("/br/com/loteriaBest/imgs/mostrarSenha.png")));
		btnNewButton.setBounds(359, 94, 41, 33);
		painel_principal.add(btnNewButton);
	}

	private void manipulaSenha() {
		if (imput_senha.getEchoChar() != '\u0000') { // mascara e desmacara senha
			imput_senha.setEchoChar('\u0000');
		} else {
			
			//imput_senha.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
			imput_senha.setEchoChar('*');
		}
	}

	private void formWindowActivated() {

		Connection conn = (Connection) new connectionBank().conectaBDteste();
		try {
			if (conn != null) {
				conectado = 1;
			} else {
				conectado = 0;
			}

		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}
