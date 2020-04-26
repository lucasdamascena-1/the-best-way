package br.com.fiap.thebestway.domain;

import br.com.fiap.thebestway.domain.enums.EstadoPagamento;
import br.com.fiap.thebestway.domain.enums.TipoCartao;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	private String numeroCartao;
	private Integer numeroDeParcelas;
	private Date dataVencimento;
	private Integer tipoCartao;

	public PagamentoComCartao() {
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, String numeroCartao,
			Integer numeroDeParcelas, Date dataVencimento, TipoCartao tipoCartao) {
		super(id, estado, pedido);
		this.numeroCartao = numeroCartao;
		this.numeroDeParcelas = numeroDeParcelas;
		this.dataVencimento = dataVencimento;
		this.tipoCartao = (tipoCartao == null) ? null : tipoCartao.getCodigo();
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public TipoCartao getTipoCartao() {
		return TipoCartao.toEnum(tipoCartao);
	}

	public void setTipoCartao(TipoCartao tipoCartao) {
		this.tipoCartao = tipoCartao.getCodigo();
	}
}