package com.sploder12.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
public Window(int width, int height, String title, Render render){	
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));        //Makes The Screen
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);          //makes the X function (to exit)
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(render);
		frame.setVisible(true);
		render.start();
	}
}
