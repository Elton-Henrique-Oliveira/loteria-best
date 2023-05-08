package br.com.loteriaBest.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.loteriaBest.connection.connectionBank;
import br.com.loteriaBest.entities.loteriaEntity;
import br.com.loteriaBest.entities.userEntity;

public class loteriaRepository {
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;

	private String sql = "";
	
	public loteriaEntity consultaLoteria(int pEmpresa, userEntity pUsuarioAutenticacao, loteriaEntity pLoteria) {

		loteriaEntity objetoLoteria = new loteriaEntity();
		objetoLoteria.setMsg_erro("");

		sql = "select *";
		sql += " from dbloteria";
		sql += "  where codigo_empresa = " + pEmpresa;
		if(!pLoteria.getNome().trim().equals("")) {
			sql += "    and nome like '%" + pLoteria.getNome() + "%'";	
		}
		if(pLoteria.getCodigo() != 0) {
			sql += "    and codigo = " + pLoteria.getCodigo();
		}
		if(pLoteria.getAtivo() != 0) {
			sql += "    and codigo_ativo = " + pLoteria.getAtivo();
		}
		conn = new connectionBank().conectaBD();
		try {

			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next() == false) {

				objetoLoteria.setMsg_erro("Nenhuma loteria encontrada!!");

			}
			rs.beforeFirst();

			if (rs.next()) {
				
				objetoLoteria.setEmpresa(rs.getInt("codigo_empresa"));
				objetoLoteria.setCodigo(rs.getInt("codigo"));
				objetoLoteria.setDescricao(rs.getString("descricao"));
				objetoLoteria.setGrupo(rs.getInt("codigo_grupo"));
				objetoLoteria.setNome(rs.getString("nome"));
				objetoLoteria.setNumero_maximo(rs.getInt("numero_maximo"));
				objetoLoteria.setNumero_minimo(rs.getInt("numero_minimo"));
				objetoLoteria.setPorcentagem_acrescimo(rs.getInt("porcentagem_acrescimo"));
				objetoLoteria.setPorcentagem_comissao(rs.getInt("porcentagem_recompensa"));
				objetoLoteria.setPorcentagem_empresa(rs.getInt("porcentagem_empresa"));
				objetoLoteria.setPremio_inicial(rs.getInt("premio_inicial"));
				objetoLoteria.setPremio_final(rs.getInt("premio_final"));
				objetoLoteria.setValor_arrecadado(rs.getInt("valor_arrecadado"));
				objetoLoteria.setValor_bilhete(rs.getInt("valor_bilhete"));
				objetoLoteria.setAtivo(rs.getInt("codigo_ativo"));
				objetoLoteria.setMsg_erro("");

			}
			rs.close();

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, sql + "\nbusca_loterias" + error);
			System.out.println(sql + "\nbusca_loterias" + error);
		}

		return objetoLoteria;
	}
	
	public void cadastroLoteriaNova(int pEmpresa, loteriaEntity pLoteria) throws SQLException {

		sql  = "insert into dbloteria(nome, descricao, numero_maximo, numero_minimo, valor_bilhete, valor_arrecadado, premio_inicial, premio_final,";
		sql += "                      porcentagem_acrescimo, porcentagem_recompensa, porcentagem_empresa, codigo_grupo, codigo_empresa, codigo_ativo)";
		sql += " values (";
		sql += " '" + pLoteria.getNome() + "',";
		sql += " '" + pLoteria.getDescricao() + "',";
		sql += "  " + pLoteria.getNumero_maximo() + ",";
		sql += "  " + pLoteria.getNumero_minimo() + ",";
		sql += "  " + pLoteria.getValor_bilhete() + ");";
		sql += "  " + pLoteria.getValor_arrecadado() + ",";
		sql += "  " + pLoteria.getPremio_inicial() + ",";
		sql += "  " + pLoteria.getPremio_final() + ",";
		sql += "  " + pLoteria.getPorcentagem_acrescimo() + ",";
		sql += "  " + pLoteria.getPorcentagem_comissao() + ",";
		sql += "  " + pLoteria.getPorcentagem_empresa() + ",";
		sql += "  " + pLoteria.getGrupo() + ",";
		sql += "  1,";
		sql += "  " + pLoteria.getAtivo();
		
		conn = new connectionBank().conectaBD();
		try {

			pstm = conn.prepareStatement(sql);
			pstm.execute();
			pstm.close();

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, sql + " cadastro loteria" + error);
		}

		conn.close();

	}
	
	public void alteraLoteria(int pEmpresa, loteriaEntity pLoteria) {

		sql = "update dbloteria";
		sql += " set ";
		sql += "	nome = ?,descricao = ?,numero_maximo = ?,numero_minimo = ?,valor_bilhete = ?,valor_arrecadado = ?";
		sql += "    ,premio_inicial = ?,premio_final = ?,porcentagem_acrescimo = ?,porcentagem_recompensa = ?,porcentagem_empresa = ?,codigo_grupo = ?,codigo_ativo = ?";
		sql += "  where ";
		sql += " 	 codigo_empresa = ?";
		sql += " and codigo = ?;";

		conn = new connectionBank().conectaBD();
		try {
			
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, pLoteria.getNome());
			pstm.setString(2, pLoteria.getDescricao());
			pstm.setInt(3, pLoteria.getNumero_maximo());
			pstm.setInt(4, pLoteria.getNumero_minimo());
			pstm.setInt(5, pLoteria.getValor_bilhete());
			pstm.setInt(6, pLoteria.getValor_arrecadado());
			pstm.setInt(7, pLoteria.getPremio_inicial());
			pstm.setInt(8, pLoteria.getPremio_final());
			pstm.setInt(9, pLoteria.getPorcentagem_acrescimo());
			pstm.setInt(10, pLoteria.getPorcentagem_comissao());
			pstm.setInt(11, pLoteria.getPorcentagem_empresa());
			pstm.setInt(12, pLoteria.getGrupo());
			pstm.setInt(13, pLoteria.getAtivo());
			pstm.setInt(14, pEmpresa);
			pstm.setInt(15, pLoteria.getCodigo());
			pstm.execute();
			pstm.close();

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, "ALTERAÇÃO DADOS LOTERIA " + error);
		}
	}
}
