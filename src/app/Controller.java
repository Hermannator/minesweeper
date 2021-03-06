package app;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controller {
	Minesweeper game;
	GUI gui;
	IO io;
	public Controller(GUI gui) {
		this.gui = gui;
		this.io = new IO();
	}
	public void showMenu() {
		gui.updateScene(gui.menu());
	}
	public void updateBoard() {
		gui.updateScene(gui.boardScene(game.getBoard(),game.getGameWon(),game.getGameOver(),game.getBombCounter()));
	}
	public void handleClick(Field field) {
		game.clickField(field);
		updateBoard();
	}
	public void handleFlag(Field field) {
		game.flagField(field);
		updateBoard();
	}
	public void handleRevealAdjacent(Field field) {
		game.revealAdjacentFields(field);
		updateBoard();
	}
	public void newGame(int columns, int rows, int numberOfBombs) {
		game = new Minesweeper(new Gameboard(columns,rows,numberOfBombs));
		updateBoard();
	}
	public void save() {
		try {
			io.save("minesweeperSave.txt",game);
		}catch(IOException e) {}
	}
	public void load() {
		try {
			game = io.load("minesweeperSave.txt");
			updateBoard();
		}catch(IOException e) {
			Alert error = new Alert(AlertType.ERROR);
			error.setContentText("404 - file not found");
			error.showAndWait();
		}
	}
}
