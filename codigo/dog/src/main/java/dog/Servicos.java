package dog;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONObject;


import spark.Request;
import spark.Response;

public class Servicos {
	
	public DAO BD;
	
	/*
	 * Conexão com banco de dados
	 * */
	public Servicos () {	
			BD = new DAO();
			if(BD.conectar())
				System.out.println("erro na conexão");
			else
				System.out.println("200OK");
	}
	
	/*
	 * serviço de login 
	 * */
	public Object login(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    
	    JSONObject resp, aux;
	    
	    aux = new JSONObject(request.body());
	    
	    String email = aux.getString("email");
	    String senha = aux.getString("senha");
	    Usuario user = new Usuario();
	    
	    user.getUser(email, BD);
	    resp = user.toJson();
	    
	    if(user.getSenha().equals(senha))
	    	resp.put("status", 1);
	    else
	    	resp.put("status", 0);	    
	    
	    return resp;
	}//end login
	
	/*
	 * busca todos os os caes cadastrados no banco de dados
	 * return Json[] resp - atributos dos caes cadastrados no banco 
	 * */
	public Object doacoes(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    
	    JSONArray resp = new JSONArray();
	    Doacao[] caes = BD.get_doacaes();
	    
	    for(int i = 0; i < caes.length ; i++) 
	    	resp.put(caes[i].toJson());
	    
	 return resp;   
	}//end doacoes
	
	/*
	 * busca um cao dadastrado no banco de dados
	 * @return JSON cao - atributos de um animal 
	 * */
	public Object buscaadocao(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    
	    int id = Integer.parseInt(request.params(":id"));
	    Doacao cao = BD.get_doacao(id);
	    Usuario usuario = BD.getUserId(cao.getUser_doacao());
	    JSONObject resp = cao.toJson();
	    resp.put("email", usuario.getEmail());
	    resp.put("celular", usuario.getCelular());
	    
	    return resp;   
	}//buscaacocao
	
	/*
	 * busca todas as dicas no banco de dados
	 * @return JSON[] resp - array com atributos das dicas
	 * */
	public Object dicas(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    
	    JSONArray resp = new JSONArray();
	    Dica[] D = BD.get_dicas();
	
	    for(int i = 0; i < D.length; i++)
	    	resp.put(D[i].toJson());
	    
	    return resp;
	}//end dicas
	
	/*
	 * cadastro de animais 
	 * */
	public Object cadastroDenuncia(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    
	    JSONObject execute = new JSONObject(request.body());
	    Denuncia denun = new Denuncia(1,
	    		                   execute.getString("data_post"),
	    		                   execute.getString("relato"),
	    		                   Integer.parseInt(execute.getString("tipo")),
	    		                   execute.getString("cep"),
	    		                   execute.getInt("user_denuncia"),
	    		                   execute.getString("data_denuncia"));

	    return BD.inserir_denuncia(denun);
	}//end cadastroDenuncia
	
	/*
	 * 
	 * */
	public Object denuncias(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	 
	    JSONArray resp = new JSONArray();
	    Denuncia[] tmp = BD.getDenuncias();
	    
	    for(int i = 0; i < tmp.length; i++)
	    	resp.put(tmp[i].toJson());
	    
	    return resp;
	}//end denuncias
	
	/*
	 * 
	 * */
	public Object cadastroUser(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    	    
	    JSONObject resq = new JSONObject(request.body());
	    JSONObject resp = new JSONObject();
	    Usuario user = new Usuario();
	   	    
	    user.getUser(resq.getString("email"), BD);
	    if(!user.getEmail().equals("")){
	    	resp.put("status", "0");
	    }
	    else {
	    		
	    	user.setEmail(resq.getString("email"));
	    	user.setSenha(resq.getString("senha"));
	    	user.setNome(resq.getString("nome"));
	    	user.setSobrenome(resq.getString("sobrenome"));
	    	user.setCelular(resq.getString("numero"));
	    	
	    	BD.setUser(user);
	    	resp.put("status", "1");
	    }
	    
	    return resp;
	}//end cadastroUser 
	
	/*
	 * 
	 * */
	public Object cadastroDica(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
	    
	    Dica dica;
	    String img = request.queryParams("imagem");
	    System.out.println("passei" + "  " + img);
	    
	    if(img.equals("true")) {
	    	try {
	    	img = UploadFile.saveArq(request, "img");
	    	}
	    	catch(Exception erro) {
	    		System.out.println("Erro - >uplodfile:\n" + erro);
	    	}
	    }
	    else {
	    	img = "padrao.png";
	    }
	    
	    dica = new Dica(-1,
	    		        request.queryParams("titulo"),
	    		        request.queryParams("conteudo"),
	    		        img,
	    		        Integer.parseInt(request.queryParams("tipo")),
	    		        request.queryParams("data"),
	    		        0,
	    		        Integer.parseInt(request.queryParams("user"))
	    		);    
	    BD.inserir_dica(dica);
	 
	    JSONObject resp = new JSONObject();
	    resp.put("status", "200OK");
	    return resp;
	}//end cadastroDica
	
