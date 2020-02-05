You can use wait, notify and notifyAll methods to communicate between threads in Java. For example, if you have two threads running in your program e.g.Producer and Consumer then producer thread can communicate to the consumer that it can start consuming now because there are items to consume in the queue. Similarly, a consumer thread can tell the producer that it can also start putting items now because there is some space in the queue, which is created as a result of consumption. A thread can use wait() method to pause and do nothing depending upon some condition. For example, in the producer-consumer problem, producer thread should wait if the queue is full and consumer thread should wait if the queue is empty.

If some thread is waiting for some condition to become true, you can use notify and notifyAll methods to inform them that condition is now changed and they can wake up.

Both notify() and notifyAll() method sends a notification but notify sends the notification to only one of the waiting thread, no guarantee which thread will receive notification and notifyAll() sends the notification to all threads.

So if only one thread is waiting for an object lock, also known as a monitor then both notify and notifyAll will send the notification to it. If multiple threads are waiting on a monitor then notify will only inform one of the lucky thread and rest will not receive any notification, but notifyAll will inform all threads.

In this Java multi-threading tutorial you will learn how to use wait, notify and notifyAll() method in Java to implement inter-thread communication by solving the producer-consumer problem.

BTW, if you are serious about mastering concurrency and multi-threading, I strongly suggest you to read Java Concurrency in Practice by Brian Goetz, without reading that book your journey to Java multi-threading is not complete. It's probably one of the most recommended books to Java developers.





How to use wait and notify in code
Even though wait and notify are quite a fundamental concept and they are defined in the object class, surprisingly, it's not easy to write code using wait and notify. You can test this during an interview by asking the candidate to write code to solve producer consumer problem using wait and notify by hand.


I am sure many will be stuck and make mistakes e.g. synchronizing at the wrong place, not calling wait on a right object or not following standard idiom. To be honest its confusing for non-regular coders.

First confusion arise from the fact, how to call wait() method? Since wait method is not defined in Thread class, you cannot simply call Thread.wait(), that won't work but since many Java developers are used to calling Thread.sleep() they try the same thing with wait() method and stuck.

You need to call wait() method on the object which is shared between two threads, in producer-consumer problem its the queue which is shared between producer and consumer threads.

The second confusion comes from the fact that wait needs to be a call from synchronized block or method? So if you use synchronized block, which object should be put to go inside the block? This should be the same object, whose lock you want to acquire i.e. the shared object between multiple threads. In our case it's queue.

How to use wait and notify method in Java with example



Always call wait and notify from Loop Instead of If Block
Once you know that you need to call wait from synchronized context and on the shared object, next thing is to avoid mistake made by several Java developer by calling wait() method inside if block instead of while loop.


Since you call wait inside a conditional block e.g. producer thread should call wait() if queue is full, first instinct goes towards using if block, but calling wait() inside if block can lead to subtle bugs because it's possible for thread to wake up spuriously even when waiting condition is not changed.

If you don't check the condition again after waking up by using a loop, you will take the wrong action which may cause problem e.g. trying to insert item on a full queue or trying to consume from an empty queue. That's why you should always call wait and notify method from a loop and not from if block.

I also suggest reading Effective Java item on the same topic, probably the best reference in how to properly call wait and notify method.




Based upon above knowledge here is  the standard code template or idiom to call wait and notify method in Java :

// The standard idiom for calling the wait method in Java
synchronized (sharedObject) {
   while (condition) {
      sharedObject.wait(); // (Releases lock, and reacquires on wakeup)
   }
   ... // do action based upon condition e.g. take or put into queue
}

As I suggested, you should always invoke wait method from a loop. The loop is used to test the condition before and after waiting. If the condition still holds and the notify (or notifyAll) method has already been invoked before a thread calls wait() method, then there is no guarantee that the thread will ever awake from the wait, potentially causing a deadlock.




Java wait(), notify() and notifyAll() Example
Here is our sample program to demonstrate how to use wait and notify method in Java. In this program, we have used the standard idiom discussed above to call wait(), notify() and notifyAll() method in Java.

In this program, we have two threads named PRODUCER and CONSUMER and implemented using Producer and Consumer class which extends Thread class. The logic of what producer and the consumer should do is written in their respective run() method.

Main thread starts both producer and consumer threads and also create an object of LinkedList class to share as Queue between them. If you don't know LinkedList also implements Queue interface in Java.

