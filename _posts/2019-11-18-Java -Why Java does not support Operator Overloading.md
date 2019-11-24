---
title: Java - Why Java does not support Operator Overloading
date: 2017-011-11 00:00:00 Z
categories:
- Java
tags:
- Java
layout: article
cover: /assets/logo/java.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---


# **Why Java does not support Operator Overloading?**

**What is Operator Overloading ?**

The meaning of an operator is always same for variable of basic types like: int,
float, double etc. For example: To add two integers,*+*operator is used.



However, for user-defined types (like: objects), you can redefine the way
operator works. For example:

If there are two objects of a class that contains string as its data members.
You can redefine the meaning of + operator and use it to concatenate those
strings.



To overload an operator, a special operator function is defined inside the class
as

```cpp
class className
{
    ... .. ...
    public
       returnType operator symbol (arguments)
       {
           ... .. ...
       } 
    ... .. ...
};
#include <iostream>
using namespace std;

class Test
{
   private:
      int count;

   public:
       Test(): count(5){}

       void operator ++() 
       { 
          count = count+1; 
       }
       void Display() { cout<<"Count: "<<count; }
};

int main()
{
    Test t;
    // this calls "function void operator ++()" function
    ++t;    
    t.Display();
    return 0;
}
```

-   This function is called when*++*operator operates on the object
    of*Test*class (object*t*in this case).

-   In the program,void operator ++ ()operator function is defined
    (inside*Test*class).

-   This function increments the value of*count*by 1 for*t*object.

<br>

# **Why Java Doesn't Support it?**



**1.Java is Simple, No Confusions Please !!**  
Java does not support operator overloading: Java is relatively a very simple
language to use compared C/C++ with the non-support of complex and confusing
features like**pointers**,**multiple inheritance**and**operator
overloading**. These features are rarely used in practice and at the same time
poorly understood by the language beginners.



**2.JVM Performance : How many things Should i do?**  
Form JVM perspective supporting operator overloading is more difficult and if
the same thing can be achieved by using method overloading in more intuitive and
clean way it does make sense to not support operator overloading in java. a
complex JVM will result in slower JVM


