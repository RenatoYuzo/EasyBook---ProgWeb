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

@Named
@ViewScoped
public class indexController extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8499622813378681496L;

	private Ebook selectedBook;
	
	@Inject
	private EbookDao ebookService;
	private List<Ebook> listaEbooks;	
	
	@PostConstruct
	@Override
	public void init() {
		
		selectedBook = new Ebook();		
		listaEbooks = ebookService.achaTodosEbooks();
		
		
	}
	
	public void salvaEbook(Ebook e) {
		selectedBook = e;
	}
	
	public List<Ebook> getListaEbooks() {
		return listaEbooks;
	}	

	public void setListaEbooks(List<Ebook> listaEbooks) {
		this.listaEbooks = listaEbooks;
	}
	
	public void redirect() {
		navigation(redirect("index.xhtml"));
	}

	public Ebook getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Ebook selectedBook) {
		this.selectedBook = selectedBook;
	}
	
	public StreamedContent byteToImg(byte[] imgByte) {
		return new DefaultStreamedContent(new ByteArrayInputStream(imgByte));
	}

}
