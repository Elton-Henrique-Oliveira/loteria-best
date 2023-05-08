package br.com.loteriaBest.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.loteriaBest.connection.connectionBank;
import br.com.loteriaBest.entities.companyEntity;

public class companyRepository {
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;

	private String sql = "";

	public companyEntity buscaEmpresa(int pEmpresa) {

		companyEntity companyEntity = new companyEntity();

		sql = "select codigo,nome,codigo_ativo";
		sql += " from dbempresa";
		sql += " where codigo = " + pEmpresa;

		conn = new connectionBank().conectaBD();
		try {

			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			if (rs.next() == false) {

				companyEntity.setMsg_erro("Empresa Não cadastrada/inativa!");
				
			}
			rs.beforeFirst();

			while (rs.next()) {

				companyEntity.setCodigo(rs.getInt("codigo"));
				companyEntity.setNome(rs.getString("nome"));
				companyEntity.setAtivo(rs.getInt("codigo_ativo"));
				companyEntity.setMsg_erro("");

			}
			rs.close();

		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, sql + "\nbusca_empresa" + error);
			System.out.println(sql + "\nbusca_empresa" + error);
		}

		return companyEntity;
	}
}
