# Documenetation

### Word to Markdown
#### 1. Writage : https://www.writage.com/

After Install this, open doc `Save As ` markdown option will come Automatically.

If Trail Expire, follow Below Steps
* Unintsall Wrirage
* Remove `C:\Program Files (x86)\Writage` folder
* Delete Writage data in `C:\Users\<Username>\AppData`.
* Open `regedit` & delete all entries of Writage.
* Restrat once. 
* Install agian,...use it for another 14 days






#### 2. Pandoc -  Install Run below cmd
```node
pandoc -s "<name>.docx" -t markdown -o "<name>.md"
```
Here : Images won't exatrats
Ref: https://pandoc.org/demos.html



 ### Build Errors Fix
 
 Find and Replace below symbols in all .md files with Notepad++
 ```java
‹
œ
™
€
Â
```



## Tutorails Making
Tutorials

 1.Open word, Change Headings to H1.
-	Remove, if two headings are there next to next
-	Remove, If any special. chars in Heading. remove them
  
  
  2. Kutools Plus > Split > select heading1
•	(if anything Break, Open word from that particular heading & Split again, 
-	it occurs when if two headings are there next to next
-	If any special. chars in Heading. remove them
    
  3.After Split complete save them as .md using Word, BreakData
  
  4.Once save done, copy .md files to a separate folder & Run below command for "file names"
 Display Folder content in Tree Format
    `dir /s /b /o:gn>list.txt`
	
 5.open `list.txt` replace `.md` with nothing
 
 6.arrage filenames by numbers order.
 
 7.Change in `FileNames.java` - 	 folder, category, tag, meta before running java file
 
 8.Make sure `FileNames.java & list.txt` in same folder - Run Java File
 
 9.open Generated `navigation.txt & post.txt` - Make sure URL's are same in Both files.
 
 10.follow commndline output to Create Menu for Tutorials
```
	_config.yaml location ----> {root_folder}/_config.yml
	 navigation.yaml ---------> {root_folder}/_data/navigation.yaml
```


















 

## Documentation
### Theme : [TeXt Theme](https://github.com/kitian616/jekyll-TeXt-theme)
 
### Start

- [Quick Start](https://tianqi.name/jekyll-TeXt-theme/docs/en/quick-start)
- [Update from 1.x to 2.x](https://tianqi.name/jekyll-TeXt-theme/docs/en/update-from-1-to-2)

### Customization

- [Configuration](https://tianqi.name/jekyll-TeXt-theme/docs/en/configuration)
- [Navigation](https://tianqi.name/jekyll-TeXt-theme/docs/en/navigation)
- [Layouts](https://tianqi.name/jekyll-TeXt-theme/docs/en/layouts)
- [Logo and Favicon](https://tianqi.name/jekyll-TeXt-theme/docs/en/logo-and-favicon)
- [Authors](https://tianqi.name/jekyll-TeXt-theme/docs/en/authors)
- [Internationalization](https://tianqi.name/jekyll-TeXt-theme/docs/en/i18n)

### Content

- [Writing Posts](https://tianqi.name/jekyll-TeXt-theme/docs/en/writing-posts)
- [Additional styles](https://tianqi.name/jekyll-TeXt-theme/docs/en/additional-styles)
- [Extensions](https://tianqi.name/jekyll-TeXt-theme/docs/en/extensions)
- [Markdown Enhancements](https://tianqi.name/jekyll-TeXt-theme/docs/en/markdown-enhancements)

## Demo Pages

| Name | Description |
| --- | --- |
| [Home](https://tianqi.name/jekyll-TeXt-theme/test/) | Home page |
| [Archive](https://tianqi.name/jekyll-TeXt-theme/archive.html) | Archive page |
| [Layout Examples](https://tianqi.name/jekyll-TeXt-theme/samples.html) | Examples for different layouts |
 