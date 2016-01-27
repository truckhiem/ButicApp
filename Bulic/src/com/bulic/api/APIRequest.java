package com.bulic.api;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bulic.utils.General;

public class APIRequest {
	
	private Context mContext;

	public APIRequest(Context mContext){
		this.mContext = mContext;
	}
	
	public void requestGET(String url, final Handler mHandler){
		String fullUrl = APIDefine.BASE_URL + url + APIDefine.API_KEY;
		RequestQueue queue = Volley.newRequestQueue(mContext);

		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.GET, fullUrl,
		            new Response.Listener<String>() {
		    @Override
		    public void onResponse(String response) {
		    	General.Log("Response OK: ");
		        Message msg = new Message();
		        msg.what = APIDefine.RESPONSE_OK;
		        msg.obj = response;
		        mHandler.sendMessage(msg);
		    }
		}, new Response.ErrorListener() {
		    @Override
		    public void onErrorResponse(VolleyError error) {
		    	General.Log("That didn't work!");
		    	Message msg = new Message();
		        msg.what = APIDefine.RESPONSE_FAIL;
		        mHandler.sendMessage(msg);
		    }
		});
		queue.add(stringRequest);
	}
	

}
