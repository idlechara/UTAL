package wea;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import java.applet.*;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Awesome");
		JCheckBox check = new JCheckBox("cool!");
		JLabel label = new JLabel(Boolean.toString(check.isEnabled()));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2,3));
		frame.getContentPane().add(check);
		ItemLis listen = new ItemLis(label, frame, check);
		frame.getContentPane().add(label);
		check.addItemListener(listen);
		frame.pack();
		frame.setVisible(true);
	}
}
