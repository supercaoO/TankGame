package com.tankgame;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TankGame {
	public static void main(String[] args) {
		try {
			new TankGameFrame().launchFrame();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
