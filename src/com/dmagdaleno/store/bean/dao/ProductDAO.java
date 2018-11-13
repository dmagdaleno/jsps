package com.dmagdaleno.store.bean.dao;

import java.util.List;
import com.dmagdaleno.store.bean.Product;
import com.dmagdaleno.store.exception.DBException;

public interface ProductDAO {
  
  void cadastrar(Product product) throws DBException;
  void atualizar(Product product) throws DBException;
  void remover(int codigo) throws DBException;
  Product buscar(int id);
  List<Product> listar();
}