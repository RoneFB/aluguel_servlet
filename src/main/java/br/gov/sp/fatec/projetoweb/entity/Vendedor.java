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
@Table(name = "usu_usuario")
@PrimaryKeyJoinColumn(name="pess_id")
@AttributeOverride(name = "id", column = @Column(name = "usu_id"))
public class Vendedor extends Pessoa{

	
	@Column(name="usu_senha")
	private String senha;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy="vendedor")
	private Set<Aluguel> aluguel;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Set<Aluguel> getAluguel() {
		return aluguel;
	}

	public void setAluguel(Set<Aluguel> aluguel) {
		this.aluguel = aluguel;
	}
	
	
}
