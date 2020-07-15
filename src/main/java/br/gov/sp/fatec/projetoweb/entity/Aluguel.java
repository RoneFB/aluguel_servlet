package br.gov.sp.fatec.projetoweb.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="alu_aluguel")
@AttributeOverride(name = "id", column = @Column(name = "alu_id"))
public class Aluguel extends BaseEntity{
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cli_id")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usu_id")
	private Vendedor vendedor;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "alu_recebe", 
    joinColumns = { @JoinColumn(name = "alu_id") }, 
    inverseJoinColumns = { @JoinColumn(name = "item_id") })
	private Set<Roupa> roupas;

	@Column(name="alu_data_locacao")
	private Date dataLocacao;
	
	@Column(name="alu_data_retirada")
	private Date dataRetirada;
	
	@Column(name="alu_data_devolucao")
	private Date dataDevolucao;
	
	@Column(name="alu_ajuste")
	private String ajuste;


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	

	public Set<Roupa> getRoupas() {
		return roupas;
	}

	public void setRoupas(Set<Roupa> roupas) {
		this.roupas = roupas;
	}

	public Date getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public Date getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getAjuste() {
		return ajuste;
	}

	public void setAjuste(String ajuste) {
		this.ajuste = ajuste;
	}


}
