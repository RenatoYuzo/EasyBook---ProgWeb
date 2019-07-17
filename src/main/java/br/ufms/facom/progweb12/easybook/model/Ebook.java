package br.ufms.facom.progweb12.easybook.model;

import java.io.InputStream;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractEntity;

@Entity
public class Ebook extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2810728485140982198L;

	public Ebook() {

	}

	public Ebook(String titulo, String editora, String resumo, Integer ano, String autor, String genero, Double preco,
			InputStream isPdf, Long sizePdf, InputStream isImg, Long sizeImg) {
		this.titulo = titulo;
		this.editora = editora;
		this.resumo = resumo;
		this.ano = ano;
		this.autor = autor;
		this.genero = genero;
		this.preco = preco;
		this.isPdf = isPdf;
		this.sizePdf = sizePdf;
		this.isImg = isImg;
		this.sizeImg = sizeImg;
	}

	public Ebook(Integer id, String titulo, String editora, String resumo, Integer ano, String autor, String genero,
			Double preco, InputStream isPdf, Long sizePdf, InputStream isImg, Long sizeImg) {
		this.id = id;
		this.titulo = titulo;
		this.editora = editora;
		this.resumo = resumo;
		this.ano = ano;
		this.autor = autor;
		this.genero = genero;
		this.preco = preco;
		this.isPdf = isPdf;
		this.sizePdf = sizePdf;
		this.isImg = isImg;
		this.sizeImg = sizeImg;
	}

	@Id
	private Integer id;
	private String titulo;
	private String editora;
	private String resumo;
	private Integer ano;
	private String autor;
	private String genero;
	private Double preco;
	private byte[] pdfByte;
	private byte[] imgByte;

	@Transient
	private InputStream isPdf;
	@Transient
	private Long sizePdf;
	@Transient
	private InputStream isImg;
	@Transient
	private Long sizeImg;

	public byte[] getPdfByte() {
		return pdfByte;
	}

	public void setPdfByte(byte[] pdfByte) {
		this.pdfByte = pdfByte;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public InputStream getIsPdf() {
		return isPdf;
	}

	public void setIsPdf(InputStream isPdf) {
		this.isPdf = isPdf;
	}

	public Long getSizePdf() {
		return sizePdf;
	}

	public void setSizePdf(Long sizePdf) {
		this.sizePdf = sizePdf;
	}

	public byte[] getImgByte() {
		return imgByte;
	}

	public void setImgByte(byte[] imgByte) {
		this.imgByte = imgByte;
	}

	public InputStream getIsImg() {
		return isImg;
	}

	public void setIsImg(InputStream isImg) {
		this.isImg = isImg;
	}

	public Long getSizeImg() {
		return sizeImg;
	}

	public void setSizeImg(Long sizeImg) {
		this.sizeImg = sizeImg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((editora == null) ? 0 : editora.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + Arrays.hashCode(imgByte);
		result = prime * result + ((isImg == null) ? 0 : isImg.hashCode());
		result = prime * result + ((isPdf == null) ? 0 : isPdf.hashCode());
		result = prime * result + Arrays.hashCode(pdfByte);
		result = prime * result + ((resumo == null) ? 0 : resumo.hashCode());
		result = prime * result + ((sizeImg == null) ? 0 : sizeImg.hashCode());
		result = prime * result + ((sizePdf == null) ? 0 : sizePdf.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ebook other = (Ebook) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (editora == null) {
			if (other.editora != null)
				return false;
		} else if (!editora.equals(other.editora))
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (!Arrays.equals(imgByte, other.imgByte))
			return false;
		if (isImg == null) {
			if (other.isImg != null)
				return false;
		} else if (!isImg.equals(other.isImg))
			return false;
		if (isPdf == null) {
			if (other.isPdf != null)
				return false;
		} else if (!isPdf.equals(other.isPdf))
			return false;
		if (!Arrays.equals(pdfByte, other.pdfByte))
			return false;
		if (resumo == null) {
			if (other.resumo != null)
				return false;
		} else if (!resumo.equals(other.resumo))
			return false;
		if (sizeImg == null) {
			if (other.sizeImg != null)
				return false;
		} else if (!sizeImg.equals(other.sizeImg))
			return false;
		if (sizePdf == null) {
			if (other.sizePdf != null)
				return false;
		} else if (!sizePdf.equals(other.sizePdf))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Ebook [id=" + id + ", titulo=" + titulo + ", editora=" + editora + ", resumo=" + resumo + ", ano=" + ano
				+ ", autor=" + autor + ", genero=" + genero + ", preco=" + preco + "]";
	}

}
