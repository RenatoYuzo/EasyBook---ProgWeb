package br.ufms.facom.progweb12.easybook.abstracts;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import br.ufms.facom.progweb12.easybook.controller.SessionContext;
import br.ufms.facom.progweb12.easybook.converter.ModelSqlConverter;

public abstract class AbstractDao<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -372748262038367956L;

	protected Class<T> clazz;

	public AbstractDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void update(T entity) {
		try {
			ModelSqlConverter msc = new ModelSqlConverter(entity);
			QueryRunner run = new QueryRunner();
			run.update(SessionContext.conn, msc.updateSQL());
		} catch (Exception e) {
			System.err.println("Erro AbstractDao[01]: " + e.getMessage());
		}
	}

	public void delete(T entity) {
		try {
			ModelSqlConverter msc = new ModelSqlConverter(entity);
			QueryRunner run = new QueryRunner();
			run.update(SessionContext.conn, msc.deleteSQL());
		} catch (Exception e) {
			System.err.println("Erro AbstractDao[02]: " + e.getMessage());
		}
	}

	public void persist(T entity) {
		try {
			ModelSqlConverter msc = new ModelSqlConverter(entity);
			QueryRunner run = new QueryRunner();
			run.update(SessionContext.conn, msc.InsertSQL());
		} catch (Exception e) {
			System.err.println("Erro AbstractDao[03]: " + e.getMessage());
		}
	}

	public List<T> findAll() {
		try {
			String sql = "select * from " + clazz.getSimpleName();
			return executeQueryListResult(sql, clazz);
		} catch (Exception e) {
			System.err.println("Erro AbstractDao[04]: " + e.getMessage());
			return null;
		}
	}

	public T findById(Integer id) {
		try {
			QueryRunner run = new QueryRunner();
			BeanHandler<T> blh = new BeanHandler<>(clazz);

			String sql = "SELECT * FROM " + clazz.getSimpleName() + " WHERE id=" + id;
			return run.query(SessionContext.conn, sql, blh);
		} catch (Exception e) {
			System.err.println("Erro AbstractDao[05]: " + e.getMessage());
			return null;
		}
	}

	public T findByLogin(String login, Connection conn) {
		try {
			QueryRunner run = new QueryRunner();
			BeanHandler<T> blh = new BeanHandler<>(clazz);

			String sql = "SELECT * FROM " + clazz.getSimpleName() + " WHERE login='" + login + "'";
			return run.query(conn, sql, blh);
		} catch (Exception e) {
			System.err.println("Erro AbstractDao[06]: " + e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("hiding")
	public <T> List<T> executeQueryListResult(String sql, Class<T> classtype) {
		try {
			QueryRunner run = new QueryRunner();
			BeanListHandler<T> blh = new BeanListHandler<>(classtype);
			return run.query(SessionContext.conn, sql, blh);
		} catch (Exception e) {
			System.err.println("Erro AbstractDao[07]: " + e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("hiding")
	public <T> T executeQuerySingleResult(String sql, Class<T> classtype) {
		try {
			QueryRunner run = new QueryRunner();
			BeanHandler<T> blh = new BeanHandler<>(classtype);
			return run.query(SessionContext.conn, sql, blh);
		} catch (SQLException e) {
			System.err.println("Erro AbstractDao[08]: " + e.getMessage());
			return null;
		}
	}

}
