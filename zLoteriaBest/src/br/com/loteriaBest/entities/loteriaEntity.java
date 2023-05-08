package br.com.loteriaBest.entities;

public class loteriaEntity {
	private String descricao, nome, msg_erro;
	private int codigo, numero_minimo, numero_maximo, valor_bilhete, valor_arrecadado, premio_inicial, premio_final, porcentagem_acrescimo, porcentagem_comissao, porcentagem_empresa, grupo, empresa, ativo;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMsg_erro() {
		return msg_erro;
	}
	public void setMsg_erro(String msg_erro) {
		this.msg_erro = msg_erro;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getNumero_minimo() {
		return numero_minimo;
	}
	public void setNumero_minimo(int numero_minimo) {
		this.numero_minimo = numero_minimo;
	}
	public int getNumero_maximo() {
		return numero_maximo;
	}
	public void setNumero_maximo(int numero_maximo) {
		this.numero_maximo = numero_maximo;
	}
	public int getValor_bilhete() {
		return valor_bilhete;
	}
	public void setValor_bilhete(int valor_bilhete) {
		this.valor_bilhete = valor_bilhete;
	}
	public int getValor_arrecadado() {
		return valor_arrecadado;
	}
	public void setValor_arrecadado(int valor_arrecadado) {
		this.valor_arrecadado = valor_arrecadado;
	}
	public int getPremio_inicial() {
		return premio_inicial;
	}
	public void setPremio_inicial(int premio_inicial) {
		this.premio_inicial = premio_inicial;
	}
	public int getPremio_final() {
		return premio_final;
	}
	public void setPremio_final(int premio_final) {
		this.premio_final = premio_final;
	}
	public int getPorcentagem_acrescimo() {
		return porcentagem_acrescimo;
	}
	public void setPorcentagem_acrescimo(int porcentagem_acrescimo) {
		this.porcentagem_acrescimo = porcentagem_acrescimo;
	}
	public int getPorcentagem_comissao() {
		return porcentagem_comissao;
	}
	public void setPorcentagem_comissao(int porcentagem_comissao) {
		this.porcentagem_comissao = porcentagem_comissao;
	}
	public int getPorcentagem_empresa() {
		return porcentagem_empresa;
	}
	public void setPorcentagem_empresa(int porcentagem_empresa) {
		this.porcentagem_empresa = porcentagem_empresa;
	}
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public int getEmpresa() {
		return empresa;
	}
	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}
	public int getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
}
