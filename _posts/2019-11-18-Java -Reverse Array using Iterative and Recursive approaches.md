---
title: Java - Reverse Array using Iterative and Recursive
date: 2019-011-11 00:00:00 Z
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

Arrays - Reverse Array using Iterative and Recursive approaches
===============================================================

1.initialize array 

2.Choose **start** index 

3.Choose **end** index 

4.Swap the elements using **temp** variable 

```java
package array;

public class ReverseArry {

	/*
	 * Recursive approach: In recursive approach the function calls itself until
	 * the condition is met. And it is slower than iteration,which means it uses
	 * more memory than iteration. recursion is like a selection structure, and
	 * which makes code smaller and clean. And a function partially defined by
	 * itself. Here tracing the code will be more difficult in the case large
	 * programs
	 */

	public static int[] recursiveArry(int a[], int start, int end) {
		if (start <= end) {
			int temp;
			temp = a[start];
			a[start] = a[end];
			a[end] = temp;
			recursiveArry(a, start + 1, end - 1);
		}
		return a;
	}

	
	/*
	 *Iterative approach: Iterative approach is a repetition process until the condition fails.
	 *here loops are used such as for ,while etc. Here code may be longer but it is faster than recursive. 
	 *And it consumes less memory compared to recursive approach.
	 *If the loop condition is always true in such cases it will be an infinite loop.
	 * 
	 * */	
	public static int[] iteravtiveArray(int a[], int start, int end) {
		while(start<end)
		{
			int temp;
			temp = a[start];
			a[start]=a[end];
			a[end]=temp;			
			start++;
			end--;
		}		
		
		return a;
	}
	
	
	
	 
	static void printArray(int arr[], int size) {
		int i;
		for (i = 0; i < size; i++)
			System.out.print(arr[i] + " ");
		System.out.println("");
	}

	 
	public static void main(String[] args) {
		
                // 1. Initialize array
                int arr[] = { 1, 2, 3, 4, 5, 6 };
		System.out.println("Input array is ");
		printArray(arr, 6);
	
		// 2. Choose Starting & ending point
		int b[] = recursiveArry(arr, 0, 5);
		System.out.println("Recursive -Reversed array is ");
		printArray(b, 6);
		
		int c[] = iteravtiveArray(arr, 0, 5);
		System.out.println("IteravtiveArray -Reversed array is ");
		printArray(c, 6);
	}
}
----------------------------------------------- 
Input array is 
1 2 3 4 5 6 
Recursive -Reversed array is 
6 5 4 3 2 1
```
