import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * Diese Klasse ist für die Oberfläche verantwortlich
 * @author Gordon Bosch
 *
 */
public class UI implements KeyListener {

	private JFrame frmShowProduct;
	private static JTextField textField_nr;
	private static JTextArea textArea_info;
	private static JTextField textField_name;
	
	public static JTextField getTextField_name() {
		return textField_name;
	}
	public JFrame getFrmShowProduct() {
		return frmShowProduct;
	}
	public void setFrmShowProduct(JFrame frmShowProduct) {
		this.frmShowProduct = frmShowProduct;
	}
	public static JTextField getTextField() {
		return textField_nr;
	}
	public static JTextArea getTextArea() {
		return textArea_info;
	}
	public UI() {
		initialize();
	}

	/**
	 * hier wird die GUI zusammengesetzt, Swing as usual
	 * 
	 */
	private void initialize() {
		frmShowProduct = new JFrame();
		textField_nr = new JTextField();
		textArea_info = new JTextArea();
		textField_nr.setHorizontalAlignment(SwingConstants.CENTER);
		JButton btnShow = new JButton("show");
		
		frmShowProduct.setTitle("Show Product");
		frmShowProduct.setBounds(100, 100, 450, 300);
		frmShowProduct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textField_nr.setColumns(10);

		JLabel lblProductkey = new JLabel("Productnr:");
		
		JLabel lblProductname = new JLabel("Productname:");
		
		textField_name = new JTextField();
		textField_name.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		GroupLayout groupLayout = new GroupLayout(frmShowProduct.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblProductname)
							.addGap(40)
							.addComponent(textField_name, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnSave))
						.addComponent(textArea_info, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblProductkey, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(textField_nr, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(41)
							.addComponent(btnShow, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
					.addGap(34))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblProductkey))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(textField_nr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnShow))
					.addGap(18)
					.addComponent(textArea_info, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProductname)
						.addComponent(btnSave)
						.addComponent(textField_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23))
		);
		frmShowProduct.getContentPane().setLayout(groupLayout);
		btnShow.addActionListener(new ActionListener() { // der Button-Druck triggert die Produktsuche
			public void actionPerformed(ActionEvent e) {
				try {
					Main.requestProduct(); 
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.addActionListener(new ActionListener() { // der Button-Druck triggert die Produktsuche
			public void actionPerformed(ActionEvent e) {
				try {
					Main.postProduct(); 
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		textField_nr.addKeyListener(this);
		textField_name.addKeyListener(this);
	}
	public void keyTyped(KeyEvent e) {}
	/**
	 * hier implementieren wir die Abfrage, ob Enter gedrückt wurde und welche Fkt es betrifft
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			try { 
				if(textField_nr.hasFocus()){
					Main.requestProduct();
				}
				if(textField_name.hasFocus()){
					Main.postProduct();
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	public void keyReleased(KeyEvent e) {}
	/**
	 * Wirft einen Error-Popup, wenn benötigt
	 * @param message die Error-Message
	 */
	public static void throwPopup(String message, int i) {
		String title = "Error";
		if(i==JOptionPane.INFORMATION_MESSAGE) title="Gespeichert";
		JOptionPane.showMessageDialog(null, message, title, i);
	}
}
