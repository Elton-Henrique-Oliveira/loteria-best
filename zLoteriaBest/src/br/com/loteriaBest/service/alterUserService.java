 package br.com.loteriaBest.service;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.com.loteriaBest.entities.userEntity;
import br.com.loteriaBest.userCase.userUseCase;

@SuppressWarnings("serial")
public class alterUserService extends JFrame {

	private JPanel contentPane;
	private JPasswordField campo_senha_2;
	private JPasswordField campo_senha_1;
	private static alterUserService frame;
		/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userEntity usuarioBusca = new userEntity();
					usuarioBusca.setNome_usuario("Teste");
					frame = new alterUserService(usuarioBusca);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public alterUserService(userEntity pUsuario) {
		setTitle("Seja bem vindo " + pUsuario.getNome_usuario());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel painel_principal = new JPanel();
		painel_principal.setLayout(null);
		painel_principal.setBackground(SystemColor.controlDkShadow);
		painel_principal.setBounds(0, 0, 432, 238);
		contentPane.add(painel_principal);
		
		JLabel lbl_senha = new JLabel("Senha nova");
		lbl_senha.setForeground(Color.WHITE);
		lbl_senha.setFont(new Font("Candara Light", Font.BOLD, 15));
		lbl_senha.setBounds(38, 75, 86, 33);
		painel_principal.add(lbl_senha);
		
		JButton btn_alterar = new JButton("ALTERAR");
		btn_alterar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				userUseCase userUseCase = new userUseCase();
				userEntity usuarioAlteracaoSenha = new userEntity();
				
				usuarioAlteracaoSenha = userUseCase.alterarSenha(1, pUsuario,campo_senha_1.getText(), campo_senha_2.getText());
				
				if(usuarioAlteracaoSenha.getMsg_erro().trim().equals("") == true) {
					validateUserService window = new validateUserService();
					window.setVisible(true);
					window.setLocationRelativeTo(null);
				}else {
					JOptionPane.showMessageDialog(null, usuarioAlteracaoSenha.getMsg_erro());
				}
			}
		});
		btn_alterar.setForeground(Color.BLACK);
		btn_alterar.setFont(new Font("Candara Light", Font.BOLD, 15));
		btn_alterar.setBounds(117, 137, 210, 43);
		painel_principal.add(btn_alterar);
		
		campo_senha_2 = new JPasswordField();
		campo_senha_2.setForeground(Color.WHITE);
		campo_senha_2.setFont(new Font("Arial", Font.PLAIN, 15));
		campo_senha_2.setBackground(Color.LIGHT_GRAY);
		campo_senha_2.setBounds(134, 75, 220, 33);
		painel_principal.add(campo_senha_2);
		
		JLabel lbl_senha_mostra = new JLabel("");
		lbl_senha_mostra.setForeground(SystemColor.textHighlight);
		lbl_senha_mostra.setBackground(Color.WHITE);
		lbl_senha_mostra.setBounds(364, 98, 30, 29);
		painel_principal.add(lbl_senha_mostra);
		
		JLabel lbl_senha_1 = new JLabel("Senha nova");
		lbl_senha_1.setForeground(Color.WHITE);
		lbl_senha_1.setFont(new Font("Candara Light", Font.BOLD, 15));
		lbl_senha_1.setBounds(38, 31, 86, 33);
		painel_principal.add(lbl_senha_1);
		
		campo_senha_1 = new JPasswordField();
		campo_senha_1.setForeground(Color.WHITE);
		campo_senha_1.setFont(new Font("Arial", Font.PLAIN, 15));
		campo_senha_1.setBackground(Color.LIGHT_GRAY);
		campo_senha_1.setBounds(134, 31, 220, 33);
		painel_principal.add(campo_senha_1);
		
		JLabel lbl_senha_mostra_1 = new JLabel("");
		lbl_senha_mostra_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				manipulaSenha(2);
			}
		});
		lbl_senha_mostra_1.setIcon(new ImageIcon(alterUserService.class.getResource("/br/elton/imagens/pngwing.com_3.png")));
		lbl_senha_mostra_1.setForeground(SystemColor.textHighlight);
		lbl_senha_mostra_1.setBackground(Color.WHITE);
		lbl_senha_mostra_1.setBounds(364, 31, 30, 29);
		painel_principal.add(lbl_senha_mostra_1);
		
		JLabel lbl_senha_mostra_2 = new JLabel("");
		lbl_senha_mostra_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				manipulaSenha(1);
			}
		});
		lbl_senha_mostra_2.setIcon(new ImageIcon(alterUserService.class.getResource("/br/elton/imagens/pngwing.com_3.png")));
		lbl_senha_mostra_2.setForeground(SystemColor.textHighlight);
		lbl_senha_mostra_2.setBackground(Color.WHITE);
		lbl_senha_mostra_2.setBounds(364, 82, 30, 29);
		painel_principal.add(lbl_senha_mostra_2);
	}
	
	private void manipulaSenha(int pIndicador) {
		
		if(pIndicador == 1) {
			if (campo_senha_2.getEchoChar() != '\u0000') { // mascara e desmacara senha
				campo_senha_2.setEchoChar('\u0000');
			} else {
				campo_senha_2.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
			}
		}
		
		if(pIndicador == 2) {
			if (campo_senha_1.getEchoChar() != '\u0000') { // mascara e desmacara senha
				campo_senha_1.setEchoChar('\u0000');
			} else {
				campo_senha_1.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
			}
		}

	}
}
