package com.dmagdaleno.store.bean.dao.teste;

import java.util.Calendar;
import java.util.List;

import com.dmagdaleno.store.bean.Product;
import com.dmagdaleno.store.bean.dao.DAOFactory;
import com.dmagdaleno.store.bean.dao.ProductDAO;
import com.dmagdaleno.store.exception.DBException;

public class ProductDAOTeste {

  public static void main(String[] args) {
    ProductDAO dao = DAOFactory.getInstance();
    
    //Cadastrar um produto
    Product produto = new Product(0,"Caderno",20,Calendar.getInstance(),100);
    try {
      dao.cadastrar(produto);
      System.out.println("Produto cadastrado.");
    } catch (DBException e) {
      e.printStackTrace();
    }

    //Buscar um produto pelo código e atualizar
    produto = dao.buscar(1);
    produto.setNome("Caderno capa dura");
    produto.setValor(30);
    try {
      dao.atualizar(produto);
      System.out.println("Produto atualizado.");
    } catch (DBException e) {
      e.printStackTrace();
    }
    
    //Listar os produtos
    List<Product> lista = dao.listar();
    for (Product item : lista) {
      System.out.println(item.getNome() + " " + item.getQuantidade() + " " + item.getValor());
    }
    
    //Remover um produto
    try {
      dao.remover(1);
      System.out.println("Produto removido.");
    } catch (DBException e) {
      e.printStackTrace();
    } 
  } 
}