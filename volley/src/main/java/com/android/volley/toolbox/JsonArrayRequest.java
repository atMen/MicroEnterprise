/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A request for retrieving a {@link JSONArray} response body at a given URL.
 */
public class JsonArrayRequest extends JsonRequest<JSONArray> {

	/**
	 * Creates a new request.
	 * 
	 * @param url
	 *            URL to fetch the JSON from
	 * @param listener
	 *            Listener to receive the JSON response
	 * @param errorListener
	 *            Error listener, or null to ignore errors.
	 */
	public JsonArrayRequest(String url, JSONObject jsonRequest,
			Listener<JSONArray> listener, ErrorListener errorListener) {
		this(jsonRequest.length() <= 0 ? Method.GET : Method.POST, url, jsonRequest,
                listener, errorListener);
	}


	public JsonArrayRequest(int method, String url, JSONObject jsonRequest,
			Listener<JSONArray> listener, ErrorListener errorListener) {
		super(method, url, (jsonRequest == null) ? null : jsonRequest
				.toString(), listener, errorListener);
	}

	@Override
	protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
		try {
			// String jsonString = new String(response.data,
			// HttpHeaderParser.parseCharset(response.headers));
			String jsonString = covert(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			return Response.success(new JSONArray(jsonString),
					HttpHeaderParser.parseCacheHeaders(response));
		} catch (JSONException je) {
			return Response.error(new ParseError(je));
		}
	}

	private String covert(byte[] bytes, String charsetName) {
		// TODO Auto-generated method stub
		String jsonStr = null;
		try {
			String data = new String(bytes, "utf-8");
			while (!isJsonStart(data)) {
				data = data.substring(1);
			}
			if (!data.substring(0, 1).equals("[")) {
				data = "[" + data + "]";
			}
			jsonStr = data;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return jsonStr;
	}

	private boolean isJsonStart(String value) {
		if (value.substring(0, 1).equals("{")) {
			return true;
		}
		if (value.substring(0, 1).equals("[")) {
			return true;
		}
		return false;
	}
}
