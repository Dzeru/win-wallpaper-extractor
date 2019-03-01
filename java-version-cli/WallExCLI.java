import java.io.*;
import java.util.*;
import java.nio.file.*;

public class WallExCLI
{
	/*
		Username args[0]
		DirTo args[1]
		*/
	public static void main(String... args) throws IOException
	{	
		System.out.println("WallEx - Windows 10 Wallpaper Extractor");
		System.out.println("Author is Dzeru, 2019");
		System.out.println("Use GNU General Public License v3.0");
		System.out.println("");
		
		System.out.println("START");
		
		if(args.length == 0)
		{
			System.err.println("ERROR: Write the username. Try again.");
			return;
		}
		
		//Default folder for wallpapers etc.
		File file = new File("C:" + File.separator + "Users" + File.separator + args[0] + File.separator + "AppData" + File.separator + "Local" + File.separator + "Packages" + File.separator + "Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy" + File.separator + "LocalState" + File.separator + "Assets" + File.separator);
		
		if(!file.exists())
		{
			System.err.println("ERROR: Default folder for wallpapers does not exist. May be you write wrong username. Try again.");
			return;
		}
		
		//There files will be copied, if user does not write any folder
		String defaultDirPath = "C:" + File.separator + "Users" + File.separator + args[0] + File.separator + "Pictures" + File.separator + "defaultWallpapersDir_" + System.currentTimeMillis() + File.separator;
		
		//There files will be copied
		String dirToPath;
		
		if(args.length == 1)
		{
			dirToPath = defaultDirPath;
			System.out.println("Files will be copied to default path " + defaultDirPath);
		}
		else
		{
			dirToPath = args[1];
		
			if(!Character.toString(dirToPath.charAt(dirToPath.length() - 1)).equals(File.separator))
			{
				dirToPath = dirToPath + File.separator;
			}
			
			System.out.println("Files will be copied to path " + dirToPath);
		}
		
		File dirTo = new File(dirToPath);
		
		if(!dirTo.exists())
		{
			dirTo.mkdir();
			System.out.println("Path to copied files was created");
		}
		
		for(File f : file.listFiles())
		{
			if(f.length() > 8 * 200)
			{
				Files.copy(f.toPath(), Paths.get(dirToPath + f.getName() + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Copy: " + dirToPath + f.getName() + ".jpg");
			}		
		}
	
		System.out.println("DONE.");
	}
}