package dog;

import java.sql.*;

public class DAO {
	
	public Connection conexao;

	public DAO() {
		conexao = null;
	}
	
	//public Connection getConexao() {
	//	return conexao;
	//}

	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "nazarick.postgres.database.azure.com";
		String mydatabase = "blogdog";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "Ainz@nazarick";
		String password = "yRaptor@MartinF15";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}

	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	/*
	 * consulta o banco de dados e busca todos os caes cadastrados na tabel doacao
	 * @return doacao[] tmp - caes cadastrados na tabela doacao
	 * */
	public Doacao[] get_doacaes() {
		Doacao[] tmp = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM doacao");		
	         if(rs.next()){
	             rs.last();
	             tmp = new Doacao[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	            	 tmp[i] = new Doacao(rs.getInt("id_doacao"),
	            			 			 rs.getString("data_post"),
	            			 			 rs.getString("data_adocao"),
	            			 			 rs.getInt("especie"),
	            			 			 rs.getString("raca"),
	            			 			 rs.getString("nome_animal"),
	            			 			 rs.getInt("porte"),
	            			 			 rs.getString("historico"),
	            			 			 rs.getInt("idade"),
	            			 			 rs.getInt("pelagem"),
	            			 			 rs.getInt("temperamento"),
	            			 			 rs.getString("sexo").charAt(0) ,
	            			 			 rs.getString("cep"),
	            			 			 rs.getString("img_1"),
	            			 			 rs.getString("img_2"),
	            			 			 rs.getString("img_3"),
	            			 			 rs.getString("img_4"),
	            			 			 rs.getString("img_5"),
	            			 			 rs.getInt("user_doacao")
	            			 			 );
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return tmp;
	}
	
	/*
	 * consulta o banco de dados e busca um cão pelo id na tabel doacao
	 * @param int codigo - id do cão a ser procurado
	 * @return doacao tmp - cão procurado
	 * */
	public Doacao get_doacao(int codigo) {
		
		Doacao tmp = null;

		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM doacao A WHERE A.id_doacao = " + codigo +  ";");
			
			rs.next();
       	 		tmp = new Doacao(rs.getInt("id_doacao"),
					 			 rs.getString("data_post"),
					 			 rs.getString("data_adocao"),
					 			 rs.getInt("especie"),
					 			 rs.getString("raca"),
					 			 rs.getString("nome_animal"),
					 			 rs.getInt("porte"),
					 			 rs.getString("historico"),
					 			 rs.getInt("idade"),
					 			 rs.getInt("pelagem"),
					 			 rs.getInt("temperamento"),
					 			 rs.getString("sexo").charAt(0) ,
					 			 rs.getString("cep"),
					 			 rs.getString("img_1"),
					 			 rs.getString("img_2"),
					 			 rs.getString("img_3"),
					 			 rs.getString("img_4"),
					 			 rs.getString("img_5"),
					 			 rs.getInt("user_doacao")
					 			 );
	          st.close();
	          return tmp;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return tmp;
	}
	
	public Dica[] get_dicas() {
		Dica[] tmp = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM dica");		
	         if(rs.next()){
	             rs.last();
	             tmp = new Dica[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	            	 tmp[i] = new Dica(rs.getInt("id_dica"), 
          				   rs.getString("titulo"),
          				   rs.getString("descricao"),
          				   rs.getString("img_dica"),
          				   rs.getInt("tipo_dica"),
        		           rs.getString("data_dica"),
        		           rs.getInt("like_dica"),
        		           rs.getInt("user_dica"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return tmp;
	}
	
	public boolean inserir_denuncia(Denuncia tmp) {
		boolean status = false;
		
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO denuncia (data_post, relato, tipo_denuncia, cep, user_denuncia, data_denuncia)"
					       + " VALUES ('" + tmp.getData_post()         + "', '"
					       				 + tmp.getRelato()            + "', "
					       				 + tmp.getTipo_denuncia()     + ", '"
					       				 + tmp.getCep()               + "', "
					       				 + tmp.getUser_denuncia()     + ", '"
					       				 + tmp.getData_denuncia()     + "');"
					);
	
			st.close();
			status = true;
		} catch (SQLException u) { 
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Denuncia[] getDenuncias() {
		Denuncia[] tmp = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM denuncia");		
	         if(rs.next()){
	             rs.last();
	             tmp = new Denuncia[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	            	 tmp[i] = new Denuncia(rs.getInt("id_denuncia"), 
          				   rs.getString("data_post"), 
          		           rs.getString("relato"),
          		           rs.getInt("tipo_denuncia"),
          		           rs.getString("cep"),
          		           rs.getInt("user_denuncia"),
          		           rs.getString("data_denuncia"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return tmp;
	}
	
	public boolean setUser(Usuario tmp) {
		boolean status = false;
		
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO usuario (email, senha, nome, sobrenome, tipo, celular) "
					       + "VALUES ('" + tmp.getEmail() + "', '"  
					       				 + tmp.getSenha() + "', '" 
					       				 + tmp.getNome()   + "', '" 
					       				 + tmp.getSobrenome()  + "',"
					       				 + 1  + ",'"
					       				 + tmp.getCelular() + "');");
			
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean inserir_dica(Dica tmp) {
		boolean status = false;
		
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO dica (titulo, descricao, img_dica, tipo_dica, data_dica, user_dica)"
					       + "VALUES ('" + tmp.getTitulo()    + "', '"
					       	             + tmp.getDescricao() + "', '"
					       	             + tmp.getImg_dica()  + "',  "
					       	             + tmp.getTipo_dica() + " , '"
					       	             + tmp.getData_dica() + "',  "
					       	             + tmp.getUser_dica() + ");"
					);
			
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean inserirAdocao(Doacao tmp) {
		boolean status = false;
		
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO doacao (data_post, especie, raca, nome_animal, porte, "
					      + "historico, idade, pelagem, temperamento, sexo, cep, img_1, user_doacao) "
					       + "VALUES ('" + tmp.getData_post()    + "',  "
					       	             + tmp.getEspecie()      + " , '"
					       	             + tmp.getRaca()         + "', '"
					       	             + tmp.getNome_animal()  + "',  "
					       	             + tmp.getPorte()        + " , '"
					       	             + tmp.getHistorico()    + "',  "
					       	             + tmp.getIdade()        + " ,  "
					       	             + tmp.getPelagem()      + " ,  "
					       	             + tmp.getTemperamento() + " , '"
					       	             + tmp.getSexo()         + "', '"
					       	             + tmp.getCep()          + "', '"
					       	             + tmp.getImg_1()        + "',  "
					       	             + tmp.getUser_doacao()  + ");"
					);
			
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Usuario[] getOngs() {
		Usuario[] tmp = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM usuario");		
	         if(rs.next()){
	             rs.last();
	             tmp = new Usuario[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	            	 tmp[i] = new Usuario(rs.getInt("id_user"),
	            			              rs.getString("email"),
	            			              rs.getString("senha"),
	            			              rs.getString("nome"),
	            			              rs.getString("sobrenome"),
	            			              rs.getString("sigla"),
	            			              rs.getString("descricao"),
	            			              rs.getString("cnpj"),
	            			              rs.getString("img_user"),
	            			              rs.getInt("tipo"),
	            			              rs.getString("celular")
	            			 
	            			 );
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return tmp;
	}
	
public Usuario getUserId(int codigo) {
		
		Usuario tmp = null;

		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuario A WHERE A.id_user = " + codigo +  ";");
			
			rs.next();
			
			tmp = new Usuario(rs.getInt("id_user"),
		              rs.getString("email"),
		              rs.getString("senha"),
		              rs.getString("nome"),
		              rs.getString("sobrenome"),
		              rs.getString("sigla"),
		              rs.getString("descricao"),
		              rs.getString("cnpj"),
		              rs.getString("img_user"),
		              rs.getInt("tipo"),
		              rs.getString("celular")
		 
		 );
  
			
	          st.close();
	          return tmp;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return tmp;
	}

	public boolean excluirDoacao(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM doacao WHERE id_doacao = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirDica(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM dica WHERE id_dica = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirDenuncia(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM denuncia WHERE id_denuncia = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	

		
	
	

}





