package br.ufms.facom.progweb12.easybook.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractController;
import br.ufms.facom.progweb12.easybook.dao.VendaDao;
import br.ufms.facom.progweb12.easybook.model.Ebook;
import br.ufms.facom.progweb12.easybook.model.Venda;

@Named
@SessionScoped
public class TelaPagamentoController extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4591196869124055646L;

	@Inject
	private VendaDao vendaDao;

	private String formaPagamento;
	private String numeroCartaoCredito;
	private Integer mesCartaoCredito;
	private Integer anoCartaoCredito;
	private Integer codigoSegurancaCartaoCredito;
	private String nomeCartaoCredito;
	private String cpfCartaoCredito;
	private String numeroCartaoDebito;
	private Integer mesCartaoDebito;
	private Integer anoCartaoDebito;
	private Integer codigoSegurancaCartaoDebito;
	private String nomeCartaoDebito;
	private String cpfCartaoDebito;

	public static List<Ebook> listaCarrinho;

	@PostConstruct
	@Override
	public void init() {
		formaPagamento = "credito";

		TelaPagamentoController.listaCarrinho = new ArrayList<Ebook>();
	}

	public void adicionaEbookNoCarrinho(Ebook e) {
		if (TelaPagamentoController.listaCarrinho == null)
			TelaPagamentoController.listaCarrinho = new ArrayList<Ebook>();
		TelaPagamentoController.listaCarrinho.add(e);
		addInfo("Ebook adicionado no carrinho.");
	}

	public void removeEbookDoCarrinho(Ebook e) {
		if (TelaPagamentoController.listaCarrinho != null && !TelaPagamentoController.listaCarrinho.isEmpty())
			TelaPagamentoController.listaCarrinho.remove(e);
	}

	public String calculaValorTotal() {
		if (listaCarrinho == null || listaCarrinho.isEmpty())
			return "0";
		else {
			Double total = 0d;
			for (Ebook ebook : listaCarrinho) {
				total += ebook.getPreco();
			}
			return total.toString();
		}
	}

	public void actionComprar() {
		if (TelaPagamentoController.listaCarrinho == null)
			TelaPagamentoController.listaCarrinho = new ArrayList<Ebook>();
		for (Ebook ebook : TelaPagamentoController.listaCarrinho) {
			Venda v = new Venda(SessionContext.usuarioLogado.getId(), ebook.getId());
			vendaDao.persist(v);
		}
		TelaPagamentoController.listaCarrinho = new ArrayList<Ebook>();
		navigation(redirect("meus-ebooks-list.xhtml"));
		addInfo("Compra realizada com sucesso!");
	}

	/***********************************************************
	 ******************* GETTERS AND SETTERS *******************
	 ***********************************************************/
	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getNumeroCartaoCredito() {
		return numeroCartaoCredito;
	}

	public void setNumeroCartaoCredito(String numeroCartaoCredito) {
		this.numeroCartaoCredito = numeroCartaoCredito;
	}

	public Integer getMesCartaoCredito() {
		return mesCartaoCredito;
	}

	public void setMesCartaoCredito(Integer mesCartaoCredito) {
		this.mesCartaoCredito = mesCartaoCredito;
	}

	public Integer getAnoCartaoCredito() {
		return anoCartaoCredito;
	}

	public void setAnoCartaoCredito(Integer anoCartaoCredito) {
		this.anoCartaoCredito = anoCartaoCredito;
	}

	public Integer getCodigoSegurancaCartaoCredito() {
		return codigoSegurancaCartaoCredito;
	}

	public void setCodigoSegurancaCartaoCredito(Integer codigoSegurancaCartaoCredito) {
		this.codigoSegurancaCartaoCredito = codigoSegurancaCartaoCredito;
	}

	public String getNomeCartaoCredito() {
		return nomeCartaoCredito;
	}

	public void setNomeCartaoCredito(String nomeCartaoCredito) {
		this.nomeCartaoCredito = nomeCartaoCredito;
	}

	public String getCpfCartaoCredito() {
		return cpfCartaoCredito;
	}

	public void setCpfCartaoCredito(String cpfCartaoCredito) {
		this.cpfCartaoCredito = cpfCartaoCredito;
	}

	public Integer getMesCartaoDebito() {
		return mesCartaoDebito;
	}

	public void setMesCartaoDebito(Integer mesCartaoDebito) {
		this.mesCartaoDebito = mesCartaoDebito;
	}

	public Integer getAnoCartaoDebito() {
		return anoCartaoDebito;
	}

	public void setAnoCartaoDebito(Integer anoCartaoDebito) {
		this.anoCartaoDebito = anoCartaoDebito;
	}

	public Integer getCodigoSegurancaCartaoDebito() {
		return codigoSegurancaCartaoDebito;
	}

	public void setCodigoSegurancaCartaoDebito(Integer codigoSegurancaCartaoDebito) {
		this.codigoSegurancaCartaoDebito = codigoSegurancaCartaoDebito;
	}

	public String getNomeCartaoDebito() {
		return nomeCartaoDebito;
	}

	public void setNomeCartaoDebito(String nomeCartaoDebito) {
		this.nomeCartaoDebito = nomeCartaoDebito;
	}

	public String getCpfCartaoDebito() {
		return cpfCartaoDebito;
	}

	public void setCpfCartaoDebito(String cpfCartaoDebito) {
		this.cpfCartaoDebito = cpfCartaoDebito;
	}

	public String getNumeroCartaoDebito() {
		return numeroCartaoDebito;
	}

	public void setNumeroCartaoDebito(String numeroCartaoDebito) {
		this.numeroCartaoDebito = numeroCartaoDebito;
	}

	public List<Ebook> getListaCarrinho() {
		return listaCarrinho;
	}

	public void setListaCarrinho(List<Ebook> listaCarrinho) {
		TelaPagamentoController.listaCarrinho = listaCarrinho;
	}

}
