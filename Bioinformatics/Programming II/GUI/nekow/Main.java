package nekow;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		JFrame area = new JFrame("nekow");
		area.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TextArea nekos = new TextArea("Escriba aca");
		JLabel salida = new JLabel(nekos.getText()); 
		salida.addKeyListener(new Vigilante(nekos,area,salida));
		area.getContentPane().setLayout(new BorderLayout());
		area.getContentPane().add(nekos, BorderLayout.WEST);
		area.getContentPane().add(salida, BorderLayout.SOUTH);
		area.pack();
		area.setVisible(true);
	}
}
