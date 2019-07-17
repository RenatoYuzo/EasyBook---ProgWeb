package br.ufms.facom.progweb12.easybook.converter;

import java.sql.SQLException;

import javax.el.ValueExpression;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import br.ufms.facom.progweb12.easybook.controller.SessionContext;

@RequestScoped
@Named(value = "entityConverter")
@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}

		Object object = component.getAttributes().get(value);
		if (object == null) {
			ValueExpression valueExpression = component.getValueExpression("value");
			Class<?> entityClass = valueExpression.getType(context.getELContext());
			try {

				String valor = "-1";
				if (value != null)
					valor = value.toString();

				String sql = "SELECT * from " + entityClass.getSimpleName() + " where id='" + valor + "'";
				object = executeQuerySingleResult(sql, entityClass);

			} catch (Exception e) {
				System.out.println("Erro EntityConverter[01]" + e.getMessage());
			}

		}
		return object;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return null;
	}

	public <T> T executeQuerySingleResult(String sql, Class<T> classtype) throws SQLException {
		QueryRunner run = new QueryRunner();
		BeanHandler<T> blh = new BeanHandler<>(classtype);
		return run.query(SessionContext.conn, sql, blh);
	}

}
