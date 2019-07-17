package br.ufms.facom.progweb12.easybook.converter;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ModelSqlConverter {

	private Object keyValue;
	private Object model;
	private List<String> campos = new ArrayList<>();
	private List<Object> valores = new ArrayList<>();

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public ModelSqlConverter(Object model) {

		this.model = model;
		Field[] fields = model.getClass().getDeclaredFields();

		for (Field f : fields) {
			f.setAccessible(true);

			Object objectValue = null;
			try {
				objectValue = f.get(model);
				if (f.getName().equalsIgnoreCase("id")) {
					keyValue = objectValue;
				}

				if (f.isAnnotationPresent(javax.persistence.Id.class))
					continue;
				if (f.isAnnotationPresent(javax.persistence.Transient.class))
					continue;
				if (f.getName().equalsIgnoreCase("serialVersionUID"))
					continue;

				add(f.getName(), objectValue);

			} catch (IllegalArgumentException | IllegalAccessException e) {
				System.err.println("Erro ModelSqlConverter[01]: " + e.getMessage());
			}

		}

	}

	private void add(String campo, Object valor) {
		this.campos.add(campo);
		this.valores.add(valor);
	}

	public String InsertSQL() {
		String sql = " INSERT INTO " + model.getClass().getSimpleName() + " ( ";

		for (int i = 0; i < campos.size(); i++) {

			if (i == 0) {
				sql += campos.get(i);
			} else {
				sql += ", " + campos.get(i);
			}

		} // end for

		sql += " ) VALUES ( ";

		for (int i = 0; i < campos.size(); i++) {

			Object valor = valores.get(i);
			String valorStr = "";

			if (valor == null) {
				valorStr = "null";
			} else if (valor instanceof String) {
				valorStr = "'" + valor.toString().replaceAll("'", "''") + "'";
			} else if (valor instanceof java.util.Date) {
				valorStr = "'" + sdf.format(valor) + "'";
			} else {
				valorStr = valor.toString();
			}

			if (i == 0) {
				sql += valorStr;
			} else {
				sql += ", " + valorStr;
			}

		} // end for
		sql += " ) ";
		return sql;
	}

	public String updateSQL() {

		String sql = " UPDATE " + model.getClass().getSimpleName();

		sql += " SET ";

		for (int i = 0; i < campos.size(); i++) {
			String campo = campos.get(i);
			Object valor = valores.get(i);
			String valorStr = "";

			if (valor == null) {
				valorStr = "null";
			} else if (valor instanceof String) {
				valorStr = "'" + valor.toString().replaceAll("'", "''") + "'";
			} else if (valor instanceof java.util.Date) {
				valorStr = "'" + sdf.format(valor) + "'";
			} else {
				valorStr = valor.toString();
			}

			if (i == 0) {
				sql += campo + " = " + valorStr;
			} else {
				sql += ", " + campo + " = " + valorStr;
			}
		}

		sql += " WHERE id=" + this.keyValue;
		return sql;
	}

	public String deleteSQL() {
		String sql = " DELETE FROM " + model.getClass().getSimpleName() + " WHERE id=" + this.keyValue;
		return sql;
	}

}
