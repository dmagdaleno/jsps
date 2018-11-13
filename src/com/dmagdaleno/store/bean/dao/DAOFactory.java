package com.dmagdaleno.store.bean.dao;

import com.dmagdaleno.store.bean.dao.impl.ProductDAOOracle;

public class DAOFactory {
	
	public static ProductDAO getInstance() {
		return new ProductDAOOracle();
	}

}
