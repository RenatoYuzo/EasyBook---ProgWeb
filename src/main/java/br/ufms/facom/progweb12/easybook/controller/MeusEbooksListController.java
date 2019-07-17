package br.ufms.facom.progweb12.easybook.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractController;
import br.ufms.facom.progweb12.easybook.dao.EbookDao;
import br.ufms.facom.progweb12.easybook.model.Ebook;
import br.ufms.facom.progweb12.easybook.utils.Utils;

@Named
@ViewScoped
public class MeusEbooksListController extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4288283456158141808L;

	@Inject
	private EbookDao ebookService;

	private List<Ebook> listaEbooks;

	@PostConstruct
	@Override
	public void init() {
		if (!verificaAutenticacaoUsuarioSimplesLogado())
			return;

		listaEbooks = ebookService.achaMeusEbooks();
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

}
