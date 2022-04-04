## TYPESCRIPT


### What is TypeScript 
* TypeScript is a typed super-set of JavaScript that compiles to plain JavaScript.  
* It's developed by Microsoft.  
* Right now the stable version is 2.3.
* It's used by the major frameworks like `React, Angular, NodeJs express, ASP.NET, React Native, etc`. 


### Installation
Visual Studio 2017 and Visual Studio 2015 Update 3 include TypeScript by default.
Otherwise:  
`npm install -g typescript`
  

### Compiling your code
Typescript file extension is `.ts` (for example: tsc greeter.ts)  

You can compile it doing:
`tsc greeter.ts`
The result will be a file greeter.js
  
    

## 1) Type annotations: 
they introduced the types for variables `(like string, number, boolean, number[] or Array<number>, [string, number], ecc).`  

In this case if you assign a string value to a number variable the compiler will catch an error.
   Example:
```
var name: string = 'Enrico';  // OK
var name: string = 1;         // ERROR
```
There are 5 special types:
1. `any`  - takes all variable value types
    `var power: any;`

2. `null`, `undefined`  - These literals can be assigned to anything
    `var num: number = null;` 

4. `void` 
    ```javascript
    function log(message): void {
        // a function does not have a return type
    }
    ```
 
5.  `never` is the return type for a function that always throws an exception or one that never returns
    ```
    function error(message: string): never {
        throw new Error(message);
    }
    ```

### variable Declaration
use `"let"` instead of `"var"` during variable declaration
Example:
```javascript
let myvar: string;
```
  
  


Use `"const"` for variable that must have immutable/Constant value  
`const foo = 123;`
Type assertion: there are 2 ways:
```
let someValue: any = "this is a string";
let strLength1: number = (<string>someValue).length;
let strLength2: number = (someValue as string).length;
```
   
   <br/><br/> 
   
## 2) Enums: 
   an enum is a way of giving more friendly names to sets of numeric values
   
```
enum Color {Red, Green, Blue}
let c: Color = Color.Green;

or with assigned values
enum Color {Red = 1, Green, Blue}
let c: Color = Color.Green;
```
   
   
   <br/><br/> 
   
## 3) Interfaces: 
   an interface is a particular class that describes an objects.
    Example:
``` 
interface Person {
    firstName: string;
    lastName: string;
}
// Consume (example 1)
function newPartecipant(person: Person): void{
    
}
// Consume (example 2)
var partecipant: Person;
partecipant = {
    firstName: 'John',
    lastName: 'Doe'
};
```

<br/><br/> 


## 4) Classes: 
support for class-based object-oriented programming. Every class will have a **constructor** and a few **public fields**.  

When you declare constructor arguments, if you use "public" keyword before the argument will allow Typescript to automatically create properties with that name.
```
class Student {
    fullName: string;
    
    constructor(public firstName, public middleInitial, public lastName) {
        this.fullName = firstName + " " + middleInitial + " " + lastName;
    }
}
// Consume
var user = new Student("Jane", "M.", "User");
```


  
It's possible also to extend a class (so you will inherit all its functions\properties)
```
class ExtendedAdder extends Adder {
    ....
}
```

<br/><br/> 



## 5) Functions
```
function buildName(firstName: string, lastName = "Smith") : string {
    return firstName + " " + lastName;
}
```
As argument you can have also this variants:
lastName?: string    // Optional parameter  
lastName = "Smith"   // Assign default value  

### Arrow function  
This symbol is =>
`"this`" is a variable that’s set when a function is called. This makes it a very powerful and flexible feature, but it comes at the cost of always having to know about the context that a function is executing in. 

This is notoriously confusing, especially when returning a function or passing a function as an argument.


The scope of Arrow functions is to replace the usage of `"this"`.

Arrow functions capture the this where the function is created rather than where it is invoked.
()=>something
1. You don't need to keep typing function
2. It lexically captures the meaning of this
3. It lexically captures the meaning of arguments
For example this function increments a value X of 1 unity:
```
var inc = (x)=>x+1;
```
Altro esempio di utilizzo:
```
class Adder {
    constructor(public a: number) {}
    // This function is now safe to pass around
    add = (b: number): number => {
        return this.a + b;
    }
}
```


<br/><br/> 


## 6) Generics
A major part of software engineering is building components that not only have well-defined and consistent APIs, but are also reusable.

Components that are capable of working on the data of today as well as the data of tomorrow will give you the most flexible capabilities for building up large software systems.

An example of a function that works with any type of data is the following:
```
function identity(arg: any): any {
    return arg;
}
```
in this case we don't used generics type.  

The problem is that using "any" we are LOSING the information about what that TYPE was when the function returns.  

The right way to write this generic function is the following:
```
function identity<T>(arg: T): T {
    return arg;
}
```
This T allows us to capture the type the user provides (e.g. number)
```
// Consume example
let output = identity<string>("myString");
```


The generic is applicable not only to function arguments but also to classes.
For example:
```
class GenericNumber<T> {
    zeroValue: T;
    add: (x: T, y: T) => T;
}
let myGenericNumber = new GenericNumber<number>();
myGenericNumber.zeroValue = 0;
myGenericNumber.add = function(x, y) { return x + y; };
```

<br/><br/> 


## 7) Symbol: a primitive data type
Symbol is introduced starting from ECMAScript 2015 and it is a primitive data type, just like number and string.
Symbols are immutable, and unique so you can use it as ID\KEYS for object properties.
```
let sym = Symbol();
let obj = {
    [sym]: "value"
};
```
## 8) Modules
Modules are executed within their own scope, not in the global scope; this means that variables, functions, classes, etc. declared in a module are not visible outside the module unless they are explicitly exported using one of the export forms.
Modules are declarative; the relationships between modules are specified in terms of imports and exports at the file level.
If you export a module, other modules will able to use it using keywords "implements"... For example:
// EXPORT
```
export interface StringValidator {
    isAcceptable(s: string): boolean;
}
export const numberRegexp = /^[0-9]+$/;
export class ZipCodeValidator implements StringValidator {
    isAcceptable(s: string) {
        return s.length === 5 && numberRegexp.test(s);
    }
}
```
// IMPORT
```
import { ZipCodeValidator } from "./ZipCodeValidator";
let myValidator = new ZipCodeValidator();
```
9) Namespaces
To better organize the source code, instead of putting lots of different names into the global namespace, let’s wrap up our objects into a namespace.
Example:
```
namespace Validation {
    export interface StringValidator {
        isAcceptable(s: string): boolean;
    }
    const lettersRegexp = /^[A-Za-z]+$/;
    const numberRegexp = /^[0-9]+$/;
    export class LettersOnlyValidator implements StringValidator {
        isAcceptable(s: string) {
            return lettersRegexp.test(s);
        }
    }
    export class ZipCodeValidator implements StringValidator {
        isAcceptable(s: string) {
            return s.length === 5 && numberRegexp.test(s);
        }
    }
}
```
// CONSUME
```
// Some samples to try
let strings = ["Hello", "98052", "101"];
// Validators to use
let validators: { [s: string]: Validation.StringValidator; } = {};
validators["ZIP code"] = new Validation.ZipCodeValidator();
validators["Letters only"] = new Validation.LettersOnlyValidator();
```
