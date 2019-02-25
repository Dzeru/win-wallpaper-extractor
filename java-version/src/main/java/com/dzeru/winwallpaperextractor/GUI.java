package com.dzeru.winwallpaperextractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GUI
{
	private final static Dimension LABEL_DIMENSION = new Dimension(150, 40);
	private final static Dimension TEXT_FIELD_DIMENSION = new Dimension(220, 40);
	private final static Dimension RUN_DIMENSION = new Dimension(150, 150);
	private final static Dimension STATUS_DIMENSION = new Dimension(220, 150);

	private final static String initialStatusText = "STATUS:\n";

	public static void createGUI()
	{
		JFrame frame = new JFrame("Win10 Wallpaper Extractor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setResizable(false);

		Font usualFont = new Font("Arial", Font.PLAIN, 22);
		Font bigFont = new Font("Arial", Font.BOLD, 30);
		Font smallFont = new Font("Arial", Font.PLAIN, 16);

		JPanel manualLocationPanel = new JPanel();
		manualLocationPanel.setLayout(null);

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setSize(LABEL_DIMENSION);
		usernameLabel.setLocation(30, 30);
		usernameLabel.setFont(usualFont);

		JLabel dirToLabel = new JLabel("Folder to save");
		dirToLabel.setSize(LABEL_DIMENSION);
		dirToLabel.setLocation(30, 90);
		dirToLabel.setFont(usualFont);

		JTextField usernameTextField = new JTextField();
		usernameTextField.setSize(TEXT_FIELD_DIMENSION);
		usernameTextField.setLocation(200, 30);
		usernameTextField.setFont(usualFont);

		JTextField dirToTextField = new JTextField();
		dirToTextField.setSize(TEXT_FIELD_DIMENSION);
		dirToTextField.setLocation(200, 90);
		dirToTextField.setFont(usualFont);

		JTextArea statusTextArea = new JTextArea(initialStatusText);
		statusTextArea.setSize(STATUS_DIMENSION);
		statusTextArea.setLocation(200, 150);
		statusTextArea.setFont(smallFont);
		statusTextArea.setLineWrap(true);
		statusTextArea.setWrapStyleWord(true);
		statusTextArea.setEditable(false);

		JScrollPane scrollStatusArea = new JScrollPane(statusTextArea);
		scrollStatusArea.setLocation(200, 150);
		scrollStatusArea.setSize(STATUS_DIMENSION);
		scrollStatusArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollStatusArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JButton runButton = new JButton("Run");
		runButton.setSize(RUN_DIMENSION);
		runButton.setLocation(30, 150);
		runButton.setFont(bigFont);

		runButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent event)
			{
				statusTextArea.setText(initialStatusText);

				String[] extractorArgs = new String[2];
				extractorArgs[0] = usernameTextField.getText();
				extractorArgs[1] = dirToTextField.getText();
				Extractor extractor = new Extractor();
				try
				{
					String status = extractor.extractWallpapers(extractorArgs);
					statusTextArea.append(status);
				}
				catch(IOException e)
				{
					statusTextArea.append("ERROR! " + e.getMessage());
				}
			}
		});

		manualLocationPanel.add(usernameLabel);
		manualLocationPanel.add(usernameTextField);
		manualLocationPanel.add(dirToLabel);
		manualLocationPanel.add(dirToTextField);
		manualLocationPanel.add(runButton);
		manualLocationPanel.add(scrollStatusArea);

		frame.setContentPane(manualLocationPanel);
		frame.setVisible(true);
	}
}
