package br.com.movimento_estoque.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.movimento_estoque.connection.Conexao;
import br.com.movimento_estoque.model.Produto;

public class Repository {
	
	private Connection con;
	
	private static String dataDe; 

	public Repository() throws SQLException {
		
		//this.con = Conexao.getConnection();
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	public boolean alterarDetParaZero(String banco, String data) throws SQLException {
	
		this.con = Conexao.getConnection(banco);
		
		dataDe = data;
		
		String update = "update tbdetconta det set det.cancela = 0 where det.dt_mov >= '"+data+"' " ;
		
		PreparedStatement state = this.con.prepareStatement(update);
		
		//state.setString(1, data);
		
		boolean atualizado = state.executeUpdate() > 0;
		state.close();
		state.close();
		
		System.out.println("passou por aqui");
		
		return atualizado;
		
	}
	
	public ArrayList<Produto> buscarProdsComanda(String banco) throws SQLException{
		
		ArrayList <Produto> produtos = new ArrayList<>();
		this.con = Conexao.getConnection(banco);
		
		String select = "SELECT * FROM tbdetconta where dt_mov >= '" +dataDe +"' and operacao = 'CA'";
		
		PreparedStatement state = this.con.prepareStatement(select);
		ResultSet result = state.executeQuery();
		
		while (result.next()) {
			Produto produto = new Produto();
			
			produto.setNum_cotrole(result.getLong("NUM_CONTROLE"));
			produto.setCodigo(result.getLong("CODIGO"));
			produto.setQuant(result.getLong("QUANT"));
			produto.setNquant(result.getLong("NQUANT"));
			produto.setOperacao(result.getString("OPERACAO"));
			produto.setCancela(result.getLong("CANCELA"));
			produto.setPreco(result.getFloat("PRECO"));
			
			produtos.add(produto);
		}
		
		result.close();
		state.close();
		this.con.close();
		
		System.out.println("passou por aqui");
		
		return produtos;
	}
	
	public boolean alterarParaConcertar(String banco, Produto produto) throws SQLException {
	
		this.con = Conexao.getConnection(banco);
		
		String update = "UPDATE tbdetconta SET cancela = ? "
				+ " WHERE num_controle = ? and cancela = 0 and operacao = 'IN' and codigo = ? and npreco = ? ROWS 1";
		
		PreparedStatement state = this.con.prepareStatement(update);
		
		state.setLong(1, produto.getNquant() + (produto.getNquant() * -2));
		state.setLong(2, produto.getNum_cotrole());
		state.setLong(3, produto.getCodigo());
		state.setFloat(4, produto.getPreco());
		//state.setString(5, produto.getDe());
		
		boolean atualizado = state.executeUpdate() > 0;
		state.close();
		state.close();
		
		System.out.println("Produtos com saldos lindos!!!");
		
		return atualizado;
	}
	
	/*public boolean delete(Long idContato) throws SQLException {
		
		this.con = Conexao.getConnection();
		String delete = "DELETE FROM contato WHERE id = ? ";
		
		PreparedStatement state = this.con.prepareStatement(delete);
		state.setLong(1, idContato);
		
		Boolean deletado = state.executeUpdate() > 0;
		state.close();
		this.con.close();
		
		return deletado;
	}*/
	
	/*public boolean alterar(Contato contato) throws SQLException {
		
		this.con = Conexao.getConnection();
		
		String update = "UPDATE contato SET nome = ?, email = ?, telefone = ? "
				+ " WHERE id = ? ";
		
		PreparedStatement state = this.con.prepareStatement(update);
		
		state.setString(1, contato.getNome());
		state.setString(2, contato.getEmail());
		state.setString(3, contato.getTelefone());
		state.setLong(4, contato.getId());
		
		boolean atualizado = state.executeUpdate() > 0;
		state.close();
		state.close();
		
		return atualizado;
	}*/
	
	/*public Contato buscar (Long id) throws SQLException{
		
		this.con = Conexao.getConnection();
		Contato contato = new Contato();
		String select = "SELECT * FROM contato WHERE id = ? ";
		
		PreparedStatement state = this.con.prepareStatement(select);
		state.setLong(1, id);
		
		ResultSet result = state.executeQuery();
		while(result.next()) {
			
			contato.setId(result.getLong("id"));
			contato.setNome(result.getString("nome"));
			contato.setEmail(result.getString("email"));
			contato.setTelefone(result.getString("telefone"));
		}
		
		state.close();
		result.close();
		this.con.close();
		
		return contato;
	}*/

}
