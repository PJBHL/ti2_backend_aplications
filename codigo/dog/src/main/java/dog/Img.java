package dog;

import org.json.JSONObject;

public class Img implements JsonFormatter{

	private int user_img;
	private String img;
	
	public Img(int user_img, String img) {
		super();
		this.user_img = user_img;
		this.img = img;
	}

	public int getUser_img() {
		return user_img;
	}

	public void setUser_img(int user_img) {
		this.user_img = user_img;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@Override
	public JSONObject toJson() {
		
		JSONObject obj = new JSONObject();
	
		obj.put("user_img", this.getUser_img());
		obj.put("img", this.getImg());
		
		return obj;
	}
	
}
