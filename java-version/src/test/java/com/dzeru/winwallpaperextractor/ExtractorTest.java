package com.dzeru.winwallpaperextractor;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ExtractorTest
{
	private static Properties testProperties;

	/*
	Since the app is associated with Windows 10, some tests require REAL Win10 username.
	It can be configured via test.properties file, currentWindows10User property.
	 */
	static
	{
		InputStream testPropertiesPath = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.properties");
		testProperties = new Properties();
		try
		{
			testProperties.load(testPropertiesPath);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testWithNotExistingUsername()
	{
		String[] args = new String[2];
		args[0] = "John12kjfdsf87DJDFJnj4r43iw382urjFJHFUE3*k3jrk3weojioj!!ljkkldsf2/?df";
		args[1] = "C:/test/";

		Extractor extractor = new Extractor();
		String status = "";

		try
		{
			status = extractor.extractWallpapers(args);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		assertEquals("START\n" +
				"ERROR: Default folder for wallpapers does not exist.\nMay be you write wrong username. Try again.", status);
		assertNotEquals("START\n" +
				"ERROR: Write the username. Try again.\n", status);
		}

	@Test
	public void testWithExistingUsername()
	{
		String[] args = new String[2];
		args[0] = testProperties.getProperty("currentWindows10User");
		args[1] = "C:/test/";

		Extractor extractor = new Extractor();
		String status = "";

		try
		{
			status = extractor.extractWallpapers(args);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		assertEquals("DONE.", status.substring(status.length() - 5));
		assertNotEquals("START\n" +
				"ERROR: Write the username. Try again.\n", status);
		assertNotEquals("START\n" +
				"ERROR: Default folder for wallpapers does not exist.\nMay be you write wrong username. Try again.", status);
	}

	@Test
	public void testWithoutUsername()
	{
		String[] args = new String[2];
		args[1] = "C:/test/";

		Extractor extractor = new Extractor();
		String status = "";

		try
		{
			status = extractor.extractWallpapers(args);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		assertEquals("START\n" +
				"ERROR: Write the username. Try again.\n", status);
	}
}
