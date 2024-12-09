package UI;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;

public class tema {
	
	public void aparencia() {
		try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());
			UIManager.put("OptionPane.messageFont",new Font("Tahoma",Font.PLAIN,26));
			UIManager.put("TextField.font", new Font("Tahoma", Font.PLAIN, 24));
			UIManager.put("List.font", new Font("Tahoma",Font.PLAIN,30));
			UIManager.put("ComboBox.font", new Font("Tahoma",Font.PLAIN,22));
			UIManager.put("Button.font", new Font("Tahoma",Font.BOLD,22));
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	
	


}

