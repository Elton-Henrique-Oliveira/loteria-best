package br.com.loteriaBest.service;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.loteriaBest.entities.groupEntity;
import br.com.loteriaBest.entities.userEntity;
import br.com.loteriaBest.userCase.groupUseCase;
import br.com.loteriaBest.userCase.userUseCase;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class createUserService extends JInternalFrame {
	private JTextField nome;
	private JTextField usuarioLogin;
	private JTextField senha;
	private JTextField email;
	private JTextField telefone;
	@SuppressWarnings("rawtypes")
	private JComboBox status;
	@SuppressWarnings("rawtypes")
	private JComboBox tipos;
	private JLabel botao_excluir;
	private JLabel botao_pesquisar;
	private JLabel botao_editar;
	private JLabel botao_incluir;
	@SuppressWarnings("rawtypes")
	private JComboBox grupo;
	private ArrayList<groupEntity> grupos = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userEntity usuarioTeste = new userEntity();
					usuarioTeste.setNome_usuario("Teste");
					usuarioTeste.setEmpresa(1);
					usuarioTeste.setNivel(1);
					usuarioTeste.setMsg_erro("");

					createUserService frame = new createUserService(usuarioTeste);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public createUserService(userEntity usuario) {
		setIconifiable(true);
		setBackground(Color.LIGHT_GRAY);
		setNormalBounds(new Rectangle(5, 5, 5, 5));
		setTitle("Usuario");

		setClosable(true);
		setBounds(0, 40, 938, 528);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(31, 69, 113, 35);
		panel.add(lblNewLabel);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 13));
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(31, 115, 113, 35);
		panel.add(lblUsuario);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Arial", Font.BOLD, 13));
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setBounds(411, 115, 88, 35);
		panel.add(lblSenha);

		JLabel lblNewLabel_1_1_1 = new JLabel("Telefone");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setBounds(56, 150, 88, 35);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Email/discord");
		lblNewLabel_1_1_2.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_2.setBounds(31, 196, 113, 35);
		panel.add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Permiss\u00E3o");
		lblNewLabel_1_1_2_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_2_1.setBounds(31, 242, 113, 35);
		panel.add(lblNewLabel_1_1_2_1);

		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Status");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1_1_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_2_1_1.setBounds(411, 242, 88, 35);
		panel.add(lblNewLabel_1_1_2_1_1);

		tipos = new JComboBox();
		tipos.setFont(new Font("Arial", Font.PLAIN, 12));
		tipos.setBounds(167, 242, 193, 28);
		panel.add(tipos);

		status = new JComboBox();
		status.setFont(new Font("Arial", Font.PLAIN, 12));
		status.setBounds(509, 245, 193, 28);
		panel.add(status);

		nome = new JTextField();
		nome.setFont(new Font("Arial", Font.PLAIN, 12));
		nome.setBounds(167, 72, 583, 28);
		panel.add(nome);
		nome.setColumns(10);

		usuarioLogin = new JTextField();
		usuarioLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					consultaUsuario(usuario);
				}
			}
		});
		usuarioLogin.setFont(new Font("Arial", Font.PLAIN, 12));
		usuarioLogin.setColumns(10);
		usuarioLogin.setBounds(167, 118, 241, 28);
		panel.add(usuarioLogin);

		senha = new JTextField();
		senha.setFont(new Font("Arial", Font.PLAIN, 12));
		senha.setColumns(10);
		senha.setBounds(509, 118, 241, 28);
		panel.add(senha);

		email = new JTextField();
		email.setFont(new Font("Arial", Font.PLAIN, 12));
		email.setColumns(10);
		email.setBounds(167, 203, 241, 28);
		panel.add(email);

		telefone = new JTextField();
		telefone.setFont(new Font("Arial", Font.PLAIN, 12));
		telefone.setColumns(10);
		telefone.setBounds(166, 157, 241, 28);
		panel.add(telefone);

		botao_incluir = new JLabel("");
		botao_incluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					criarUsuarioNovo(usuario);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		botao_incluir
				.setIcon(new ImageIcon(createUserService.class.getResource("/br/com/loteriaBest/imgs/adicionar.png")));
		botao_incluir.setBounds(322, 365, 50, 65);
		botao_incluir.setVisible(false);
		panel.add(botao_incluir);

		botao_pesquisar = new JLabel("");
		botao_pesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				consultaUsuario(usuario);
			}
		});
		botao_pesquisar
				.setIcon(new ImageIcon(createUserService.class.getResource("/br/com/loteriaBest/imgs/consultar.png")));
		botao_pesquisar.setBounds(401, 365, 50, 65);
		panel.add(botao_pesquisar);

		botao_editar = new JLabel("");
		botao_editar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					alteraDadosUsuario(usuario);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		botao_editar.setIcon(new ImageIcon(createUserService.class.getResource("/br/com/loteriaBest/imgs/editar.png")));
		botao_editar.setBounds(480, 365, 50, 65);
		botao_editar.setVisible(false);
		panel.add(botao_editar);

		botao_excluir = new JLabel("");
		botao_excluir
				.setIcon(new ImageIcon(createUserService.class.getResource("/br/com/loteriaBest/imgs/remover.png")));
		botao_excluir.setBounds(549, 365, 58, 65);
		botao_excluir.setVisible(false);
		panel.add(botao_excluir);

		JLabel lblNewLabel_1_1_2_1_2 = new JLabel("Grupo");
		lblNewLabel_1_1_2_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_2_1_2.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1_1_2_1_2.setBounds(31, 288, 113, 35);
		panel.add(lblNewLabel_1_1_2_1_2);

		grupo = new JComboBox();
		grupo.setFont(new Font("Arial", Font.PLAIN, 12));
		grupo.setBounds(167, 288, 193, 28);
		panel.add(grupo);

		iniciaCombos(usuario);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void iniciaCombos(userEntity usuario) {

		botao_editar.setVisible(true);
		botao_incluir.setVisible(true);

		status.setModel(new DefaultComboBoxModel(new String[] { "Ativo", "Inativo" }));
		tipos.setModel(new DefaultComboBoxModel(new String[] { "Diretor", "Gerente", "Funcionario", "Cliente" }));

		groupUseCase groupUseCase = new groupUseCase();

		grupos = groupUseCase.listaGrupos(usuario.getEmpresa());

		for (int i = 0; i < grupos.size(); i++) {
			grupo.addItem(grupos.get(i).getNome());
		}

	}

	public userEntity leCamposTela() {
		userEntity userEntity = new userEntity();

		userEntity.setCodigo_usuario(0);
		userEntity.setData_nascimento("");
		userEntity.setEmail(email.getText());
		userEntity.setEmpresa(1);
		userEntity.setGrupo(grupo.getSelectedIndex() + 1);
		userEntity.setNome_usuario(nome.getText());
		userEntity.setTelefone(telefone.getText());
		userEntity.setUsuario_login(usuarioLogin.getText());
		userEntity.setUsuario_senha(senha.getText());
		userEntity.setNivel(tipos.getSelectedIndex() + 1);
		userEntity.setStatus(status.getSelectedIndex() + 1);
		userEntity.setMsg_erro("");

		return userEntity;

	}

	private void criarUsuarioNovo(userEntity pUsuarioAutenticado) throws SQLException {
		userEntity usuarioBusca = new userEntity();
		userUseCase userUseCase = new userUseCase();

		usuarioBusca = userUseCase.criarUsuario(1, pUsuarioAutenticado, leCamposTela());

		preencheCamposTela(pUsuarioAutenticado, usuarioBusca);
	}

	private void preencheCamposTela(userEntity pUsuarioAutenticado, userEntity pUsuario) {

		if (pUsuario.getMsg_erro().trim().equals("") == false) {
			JOptionPane.showMessageDialog(null, pUsuario.getMsg_erro());
		} else {

			email.setText(pUsuario.getEmail());
			grupo.setSelectedIndex(pUsuario.getGrupo() - 1);
			tipos.setSelectedIndex(pUsuario.getNivel() - 1);
			status.setSelectedIndex(pUsuario.getStatus() - 1);
			usuarioLogin.setText(pUsuario.getUsuario_login());
			if (pUsuarioAutenticado.getNivel() <= 2) {
				senha.setText(pUsuario.getUsuario_senha());
			}
			telefone.setText(pUsuario.getTelefone());
			nome.setText(pUsuario.getNome_usuario());

		}
	}

	private void alteraDadosUsuario(userEntity pUsuarioAutenticado) throws SQLException {
		userEntity usuarioBusca = new userEntity();
		userUseCase userUseCase = new userUseCase();

		usuarioBusca = userUseCase.alteraUsuario(1, pUsuarioAutenticado, leCamposTela());

		preencheCamposTela(pUsuarioAutenticado, usuarioBusca);
	}

	public void consultaUsuario(userEntity pUsuarioAutenticado) {
		userEntity usuarioBusca = new userEntity();
		userUseCase userUseCase = new userUseCase();

		usuarioBusca = userUseCase.buscaUsuario(1, pUsuarioAutenticado, leCamposTela());

		preencheCamposTela(pUsuarioAutenticado, usuarioBusca);
	}
}
