package br.com.loteriaBest.userCase;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.loteriaBest.entities.userEntity;
import br.com.loteriaBest.repository.userRepository;

public class userUseCase {

	private userEntity userEntity = new userEntity();
	private ArrayList<userEntity> listaUsuarios = new ArrayList<>();

	public userEntity verificaLogin(int pEmpresa, String pUsuarioLogin, String pUsuarioSenha) {

		String msg_erro = "";

		msg_erro = verificaUsuarioSenhaPreenchida(pUsuarioLogin, pUsuarioSenha);

		if (msg_erro.trim().equals("") == true) {

			listaUsuarios = verificaUsuarioSenhaBanco(pEmpresa, pUsuarioLogin, pUsuarioSenha);

			return listaUsuarios.get(0);

		} else {

			userEntity.setMsg_erro(msg_erro);

			return userEntity;
		}
	}

	public userEntity alterarSenha(int pEmpresa, userEntity pUsuario, String pSenhaAlterada1, String pSenhaAlterada2) {

		String msg_erro = "";

		msg_erro = verificaSenhasIguais(pSenhaAlterada1, pSenhaAlterada2);

		if (msg_erro.trim().equals("") == true) {

			atualizaSenhaUsuarioBanco(pEmpresa, pUsuario, pSenhaAlterada1);
			userEntity.setMsg_erro("");

			return userEntity;

		} else {

			userEntity.setMsg_erro(msg_erro);

			return userEntity;
		}
	}

	public String verificaUsuarioSenhaPreenchida(String pUsuarioLogin, String pUsuarioSenha) {

		String msg_erro = "";

		if (pUsuarioLogin.trim().equals("") == true) {
			msg_erro += "\nO usuário não pode ser vazio!!";
		}
		if (pUsuarioSenha.trim().equals("") == true) {
			msg_erro += "\nA senha não pode ser vazia!!";
		}

		return msg_erro;
	}

	public String verificaSenhasIguais(String pSenhaAlterada1, String pSenhaAlterada2) {

		String msg_erro = "";

		if (pSenhaAlterada1.trim().equals("") == true) {
			msg_erro += "\nO usuário não pode ser vazio!!";
		}
		if (pSenhaAlterada2.trim().equals("") == true) {
			msg_erro += "\nA senha não pode ser vazia!!";
		}
		if (pSenhaAlterada1.equals(pSenhaAlterada2) == false) {
			msg_erro += "\nAs senhas não são iguais!!";
		}

		return msg_erro;
	}

	private ArrayList<userEntity> verificaUsuarioSenhaBanco(int pEmpresa, String pUsuarioLogin, String pUsuarioSenha) {

		userRepository userRepository = new userRepository();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList<userEntity> usuarioBusca = new ArrayList();

		usuarioBusca = userRepository.consultaUsuarioLogin(1, pEmpresa, pUsuarioLogin, pUsuarioSenha);

		return usuarioBusca;
	}

	public userEntity buscaUsuario(int pEmpresa, userEntity pUsuarioAutenticacao, userEntity pUsuario) {

		userRepository userRepository = new userRepository();
		userEntity usuarioBusca = new userEntity();

		usuarioBusca = userRepository.consultaUsuario(pEmpresa, pUsuarioAutenticacao, pUsuario);

		return usuarioBusca;
	}
	
	private boolean verificaPermissaoCriar(int pIndicador, int pEmpresa, userEntity pUsuarioAutenticado, userEntity pUsuario) {

		userRepository userRepository = new userRepository();
		userEntity usuarioBusca = new userEntity();
		
		if(pIndicador == 0) {
			usuarioBusca = userRepository.consultaUsuario(pEmpresa, pUsuarioAutenticado, pUsuario);
			if (usuarioBusca.getMsg_erro().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Usuário já existente!");
				return false;
			}
			
			if (pUsuario.getStatus() != 1) {
				JOptionPane.showMessageDialog(null, "Não é possível cadastrar funcionário/cliente inativado!");
				return false;
			}
		}else {
			usuarioBusca = userRepository.consultaUsuario(pEmpresa, pUsuarioAutenticado, pUsuario);
			if (!usuarioBusca.getMsg_erro().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Usuário não existente para ser alterado!");
				return false;
			}
		}

		if (pUsuarioAutenticado.getNivel() == 3 && pUsuario.getNivel() <= 3) {
			JOptionPane.showMessageDialog(null, "Usuário sem permissão para cadastrar/alterar um funcionário!");
			return false;
		}
		
		if (pUsuarioAutenticado.getNivel() == 3 && pUsuario.getStatus() != 1) {
			JOptionPane.showMessageDialog(null, "Usuário sem permissão para inativar cliente/funcionário!");
			return false;
		}
		
		if (pUsuario.getNivel() <= 3 && pUsuario.getUsuario_senha().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Para cadastrar/alterar um funcionario informe uma senha!");
			return false;
		}

		if (pUsuario.getNivel() > 3 && !pUsuario.getUsuario_senha().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Para cadastrar/alterar um cliente a senha não pode ser informada!");
			return false;
		}
		
		if (pUsuario.getTelefone().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Informe o telefone para contato!");
			return false;
		}

		if (pUsuario.getNome_usuario().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Informe o nome do usuario!");
			return false;
		}

		return true;
	}

	public userEntity criarUsuario(int pEmpresa, userEntity pUsuarioAutenticado, userEntity pUsuario)
			throws SQLException {

		userEntity usuarioInclusao = new userEntity();

		if (verificaPermissaoCriar(0, pEmpresa, pUsuarioAutenticado, pUsuario)) {
			userRepository userRepository = new userRepository();
			userRepository.cadastroUsuarioNovo(pEmpresa, pUsuario);

			usuarioInclusao = userRepository.consultaUsuario(pEmpresa, pUsuarioAutenticado, pUsuario);

			JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso!!");
		} else {
			usuarioInclusao = pUsuario;
			usuarioInclusao.setMsg_erro("");
		}

		return usuarioInclusao;
	}

	public userEntity alteraUsuario(int pEmpresa, userEntity pUsuarioAutenticado, userEntity pUsuario)
			throws SQLException {

		userEntity usuarioInclusao = new userEntity();

		if (verificaPermissaoCriar(1, pEmpresa, pUsuarioAutenticado, pUsuario)) {
			userRepository userRepository = new userRepository();
			userRepository.alteraUsuario(pEmpresa, pUsuario);

			usuarioInclusao = userRepository.consultaUsuario(pEmpresa, pUsuarioAutenticado, pUsuario);

			JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!!");
		} else {
			usuarioInclusao = pUsuario;
			usuarioInclusao.setMsg_erro("");
		}

		return usuarioInclusao;
	}

	private void atualizaSenhaUsuarioBanco(int pEmpresa, userEntity pUsuario, String pSenhaAlterada) {

		userRepository userRepository = new userRepository();

		userRepository.alteraSenhaUsuario(pEmpresa, pUsuario, pSenhaAlterada);
	}
}
