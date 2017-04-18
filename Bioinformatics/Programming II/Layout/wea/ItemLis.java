package wea;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ItemLis implements ItemListener {
	JLabel label;
	JFrame frame;
	JCheckBox chk;
	public ItemLis(JLabel label, JFrame frame, JCheckBox chk){
		this.label = label;
		this.frame = frame;
		this.chk = chk;
	}
	public void itemStateChanged(ItemEvent e) {
		System.out.println("neko");
		label.setText(Boolean.toString(chk.isSelected()));
	}

}
