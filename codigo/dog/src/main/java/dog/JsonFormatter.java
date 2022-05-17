package dog;


import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Interface que declara o protocolo para conversão de um objeto Java em JSON.
 * 
 * @author Prof. Hugo de Paula
 *
 */
public interface JsonFormatter {
	public JSONObject toJson();

	public default JSONArray toJsonArray() {
		return new JSONArray().put(toJson());
	}
}
