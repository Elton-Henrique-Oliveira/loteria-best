package br.com.loteriaBest.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.loteriaBest.connection.connectionBank;
import br.com.loteriaBest.entities.userEntity;

public class userRepository {

	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;

	private String sql = "";

	public ArrayList<userEntity> consultaUsuarioLogin(int pIndicador, int pEmpresa, String pUsuario, String pSenha) {

		// pIndicador == 1 :> consulta por usuario/senha
		// pIndicador != 1 :> consulta todos usuarios cadastrados
		// retorna uma lista com os usuarios encontrados no banco

		ArrayList<userEntity> lista_retorno = new ArrayList<>();

		sql = "select *";
		sql += " from dbusuario";
		if (pIndicador == 1) {
			sql += " where codigo_empresa = " + pEmpresa + "   and login = '" + pUsuario + "'" + "   and senha = '"
					+ pSenha + "';";
		}

		conn = new connectionBank().conectaBD();
		try {

			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next() == false) {

				userEntity objetoUsuario = new userEntity();
				if (pIndicador == 1) {
					objetoUsuario.setMsg_erro("Usuario/senha incorretas!!");
				}
				if (pIndicador != 1) {
					objetoUsuario.setMsg_erro("Nenhum usuário encontrado!!");
				}

				lista_retorno.add(objetoUsuario);
			}
			rs.beforeFirst();

			while (rs.next()) {
				userEntity objetoUsuario = new userEntity();
				if (rs.getInt("codigo_ativo") != 1) {
					objetoUsuario.setMsg_erro("Usuário Inativo!");
				} else {
					objetoUsuario.setCodigo_usuario(rs.getInt("codigo"));
					objetoUsuario.setData_nascimento(manipulaData(rs.getInt("data_nascimento")));
					objetoUsuario.setEmail(rs.getString("email"));
					objetoUsuario.setEmpresa(rs.getInt("codigo_empresa"));
					objetoUsuario.setGrupo(rs.getInt("codigo_grupo"));
					objetoUsuario.setNome_usuario(rs.getString("nome"));
					objetoUsuario.setTelefone(rs.getString("telefone"));
					objetoUsuario.setUsuario_login(rs.getString("login"));
					objetoUsuario.setUsuario_senha(rs.getString("senha"));
					objetoUsuario.setNivel(rs.getInt("tipo_nivel"));
					objetoUsuario.setStatus(rs.getInt("codigo_ativo"));
					objetoUsuario.setMsg_erro("");
				}
				lista_retorno.add(objetoUsuario);
			}
			rs.close();

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, sql + "\nbusca_usuarios" + error);
			System.out.println(sql + "\nbusca_usuarios" + error);
		}

		return lista_retorno;
	}

	public userEntity consultaUsuario(int pEmpresa, userEntity pUsuarioAutenticacao, userEntity pUsuario) {

		userEntity objetoUsuario = new userEntity();
		objetoUsuario.setMsg_erro("");

		sql = "select *";
		sql += " from dbusuario";
		sql += "  where codigo_empresa = " + pEmpresa;
		sql += "    and login like '%" + pUsuario.getUsuario_login() + "%'";

		conn = new connectionBank().conectaBD();
		try {

			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next() == false) {

				objetoUsuario.setMsg_erro("Nenhum usuário encontrado!!");

			}
			rs.beforeFirst();

			if (rs.next()) {

				objetoUsuario.setCodigo_usuario(rs.getInt("codigo"));
				objetoUsuario.setData_nascimento(manipulaData(rs.getInt("data_nascimento")));
				objetoUsuario.setEmail(rs.getString("email"));
				objetoUsuario.setEmpresa(rs.getInt("codigo_empresa"));
				objetoUsuario.setGrupo(rs.getInt("codigo_grupo"));
				objetoUsuario.setNome_usuario(rs.getString("nome"));
				objetoUsuario.setTelefone(rs.getString("telefone"));
				objetoUsuario.setUsuario_login(rs.getString("login"));
				objetoUsuario.setUsuario_senha(rs.getString("senha"));
				objetoUsuario.setNivel(rs.getInt("tipo_nivel"));
				objetoUsuario.setStatus(rs.getInt("codigo_ativo"));
				objetoUsuario.setMsg_erro("");

			}
			rs.close();

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, sql + "\nbusca_usuarios" + error);
			System.out.println(sql + "\nbusca_usuarios" + error);
		}

		return objetoUsuario;
	}

	public void cadastroUsuarioNovo(int pEmpresa, userEntity pUsuario) throws SQLException {

		sql = "insert into dbusuario(nome,login,senha,telefone,email,codigo_grupo,tipo_nivel,codigo_empresa,codigo_ativo)";
		sql += " values (";
		sql += " '" + pUsuario.getNome_usuario() + "',";
		sql += " '" + pUsuario.getUsuario_login() + "',";
		sql += " '" + pUsuario.getUsuario_senha() + "',";
		sql += " '" + pUsuario.getTelefone() + "',";
		sql += " '" + pUsuario.getEmail() + "',";
		sql += "  " + pUsuario.getGrupo() + ",";
		sql += "  " + pUsuario.getNivel() + ",";
		sql += "  1,";
		sql += "  " + pUsuario.getStatus() + ");";

		conn = new connectionBank().conectaBD();
		try {

			pstm = conn.prepareStatement(sql);
			pstm.execute();
			pstm.close();

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, sql + " cadastro usuario" + error);
		}

		conn.close();

	}

	public void alteraUsuario(int pEmpresa, userEntity pUsuario) {

		sql = "update dbusuario";
		sql += " set ";
		sql += "	senha = ?, nome = ?, telefone = ?, email = ?, tipo_nivel = ?, codigo_ativo = ?, codigo_grupo = ?";
		sql += "  where ";
		sql += " 	 codigo_empresa = ?";
		sql += " and login like ?;";

		conn = new connectionBank().conectaBD();
		try {

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, pUsuario.getUsuario_senha());
			pstm.setString(2, pUsuario.getNome_usuario());
			pstm.setString(3, pUsuario.getTelefone());
			pstm.setString(4, pUsuario.getEmail());
			pstm.setInt(5, pUsuario.getNivel());
			pstm.setInt(6, pUsuario.getStatus());
			pstm.setInt(7, pUsuario.getGrupo());
			pstm.setInt(8, pEmpresa);
			pstm.setString(9, pUsuario.getUsuario_login());
			pstm.execute();
			pstm.close();

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "ALTERAÇÃO DADOS USUARIO " + error);
		}
	}

	public void removerUsuario(int pEmpresa, userEntity pUsuario) {

		sql = "delete";
		sql += "	from";
		sql += "		dbusuario";
		sql += "	where";
		sql += "		codigo_empresa = ? and login like ?;";

		conn = new connectionBank().conectaBD();
		try {

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, pEmpresa);
			pstm.setString(1, pUsuario.getUsuario_login());
			pstm.execute();
			pstm.close();

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "Erro ao remover um usuario: " + error);
		}

	}

	public void alteraSenhaUsuario(int pEmpresa, userEntity pUsuario, String pSenhaAlterada) {

		sql = "update dbusuario";
		sql += " set ";
		sql += "	senha = ?";
		sql += "  where ";
		sql += " 	 codigo_empresa = ?";
		sql += " and codigo = ?;";

		conn = new connectionBank().conectaBD();
		try {

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, pSenhaAlterada);
			pstm.setInt(2, pEmpresa);
			pstm.setInt(3, pUsuario.getCodigo_usuario());
			pstm.execute();
			pstm.close();

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "ALTERAÇÃO SENHA USUARIO" + error);
		}
	}

	private String manipulaData(int pData) {
		String data_retorno = "";
		String number = String.valueOf(pData);
		int contador = 0;

		char[] digits1 = number.toCharArray();

		for (int i = 0; i < digits1.length; i++) {
			contador++;
			data_retorno += digits1[i];
			if (contador == 2 || contador == 4) {
				data_retorno += "/";
			}
		}

		return data_retorno;
	}
}
