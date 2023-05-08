package br.com.loteriaBest.service;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import br.com.loteriaBest.entities.companyEntity;
import br.com.loteriaBest.entities.userEntity;
import br.com.loteriaBest.userCase.companyUseCase;

public class mainService {

	public static JFrame frame;
	private JDesktopPane desktop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					userEntity usuarioTeste = new userEntity();
					usuarioTeste.setNome_usuario("Teste");
					usuarioTeste.setEmpresa(1);
					usuarioTeste.setNivel(2);
					usuarioTeste.setMsg_erro("");

					mainService window = new mainService(usuarioTeste);
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainService(userEntity usuario) {
		initialize(usuario);
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(userEntity usuario) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1445, 607);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel painel_principal = new JPanel();
		frame.getContentPane().add(painel_principal, BorderLayout.CENTER);
		painel_principal.setLayout(null);

		desktop = new JDesktopPane();
		desktop.setBounds(0, 40, 938, 528);
		painel_principal.add(desktop);

		JMenuBar menu = new JMenuBar();
		menu.setBounds(0, 0, 1429, 35);
		painel_principal.add(menu);

		JMenu menu_espaco_1 = new JMenu("");
		menu.add(menu_espaco_1);

		JMenu menu_usuarios = new JMenu("Usuarios");
		menu.add(menu_usuarios);

		JMenuItem menu_usuarios_manutencao = new JMenuItem("Manuten\u00E7\u00E3o");
		menu_usuarios_manutencao.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				createUserService createUserService = new createUserService(usuario);
				createUserService.setVisible(true);
				createUserService.setBounds(0, 0, 938, 528);
				desktop.add(createUserService);
			}
		});
		menu_usuarios.add(menu_usuarios_manutencao);
		
		JMenu mnLoteria = new JMenu("Loteria");
		menu.add(mnLoteria);
		
		JMenuItem menu_loteria_capa = new JMenuItem("Loteria Capa");
		menu_loteria_capa.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				eventService eventService = new eventService(usuario);
				eventService.setVisible(true);
				eventService.setBounds(0, 0, 938, 528);
				desktop.add(eventService);
			}
		});
		mnLoteria.add(menu_loteria_capa);

		JLabel funcionario = new JLabel(usuario.getNome_usuario());
		funcionario.setFont(new Font("Arial", Font.PLAIN, 26));
		funcionario.setBounds(964, 56, 455, 35);
		painel_principal.add(funcionario);

		JLabel empresa = new JLabel(buscaNomeEmpresa(usuario.getEmpresa()));
		empresa.setFont(new Font("Arial", Font.PLAIN, 26));
		empresa.setBounds(964, 148, 455, 35);
		painel_principal.add(empresa);

		JLabel data = new JLabel(verificaDataAtual());
		data.setFont(new Font("Arial", Font.PLAIN, 26));
		data.setBounds(964, 196, 455, 35);
		painel_principal.add(data);

		JLabel cargo = new JLabel(verificaCargo(usuario.getNivel()));
		cargo.setFont(new Font("Arial", Font.PLAIN, 26));
		cargo.setBounds(964, 102, 455, 35);
		painel_principal.add(cargo);
	}

	private String buscaNomeEmpresa(int pEmpresa) {
		String nomeRetorno = "";

		companyEntity companyEntity = new companyEntity();
		companyUseCase companyUseCase = new companyUseCase();

		companyEntity = companyUseCase.verificaNomeEmpresa(pEmpresa);
		if (!companyEntity.getMsg_erro().trim().equals("")) {
			nomeRetorno = "Empresa inaexistente/inativa";
		} else {
			nomeRetorno = companyEntity.getNome();
		}

		return nomeRetorno;
	}

	private String verificaDataAtual() {
		String dataRetorno = "";

		Date data = new Date();
		DateFormat formatadorData = DateFormat.getDateInstance(DateFormat.SHORT);
		dataRetorno = formatadorData.format(data);

		return dataRetorno;
	}

	private String verificaCargo(int pCargo) {
		String cargoRetorno = "";

		switch (pCargo) {
		case 1:
			cargoRetorno = "Diretor";
			break;
		case 2:
			cargoRetorno = "Gerente";
			break;
		case 3:
			cargoRetorno = "Funcionario";
			break;
		case 4:
			cargoRetorno = "Cliente";
			break;

		default:
			break;
		}

		return cargoRetorno;
	}
}
