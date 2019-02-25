#include <msclr\marshal_cppstd.h>
#include "Extractor.h"
#include "ExtractorForm.h"

using namespace System;
using namespace System::Windows::Forms;

[STAThreadAttribute]
void Main(array<String^>^ args)
{
	Application::EnableVisualStyles();
	Application::SetCompatibleTextRenderingDefault(false);
	winwallpaperextractor::ExtractorForm form;
	Application::Run(%form);
}