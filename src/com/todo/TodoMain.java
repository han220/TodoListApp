package com.todo;




import java.util.List;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
		TodoList l = new TodoList();
		
		// Load Data
		TodoUtil.loadList(l, "todolist.txt");
		
		boolean isList = false;
		boolean quit = false;
		do {
			Menu.displaymenu();
			isList = false;
			String[] input = Menu.prompt();
			switch (input[0]) {
			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				isList = true;
				break;
			
			case "find":
				l.findItem(String.join(" ", List.of(input).subList(1, input.length)));
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("알 수 없는 명령어입니다.");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		
		// 파일 저장
		TodoUtil.saveList(l, "todolist.txt");
	}
}
