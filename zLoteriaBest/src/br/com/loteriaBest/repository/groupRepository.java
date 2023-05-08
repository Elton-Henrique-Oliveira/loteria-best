package br.com.loteriaBest.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.loteriaBest.connection.connectionBank;
import br.com.loteriaBest.entities.groupEntity;

public class groupRepository {

	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;

	private String sql = "";
	private ArrayList<groupEntity> grupos = new ArrayList<>();

	public ArrayList<groupEntity> buscaGrupos(int pEmpresa) {

		sql = "select codigo,nome,codigo_empresa";
		sql += " from dbgrupo";
		sql += " where codigo_empresa = " + pEmpresa;
		conn = new connectionBank().conectaBD();
		try {

			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next() == false) {
				groupEntity groupEntity = new groupEntity();
				groupEntity.setMsg_erro("Nenhum grupo cadastrado!");

			}
			rs.beforeFirst();

			while (rs.next()) {

				groupEntity groupEntity = new groupEntity();
				groupEntity.setCodigo(rs.getInt("codigo"));
				groupEntity.setNome(rs.getString("nome"));
				groupEntity.setCodigo_empresa(rs.getInt("codigo_empresa"));
				groupEntity.setMsg_erro("");
				grupos.add(groupEntity);

			}
			rs.close();
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, sql + "\nbusca grupos" + error);
			System.out.println(sql + "\nbusca grupos" + error);
		}

		return grupos;
	}
}
