package br.gov.sp.fatec.projetoweb.entity;

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
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "alu_recebe", 
    joinColumns = { @JoinColumn(name = "alu_id") }, 
    inverseJoinColumns = { @JoinColumn(name = "item_id") })
	private Set<Calcado> calcados;

	@Column(name="alu_data_locacao")
	private LocalDate dataLocacao;
	
	@Column(name="alu_data_retirada")
	private LocalDate dataRetirada;
	
	@Column(name="alu_data_devolucao")
	private LocalDate dataDevolucao;
	
	@Column(name="alu_ajuste")
	private String ajuste;

	public Aluguel() {
		super();
	}

	public Aluguel(Cliente cliente, Vendedor vendedor, LocalDate dataLocacao, LocalDate dataRetirada,
			LocalDate dataDevolucao, String ajuste) {
		super();
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.dataLocacao = dataLocacao;
		this.dataRetirada = dataRetirada;
		this.dataDevolucao = dataDevolucao;
		this.ajuste = ajuste;
	}

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

	public Set<Calcado> getCalcados() {
		return calcados;
	}

	public void setCalcados(Set<Calcado> calcados) {
		this.calcados = calcados;
	}

	public LocalDate getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(LocalDate dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getAjuste() {
		return ajuste;
	}

	public void setAjuste(String ajuste) {
		this.ajuste = ajuste;
	}


}
