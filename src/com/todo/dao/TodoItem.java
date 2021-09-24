package com.todo.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private String title;
	private String desc;
	private String current_date;
	
	public static final SimpleDateFormat dueSdf = new SimpleDateFormat("yyyy/MM/dd");
	private String duedate, category;

	public TodoItem(String category, String title, String desc, String duedate) {
		this.title = title;
		this.desc = desc;
		this.category = category;
		this.duedate = duedate;
		this.current_date = sdf.format(new Date());
	}

	public TodoItem(String category, String title, String desc, String duedate, String current_date) {
		this.title = title;
		this.desc = desc;
		this.current_date = current_date;
		this.category = category;
		this.duedate = duedate;
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
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getDueDate() {
		return duedate;
	}
	
	public Date getDueDate_date() {
		try {
			return dueSdf.parse(current_date);
		} catch (ParseException e) {
			return null;
		} 
	}

	public String toSaveString() {
		return category + "##" + title + "##" + desc + "##" + duedate + "##" + current_date + "\n";
	}
	
	public String print(int i) {
		return String.format("%d. [%s] %s - %s - %s - %s", i, category, title, desc, duedate, current_date);
	}
}
