package com.dzeru.winwallpaperextractor;

import javax.swing.*;

import static com.dzeru.winwallpaperextractor.GUI.createGUI;

public class RunExtractor
{
	public static void main(String... args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createGUI();
			}
		});
	}
}
