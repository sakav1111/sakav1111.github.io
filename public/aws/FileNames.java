import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/*
  
 1.Open word, Change Headings to H1.
  - Remove , if two headings are there nextr to next
 - Remove , If any special. chars in Heading. remove them
  
  
 2. Kutools Plus > Split > select heading1
 (if any thing Break, Open word from that pertclular heading & Split again, 
 - it occurs when if two headings are there nextr to next
 - If any special. chars in Heading. remove them
  
  
  3.After Split complete save them as .md using WordBreakData
  
  4.Once save done, copy .md files to a seperate folder & Run below command for "file names"
 Display Folder content in Tree Format
    dir /s /b /o:gn>list.txt
	
 5.open list.txt replace .md with nothing
 
 6.arrage filenames by numbers order.
 
 7.Change in FileNames.java - 	 folder,category, tag, meta before running java file
 
 8.Make sure FileNames.java & list.txt in same folder - Run Java File
 
 9.open Generated navigation.txt & post.txt - Make sure URL's are same in Both files.
 
 10.follow commndline output to Create Menu for Tutorials
	_config.yaml location ----> {root_folder}/_config.yml
	 navigation.yaml ---------> {root_folder}/_data/navigation.yaml
 * */

public class FileNames {

	public static void main(String[] args) {

		navigation();

		System.out
				.println("1.navigation.yaml - add Menu, Childern {Title, URL} in sidemenu #SQL Tutorials - Sidemenu\r\n"
						+ "SQLMenu:\r\n" + "  - title:      SQL Tutorial\r\n" + "    children:\r\n"
						+ "      - title:  SQL Basics\r\n" + "        url:    /sql/sql-basics \n");

		System.out.println("2.navigation.yaml - add " + "\n sidebar:\r\n" + "  nav: Java\r\n" + "  nav: SQLMenu \n");

		System.out.println("3.Create new folder for Posts, place Posts inside that \n ");

		System.out.println("4._config.yml : \n add -Scope Properties \n " + " ## SpringBoot Tutorial       \r\n"
				+ "  - scope:\r\n" + "      path: \"springboot\"\r\n" + "    values:\r\n" + "      layout: article\r\n"
				+ "      nav_key: java\n");

		System.out.println("5.Open Each Post & add : \n ---\r\n" + "title: \r\n" + "permalink: /sql/\r\n"
				+ "key: java-\r\n" + "categories: [Java]\r\n" + "tags: [Java]\r\n" + "---");
	}

	public static void navigation() {

		String title = "      - title:  ";
		String url = "        url:    ";
		
		//****** 1.change here
		String folder = "/aws/";
		StringBuffer navsb = new StringBuffer();
		url = url + folder;

		StringBuffer postsb = new StringBuffer();
		postsb.append(System.getProperty("line.separator"));

		//****** 2.change here - 3 places
		String category = "aws";
		String tag = "aws";
		String meta = "AWS- ";

		/*
		 * --- title: Quick Start permalink: /docs/en/quick-start key: docs-quick-start
		 * categories: [Java] tags: [Java] ---
		 */

		try {

			System.out.println("========================\n    navigation.yml   \n====================");

			Scanner scanner = new Scanner(new File("list.txt"));
			while (scanner.hasNextLine()) {

				String name = scanner.nextLine();
				name = name.substring(name.lastIndexOf(".") + 1).trim();
				//	name = name.substring(name.indexOf(".") + 1).trim();
				// System.out.println(name);

				String titleline = title + name;
				// System.out.println(titleline);
				navsb.append(titleline);
				navsb.append(System.getProperty("line.separator"));
				postsb.append("---").append(System.getProperty("line.separator"));
				postsb.append("title: " + meta + name).append(System.getProperty("line.separator"));

				String utltemp = name.replace(' ', '-');

				postsb.append("permalink: " + folder + (utltemp.toLowerCase()))
						.append(System.getProperty("line.separator"));
				postsb.append("key: " + (category + "-" + utltemp).toLowerCase())
						.append(System.getProperty("line.separator"));
				postsb.append("categories:").append(System.getProperty("line.separator")).append("- "+category).append(System.getProperty("line.separator"));
				postsb.append("tags:").append(System.getProperty("line.separator")).append("- "+tag).append(System.getProperty("line.separator"));
				//postsb.append("tags: [" + tag + "]").append(System.getProperty("line.separator"));
				postsb.append("---").append(System.getProperty("line.separator"));

				// separtaing last
				postsb.append(System.getProperty("line.separator")).append(System.getProperty("line.separator"))
						.append(System.getProperty("line.separator"));

				String urlline = url + (utltemp.toLowerCase());
				// System.out.println(urlline);
				navsb.append(urlline);
				navsb.append(System.getProperty("line.separator"));

			}
			System.out.println(navsb);
			System.out.println(postsb);

			scanner.close();

			BufferedWriter navbuf = new BufferedWriter(new FileWriter(new File("navigation.txt")));
			navbuf.write(navsb.toString());
			navbuf.flush();

			BufferedWriter postbuf = new BufferedWriter(new FileWriter(new File("post.txt")));
			postbuf.write(postsb.toString());
			postbuf.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}