	public Object cadastroAdocao(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
	 
	    String img = "";
	    
	    
	    //System.out.println(request.body());
	    
	    try {
	    	img = UploadFile.saveArq(request, "img");
	    }
	    catch(Exception erro) {
	    	System.out.println("Erro - >uplodfile:\n" + erro);
	    }
	    
	    System.out.println("pasei 1");
	    
	  
	    Doacao doacao = new Doacao(-1, 
	    		                   request.queryParams("data"), 
	    		                   "-1", 
	    		                   Integer.parseInt(request.queryParams("especie")), 
	    		                   request.queryParams("raca"), 
	    		                   request.queryParams("nome"), 
	    		                   Integer.parseInt(request.queryParams("porte")), 
	    		                   request.queryParams("historico"), 
	    		                   Integer.parseInt(request.queryParams("idade")), 
	    		                   Integer.parseInt(request.queryParams("pelagem")), 
	    		                   Integer.parseInt(request.queryParams("temperamento")), 
	    		                   request.queryParams("sexo").charAt(0), 
	    		                   request.queryParams("cep"), 
	    		                   img, 
	    		                   "", 
	    		                   "", 
	    		                   "", 
	    		                   "", 
	    		                   Integer.parseInt(request.queryParams("user"))
	    		                   );
	    
	    BD.inserirAdocao(doacao);
	    
	    return "200ok";
	}
	
	public Object ongs(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	 
	    JSONArray resp = new JSONArray();
	    Usuario[] tmp = BD.getOngs();
	    
	    for(int i = 0; i < tmp.length; i++) {
	    	if(tmp[i].getTipo() == 2) {
	    	resp.put(tmp[i].toJson());
	    	}
	    }
	    
	    return resp;
	}//end denuncias
	
	/*
	 * busca todos os os caes cadastrados no banco de dados
	 * return Json[] resp - atributos dos caes cadastrados no banco 
	 * */
	public Object minhasadocao(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    
	    int id = Integer.parseInt(request.params(":id"));
	    
	    JSONArray resp = new JSONArray();
	    Doacao[] caes = BD.get_doacaes();
	    
	    for(int i = 0; i < caes.length ; i++) { 
	    	if(caes[i].getUser_doacao() == id) {
	    		resp.put(caes[i].toJson());
	    	}
	    }
	    
	    return resp;   
	}//end doacoes
	
	public Object minhasdenuncias(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	 
	    int id = Integer.parseInt(request.params(":id"));
	    JSONArray resp = new JSONArray();
	    Denuncia[] tmp = BD.getDenuncias();
	    
	    for(int i = 0; i < tmp.length; i++)
	    	if(tmp[i].getUser_denuncia() == id)
	    		resp.put(tmp[i].toJson());
	    
	    return resp;
	}//end denuncias
	
	/*
	 * busca todas as dicas no banco de dados
	 * @return JSON[] resp - array com atributos das dicas
	 * */
	public Object minhasdicas(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    
	    int id = Integer.parseInt(request.params(":id"));
	    JSONArray resp = new JSONArray();
	    Dica[] D = BD.get_dicas();
	
	    for(int i = 0; i < D.length; i++)
	    	if(D[i].getUser_dica() == id)
	    		resp.put(D[i].toJson());
	    
	    return resp;
	}//end dicas
	
	public Object excluirDica(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    
	    int id = Integer.parseInt(request.params(":id"));
	    BD.excluirDica(id);
	    
	    return "200OK";
	}//end dicas
	
	public Object excluirDenuncia(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    
	    int id = Integer.parseInt(request.params(":id"));
	    BD.excluirDenuncia(id);
	    
	    return "200OK";
	}//end dicas
	
	public Object excluirDoacao(Request request, Response response) {
		response.header("Access-Control-Allow-Origin", "*");
	    response.header("Content-Type", "application/json");
	    
	    System.out.println("passei 123321");
	    
	    int id = Integer.parseInt(request.params(":id"));
	    System.out.println("passei 123321");
	    BD.excluirDoacao(id);
	    System.out.println("passei 123321");
	    
	    return "200OK";
	}//end dicas
	
	
	
	
	
	
	
	
	
	
}//end servicos