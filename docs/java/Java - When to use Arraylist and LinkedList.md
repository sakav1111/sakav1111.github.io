Java - Read Input from Console


we use Scanner class to read user input from Console in Java.
Just like in case of reading file, we have provided File as source for scanning, We need to provide System.in as source to scan for user input in Console. Once you created and initialized java.util.Scanner, you can use its various read method to read input from user. If you want to read String, you can use nextLine(), if you want to read integer numbers, you can use nextInt(). Subsequently you can use nextFloat() to read float input, nextDouble() to read double input etc. Scanner class also allows you to define your own pattern and scan for that.

Here we are reading User Input in form of String using Scanner's nextLine() method and numbers particular integer using nextInt() method of Scanner. Scanner is created by passing System.in  which is a InputStream as source which means it will scan input console for data.


public class UserInputExample {

    public static void main(String args[]) {
 
        //Creating Scanner instance to scan console for User input
        Scanner console = new Scanner(System.in);
   
        System.out.println("System is ready to accept input, please enter name : ");
        String name = console.nextLine();
        System.out.println("Hi " + name + ", Can you enter an int number now?");
        int number = console.nextInt();
        System.out.println("You have entered : " + number);
        System.out.println("Thank you");
     
    }  
 
}

Output:
System is ready to accept input, please enter name :
John
Hi John, Can you enter an int number now?
56
You have entered : 56
Thank you

That's all on How to read user input using Scanner in Java program. Scanner allows you to read various types of input directly from User without extra conversion e.g. you can read int, float, long , double or String directly.