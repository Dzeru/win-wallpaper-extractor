package com.dzeru.winwallpaperextractor;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Extractor
{
	/*
		Username: args[0]
		DirTo: args[1]
	*/
	public String extractWallpapers(String... args) throws IOException
	{
		StringBuilder status = new StringBuilder();

		status.append("START\n");

		if(args[0] == null || args[0].isEmpty())
		{
			status.append("ERROR: Write the username. Try again.\n");
			return status.toString();
		}

		//Default folder for wallpapers etc.
		File file = new File("C:" + File.separator + "Users" + File.separator + args[0] + File.separator + "AppData" + File.separator + "Local" + File.separator + "Packages" + File.separator + "Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy" + File.separator + "LocalState" + File.separator + "Assets" + File.separator);

		if(!file.exists())
		{
			status.append("ERROR: Default folder for wallpapers does not exist.\nMay be you write wrong username. Try again.");
			return status.toString();
		}

		//There files will be copied, if user does not write any folder
		String defaultDirPath = "C:" + File.separator + "Users" + File.separator + args[0] + File.separator + "Pictures" + File.separator + "defaultWallpapersDir_" + System.currentTimeMillis() + File.separator;

		//There files will be copied
		String dirToPath;

		if(args[1] == null || args[1].isEmpty())
		{
			dirToPath = defaultDirPath;
			status.append("Files will be copied to default path " + defaultDirPath + "\n");
		}
		else
		{
			dirToPath = args[1];

			if(!Character.toString(dirToPath.charAt(dirToPath.length() - 1)).equals(File.separator))
			{
				dirToPath = dirToPath + File.separator;
			}

			status.append("Files will be copied to path " + dirToPath + "\n");
		}

		File dirTo = new File(dirToPath);

		if(!dirTo.exists())
		{
			dirTo.mkdir();
			status.append("Path to copied files was created\n");
		}

		for(File f : file.listFiles())
		{
			Files.copy(f.toPath(), Paths.get(dirToPath + f.getName() + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
			status.append("Copy: " + dirToPath + f.getName() + ".jpg\n");
		}

		status.append("DONE.");

		return status.toString();
	}
}
