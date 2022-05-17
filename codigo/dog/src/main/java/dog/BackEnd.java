package dog;

import static spark.Spark.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;


import spark.Request;

public class BackEnd {
	
	private static Servicos serv = new Servicos();
	
	public static void main (String[] args) {
		
		port(4567);
		
		//pastas do frontEnd
		staticFiles.location("/public");
	    //Pasta de imagens cadastradas;
		staticFiles.externalLocation("upload");
		
		post("/login", (request, response) -> serv.login(request, response));
		
		get("/animaisdoacao", (request, response) -> serv.doacoes(request, response));
		
		get("/buscaadocao/:id", (request, response) -> serv.buscaadocao(request, response));
		
		get("/dicas", (request, response) -> serv.dicas(request, response));
		
		post("/cadastroDenuncia", (request, response) -> serv.cadastroDenuncia(request ,response));
		
		get("/denuncias", (request, response) -> serv.denuncias(request, response));
		
		post("/cadastroUser", (request, response) -> serv.cadastroUser(request ,response));
		
		post("/cadastroDica", (request, response) -> serv.cadastroDica(request ,response));
		
		post("/cadastroAdocao", (request, response) -> serv.cadastroAdocao(request ,response));
		
		get("/ongs", (request, response) -> serv.ongs(request, response));
		
		get("/minhasadocao/:id", (request, response) -> serv.minhasadocao(request, response));
		
		get("/minhasdenuncias/:id", (request, response) -> serv.minhasdenuncias(request, response));
		
		get("/minhasdicas/:id", (request, response) -> serv.minhasdicas(request, response));
		
		get("/excluiAdocao/:id", (request, response) -> serv.excluirDoacao(request, response));
		
		get("/excluiDenuncias/:id", (request, response) -> serv.excluirDenuncia(request, response));
		
		get("/execluirDicas/:id", (request, response) -> serv.excluirDica(request, response));
		
		
		

	}//end main
}//end Back end
