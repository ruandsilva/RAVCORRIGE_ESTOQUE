package br.com.movimento_estoque.service;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.movimento_estoque.model.Produto;
import br.com.movimento_estoque.repository.Repository;

public class Service {

	public Repository repository ;
	
	public Service() throws SQLException {
		repository = new Repository();
	}
	
	public void alterarDetParaZero(String banco, String data) throws SQLException {
		this.repository.alterarDetParaZero(banco, data);
	}
	
	public ArrayList<Produto> buscarProdsComanda(String banco) throws SQLException {
		return this.repository.buscarProdsComanda(banco);
	}
	
	public void alterarParaConcertar(String banco, Produto produto) throws SQLException {
		this.repository.alterarParaConcertar(banco, produto);
	}
}
