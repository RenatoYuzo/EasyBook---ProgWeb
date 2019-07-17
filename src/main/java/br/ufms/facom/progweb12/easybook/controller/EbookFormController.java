package br.ufms.facom.progweb12.easybook.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractController;
import br.ufms.facom.progweb12.easybook.dao.EbookDao;
import br.ufms.facom.progweb12.easybook.model.Ebook;

@Named
@ViewScoped
public class EbookFormController extends AbstractController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2423416945394492072L;

	@Inject
	private EbookDao ebookDao;

	private Integer id;
	private String titulo;
	private String editora;
	private String resumo;
	private Integer ano;
	private String autor;
	private String genero;
	private Double preco;
	private InputStream isPdf;
	private Long sizePdf;
	private InputStream isImg;
	private Long sizeImg;
	private List<String> listPdf;
	private List<String> listImg;
	private String text;

	private Ebook ebook;

	@PostConstruct
	@Override
	public void init() {
		if (!verificaAutenticacaoUsuarioLogado())
			return;

		listPdf = new ArrayList<String>();
		listImg = new ArrayList<String>();
		this.ebook = new Ebook();
	}

	public void onload() {
		if (ebook != null) {
			id = ebook.getId();
			titulo = ebook.getTitulo();
			editora = ebook.getEditora();
			resumo = ebook.getResumo();
			ano = ebook.getAno();
			autor = ebook.getAutor();
			genero = ebook.getGenero();
			preco = ebook.getPreco();
		}
	}

	public void actionCadastrar() {

		if (!verificaCamposObrigatorios())
			return;

		ebook = new Ebook(titulo, editora, resumo, ano, autor, genero, preco, isPdf, sizePdf, isImg, sizeImg);
		ebookDao.persist(ebook);

		navigation(redirect("ebook-list.xhtml"));
		addInfo("Cadastrado com sucesso!");
	}

	public void actionAtualizar() {

		if (!verificaCamposObrigatorios())
			return;

		ebook = new Ebook(id, titulo, editora, resumo, ano, autor, genero, preco, isPdf, sizePdf, isImg, sizeImg);
		ebookDao.update(ebook);

		navigation(redirect("ebook-list.xhtml"));
		addInfo("Atualizado com sucesso!");
	}

	public void actionAnexarPDF(FileUploadEvent event) {
		try {
			listPdf.add(event.getFile().getFileName());
			isPdf = event.getFile().getInputstream();
			sizePdf = event.getFile().getSize();
		} catch (IOException e) {
			System.err.println("Erro EbookFormController[01]: " + e.getMessage());
		}
	}

	public void actionAnexarIMG(FileUploadEvent event) {
		try {
			listImg.add(event.getFile().getFileName());
			isImg = event.getFile().getInputstream();
			sizeImg = event.getFile().getSize();
		} catch (IOException e) {
			System.err.println("Erro EbookFormController[02]: " + e.getMessage());
		}
	}

	public void actionExcluirPDFAnexado(String file) {
		listPdf.remove(file);
		sizePdf = null;
		isPdf = null;
	}

	public void actionExcluirImgAnexado(String file) {
		listImg.remove(file);
		sizeImg = null;
		isImg = null;
	}

	public boolean verificaCamposObrigatorios() {
		if (listPdf.isEmpty()) {
			addError("Obrigatório anexar um arquivo pdf.");
			return false;
		} else if (StringUtils.isEmpty(titulo.trim())) {
			addError("Campo Título é obrigatório.");
			return false;
		} else if (StringUtils.isEmpty(editora.trim())) {
			addError("Campo Editora é obrigatório.");
			return false;
		} else if (StringUtils.isEmpty(resumo.trim())) {
			addError("Campo Resumo é obrigatório.");
			return false;
		} else if (ano == null || StringUtils.isEmpty(ano.toString().trim())) {
			addError("Campo Ano é obrigatório.");
			return false;
		} else if (StringUtils.isEmpty(autor.trim())) {
			addError("Campo Autor é obrigatório.");
			return false;
		} else if (StringUtils.isEmpty(genero.trim())) {
			addError("Campo Gênero é obrigatório.");
			return false;
		} else if (StringUtils.isEmpty(preco.toString().trim())) {
			addError("Campo Preço é obrigatório.");
			return false;
		} 
		return true;
	}

	/***********************************************************
	 ******************* GETTERS AND SETTERS *******************
	 ***********************************************************/

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<String> getListPdf() {
		return listPdf;
	}

	public void setListPdf(List<String> listPdf) {
		this.listPdf = listPdf;
	}

	public Ebook getEbook() {
		return ebook;
	}

	public void setEbook(Ebook ebook) {
		this.ebook = ebook;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<String> getListImg() {
		return listImg;
	}

	public void setListImg(List<String> listImg) {
		this.listImg = listImg;
	}

}
