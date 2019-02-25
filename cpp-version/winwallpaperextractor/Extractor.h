#pragma once
#include <string>
#include <fstream>
#include <iostream>
#include <cstdlib>
#include <filesystem>
#include <Windows.h>

namespace fs = std::filesystem;

using namespace System;

class Extractor
{

public:
	Extractor() {};
	~Extractor() {};

	String^ extractWallpapers(std::vector<std::string> args)
	{
		String^ status = "";

		status += "START\n";

		if (args[0].empty())
		{
			status += "ERROR: Write the username. Try again.\n";
			return status;
		}

		//Default folder for wallpapers etc.
		std::string defaultFolderForWallpapers = "C:/Users/" + args[0] + "/AppData/Local/Packages/Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy/LocalState/Assets/";

		if (CreateDirectory(defaultFolderForWallpapers.c_str(), NULL))
		{
			status += "ERROR: Default folder for wallpapers does not exist or is corrupted.\nMay be you write wrong username. Try again.";
			return status;
		}

		//There files will be copied, if user does not write any folder
		std::string defaultDirPath = "C:/Users/" + args[0] + "/Pictures/defaultWallpapersDir_" + std::to_string(std::rand())  + "/";

		//There files will be copied
		std::string dirToPath;

		if (args[1].empty())
		{
			dirToPath = defaultDirPath;
				status += "Files will be copied to default path " + gcnew String(defaultDirPath.c_str()) + "\n";		
		}
		else
		{
			dirToPath = args[1];

			//path's end should be "/"
			if (!dirToPath[dirToPath.size() - 1].Equals("/"))
			{
				dirToPath = dirToPath + "/";
			}

			status += "Files will be copied to your path " + gcnew String(dirToPath.c_str()) + "\n";
		}

		if (CreateDirectory(dirToPath.c_str(), NULL))
		{
			status += "Path to copied files was created\n";
		}

		for (const auto &entry : fs::directory_iterator(defaultFolderForWallpapers))
		{
			if (entry.file_size() > 200 * 1024)
			{
				std::string fileName = entry.path().string().substr(defaultFolderForWallpapers.size());
				fs::path newPath = dirToPath + fileName + ".jpg";
				bool isCopied = fs::copy_file(entry.path(), newPath);

				if (isCopied)
				{
					status += "Copy: " + gcnew String(newPath.c_str()) + "\n";
				}				
			}
		}

		status += "DONE.";

		return status;
	}
};