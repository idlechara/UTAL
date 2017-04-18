package gui;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("nekowo");
		JLabel label = new JLabel("nekowo_ñau");
		Checkbox box = new Checkbox();
		JLabel jua = new JLabel("checbox esta: ");
		JLabel state = new JLabel();
		
		
		
		frame.getContentPane().setLayout(new GridLayout(3,2));
		frame.getContentPane().add(label);
		frame.getContentPane().add(box);
		frame.getContentPane().add(jua);
		frame.getContentPane().add(state);
		
		frame.pack();
		frame.setVisible(true);
	}

}
