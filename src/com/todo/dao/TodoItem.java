package com.todo.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private String title;
	private String desc;
	private String current_date;

	public TodoItem(String title, String desc) {
		this.title = title;
		this.desc = desc;
		this.current_date = sdf.format(new Date());
	}

	public TodoItem(String title, String desc, String current_date) {
		this.title = title;
		this.desc = desc;
		this.current_date = current_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCurrent_date() {
		try {
			return sdf.parse(current_date);
		} catch (ParseException e) {
			return null;
		}
	}

	public String getCurrent_date_str() {
		return current_date;
	}

	public void setCurrent_date(Date current_date) {
		this.current_date = sdf.format(current_date);
	}

	public String toSaveString() {
		return title + "##" + desc + "##" + current_date + "\n";
	}
}
