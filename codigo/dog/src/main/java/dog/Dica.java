package dog;

import org.json.JSONObject;

public class Dica implements JsonFormatter{

	private int    id_dica;
	private String titulo;
	private String descricao;
	private String img_dica;
	private int    tipo_dica;
	private String data_dica;
	private int    like_dica;
	private int    user_dica;
	
	public Dica(int id_dica, String titulo, String descricao, String img_dica, int tipo_dica, String data_dica,
			int like_dica, int user_dica) {
		super();
		this.id_dica = id_dica;
		this.titulo = titulo;
		this.descricao = descricao;
		this.img_dica = img_dica;
		this.tipo_dica = tipo_dica;
		this.data_dica = data_dica;
		this.like_dica = like_dica;
		this.user_dica = user_dica;
	}

	public int getId_dica() {
		return id_dica;
	}

	public void setId_dica(int id_dica) {
		this.id_dica = id_dica;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImg_dica() {
		return img_dica;
	}

	public void setImg_dica(String img_dica) {
		this.img_dica = img_dica;
	}

	public int getTipo_dica() {
		return tipo_dica;
	}

	public void setTipo_dica(int tipo_dica) {
		this.tipo_dica = tipo_dica;
	}

	public String getData_dica() {
		return data_dica;
	}

	public void setData_dica(String data_dica) {
		this.data_dica = data_dica;
	}

	public int getLike_dica() {
		return like_dica;
	}

	public void setLike_dica(int like_dica) {
		this.like_dica = like_dica;
	}

	public int getUser_dica() {
		return user_dica;
	}

	public void setUser_dica(int user_dica) {
		this.user_dica = user_dica;
	}
	
	
	
	@Override
	public String toString() {
		return "Dica [id_dica=" + id_dica + ", titulo=" + titulo + ", descricao=" + descricao + ", img_dica=" + img_dica
				+ ", tipo_dica=" + tipo_dica + ", data_dica=" + data_dica + ", like_dica=" + like_dica + ", user_dica="
				+ user_dica + "]";
	}

	@Override
	public JSONObject toJson() {
		
		JSONObject obj = new JSONObject();
	
		obj.put("id_dica"     , id_dica);
		obj.put("titulo"      , titulo);
		obj.put("descricao"   , descricao);
		obj.put("img_dica"    , img_dica);
		obj.put("tipo_dica"   , tipo_dica);
		obj.put("data_dica"   , data_dica);
		obj.put("like_dica"   , like_dica);
		obj.put("user_dica"   , user_dica);
		
		return obj;
	}
}
