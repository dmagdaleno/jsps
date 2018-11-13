package com.dmagdaleno.store.bean.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.dmagdaleno.store.bean.Product;
import com.dmagdaleno.store.bean.dao.ProductDAO;
import com.dmagdaleno.store.db.ConnectionManager;
import com.dmagdaleno.store.exception.DBException;

public class ProductDAOOracle implements ProductDAO {
	
	private Connection conn;

	@Override
	public void cadastrar(Product product) throws DBException {
	  PreparedStatement stmt = null;

	  try {
	    conn = ConnectionManager.getInstance().getConnection();
	    String sql = "INSERT INTO TB_PRODUTO (CD_PRODUTO, NM_PRODUTO, QT_PRODUTO, VL_PRODUTO, DT_FABRICACAO) "
	    		+ "VALUES (SQ_TB_PRODUTO.NEXTVAL, ?, ?, ?, ?)";
	    stmt = conn.prepareStatement(sql);
	    stmt.setString(1, product.getNome());
	    stmt.setInt(2, product.getQuantidade());
	    stmt.setDouble(3, product.getValor());
	    java.sql.Date data = new java.sql.Date(product.getDataFabricacao().getTimeInMillis());
	    stmt.setDate(4, data);

	    stmt.executeUpdate();
	  } 
	  catch (SQLException e) {
	    e.printStackTrace();
	    throw new DBException("Erro ao cadastradar.");
	  } 
	  finally {
	    try {
	      stmt.close();
	      conn.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
	}

	@Override
	public void atualizar(Product product) throws DBException {
	  PreparedStatement stmt = null;

	  try {
	    conn = ConnectionManager.getInstance().getConnection();
	    String sql = "UPDATE TB_PRODUTO SET NM_PRODUTO = ?, QT_PRODUTO = ?, VL_PRODUTO = ?, "
	    		+ "DT_FABRICACAO = ? WHERE CD_PRODUTO = ?";
	    stmt = conn.prepareStatement(sql);
	    stmt.setString(1, product.getNome());
	    stmt.setInt(2, product.getQuantidade());
	    stmt.setDouble(3, product.getValor());
	    java.sql.Date data = new java.sql.Date(product.getDataFabricacao().getTimeInMillis());
	    stmt.setDate(4, data);
	    stmt.setInt(5, product.getCodigo());

	    stmt.executeUpdate();
	  } catch (SQLException e) {
	    e.printStackTrace();
	    throw new DBException("Erro ao atualizar.");
	  } finally {
	    try {
	      stmt.close();
	      conn.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
	}

	@Override
	public void remover(int code) throws DBException {
	  PreparedStatement stmt = null;

	  try {
	    conn = ConnectionManager.getInstance().getConnection();
	    String sql = "DELETE FROM TB_PRODUTO WHERE CD_PRODUTO = ?";
	    stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, code);
	    stmt.executeUpdate();
	  } catch (SQLException e) {
	    e.printStackTrace();
	    throw new DBException("Erro ao remover.");
	  } finally {
	    try {
	      stmt.close();
	      conn.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
	}

	@Override
	public Product buscar(int id) {
	  Product product = null;
	  PreparedStatement stmt = null;
	  ResultSet rs = null;
	  try {
	    conn = ConnectionManager.getInstance().getConnection();
	    stmt = conn.prepareStatement("SELECT * FROM TB_PRODUTO WHERE CD_PRODUTO = ?");
	    stmt.setInt(1, id);
	    rs = stmt.executeQuery();
	    if (rs.next()){
	      int codigo = rs.getInt("CD_PRODUTO");
	      String nome = rs.getString("NM_PRODUTO");
	      int qtd = rs.getInt("QT_PRODUTO");
	      double valor = rs.getDouble("VL_PRODUTO");
	      java.sql.Date data = rs.getDate("DT_FABRICACAO");
	      Calendar dataFabricacao = Calendar.getInstance();
	      dataFabricacao.setTimeInMillis(data.getTime());

	      product = new Product(codigo, nome, valor, dataFabricacao, qtd);
	    }
	      
	  } catch (SQLException e) {
	    e.printStackTrace();
	  }finally {
	    try {
	      stmt.close();
	      rs.close();
	      conn.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
	  return product;
	}

	@Override
	public List< Product> listar() {
	  List<Product> lista = new ArrayList<>();
	  PreparedStatement stmt = null;
	  ResultSet rs = null;
	  try {
	    conn = ConnectionManager.getInstance().getConnection();
	    stmt = conn.prepareStatement("SELECT * FROM TB_PRODUTO");
	    rs = stmt.executeQuery();

	    //Percorre todos os registros encontrados
	    while (rs.next()) {
	      int codigo = rs.getInt("CD_PRODUTO");
	      String nome = rs.getString("NM_PRODUTO");
	      int qtd = rs.getInt("QT_PRODUTO");
	      double valor = rs.getDouble("VL_PRODUTO");
	      java.sql.Date data = rs.getDate("DT_FABRICACAO");
	      Calendar dataFabricacao = Calendar.getInstance();
	      dataFabricacao.setTimeInMillis(data.getTime());
	        
	      Product product = new Product(codigo, nome, valor, dataFabricacao, qtd);
	      lista.add(product);
	    }
	  } catch (SQLException e) {
	    e.printStackTrace();
	  }finally {
	    try {
	      stmt.close();
	      rs.close();
	      conn.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
	  return lista;
	}

}
