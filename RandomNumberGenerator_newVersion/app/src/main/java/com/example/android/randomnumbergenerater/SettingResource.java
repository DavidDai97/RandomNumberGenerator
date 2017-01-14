package com.example.android.randomnumbergenerater;

import java.io.Serializable;

/**
 * Created by lenovo on 2016/8/3.
 */
public class SettingResource implements Serializable {

	private int resourceId;
	private String comingClass;

	public SettingResource(int id, String come){
		resourceId = id;
		comingClass = come;
	}

	public int getResourceId(){
		return resourceId;
	}

	public String getComingClass(){
		return comingClass;
	}
}
