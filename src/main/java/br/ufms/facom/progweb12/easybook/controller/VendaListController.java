package br.ufms.facom.progweb12.easybook.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.export.ExcelOptions;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractController;
import br.ufms.facom.progweb12.easybook.dao.VendaDao;
import br.ufms.facom.progweb12.easybook.model.Venda;

@Named
@ViewScoped
public class VendaListController extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2111560125530645614L;

	@Inject
	private VendaDao vendaDao;

	private ExcelOptions excelOpt;

	private List<Venda> listaVenda;

	@PostConstruct
	@Override
	public void init() {
		if (!verificaAutenticacaoUsuarioLogado())
			return;
		
		listaVenda = vendaDao.findAll();
		customizationOptions();
	}

	private void customizationOptions() {
		excelOpt = new ExcelOptions();
		excelOpt.setFacetBgColor("#c7c7c7");
		excelOpt.setFacetFontSize("10");
		excelOpt.setFacetFontColor("#000000");
		excelOpt.setFacetFontStyle("BOLD");
		excelOpt.setCellFontColor("#000000");
		excelOpt.setCellFontSize("8");
	}

	public List<Venda> getListaVenda() {
		return listaVenda;
	}

	public void setListaVenda(List<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}

	public ExcelOptions getExcelOpt() {
		return excelOpt;
	}

	public void setExcelOpt(ExcelOptions excelOpt) {
		this.excelOpt = excelOpt;
	}

}
