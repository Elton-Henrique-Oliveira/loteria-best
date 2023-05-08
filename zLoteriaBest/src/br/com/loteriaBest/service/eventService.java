package br.com.loteriaBest.service;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.loteriaBest.entities.loteriaEntity;
import br.com.loteriaBest.entities.userEntity;
import br.com.loteriaBest.userCase.loteriaUseCase;
import br.com.loteriaBest.userCase.userUseCase;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class eventService extends JInternalFrame {
	private JTextField codigo;
	private JTextField nome;
	private JTextField numero_inicial;
	private JTextField numero_final;
	private JTextField bilhete_valor;
	private JTextField premio_inicial;
	private JTextField premio_acumulado;
	private JTextField porcentagem_acumula;
	private JTextField porcentagem_comissao;
	private JTextField porcentagem_empresa;
	private JTextPane descricao;
	private JLabel valor_arrecadado;
	private JComboBox status;

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

					eventService frame = new eventService(usuarioTeste);
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
	public eventService(userEntity pUsuarioAutenticado) {
		setBounds(0, 40, 938, 528);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(68, 49, 50, 31);
		panel.add(lblNewLabel);

		JLabel lblNomeSorteio = new JLabel("Nome");
		lblNomeSorteio.setFont(new Font("Arial", Font.BOLD, 13));
		lblNomeSorteio.setBounds(245, 49, 44, 31);
		panel.add(lblNomeSorteio);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescrio.setFont(new Font("Arial", Font.BOLD, 13));
		lblDescrio.setBounds(49, 302, 70, 31);
		panel.add(lblDescrio);

		JLabel lblNumeroInicial = new JLabel("Numero Inicial");
		lblNumeroInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroInicial.setFont(new Font("Arial", Font.BOLD, 13));
		lblNumeroInicial.setBounds(49, 116, 137, 31);
		panel.add(lblNumeroInicial);

		JLabel lblNumeroFinal = new JLabel("Numero final");
		lblNumeroFinal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroFinal.setFont(new Font("Arial", Font.BOLD, 13));
		lblNumeroFinal.setBounds(572, 116, 84, 31);
		panel.add(lblNumeroFinal);

		JLabel lblValorBilhete = new JLabel("Valor bilhete");
		lblValorBilhete.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorBilhete.setFont(new Font("Arial", Font.BOLD, 13));
		lblValorBilhete.setBounds(49, 161, 137, 31);
		panel.add(lblValorBilhete);

		JLabel lblPremioInicial = new JLabel("Premio inicial");
		lblPremioInicial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPremioInicial.setFont(new Font("Arial", Font.BOLD, 13));
		lblPremioInicial.setBounds(49, 206, 137, 31);
		panel.add(lblPremioInicial);

		JLabel lblPremioAcumulado = new JLabel("Premio acumulado");
		lblPremioAcumulado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPremioAcumulado.setFont(new Font("Arial", Font.BOLD, 13));
		lblPremioAcumulado.setBounds(49, 251, 137, 31);
		panel.add(lblPremioAcumulado);

		JLabel lblAcu = new JLabel("% acumula");
		lblAcu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAcu.setFont(new Font("Arial", Font.BOLD, 13));
		lblAcu.setBounds(585, 161, 71, 31);
		panel.add(lblAcu);

		JLabel lblPremioAcumulado_1_1 = new JLabel("% comissao");
		lblPremioAcumulado_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPremioAcumulado_1_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblPremioAcumulado_1_1.setBounds(578, 206, 78, 31);
		panel.add(lblPremioAcumulado_1_1);

		JLabel lblPremioAcumulado_1_2 = new JLabel("% da empresa");
		lblPremioAcumulado_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPremioAcumulado_1_2.setFont(new Font("Arial", Font.BOLD, 13));
		lblPremioAcumulado_1_2.setBounds(563, 251, 93, 31);
		panel.add(lblPremioAcumulado_1_2);

		codigo = new JTextField();
		codigo.setBounds(128, 54, 86, 25);
		panel.add(codigo);
		codigo.setColumns(10);

		nome = new JTextField();
		nome.setColumns(10);
		nome.setBounds(299, 54, 544, 25);
		panel.add(nome);

		numero_inicial = new JTextField();
		numero_inicial.setColumns(10);
		numero_inicial.setBounds(203, 119, 106, 25);
		panel.add(numero_inicial);

		numero_final = new JTextField();
		numero_final.setColumns(10);
		numero_final.setBounds(666, 119, 86, 25);
		panel.add(numero_final);

		bilhete_valor = new JTextField();
		bilhete_valor.setColumns(10);
		bilhete_valor.setBounds(203, 164, 106, 25);
		panel.add(bilhete_valor);

		premio_inicial = new JTextField();
		premio_inicial.setColumns(10);
		premio_inicial.setBounds(203, 209, 106, 25);
		panel.add(premio_inicial);

		premio_acumulado = new JTextField();
		premio_acumulado.setEditable(false);
		premio_acumulado.setColumns(10);
		premio_acumulado.setBounds(203, 254, 106, 25);
		panel.add(premio_acumulado);

		porcentagem_acumula = new JTextField();
		porcentagem_acumula.setColumns(10);
		porcentagem_acumula.setBounds(666, 164, 86, 25);
		panel.add(porcentagem_acumula);

		porcentagem_comissao = new JTextField();
		porcentagem_comissao.setColumns(10);
		porcentagem_comissao.setBounds(666, 209, 86, 25);
		panel.add(porcentagem_comissao);

		porcentagem_empresa = new JTextField();
		porcentagem_empresa.setColumns(10);
		porcentagem_empresa.setBounds(666, 254, 86, 25);
		panel.add(porcentagem_empresa);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 344, 469, 120);
		panel.add(scrollPane);

		descricao = new JTextPane();
		scrollPane.setViewportView(descricao);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					criarLoteria(pUsuarioAutenticado);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(eventService.class.getResource("/br/com/loteriaBest/imgs/adicionar.png")));
		lblNewLabel_1.setBounds(606, 376, 50, 58);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				consultaLoteria(pUsuarioAutenticado);
			}
		});
		lblNewLabel_1_1
				.setIcon(new ImageIcon(eventService.class.getResource("/br/com/loteriaBest/imgs/consultar.png")));
		lblNewLabel_1_1.setBounds(681, 376, 55, 58);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					alteraLoteria(pUsuarioAutenticado);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		lblNewLabel_1_2.setIcon(new ImageIcon(eventService.class.getResource("/br/com/loteriaBest/imgs/editar.png")));
		lblNewLabel_1_2.setBounds(758, 376, 50, 58);
		panel.add(lblNewLabel_1_2);

		valor_arrecadado = new JLabel("Valor Arrecadado:  0");
		valor_arrecadado.setHorizontalAlignment(SwingConstants.LEFT);
		valor_arrecadado.setFont(new Font("Arial", Font.BOLD, 13));
		valor_arrecadado.setBounds(196, 302, 230, 31);
		panel.add(valor_arrecadado);

		JLabel lblPremioAcumulado_1 = new JLabel("Premio acumulado");
		lblPremioAcumulado_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPremioAcumulado_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblPremioAcumulado_1.setBounds(519, 302, 137, 31);
		panel.add(lblPremioAcumulado_1);

		status = new JComboBox();
		status.setModel(new DefaultComboBoxModel(new String[] { "Ativo", "Inativo" }));
		status.setFont(new Font("Arial", Font.PLAIN, 12));
		status.setBounds(666, 306, 177, 28);
		panel.add(status);
	}

	private loteriaEntity leCamposTela(userEntity pUsuarioAutenticado) {
		loteriaEntity loteriaTela = new loteriaEntity();

		try {
			loteriaTela.setEmpresa(1);
			loteriaTela.setCodigo(Integer.parseInt(codigo.getText()));
			loteriaTela.setDescricao(descricao.getText());
			loteriaTela.setGrupo(pUsuarioAutenticado.getGrupo());
			loteriaTela.setNome(nome.getText());
			loteriaTela.setNumero_maximo(Integer.parseInt(numero_final.getText()));
			loteriaTela.setNumero_minimo(Integer.parseInt(numero_inicial.getText()));
			loteriaTela.setPorcentagem_acrescimo(Integer.parseInt(porcentagem_acumula.getText()));
			loteriaTela.setPorcentagem_comissao(Integer.parseInt(porcentagem_comissao.getText()));
			loteriaTela.setPorcentagem_empresa(Integer.parseInt(porcentagem_empresa.getText()));
			loteriaTela.setPremio_inicial(Integer.parseInt(premio_inicial.getText()));
			loteriaTela.setPremio_final(Integer.parseInt(premio_acumulado.getText()));
			loteriaTela.setValor_bilhete(Integer.parseInt(bilhete_valor.getText()));
			loteriaTela.setAtivo(status.getSelectedIndex() + 1);
		} catch (Exception e) {
			System.out.println("Leitura tela: " + e);
		}

		return loteriaTela;
	}

	private void preencheCamposTela(userEntity pUsuarioAutenticado, loteriaEntity pLoteria) {
		try {
			if (pLoteria.getMsg_erro().trim().equals("") == false) {
				JOptionPane.showMessageDialog(null, pLoteria.getMsg_erro());
			} else {
				codigo.setText(Integer.toString(pLoteria.getCodigo()));
				bilhete_valor.setText(Integer.toString(pLoteria.getValor_bilhete()));
				descricao.setText(pLoteria.getDescricao());
				nome.setText(pLoteria.getNome());
				numero_inicial.setText(Integer.toString(pLoteria.getNumero_minimo()));
				numero_final.setText(Integer.toString(pLoteria.getNumero_maximo()));
				porcentagem_acumula.setText(Integer.toString(pLoteria.getPorcentagem_acrescimo()));
				porcentagem_comissao.setText(Integer.toString(pLoteria.getPorcentagem_comissao()));
				porcentagem_empresa.setText(Integer.toString(pLoteria.getPorcentagem_empresa()));
				premio_inicial.setText(Integer.toString(pLoteria.getPremio_inicial()));
				premio_acumulado.setText(Integer.toString(pLoteria.getPremio_final()));
				valor_arrecadado.setText("Valor Arrecadado: " + Integer.toString(pLoteria.getValor_arrecadado()));
				status.setSelectedIndex(pLoteria.getAtivo() - 1);
			}
		} catch (Exception e) {
			//System.out.println("Mandando para tela: " + e);
		}
	}

	public void consultaLoteria(userEntity pUsuarioAutenticado) {

		loteriaEntity loteriaBusca = new loteriaEntity();
		loteriaUseCase loteriaUseCase = new loteriaUseCase();

		loteriaBusca = loteriaUseCase.buscaLoteria(1, pUsuarioAutenticado, leCamposTela(pUsuarioAutenticado));

		preencheCamposTela(pUsuarioAutenticado, loteriaBusca);
	}

	private void criarLoteria(userEntity pUsuarioAutenticado) throws SQLException {
		loteriaEntity loteriaBusca = new loteriaEntity();
		loteriaUseCase loteriaUseCase = new loteriaUseCase();

		loteriaBusca = loteriaUseCase.criarLoteria(1, pUsuarioAutenticado, leCamposTela(pUsuarioAutenticado));

		preencheCamposTela(pUsuarioAutenticado, loteriaBusca);
	}

	private void alteraLoteria(userEntity pUsuarioAutenticado) throws SQLException {
		loteriaEntity loteriaBusca = new loteriaEntity();
		loteriaUseCase loteriaUseCase = new loteriaUseCase();

		loteriaBusca = loteriaUseCase.alteraLoteria(1, pUsuarioAutenticado, leCamposTela(pUsuarioAutenticado));

		preencheCamposTela(pUsuarioAutenticado, loteriaBusca);
	}
}
