package br.com.fiap.thebestway.domain;

import java.util.Date;

import javax.persistence.Entity;

import br.com.fiap.thebestway.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;

	private String codigo;
	private Date dataGeracaoBoleto;
	private Date dataVencimento;
	private Date dataPagamento;

	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, String codigo, Date dataGeracaoBoleto,
			Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.codigo = codigo;
		this.dataGeracaoBoleto = dataGeracaoBoleto;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getDataGeracaoBoleto() {
		return dataGeracaoBoleto;
	}

	public void setDataGeracaoBoleto(Date dataGeracaoBoleto) {
		this.dataGeracaoBoleto = dataGeracaoBoleto;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
}
