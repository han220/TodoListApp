package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== 생성 \n"
				+ "제목 입력: \n");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("[오류:중복] 이미 있는 제목입니다.");
			return;
		}
		
		System.out.println("카테고리 입력: ");
		String category = sc.next().trim(); 
		
		System.out.println("설명 입력: ");
		sc.nextLine(); // ignores the next line from previous next()
		desc = sc.nextLine();
		
		System.out.println("마감일자 입력: ");
		String duedate = sc.next().trim();
		
		TodoItem t = new TodoItem(category, title, desc, duedate);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== 삭제 \n"
				+ "삭제할 번호 입력 \n"
				+ "\n");
		
		String no = sc.next().trim();
		int no_i = Integer.parseInt(no);
		if(no_i > l.getList().size()) {
			System.out.println("[오류] 범위에 벗어남.");
			return;
		}
		l.deleteindex(no_i - 1);
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== 수정 \n"
				+ "수정할 항목 번호 입력 \n"
				+ "\n");
		String no = sc.next().trim();
		int no_i = Integer.parseInt(no);
		if(no_i > l.getList().size()) {
			System.out.println("[오류] 범위에 벗어남.");
			return;
		}
		
//		if (!l.isDuplicate(title)) {
//			System.out.println("[오류] 찾을 수 없음 ");
//			return;
//		}

		System.out.println("새로운 제목 입력: ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("[오류:중복] 제목이 중복될 수 없음.");
			return;
		}
		
		System.out.println("카테고리 입력: ");
		String category = sc.next().trim();
		
		System.out.println("새로운 설명 입력: ");
		sc.nextLine(); // ignores the next line from previous next()
		String new_description = sc.nextLine().trim();
		
		System.out.println("마감일자 입력: ");
		String duedate = sc.next().trim();
		
		// 업데이트
		l.set(no_i - 1, new TodoItem(category, new_title, new_description, duedate));
		System.out.println("업데이트 완료.");
		

	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 목록, 총 " + l.getList().size() + "개]");
		int x = 1;
		for (TodoItem item : l.getList()) {
			System.out.println(String.format("%d. [%s] %s - %s - %s - %s", x, item.getCategory(), item.getTitle(), item.getDesc(), item.getDueDate(), item.getCurrent_date_str()));
			x++;
//			System.out.println("Item Title: " + item.getTitle() + "  Item Description:  " + item.getDesc());
		}
	}
	
//	public static void find(TodoList l) {
//		Scanner sc = new Scanner(System.in);
//		String niddle = sc.nextLine();
//		l.findItem(niddle);
//	}

	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new  FileWriter(filename);
			for(TodoItem t : l.getList()) 
				w.write(t.toSaveString());
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String oneline;
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				l.addItem(new TodoItem(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()));
			}
			br.close();
		} catch (FileNotFoundException e) {
			// File does not exists
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
