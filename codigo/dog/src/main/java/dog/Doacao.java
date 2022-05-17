package dog;

import org.json.JSONObject;

public class Doacao implements JsonFormatter{

	private int    id_doacao;
	private String data_post;
	private String data_adocao;
	private int    especie;
	private String raca;
	private String nome_animal;
	private int    porte;
	private String historico;
	private int    idade;
	private int    pelagem;
	private int    temperamento;
	private char   sexo;
	private String cep;
	private String img_1;
	private String img_2;
	private String img_3;
	private String img_4;
	private String img_5;
	private int    user_doacao;
	
	public Doacao(int id_doacao, String data_post, String data_adocao, int especie, String raca, String nome_animal,
			int porte, String historico, int idade, int pelagem, int temperamento, char sexo, String cep, String img_1,
			String img_2, String img_3, String img_4, String img_5, int user_doacao) {
		super();
		this.id_doacao = id_doacao;
		this.data_post = data_post;
		this.data_adocao = data_adocao;
		this.especie = especie;
		this.raca = raca;
		this.nome_animal = nome_animal;
		this.porte = porte;
		this.historico = historico;
		this.idade = idade;
		this.pelagem = pelagem;
		this.temperamento = temperamento;
		this.sexo = sexo;
		this.cep = cep;
		this.img_1 = img_1;
		this.img_2 = img_2;
		this.img_3 = img_3;
		this.img_4 = img_4;
		this.img_5 = img_5;
		this.user_doacao = user_doacao;
	}

	public int getId_doacao() {
		return id_doacao;
	}

	public void setId_doacao(int id_doacao) {
		this.id_doacao = id_doacao;
	}

	public String getData_post() {
		return data_post;
	}

	public void setData_post(String data_post) {
		this.data_post = data_post;
	}

	public String getData_docao() {
		return data_adocao;
	}

	public void setData_docao(String data_docao) {
		this.data_adocao = data_docao;
	}

	public int getEspecie() {
		return especie;
	}

	public void setEspecie(int especie) {
		this.especie = especie;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getNome_animal() {
		return nome_animal;
	}

	public void setNome_animal(String nome_animal) {
		this.nome_animal = nome_animal;
	}

	public int getPorte() {
		return porte;
	}

	public void setPorte(int porte) {
		this.porte = porte;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getPelagem() {
		return pelagem;
	}

	public void setPelagem(int pelagem) {
		this.pelagem = pelagem;
	}

	public int getTemperamento() {
		return temperamento;
	}

	public void setTemperamento(int temperamento) {
		this.temperamento = temperamento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getImg_1() {
		return img_1;
	}

	public void setImg_1(String img_1) {
		this.img_1 = img_1;
	}

	public String getImg_2() {
		return img_2;
	}

	public void setImg_2(String img_2) {
		this.img_2 = img_2;
	}

	public String getImg_3() {
		return img_3;
	}

	public void setImg_3(String img_3) {
		this.img_3 = img_3;
	}

	public String getImg_4() {
		return img_4;
	}

	public void setImg_4(String img_4) {
		this.img_4 = img_4;
	}

	public String getImg_5() {
		return img_5;
	}

	public void setImg_5(String img_5) {
		this.img_5 = img_5;
	}

	public int getUser_doacao() {
		return user_doacao;
	}

	public void setUser_doacao(int user_doacao) {
		this.user_doacao = user_doacao;
	}
	
	
	
	@Override
	public String toString() {
		return "doacao [id_doacao=" + id_doacao + ", data_post=" + data_post + ", data_adocao=" + data_adocao
				+ ", especie=" + especie + ", raca=" + raca + ", nome_animal=" + nome_animal + ", porte=" + porte
				+ ", historico=" + historico + ", idade=" + idade + ", pelagem=" + pelagem + ", temperamento="
				+ temperamento + ", sexo=" + sexo + ", cep=" + cep + ", img_1=" + img_1 + ", img_2=" + img_2
				+ ", img_3=" + img_3 + ", img_4=" + img_4 + ", img_5=" + img_5 + ", user_doacao=" + user_doacao + "]";
	}

	@Override
	public JSONObject toJson() {
		
		JSONObject obj = new JSONObject();
	
		obj.put("id_doacao"   , id_doacao);
		obj.put("data_post"   , data_post);
		obj.put("data_adocao" , data_adocao);
		obj.put("especie"     , especie);
		obj.put("raca"        , raca);
		obj.put("nome_animal" , nome_animal);
		obj.put("porte"       , porte);
		obj.put("historico"   , historico);
		obj.put("idade"       , idade);
		obj.put("pelagem"     , pelagem);
		obj.put("temperamento", temperamento);
		obj.put("sexo"        , sexo);
		obj.put("cep"         , cep);
		obj.put("img_1"       , img_1);
		obj.put("img_2"       , img_2);
		obj.put("img_3"       , img_3);
		obj.put("img_4"       , img_4);
		obj.put("img_5"       , img_5);
		obj.put("user_doacao" , user_doacao);
		
		return obj;
	}
	
	
	
	
}
