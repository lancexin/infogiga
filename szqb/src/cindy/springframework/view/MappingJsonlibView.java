package cindy.springframework.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.view.AbstractView;

public class MappingJsonlibView extends AbstractView{
	
	private String jsonArrayName = "jsonArray";
	
	private String jsonObjectName = "jsonObject";

	public String getJsonArrayName() {
		return jsonArrayName;
	}


	public void setJsonArrayName(String jsonArrayName) {
		this.jsonArrayName = jsonArrayName;
	}


	public String getJsonObjectName() {
		return jsonObjectName;
	}


	public void setJsonObjectName(String jsonObjectName) {
		this.jsonObjectName = jsonObjectName;
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> arg0,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType(super.getContentType());
		Object ja = arg0.get(jsonArrayName);
		
		if(ja != null){
			JSONArray jsonArray = JSONArray.fromObject(ja);
			response.getWriter().print(jsonArray.toString());
			return;
		}
		Object jo =arg0.get(jsonObjectName);
		if(jo != null){
			JSONObject jsonObject = JSONObject.fromObject(jo);
			response.getWriter().print(jsonObject.toString());
			return;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(arg0);
		response.getWriter().print(jsonObject.toString());
	}


	
}
