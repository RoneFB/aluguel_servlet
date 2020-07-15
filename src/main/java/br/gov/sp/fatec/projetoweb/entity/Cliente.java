package br.gov.sp.fatec.projetoweb.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cli_cliente")
@PrimaryKeyJoinColumn(name="pess_id")
@AttributeOverride(name = "id", column = @Column(name = "cli_id"))
public class Cliente extends Pessoa{
	

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy="cliente")
	private Set<Aluguel> aluguel;
	
	@Column(name="cli_idade")
	private Integer idade;
	
	@Column(name="cli_bairro")
	private String bairro;
	
	@Column(name="cli_rua")
	private String rua;
	
	@Column(name="cli_numero")
	private Integer numero;
	
	@Column(name="cli_cidade")
	private String cidade;
	
	@Column(name="cli_telefone")
	private String telefone;



	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public Set<Aluguel> getAluguel() {
		return aluguel;
	}

	public void setAluguel(Set<Aluguel> aluguel) {
		this.aluguel = aluguel;
	}


	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	
}
