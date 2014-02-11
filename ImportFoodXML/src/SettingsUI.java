import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;


public class SettingsUI extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 895376742416901615L;
	private final JPanel contentPanel = new JPanel();
	private final Action action = new SwingAction();
	private JTextField text_token;
	private JLabel lblUrlZurCloud;
	private JLabel lblToken;
	private JTextField text_url;
	private final Action action_1 = new SwingAction_1();

	/**
	 * Create the dialog.
	 */
	public SettingsUI()
	{
		setTitle("Einstellungen");
		setBounds(100, 100, 618, 174);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblToken = new JLabel("Token:");
		}
		{
			lblUrlZurCloud = new JLabel("Cloud-Url:");
		}
		{
			text_token = new JTextField();
			text_token.setColumns(10);
		}

		text_url = new JTextField();
		text_url.setColumns(10);
		if(Main.properties.containsKey("token"))
			text_token.setText(Main.properties.getProperty("token"));
		if(Main.properties.containsKey("url"))
			text_url.setText(Main.properties.getProperty("url"));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
			.addGroup(
				gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(
						gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(
								gl_contentPanel.createSequentialGroup()
									.addComponent(lblToken, GroupLayout.DEFAULT_SIZE, 56,
										Short.MAX_VALUE)
									.addGap(49))
							.addGroup(
								gl_contentPanel.createSequentialGroup()
									.addComponent(lblUrlZurCloud)
									.addPreferredGap(ComponentPlacement.RELATED)))
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addGroup(
						gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(text_url)
							.addComponent(text_token, GroupLayout.DEFAULT_SIZE, 467,
								Short.MAX_VALUE))
					.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
			.addGroup(
				gl_contentPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(
						gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblToken, GroupLayout.PREFERRED_SIZE, 23,
								GroupLayout.PREFERRED_SIZE)
							.addComponent(text_token, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(
						gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblUrlZurCloud)
							.addComponent(text_url, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap()));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setAction(action_1);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setAction(action);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private class SwingAction extends AbstractAction
	{
		private static final long serialVersionUID = 7109753851763987176L;

		public SwingAction()
		{
			putValue(NAME, "Abbrechen");
			putValue(SHORT_DESCRIPTION, "Dialog schliessen");
		}

		public void actionPerformed(ActionEvent e)
		{
			setVisible(false);
		}
	}
	private class SwingAction_1 extends AbstractAction
	{
		private static final long serialVersionUID = 9207228052823772916L;

		public SwingAction_1()
		{
			putValue(NAME, "OK");
			putValue(SHORT_DESCRIPTION, "Bestätigen");
		}

		public void actionPerformed(ActionEvent e)
		{
			setVisible(!Main.saveProps(text_token.getText(), text_url.getText()));
		}
	}
}
