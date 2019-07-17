package br.ufms.facom.progweb12.easybook.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.export.ExcelOptions;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractController;
import br.ufms.facom.progweb12.easybook.dao.EbookDao;
import br.ufms.facom.progweb12.easybook.dao.VendaDao;
import br.ufms.facom.progweb12.easybook.model.Ebook;
import br.ufms.facom.progweb12.easybook.model.Venda;

@Named
@ViewScoped
public class EstatisticaListController extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2776541506146661836L;

	/**
	 * 
	 */

	@Inject
	private EbookDao ebookDao;
	@Inject
	private VendaDao vendaDao;

	private List<Ebook> listaEstatistica;
	private List<Venda> listaVenda;


	private ExcelOptions excelOpt;
	private Integer tiposRelatorio;

	@PostConstruct
	@Override
	public void init() {
		// TODO Auto-generated method stub
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

	public void rcTiposRelatorioOnChange() {
		if (tiposRelatorio == 0) {
			listaEstatistica = new ArrayList<Ebook>();
		} else if (tiposRelatorio == 1) {
			listaEstatistica = ebookDao.findMaisVendidos();
		} else if (tiposRelatorio == 2) {
			System.out.println("Dois");
		} else if (tiposRelatorio == 3) {
			System.out.println("Tres");
		} else if (tiposRelatorio == 4) {
			listaVenda = vendaDao.findDezUltimasVendas();
		}
	}

	public ExcelOptions getExcelOpt() {
		return excelOpt;
	}

	public void setExcelOpt(ExcelOptions excelOpt) {
		this.excelOpt = excelOpt;
	}

	public List<Ebook> getListaEstatistica() {
		return listaEstatistica;
	}

	public void setListaEstatistica(List<Ebook> listaEstatistica) {
		this.listaEstatistica = listaEstatistica;
	}

	public Integer getTiposRelatorio() {
		return tiposRelatorio;
	}

	public void setTiposRelatorio(Integer tiposRelatorio) {
		this.tiposRelatorio = tiposRelatorio;
	}

	public List<Venda> getListaVenda() {
		return listaVenda;
	}

	public void setListaVenda(List<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}

}
