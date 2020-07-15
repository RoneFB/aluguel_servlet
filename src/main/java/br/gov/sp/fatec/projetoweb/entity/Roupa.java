package br.gov.sp.fatec.projetoweb.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="alu_roupa")
@AttributeOverride(name = "id", column = @Column(name = "rou_id"))
public class Roupa extends BaseEntity{
	
	@Column(name="item_cor")
	String cor;
	@Column(name="item_sexo")
	String sexo;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roupas")
	private Set<Aluguel> aluguel_roupas;
	
	
	@Column(name="item_preco")
	private double preco;
	
	
	@Column(name="rou_tipo")
	private String tipo;
	
	@Column(name="rou_tamanho")
	private String tamanho;
	


	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Set<Aluguel> getAluguel_roupas() {
		return aluguel_roupas;
	}

	public void setAluguel_roupas(Set<Aluguel> aluguel_roupas) {
		this.aluguel_roupas = aluguel_roupas;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	
	
	
}
