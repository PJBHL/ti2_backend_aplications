package dog;

import java.sql.ResultSet;
import java.sql.Statement;

//import java.sql.*;

import org.json.JSONObject;

public class Usuario implements JsonFormatter{

	private int    id_user;
	private String email;
	private String senha;
	private String nome;
	private String sobrenome;
	private String sigla;
	private String descricao;
	private String cnpj;
	private String img_user;
	private int    tipo;
	private String celular;

	public Usuario(int id_user, String email, String senha, String nome, String sobrenome, String sigla,
			String descricao, String cnpj, String img_user, int tipo, String celular) {
		super();
		this.id_user = id_user;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.sigla = sigla;
		this.descricao = descricao;
		this.cnpj = cnpj;
		this.img_user = img_user;
		this.tipo = tipo;
		this.celular = celular;
	}	

	public Usuario() {
		super();
		this.id_user = -1;
		this.email = "";
		this.senha = "";
		this.nome = "";
		this.sobrenome = "";
		this.sigla = "";
		this.descricao = "";
		this.cnpj = "";
		this.img_user = "";
		this.tipo = -1;
		this.celular = "";
	}
	
	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getImg_user() {
		return img_user;
	}

	public void setImg_user(String img_user) {
		this.img_user = img_user;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	

	@Override
	public String toString() {
		return "Usuario [id_user=" + id_user + ", email=" + email + ", senha=" + senha + ", nome=" + nome
				+ ", sobrenome=" + sobrenome + ", sigla=" + sigla + ", descricao=" + descricao + ", cnpj=" + cnpj
				+ ", img_user=" + img_user + ", tipo=" + tipo + ", celular=" + celular + "]";
	}

	@Override
	public JSONObject toJson() {
		
		JSONObject obj = new JSONObject();
		obj.put("id_user", this.getId_user());
		obj.put("email", this.getEmail());
		obj.put("nome", this.getNome());
		obj.put("sobrenome", this.getSobrenome());
		obj.put("sigla", this.getSigla());
		obj.put("descricao", this.getDescricao());
		obj.put("cnpj", this.getCnpj());
		obj.put("img_user", this.getImg_user());
		obj.put("tipo", this.getTipo());
		obj.put("celular", this.getCelular());
				
		return obj;
	}
	
	public void getUser(String email, DAO BD) {
		
		try {
			Statement st = BD.conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM usuario A WHERE A.email = '" + email +  "' ;");
			
			rs.next();

	                this.id_user   = rs.getInt("id_user");
	        		this.email     = rs.getString("email");
	        		this.senha     = rs.getString("senha");
	        		this.nome      = rs.getString("nome");
	        		this.sobrenome = rs.getString("sobrenome");
	        		this.sigla     = rs.getString("sigla");
	        		this.descricao = rs.getString("descricao");
	        		this.cnpj      = rs.getString("cnpj");
	        		this.img_user  = rs.getString("img_user");
	        		this.tipo      = rs.getInt("tipo");
	        		this.celular   = rs.getString("celular");
	               
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
