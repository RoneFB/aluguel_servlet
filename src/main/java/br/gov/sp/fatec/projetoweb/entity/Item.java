package br.gov.sp.fatec.projetoweb.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import org.hibernate.type.DiscriminatorType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = javax.persistence.DiscriminatorType.STRING)
@DiscriminatorValue("I")
@AttributeOverride(name = "id", column = @Column(name = "item_id"))
public abstract class Item extends BaseEntity{
	
	@Column(insertable=false, updatable=false, name="item_tipo")
    private String tipo;
	
	@Column(name="item_cor")
	String cor;
	@Column(name="item_sexo")
	String sexo;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roupas")
	private Set<Aluguel> aluguel_roupas;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "calcados")
	private Set<Aluguel> aluguel_calcados;
	
	@Column(name="item_preco")
	private double preco;


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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Set<Aluguel> getAluguel_roupas() {
		return aluguel_roupas;
	}

	public void setAluguel_roupas(Set<Aluguel> aluguel_roupas) {
		this.aluguel_roupas = aluguel_roupas;
	}

	public Set<Aluguel> getAluguel_calcados() {
		return aluguel_calcados;
	}

	public void setAluguel_calcados(Set<Aluguel> aluguel_calcados) {
		this.aluguel_calcados = aluguel_calcados;
	}

	
	
	
	
}
