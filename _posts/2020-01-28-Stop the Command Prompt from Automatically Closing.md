---
title: How to - Stop the Command Prompt from Automatically Closing
date: 2019-01-29 0:02:00 Z
categories:
- HowTo
tags:
- HowTo
layout: article
cover: /assets/logo/howto.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---


# How to - Stop the Command Prompt from Automatically Closing

## Manual Way
Press Windows key + R to open the Run window.

Launch the command prompt by typing cmd /K

Adding / K after the command keeps the window open. You can, then, close the window manually when you no longer need it to be open.


## In .bat Files

- paste `cmd /k` command at the end of your batch file

- add `PAUSE` word at the end of your bat file. This will keep the Command Prompt window open until you do not press any key.

- Registry Editor(DANGER)
Third Way
You can prevent Command Prompt window from closing after running commands through adding a Registry Key in the Windows Registry. For this, type the following code in your Notepad and save the file with the  .reg extension.

```bash 
Windows Registry Editor Version 5.00

[HKEY_CLASSES_ROOT\Applications\powershell.exe\shell\open\command]
@="\ "C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe\" –noExit \ "& \\\ "%1\\\ "\""

[HKEY_CLASSES_ROOT\Microsoft.PowerShellScript.1\Shell\0\Command]
@="\ "C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\powershell.exe\ " –NoExit \ "-Command\" \"if ( ( Get-ExecutionPolicy ) –ne ‘AllSigned’) { Set-ExecutionPolicy –Scope Process Bypass }; & \\\ "%1" \\\ "\""
```

After saving the file, double click on it. This registry will save to your Registry Editor of Windows. Now run any command, the Command Prompt window will stay open as long as you will keep it open.