package br.com.loteriaBest.entities;

public class groupEntity {
	private String nome, msg_erro;
	private int codigo,codigo_empresa;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public int getCodigo_empresa() {
		return codigo_empresa;
	}
	public void setCodigo_empresa(int codigo_empresa) {
		this.codigo_empresa = codigo_empresa;
	}	
}
