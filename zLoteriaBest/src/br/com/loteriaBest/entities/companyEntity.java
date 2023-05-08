package br.com.loteriaBest.entities;

public class companyEntity {
	String nome, msg_erro;
	int ativo, codigo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMsg_erro() {
		return msg_erro;
	}
	public void setMsg_erro(String msg_erro) {
		this.msg_erro = msg_erro;
	}
}
