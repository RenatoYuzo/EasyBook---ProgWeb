package br.ufms.facom.progweb12.easybook.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import br.ufms.facom.progweb12.easybook.abstracts.AbstractEntity;

@Entity
public class Venda extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 923053900832796606L;

	public Venda() {

	}

	public Venda(Integer id_usuario, Integer id_ebook) {
		this.id_usuario = id_usuario;
		this.id_ebook = id_ebook;
		this.dataVenda = new Date();
	}

	@Id
	private Integer id;
	private Integer id_usuario;
	private Integer id_ebook;
	private Date dataVenda;

	@Transient
	private String nomeComprador;
	@Transient
	private String ebookTitulo;
	@Transient
	private String dataVendaFormatada;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Integer getId_ebook() {
		return id_ebook;
	}

	public void setId_ebook(Integer id_ebook) {
		this.id_ebook = id_ebook;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getNomeComprador() {
		return nomeComprador;
	}

	public void setNomeComprador(String nomeComprador) {
		this.nomeComprador = nomeComprador;
	}

	public String getEbookTitulo() {
		return ebookTitulo;
	}

	public void setEbookTitulo(String ebookTitulo) {
		this.ebookTitulo = ebookTitulo;
	}

	public String getDataVendaFormatada() {
		return dataVendaFormatada;
	}

	public void setDataVendaFormatada(String dataVendaFormatada) {
		this.dataVendaFormatada = dataVendaFormatada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataVenda == null) ? 0 : dataVenda.hashCode());
		result = prime * result + ((dataVendaFormatada == null) ? 0 : dataVendaFormatada.hashCode());
		result = prime * result + ((ebookTitulo == null) ? 0 : ebookTitulo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_ebook == null) ? 0 : id_ebook.hashCode());
		result = prime * result + ((id_usuario == null) ? 0 : id_usuario.hashCode());
		result = prime * result + ((nomeComprador == null) ? 0 : nomeComprador.hashCode());
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
		Venda other = (Venda) obj;
		if (dataVenda == null) {
			if (other.dataVenda != null)
				return false;
		} else if (!dataVenda.equals(other.dataVenda))
			return false;
		if (dataVendaFormatada == null) {
			if (other.dataVendaFormatada != null)
				return false;
		} else if (!dataVendaFormatada.equals(other.dataVendaFormatada))
			return false;
		if (ebookTitulo == null) {
			if (other.ebookTitulo != null)
				return false;
		} else if (!ebookTitulo.equals(other.ebookTitulo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_ebook == null) {
			if (other.id_ebook != null)
				return false;
		} else if (!id_ebook.equals(other.id_ebook))
			return false;
		if (id_usuario == null) {
			if (other.id_usuario != null)
				return false;
		} else if (!id_usuario.equals(other.id_usuario))
			return false;
		if (nomeComprador == null) {
			if (other.nomeComprador != null)
				return false;
		} else if (!nomeComprador.equals(other.nomeComprador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", id_usuario=" + id_usuario + ", id_ebook=" + id_ebook + ", dataVenda=" + dataVenda
				+ ", nomeComprador=" + nomeComprador + ", ebookTitulo=" + ebookTitulo + ", dataVendaFormatada="
				+ dataVendaFormatada + "]";
	}

}
