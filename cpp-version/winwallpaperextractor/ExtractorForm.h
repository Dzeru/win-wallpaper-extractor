#pragma once
#include <vector>
#include <string>

namespace winwallpaperextractor {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;
	using namespace std;

	/// <summary>
	/// Summary for ExtractorForm
	/// </summary>
	public ref class ExtractorForm : public System::Windows::Forms::Form
	{
	public:
		ExtractorForm(void)
		{
			InitializeComponent();
			//
			//TODO: Add the constructor code here
			//
		}

	protected:
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		~ExtractorForm()
		{
			if (components)
			{
				delete components;
			}
		}
	private: System::Windows::Forms::Label^  labelUsername;
	protected:
	private: System::Windows::Forms::Label^  labelDirTo;
	private: System::Windows::Forms::TextBox^  textBoxUsername;
	private: System::Windows::Forms::TextBox^  textBoxDirTo;
	private: System::Windows::Forms::Button^  buttonRun;
	private: System::Windows::Forms::RichTextBox^  richTextBoxStatus;

	private:
		/// <summary>
		/// Required designer variable.
		/// </summary>
		System::ComponentModel::Container ^components;

#pragma region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		void InitializeComponent(void)
		{
			this->labelUsername = (gcnew System::Windows::Forms::Label());
			this->labelDirTo = (gcnew System::Windows::Forms::Label());
			this->textBoxUsername = (gcnew System::Windows::Forms::TextBox());
			this->textBoxDirTo = (gcnew System::Windows::Forms::TextBox());
			this->buttonRun = (gcnew System::Windows::Forms::Button());
			this->richTextBoxStatus = (gcnew System::Windows::Forms::RichTextBox());
			this->SuspendLayout();
			// 
			// labelUsername
			// 
			this->labelUsername->AutoSize = true;
			this->labelUsername->Location = System::Drawing::Point(26, 38);
			this->labelUsername->Name = L"labelUsername";
			this->labelUsername->Size = System::Drawing::Size(143, 32);
			this->labelUsername->TabIndex = 0;
			this->labelUsername->Text = L"Username";
			// 
			// labelDirTo
			// 
			this->labelDirTo->AutoSize = true;
			this->labelDirTo->Font = (gcnew System::Drawing::Font(L"Arial", 14));
			this->labelDirTo->Location = System::Drawing::Point(26, 123);
			this->labelDirTo->Name = L"labelDirTo";
			this->labelDirTo->Size = System::Drawing::Size(199, 32);
			this->labelDirTo->TabIndex = 1;
			this->labelDirTo->Text = L"Where to Save";
			// 
			// textBoxUsername
			// 
			this->textBoxUsername->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->textBoxUsername->Location = System::Drawing::Point(273, 38);
			this->textBoxUsername->Name = L"textBoxUsername";
			this->textBoxUsername->Size = System::Drawing::Size(303, 40);
			this->textBoxUsername->TabIndex = 2;
			// 
			// textBoxDirTo
			// 
			this->textBoxDirTo->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->textBoxDirTo->Location = System::Drawing::Point(273, 120);
			this->textBoxDirTo->Name = L"textBoxDirTo";
			this->textBoxDirTo->Size = System::Drawing::Size(303, 40);
			this->textBoxDirTo->TabIndex = 3;
			// 
			// buttonRun
			// 
			this->buttonRun->BackColor = System::Drawing::Color::Aquamarine;
			this->buttonRun->Font = (gcnew System::Drawing::Font(L"Arial", 30));
			this->buttonRun->Location = System::Drawing::Point(32, 202);
			this->buttonRun->Name = L"buttonRun";
			this->buttonRun->Size = System::Drawing::Size(208, 190);
			this->buttonRun->TabIndex = 4;
			this->buttonRun->Text = L"Run";
			this->buttonRun->UseVisualStyleBackColor = false;
			this->buttonRun->Click += gcnew System::EventHandler(this, &ExtractorForm::buttonRun_Click);
			// 
			// richTextBoxStatus
			// 
			this->richTextBoxStatus->BorderStyle = System::Windows::Forms::BorderStyle::FixedSingle;
			this->richTextBoxStatus->Font = (gcnew System::Drawing::Font(L"Arial", 10));
			this->richTextBoxStatus->Location = System::Drawing::Point(273, 202);
			this->richTextBoxStatus->Name = L"richTextBoxStatus";
			this->richTextBoxStatus->ReadOnly = true;
			this->richTextBoxStatus->ScrollBars = System::Windows::Forms::RichTextBoxScrollBars::Vertical;
			this->richTextBoxStatus->Size = System::Drawing::Size(303, 190);
			this->richTextBoxStatus->TabIndex = 5;
			this->richTextBoxStatus->Text = L"";
			// 
			// ExtractorForm
			// 
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::None;
			this->BackColor = System::Drawing::Color::Azure;
			this->ClientSize = System::Drawing::Size(621, 436);
			this->Controls->Add(this->richTextBoxStatus);
			this->Controls->Add(this->buttonRun);
			this->Controls->Add(this->textBoxDirTo);
			this->Controls->Add(this->textBoxUsername);
			this->Controls->Add(this->labelDirTo);
			this->Controls->Add(this->labelUsername);
			this->Font = (gcnew System::Drawing::Font(L"Arial", 14, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->FormBorderStyle = System::Windows::Forms::FormBorderStyle::FixedSingle;
			this->Margin = System::Windows::Forms::Padding(5, 5, 5, 5);
			this->Name = L"ExtractorForm";
			this->Text = L"WallEx";
			this->ResumeLayout(false);
			this->PerformLayout();

		}

#pragma endregion
	private: System::Void buttonRun_Click(System::Object^  sender, System::EventArgs^  e) {
		vector<string> args;
		args.push_back(msclr::interop::marshal_as<string>(this->textBoxUsername->Text));
		args.push_back(msclr::interop::marshal_as<string>(this->textBoxDirTo->Text));

		Extractor* extractor = new Extractor();
		try
		{
			String^ status = extractor->extractWallpapers(args);
			this->richTextBoxStatus->AppendText(status);
		}
		catch (...)
		{
			this->richTextBoxStatus->AppendText("CRITICAL ERROR!");
		}
	}
};
}
