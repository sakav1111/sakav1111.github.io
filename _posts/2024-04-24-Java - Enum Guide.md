---
title: Java – Enum Guide
date: 2024-04-24 00:00:00 Z
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



An enum is a special "**class**" that represents a group of constants (unchangeable variables, like final variables).

An enum can, just like a class, have attributes and methods. The only difference is that enum constants are public, static and final (unchangeable - cannot be overridden).

An enum cannot be used to create objects, and it cannot extend other classes (but it can implement interfaces).


To create an **enum**, use the enum keyword (instead of class or interface), and separate the constants with a comma. Note that they should be in uppercase letters:

```
enum Level {
  LOW,
  MEDIUM,
  HIGH
}
```

You can access enum constants with the . syntax:
`Level myVar = Level.MEDIUM;`


## Enum with Values & Methods

1. Identify the required aruments
2.Create Enums by passing aruments values
3.Write methods for getting values getDepartmentByCode & getDepartmentValues
4.You can also group enums by **EnumSet**, we also have **EnumMap**
5.You can define methods with default implemetations, which is common for all enums.
6.You can override default method implenetation for specific eums.


```
@AllArgsConstructor
public enum DepartmentEnum {

    //Define Values for Enum with arguments
    IT("IT", "IT Engineering", "ENGINEERING"),
    CSE("CSE", "Computer Science Engineering", "ENGINEERING"),
    ECE("ECE", "Electronics Engineering", "ENGINEERING"),



    MBBS("MBBS", "Bachelor in Medicine", "MEDICINE"){
        @Override
        public void print() {
            System.out.println("MBBS : Override Print : "+this.getLabel());
        }
    },
    MD("MD", "Masters in Medicine", "MEDICINE"){
        @Override
        public void print() {
            System.out.println("MD : Override Print : "+this.getLabel());
        }
    };


    private static EnumSet<DepartmentEnum> engineeringSet =
            EnumSet.of(DepartmentEnum.CSE, DepartmentEnum.IT, DepartmentEnum.ECE);

    private static EnumSet<DepartmentEnum> medicineSet =
            EnumSet.of(DepartmentEnum.MBBS, DepartmentEnum.MD);


    //Define arguments for Enum
    @Getter
    private final String code;
    @Getter
    private final String label;
    @Getter
    private final String field;

    public static DepartmentEnum getDepartmentByCode(String code) {
        return DepartmentEnum.valueOf(code);
    }

    public static List<DepartmentEnum> getDepartmentValues() {
        return Stream.of(DepartmentEnum.values()).collect(Collectors.toList());
    }

    public void print() {
        System.out.println("Default Print");
    }
}
```

Test this code

```
public static void enumChecking(){

    log.info("Printing Enums");
    log.info("CSE : "+DepartmentEnum.CSE.getCode()+", Value: "+DepartmentEnum.CSE.getLabel());
    log.info("ECE : "+DepartmentEnum.ECE.getCode()+", Value: "+DepartmentEnum.ECE.getLabel());


    log.info("Value by code MBBS: "+DepartmentEnum.getDepartmentByCode("MBBS"));

    DepartmentEnum.CSE.print();
    DepartmentEnum.MBBS.print();

}
```


Output
```
Printing Enums
CSE : CSE, Value: Computer Science Engineering
ECE : ECE, Value: Electronics Engineering
Value by code MBBS: MBBS
Default Print
MBBS : Override Print : Bachelor in Medicine
```
I know this is very Basic. But we can use Enums for Implement Design Patterns. Will update this post with Design pattern implementation.

