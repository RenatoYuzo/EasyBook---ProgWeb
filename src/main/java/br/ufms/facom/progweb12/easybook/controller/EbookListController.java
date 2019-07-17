package br.ufms.facom.progweb12.easybook.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractController;
import br.ufms.facom.progweb12.easybook.dao.EbookDao;
import br.ufms.facom.progweb12.easybook.model.Ebook;
import br.ufms.facom.progweb12.easybook.utils.Utils;

@Named
@ViewScoped
public class EbookListController extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4222915021596910928L;
	public static final String VIEW_FORM = "ebook-form.xhtml";

	@Inject
	private EbookDao ebookService;

	private List<Ebook> listaEbooks;

	@PostConstruct
	@Override
	public void init() {
		if (!verificaAutenticacaoUsuarioLogado())
			return;

		listaEbooks = ebookService.achaTodosEbooks();
	}

	public void rowDblselect(SelectEvent event) {
		Ebook elemento = (Ebook) event.getObject();
		navigation(redirect(VIEW_FORM + "?id=" + elemento.getId()));
	}

	public List<Ebook> getListaEbooks() {
		return listaEbooks;
	}

	public void setListaEbooks(List<Ebook> listaEbooks) {
		this.listaEbooks = listaEbooks;
	}

	public StreamedContent byteToImg(byte[] imgByte) {
		return new DefaultStreamedContent(new ByteArrayInputStream(imgByte));
	}

	public void actionAbrirArquivo(byte[] pdfByte) {
		Utils.byteToFileTemp(pdfByte, "pdf");
	}
	
	public void actionDeletar(Ebook ebook) {
		ebookService.delete(ebook);		
		navigation(redirect("ebook-list.xml"));
		addInfo("Deletado com sucesso!");
	}

}
