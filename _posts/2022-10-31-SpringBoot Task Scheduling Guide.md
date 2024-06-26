---
title: SpringBoot Task Scheduling Guide
date: 2022-10-31 00:00:00 Z
categories:
- SpringBoot
tags:
- SpringBoot
layout: article
cover: /assets/logo/springboot.png
sharing: true
license: false
aside:
  toc: true
pageview: true
---

# SpringBoot Task Scheduling Guide

Spring Boot using `@Scheduled` annotation for Task Scheduling.
The `@Scheduled` annotation is added to a method along with some information about when to execute it, and Spring Boot takes care of the rest.

Spring Boot internally uses the [`TaskScheduler`](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/TaskScheduler.html) interface for scheduling the annotated methods for execution.

The purpose of this article is to build a simple project demonstrating all the concepts related to task scheduling.

Create the Project
--------------------------------------------------------------------------------------------------------------------------
<img width="756" alt="image" src="https://user-images.githubusercontent.com/20472904/199000567-f83a7fa3-d49f-4833-a370-bf894889047c.png">


Enable Scheduling
------------------------------------------------------------------------------------------------------------------------

You can enable scheduling simply by adding the `@EnableScheduling` annotation to the main application class or one of the Configuration classes.

Open `SchedulerDemoApplication.java` and add `@EnableScheduling` annotation like so -

```java
package com.example.schedulerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SchedulerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerDemoApplication.class, args);
	}
}
```

Scheduling Tasks
----------------------------------------------------------------------------------------------------------------------

Scheduling a task with Spring Boot is as simple as annotating a method with `@Scheduled` annotation, and providing few parameters that will be used to decide the time at which the task will run.

Before adding tasks, Let's first create the container for all the scheduled tasks. Create a new class called `ScheduledTasks` inside `com.example.schedulerdemo` package with the following contents -

```java
package com.example.schedulerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void scheduleTaskWithFixedRate() {}

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
}
```

The class contains four empty methods. We'll look at the implementation of all the methods one by one.

All the scheduled methods should follow the following two criteria -

-   The method should have a void return type.
-   The method should not accept any arguments.

Cool! Let's now jump into the implementation.

### 1\. Scheduling a Task with Fixed Rate

You can schedule a method to be executed at a fixed interval by using `fixedRate` parameter in the `@Scheduled` annotation. In the following example, The annotated method will be executed every 2 seconds.

```java
@Scheduled(fixedRate = 2000)
public void scheduleTaskWithFixedRate() {
    logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
}
```

```
# Sample Output
Fixed Rate Task :: Execution Time - 10:26:58
Fixed Rate Task :: Execution Time - 10:27:00
Fixed Rate Task :: Execution Time - 10:27:02
....
....
```

The `fixedRate` task is invoked at the specified interval even if the previous invocation of the task is not finished.

### 2. Fixed Delay

You can execute a task with a fixed delay between the completion of the last invocation and the start of the next, using `fixedDelay` parameter.

The `fixedDelay` parameter counts the delay after the completion of the last invocation.

Consider the following example -

```java
@Scheduled(fixedDelay = 2000)
public void scheduleTaskWithFixedDelay() {
    logger.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    try {
        TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException ex) {
        logger.error("Ran into an error {}", ex);
        throw new IllegalStateException(ex);
    }
}
```

Since the task itself takes 5 seconds to complete and we have specified a delay of 2 seconds between the completion of the last invocation and the start of the next, there will be a delay of 7 seconds between each invocation -

```
# Sample Output
Fixed Delay Task :: Execution Time - 10:30:01
Fixed Delay Task :: Execution Time - 10:30:08
Fixed Delay Task :: Execution Time - 10:30:15
....
....
```

### 3. Scheduling a Task With Fixed Rate and Initial Delay

You can use `initialDelay` parameter with `fixedRate` and `fixedDelay` to delay the first execution of the task with the specified number of milliseconds.

In the following example, the first execution of the task will be delayed by 5 seconds and then it will be executed normally at a fixed interval of 2 seconds -

```java
@Scheduled(fixedRate = 2000, initialDelay = 5000)
public void scheduleTaskWithInitialDelay() {
    logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
}
```

```
# Sample output (Server Started at 10:48:46)
Fixed Rate Task with Initial Delay :: Execution Time - 10:48:51
Fixed Rate Task with Initial Delay :: Execution Time - 10:48:53
Fixed Rate Task with Initial Delay :: Execution Time - 10:48:55
....
....
```

### 4. Scheduling a Task using Cron Expression

If the above simple parameters can not fulfill your needs, then you can use cron expressions to schedule the execution of your tasks.

In the following example, I have scheduled the task to be executed every minute -

```java
@Scheduled(cron = "0 * * * * ?")
public void scheduleTaskWithCronExpression() {
    logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
}
```

```
# Sample Output
Cron Task :: Execution Time - 11:03:00
Cron Task :: Execution Time - 11:04:00
Cron Task :: Execution Time - 11:05:00

```

Running @Scheduled Tasks in a Custom Thread Pool
-------------------------------------------------------------------------------------------------------------------------

