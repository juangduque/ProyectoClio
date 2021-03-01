; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "Proyecto Clio"
#define MyAppVersion "1.2.1"
#define MyAppPublisher "Guio Duke "
#define MyAppExeName "Proyecto clio1.2.1.exe"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{E9833BB4-ABEB-4AF2-8A60-4B8D5442AB02}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
DefaultDirName={pf}\{#MyAppName}
DisableProgramGroupPage=yes
OutputDir=C:\Users\juan-\Documents\GIT proyecto\ProyectoClio\Proyecto Clio v1.2\Clio 1.2 EXE\Instalador
OutputBaseFilename=Setup Proyecto Clio v1.2.1
SetupIconFile=C:\Users\juan-\Documents\GIT proyecto\ProyectoClio\Proyecto Clio v1.2\Clio 1.2 EXE\Icono\clioLogo.ico
Compression=lzma
SolidCompression=yes

[Languages]
Name: "spanish"; MessagesFile: "compiler:Languages\Spanish.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "C:\Users\juan-\Documents\GIT proyecto\ProyectoClio\Proyecto Clio v1.2\Clio 1.2 EXE\Ejecutable\Proyecto clio1.2.1.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\juan-\Documents\GIT proyecto\ProyectoClio\Proyecto Clio v1.2\Clio 1.2 EXE\Complementos\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{commonprograms}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: nowait postinstall skipifsilent
