---
title: Spring Boot – Ways to Read SpringBoot application.properties values
date: 2024-04-28 00:00:00 Z
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

In a Spring Boot application, you can read values from the `application.properties` file in several ways, depending on your specific requirements and preferences. Here are some common methods:

1.  Using `@Value` Annotation: You can inject property values directly into your beans using the `@Value` annotation.

```
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Component;

    @Component
    public class MyComponent {

        @Value("${my.property}")
        private String myProperty;

        // Getter and setter
    }
```



2.  Using `Environment`: You can use the `Environment` interface to access properties.

  
  ```
   import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.core.env.Environment;
    import org.springframework.stereotype.Component;

    @Component
    public class MyComponent {

        @Autowired
        private Environment environment;

        public void someMethod() {
            String propertyValue = environment.getProperty("my.property");
            // Use propertyValue
        }
    }
```


3.  Using `@ConfigurationProperties` Annotation: You can bind the entire `application.properties` file or specific sections of it to a Java bean using the `@ConfigurationProperties` annotation.
   
```
    import org.springframework.boot.context.properties.ConfigurationProperties;
    import org.springframework.stereotype.Component;

    @Component
    @ConfigurationProperties(prefix = "my")
    public class MyProperties {

        private String property;

        // Getter and setter
    }
```

    And then, you can inject this bean wherever needed:


```
    @Autowired
    private MyProperties myProperties;
```



4.  Using `@PropertySource` and `@Value`: You can use `@PropertySource` to specify the properties file and then use `@Value` as shown in the first method.

```
    import org.springframework.context.annotation.PropertySource;
    import org.springframework.stereotype.Component;
    import org.springframework.beans.factory.annotation.Value;

    @Component
    @PropertySource("classpath:my.properties")
    public class MyComponent {

        @Value("${my.property}")
        private String myProperty;

        // Getter and setter
    }
  ```

These are some common ways, each with its advantages depending on the complexity of your application and personal preference.