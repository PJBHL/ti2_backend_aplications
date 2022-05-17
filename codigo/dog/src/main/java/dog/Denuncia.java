package dog;

import org.json.JSONObject;

public class Denuncia implements JsonFormatter{

	private int    id_denuncia;
	private String data_post;
	private String relato;
	private int tipo_denuncia;
	private String cep;
	private int    user_denuncia;
	private String data_denuncia;
	
	public Denuncia(int id_denuncia, String data_post, String relato, int tipo_denuncia, String cep,
			int user_denuncia, String data_denuncia) {
		super();
		this.id_denuncia = id_denuncia;
		this.data_post = data_post;
		this.relato = relato;
		this.tipo_denuncia = tipo_denuncia;
		this.cep = cep;
		this.user_denuncia = user_denuncia;
		this.data_denuncia = data_denuncia;
	}

	public int getId_denuncia() {
		return id_denuncia;
	}

	public void setId_denuncia(int id_denuncia) {
		this.id_denuncia = id_denuncia;
	}

	public String getData_post() {
		return data_post;
	}

	public void setData_post(String data_post) {
		this.data_post = data_post;
	}

	public String getRelato() {
		return relato;
	}

	public void setRelato(String relato) {
		this.relato = relato;
	}

	public int getTipo_denuncia() {
		return tipo_denuncia;
	}

	public void setTipo_denuncia(int tipo_denuncia) {
		this.tipo_denuncia = tipo_denuncia;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getUser_denuncia() {
		return user_denuncia;
	}

	public void setUser_denuncia(int user_denuncia) {
		this.user_denuncia = user_denuncia;
	}	
	
	public String getData_denuncia() {
		return data_denuncia;
	}

	public void setData_denuncia(String data_denuncia) {
		this.data_denuncia = data_denuncia;
	}

	@Override
	public String toString() {
		return "denuncia [id_denuncia=" + id_denuncia + ", data_post=" + data_post + ", relato=" + relato
				+ ", tipo_denuncia=" + tipo_denuncia + ", cep=" + cep + ", user_denuncia=" + user_denuncia
				+ ", data_denuncia=" + data_denuncia + "]";
	}

	@Override
	public JSONObject toJson() {
		
		JSONObject obj = new JSONObject();
	
		obj.put("id_denuncia"       , id_denuncia);
		obj.put("data_post"         , data_post);
		obj.put("data_denuncia"     , data_denuncia);
		obj.put("relato"            , relato);
		obj.put("tipo_denuncia"     , tipo_denuncia);
		obj.put("cep"               , cep);
		obj.put("user_denuncia"     , user_denuncia);
		
		return obj;
	}
}