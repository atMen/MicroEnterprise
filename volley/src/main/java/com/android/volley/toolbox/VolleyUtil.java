package com.android.volley.toolbox;

import android.content.Context;
import android.os.Handler;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Map;


public class VolleyUtil {
	public static Context context;
	private Handler handler;

	public VolleyUtil(Context context, Handler handler) {
		this.context = context;
		this.handler = handler;
	}

	public interface VolleyJsonCallback {
		void onSuccess(JSONObject jsonObject);

		void onFailed(String result);
	}

	public interface VolleyJsonArrayCallback {
		void onSuccess(JSONArray jsonArray);

		void onFailed(String result);
	}

	public interface VolleyStringCallback {
		void onSuccess(String result);

		void onFailed(String result);
	}

	/**
	 * String Post请求
	 * 
	 * @param httpurl
	 * @param params
	 * @param callback
	 */
	public void getStringDataFromServer(String httpurl,
			final Map<String, String> params,
			final VolleyStringCallback callback) {
		RequestQueue requestQueue = Volley.newRequestQueue(context);
		StringRequest stringRequest = new StringRequest(httpurl, params,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						callback.onSuccess(response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						System.out.println("error" + error);
						callback.onFailed(error.getMessage());
						handler.sendEmptyMessage(9);
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				return params;
			}
		};
		stringRequest
				.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 0, 1.0f));
		requestQueue.add(stringRequest);
	}

	/**
	 * post json 请求
	 * 
	 * @param httpurl
	 * @param params
	 * @param callback
	 */
	public void getJsonDataFromServer(String httpurl,
			final Map<String, Object> params, final VolleyJsonCallback callback) {
		RequestQueue requestQueue = Volley.newRequestQueue(context);
		JSONObject jsonObject = new JSONObject(params);

		JsonObjectRequest objRequest = new JsonObjectRequest(httpurl,
				jsonObject, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject obj) {
						callback.onSuccess(obj);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						System.out.println("error" + error);
						callback.onFailed(error.getMessage() + "");
						handler.sendEmptyMessage(9);
					}
				});
		objRequest.setRetryPolicy(new DefaultRetryPolicy(500000, 0, 1.0f));
		requestQueue.add(objRequest);
	}

	/**
	 * JSONArray获取数据
	 * 
	 * @param httpurl
	 * @param params
	 * @param callback
	 */
	public void getJsonArrayDataFromServer(String httpurl,
			final Map<String, String> params,
			final VolleyJsonArrayCallback callback) {
		RequestQueue requestQueue = Volley.newRequestQueue(context);
		JSONObject jsonObject = new JSONObject(params);
		JsonArrayRequest objRequest = new JsonArrayRequest(httpurl, jsonObject,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray obj) {
						callback.onSuccess(obj);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						System.out.println("error" + error);
						callback.onFailed(error.getMessage() + "");
						handler.sendEmptyMessage(9);
					}
				});
		objRequest.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, 0, 1.0f));
		requestQueue.add(objRequest);
	}
}
