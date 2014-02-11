import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;


public class UserInterface
{

	public JFrame frmImportFromFoodxml;
	private SettingsUI sets;
	private JTextArea textArea;
	private final Action action = new SwingAction();
	private JTabbedPane tabbedPane;
	public JScrollPane scrollPane_articles;
	private ListSelectionListener listSelectionListener;
	@SuppressWarnings("rawtypes")
	private JList list;
	private JTextPane textPane;
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	public static ProgressMonitor progressMonitor;

	/**
	 * Create the application.
	 */
	public UserInterface()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmImportFromFoodxml = new JFrame();
		frmImportFromFoodxml.setIconImage(Toolkit.getDefaultToolkit().getImage(UserInterface.class.getResource("/images/icon_korona.png")));
		frmImportFromFoodxml.setTitle("FoodXML->Cloud Konverter");
		frmImportFromFoodxml.setBounds(100, 100, 750, 500);
		frmImportFromFoodxml.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmImportFromFoodxml.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setBorder(null);
		frmImportFromFoodxml.getContentPane().add(tabbedPane);

		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Eingabedatei", null, scrollPane, null);


		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Artikel", null, panel, null);

		scrollPane_articles = new JScrollPane();

		JButton btnExport = new JButton("Exportiere");
		btnExport.addActionListener(new ActionListener()
		{ // der Button-Druck triggert das Senden
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					Main.export();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
				;
			}
		});

		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(UIManager.getColor("Button.background"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(
			gl_panel.createSequentialGroup()
				.addGroup(
					gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(
							gl_panel.createSequentialGroup()
								.addGap(407)
								.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 174,
									Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED,
									GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 117,
									GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_articles, GroupLayout.PREFERRED_SIZE, 741,
							Short.MAX_VALUE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(
			gl_panel.createSequentialGroup()
				.addComponent(scrollPane_articles, GroupLayout.PREFERRED_SIZE, 383,
					Short.MAX_VALUE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(
					gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnExport, GroupLayout.DEFAULT_SIZE,
							GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textPane))
				.addContainerGap()));
		panel.setLayout(gl_panel);
		tabbedPane.setEnabledAt(1, false);

		JMenuBar menuBar = new JMenuBar();
		frmImportFromFoodxml.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("Datei");
		menuBar.add(mnFile);

		JMenuItem mntmLoad = new JMenuItem("Öffnen");
		mntmLoad.setAction(action);
		mnFile.add(mntmLoad);

		JMenuItem mntmEinstellungen = new JMenuItem("Einstellungen");
		mntmEinstellungen.setAction(action_2);
		mnFile.add(mntmEinstellungen);

		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.setAction(action_1);
		mnFile.add(mntmBeenden);

		listSelectionListener = new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				textPane.setText("Ausgewählt: " + list.getSelectedIndices().length + " Artikel");

			}
		};
	}

	private class SwingAction extends AbstractAction
	{
		private static final long serialVersionUID = 2177396644165241390L;

		public SwingAction()
		{
			putValue(NAME, "Öffnen");
			putValue(SHORT_DESCRIPTION, "Datei-Dialog");
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void actionPerformed(ActionEvent e)
		{
			if(Main.KoronaApiUrl==null || Main.Token == null)
			{
				throwPopup("Bitte setzen sie erst URL und Token in den Einstellungen", JOptionPane.ERROR_MESSAGE);
				return;
			}
			final JFileChooser pop = new JFileChooser();
			pop.setFileFilter(new FileFilter()
			{
				@Override
				public String getDescription()
				{
					return "Nur Xml-Dateien";
				}

				@Override
				public boolean accept(File f)
				{
					if (f.isDirectory() || f.getName().endsWith("xml"))
						return true;
					return false;
				}
			});
			int returnVal = pop.showOpenDialog(pop);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				if(!Main.testSetup()) return;
				progressMonitor = new ProgressMonitor(frmImportFromFoodxml, "", "", 0, 100);
				progressMonitor.setProgress(0);
				final SwingWorker worker = new SwingWorker<Void, Void>()
				{
					int progress = 0;
					Thread t;
					boolean cont = true;

					@Override
					protected Void doInBackground() throws Exception
					{
						t = new Thread(new Runnable()
						{

							@Override
							public void run()
							{
								while (cont)
								{
									try
									{
										Thread.sleep(100);
										if (progress < 99)
											progress++;
										else
											progress = 0;
										setProgress(progress);

									}
									catch (InterruptedException e)
									{
										e.printStackTrace();
									}
								}
							}
						});
						t.start();
						textArea.setText("");
						String path = pop.getSelectedFile().getAbsolutePath();
						FileReader fr = new FileReader(path);
						BufferedReader textReader = new BufferedReader(fr);
						String line;
						while ((line = textReader.readLine()) != null)
						{
							textArea.append(line + "\n");
						}
						textReader.close();
						FoodXmlParser.parse(path);
						list = new JList(FoodXmlParser.articles_final.toArray());
						list.addListSelectionListener(listSelectionListener);
						return null;
					}

					@Override
					public void done()
					{
						cont=false;
						scrollPane_articles.setViewportView(list);
						tabbedPane.setEnabledAt(1, true);
						tabbedPane.setSelectedIndex(1);
						progressMonitor.close();
					}
				};
				worker.addPropertyChangeListener(new PropertyChangeListener()
				{

					@Override
					public void propertyChange(PropertyChangeEvent evt)
					{
						if ("progress" == evt.getPropertyName())
						{
							int progress = (Integer)evt.getNewValue();
							progressMonitor.setProgress(progress);
							if (progressMonitor.isCanceled() || worker.isDone())
							{
								Toolkit.getDefaultToolkit().beep();
								worker.cancel(true);
								progressMonitor.close();
							}
						}

					}
				});
				worker.execute();
			}
		}
	}

	public static void throwPopup(String message, int i)
	{
		if(getProgressMonitor()!=null)
		{
			getProgressMonitor().close();
		}	
		String title = "Error";
		if (i == JOptionPane.INFORMATION_MESSAGE)
			title = "Gespeichert";
		JOptionPane.showMessageDialog(null, message, title, i);
	}
	
	@SuppressWarnings("rawtypes")
	public JList getList()
	{
		return list;
	}

	private class SwingAction_1 extends AbstractAction
	{
		private static final long serialVersionUID = -3791891906919481762L;

		public SwingAction_1()
		{
			putValue(NAME, "Beenden");
			putValue(SHORT_DESCRIPTION, "Programm schließen");
		}

		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	private class SwingAction_2 extends AbstractAction
	{
		private static final long serialVersionUID = -41864430450640276L;

		public SwingAction_2()
		{
			putValue(NAME, "Einstellungen");
			putValue(SHORT_DESCRIPTION, "Hier werden Token und URL festgelegt");
		}

		public void actionPerformed(ActionEvent e)
		{
			if (sets == null)
				sets = new SettingsUI();
			sets.setVisible(enabled);
			sets.setLocationRelativeTo(frmImportFromFoodxml);
		}
	}

	public static ProgressMonitor getProgressMonitor()
	{
		return progressMonitor;
	}
}
