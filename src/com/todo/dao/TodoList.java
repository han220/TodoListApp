package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}

	public void addItem(TodoItem t) {
		list.add(t);
	}

	public void deleteItem(TodoItem t) {
		list.remove(t);
	}
	
	public void deleteindex(int i) {
		list.remove(i);
	}
	
	public void set(int i, TodoItem newItem) {
		list.set(i, newItem);
	}
	
	public void findItem(String niddle) {
		int i = 1, cnt = 0;
		for(TodoItem item : list) {
			if(item.getTitle().contains(niddle) || item.getDesc().contains(niddle)) {
				System.out.println(item.print(i));
				cnt++;
			}
			i++;
		}
		System.out.println("총 " + cnt + "개의 항목을 찾았습니다.");
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public void listAll() {
		System.out.println("\n"
				+ "정렬 후 배열\n");
		int x = 1;
		for (TodoItem item : list) {
			System.out.println(String.format("%d. [%s] %s - %s - %s - %s", x, item.getCategory(), item.getTitle(), item.getDesc(), item.getDueDate(), item.getCurrent_date_str()));
			x++;
		}
		
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
}