Producer runs in an infinite loop and keeps inserting random integer value into Queue until the queue is full. We check this condition at while(queue.size == maxSize), remember before doing this check we synchronize on queue object so that no other thread modify the queue when we are doing this check.

If Queue is full then our PRODUCER thread waits until CONSUMER thread consume one item and make space in your queue and call notify() method to inform PRODUCER thread. Both wait() and notify() method are called on shared object which is queue in our case.

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Simple Java program to demonstrate How to use wait, notify and notifyAll()
 * method in Java by solving producer consumer problem.
 * 
 * @author Javin Paul
 */
public class ProducerConsumerInJava {

    public static void main(String args[]) {
        System.out.println("How to use wait and notify method in Java");
        System.out.println("Solving Producer Consumper Problem");
        
        Queue<Integer> buffer = new LinkedList<>();
        int maxSize = 10;
        
        Thread producer = new Producer(buffer, maxSize, "PRODUCER");
        Thread consumer = new Consumer(buffer, maxSize, "CONSUMER");
        
        producer.start();
        consumer.start();
        

    }

}

/**
 * Producer Thread will keep producing values for Consumer
 * to consumer. It will use wait() method when Queue is full
 * and use notify() method to send notification to Consumer
 * Thread.
 * 
 * @author WINDOWS 8
 *
 */
class Producer extends Thread {
    private Queue<Integer> queue;
    private int maxSize;
    
    public Producer(Queue<Integer> queue, int maxSize, String name){
        super(name);
        this.queue = queue;
        this.maxSize = maxSize;
    }
    
    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == maxSize) {
                    try {
                        System.out .println("Queue is full, "
                                + "Producer thread waiting for "
                                + "consumer to take something from queue");
                        queue.wait();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                Random random = new Random();
                int i = random.nextInt();
                System.out.println("Producing value : " + i);
                queue.add(i);
                queue.notifyAll();
            }

        }
    }
}

/**
 * Consumer Thread will consumer values form shared queue.
 * It will also use wait() method to wait if queue is
 * empty. It will also use notify method to send 
 * notification to producer thread after consuming values
 * from queue.
 * 
 * @author WINDOWS 8
 *
 */
class Consumer extends Thread {
    private Queue<Integer> queue;
    private int maxSize;
    
    public Consumer(Queue<Integer> queue, int maxSize, String name){
        super(name);
        this.queue = queue;
        this.maxSize = maxSize;
    }
    
    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    System.out.println("Queue is empty,"
                            + "Consumer thread is waiting"
                            + " for producer thread to put something in queue");
                    try {
                        queue.wait();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
                System.out.println("Consuming value : " + queue.remove());
                queue.notifyAll();
            }

        }
    }
}

Output
How to use wait and notify method in Java
Solving Producer Consumper Problem
Queue is empty,Consumer thread is waiting for producer thread to put something in queue
Producing value : -1692411980
Producing value : 285310787
Producing value : -1045894970
Producing value : 2140997307
Producing value : 1379699468
Producing value : 912077154
Producing value : -1635438928
Producing value : -500696499
Producing value : -1985700664
Producing value : 961945684
Queue is full, Producer thread waiting for consumer to take something from queue
Consuming value : -1692411980
Consuming value : 285310787
Consuming value : -1045894970
Consuming value : 2140997307
Consuming value : 1379699468
Consuming value : 912077154
Consuming value : -1635438928
Consuming value : -500696499
Consuming value : -1985700664
Consuming value : 961945684
Queue is empty,Consumer thread is waiting for producer thread to put something in queue
Producing value : 1182138498

In order to understand this program better, I suggest you debug it instead of running. Once you start your program in debug mode it will stop at either PRODUCER or CONSUMER thread, depending upon which one thread scheduler chose to give CPU.

Since both threads have wait condition they will go there, now you just run it and see what it does, it will most likely print the output shown above. You can even use Step Into and Step Over buttons in Eclipse to run the program step by step to understand it better.



Things to Remember about Using wait(), notify() and notifyAll() method 
You can use wait() and notify() method to implement inter-thread communication in Java. Not just one or two threads but multiple threads can communicate to each other by using these methods.
Always call wait(), notify() and notifyAll() methods from synchronized method or synchronized block otherwise JVM will throw IllegalMonitorStateException.
Always call wait and notify method from a loop and never from if() block, because loop test waiting condition before and after sleeping and handles notification even if waiting for the condition is not changed.
Always call wait in shared object e.g. shared queue in this example.
Prefer notifyAll() over notify() method due to reasons given in this article
 