By default, all the `@Scheduled` tasks are executed in a default thread pool of size one created by Spring.

You can verify that by logging the name of the current thread in all the methods -

```
logger.info("Current Thread : {}", Thread.currentThread().getName());
```

All the methods will print the following -

```
Current Thread : pool-1-thread-1
```

But hey, You can create your own thread pool and configure Spring to use that thread pool for executing all the scheduled tasks.

Create a new package `config` inside `com.example.schedulerdemo`, and then create a new class called `SchedulerConfig` inside `config` package with the following contents -

```
package com.example.schedulerdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class SchedulerConfig implements SchedulingConfigurer {
    private final int POOL_SIZE = 10;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("my-scheduled-task-pool-");
        threadPoolTaskScheduler.initialize();

        scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }
}
```

That's all you need to do for configuring Spring to use your own thread pool instead of the default one.

If you log the name of the current thread in the scheduled methods now, you'll get the output like so -

```
Current Thread : my-scheduled-task-pool-1
Current Thread : my-scheduled-task-pool-2

# etc...
```


Spring Boot Multi Thread Scheduling
===================================
Spring Boot provides impressive [scheduling](https://spring.io/guides/gs/scheduling-tasks/) functionality out-of-the-box. To install it all you need to do is include the Spring Boot Starter dependency in your Maven project:


**Configuration**

By default Spring Boot will use just a single thread for all scheduled tasks to run on. This is not ideal, because these tasks will be blocking. Instead we will configure the scheduler to run each scheduled tasks on a separate thread (if there is enough threads available).

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * Configures the scheduler to allow multiple concurrent pools.
 * Prevents blocking.
 */
@Configuration
public class SchedulerConfig implements SchedulingConfigurer
{
    /**
     * The pool size.
     */
    private final int POOL_SIZE = 10;

    /**
     * Configures the scheduler to allow multiple pools.
     *
     * @param taskRegistrar The task registrar.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar)
    {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("scheduled-task-pool-");
        threadPoolTaskScheduler.initialize();

        taskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }
}

```

In the above example, we have created a new configuration class which extends *SchedulingConfigurer*. This has allowed us to configure a task scheduler, and pass in the pool size we want to use.

**Scheduling tasks**

To create a scheduled task, all you need to do is annotate a method as follows:

```java
@Scheduled(fixedRate = 60000, initialDelay = 60000)
public void databaseCleanup()
{
    databaseCleanupService.clean();
}

```

In the above example, the *databaseCleanup()* method will be called once every minute, with an initial delay (after the application has started) of 1 minute. Instead of a fixed rate, you could instead use a cron expression.



# Dynamic Scheduler Configuration

```java
@Scheduled(cron = "0 * * * * ?")
public void scheduleTaskWithCronExpression() {
    logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
}
```
In the above code, we statically defined Cron Expression. If you want to pass this Cron dynamically we can do by using TaskSchedular


```java
@ApiOperation("Email All Employees Data on Scheduled Time")
@PostMapping("/schedule")
public void scheduleTask(@RequestBody RequestDto requestDto) {
    employeeService.scheduleTask(requestDto.getCron());
}

@Service
@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private TaskScheduler taskScheduler;

    private Map<String, ScheduledFuture<?>> scheduledTasks;


    @Override
    public void scheduleTask(String cron) {
//Parameter 2 of constructor in com.employee.service.impl.EmployeeServiceImpl required a bean of type 'org.springframework.scheduling.TaskScheduler' that could not be found.
        //Solution : @EnableScheduling at Application.java
        String taskId = "1";

        if (!CollectionUtils.isEmpty(scheduledTasks)) {
            taskId = (scheduledTasks.size()) + "";
        }

        EmailDto emailDto = getEmailData();
        Runnable task = () -> {
            try {
                emailUtil.sendMail(emailDto);
            } catch (JobExecutionException e) {
                throw new RuntimeException(e);
            }
        };
        ScheduledFuture<?> future = taskScheduler.schedule(task, new CronTrigger(cron));
        scheduledTasks.put(taskId, future);
        log.info("Task ID ", taskId);
    }


    private EmailDto getEmailData() {
        EmailDto emailDto = EmailDto.builder()
                .from("satyakaveti@gmail.com")
                .subject("All Emp Data" + new Date())
                .to(new String[]{"satyakaveti@gmail.com"})
                .cc(new String[]{"satyakaveti@gmail.com"})
                .attachmentLinks(null).build();
        StringBuilder body = new StringBuilder();
        body.append("All Employees data as of ").append(new Date().toString());
        body.append(" ===================================== ");
        List<Employee> emps = employeeRepository.findAll();
        for (Employee emp : emps) {
            EmployeeDto dto = employeeMapper.toDto(emp);
            body.append(dto.toString()).append(" <br>");
        }
        emailDto.setBody(body.toString());
        return emailDto;
    }

}
```
Here we need to pass Cron Expression to CronTrigger(cron)


```java
ScheduledFuture<?> future = taskScheduler.schedule(task, new CronTrigger(cron));
        scheduledTasks.put(taskId, future);
```

```
//Payload
{
  "cron": "*/5 * * * * ?"
}
```

