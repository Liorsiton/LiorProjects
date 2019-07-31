package com.example.liorita.tictac;

import java.util.Arrays;

public class TicTac {
	
	private int[][] board;

	public TicTac() {
		board = new int[3][3];
		init();
	}
	
	public void add(int i , int j , int input) {
		if(input != 1 && input != 0) {
			System.out.println("please insert 1 or 0");
			return;
		}
		board[i][j] = input;
	}

	public boolean isCellFree(int x , int y){
		if(board[x][y] == -1){
			return true;
		}
		return  false;
	}
	
	public boolean checkHorizontalVictory(int input) {
		if(board[0][0]== input && board[0][1]== input && board[0][2]==input) {
			return true;
		}
		if(board[1][0]== input && board[1][1]== input && board[1][2]==input) {
			return true;
		}
		if(board[2][0]== input && board[2][1]== input && board[2][2]==input) {
			return true;
		}
		return false;		  
		
	}
	
	public boolean checkVerticalVictory(int input) {
		if(board[0][0]== input && board[1][0]== input && board[2][0]==input) {
			return true;
		}
		if(board[0][1]== input && board[1][1]== input && board[2][1]==input) {
			return true;
		}
		if(board[0][2]== input && board[1][2]== input && board[2][2]==input) {
			return true;
		}
		return false;		  
		
	}
	
	public boolean checkDiagonalVictory(int input) {
		if(board[0][0]== input && board[1][1]== input && board[2][2]==input) {
			return true;
		}
		if(board[0][2]== input && board[1][1]== input && board[2][0]==input) {
			return true;
		}
		
		return false;		  
		
	}
	public void init() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j]=-1;
			}
		}
	}

	public boolean isBoardFull(){
		for(int i =0;i<board.length;i++){
			for(int j=0;j<board[i].length;j++){
				if(board[i][j]== -1){
					return false;
				}
			}
		}
		return true;
	}


	public int[][] getBoard() {
		return board;
	}

	@Override
	public String toString() {
		return "TicTac [board=" + Arrays.toString(board) + "]";
	}
	
	
	
	
	

}
