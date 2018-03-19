# GWT Boot Samples

Here you can find some samples on how you can use the GWT Boot Starters in 
your project.

# Quick Start

## Step 1 - Create a Maven Project

Just create a simple Maven Project. Add the parent project and the 
starter dependencies. To be able to compile to JavaScript you
need to add _gwt-maven-plugin_ and add your GWT module name.

```xml
   <parent>
      <groupId>com.github.gwtboot</groupId>
      <artifactId>gwt-boot-starter-parent</artifactId>
      <version>1.0.0</version>
   </parent>
   <dependencies>
      <dependency>
         <groupId>com.github.gwtboot</groupId>
         <artifactId>gwt-boot-starter</artifactId>
      </dependency>
   </dependencies>
   <build>
      <plugins>
         <plugin>
            <groupId>net.ltgt.gwt.maven</groupId>
            <artifactId>gwt-maven-plugin</artifactId>
            <configuration>
               <moduleName>hello.YourEntryPoint</moduleName>
            </configuration>
         </plugin>
      </plugins>
   </build>
```

## Step 2 - Create a GWT Module Descriptor _module.gwt.xml_

Create a GWT module descriptor at _src/main_ directory. In this file
you describe the _EntryPoint_ class which is similar to Java Main class
and method.

```xml
<module rename-to="basic">
   <inherits name="com.google.gwt.user.User"/>
   <entry-point class="hello.client.YourEntryPoint"/>
</module>
```

## Step 3 - Create a Host HTML File where your JavaScript can run

In this HTML file your generated JavaScript will run. The JavaScript
can access the HTML file. In this example the generated JavaScript
will access the div with _id="mainPanel"_. 

```html
<html>
<head>
   <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   <title>Demo GWT Webapp</title>
   <script type="text/javascript" language="javascript" 
      src="basic/basic.nocache.js" async=""></script>
</head>
<body>
   <div id="helloButton"/>
</body>
</html>
```

## Step 4 - Create your Java Entry Point _Main_ Class

The EntryPoint is the first class which will be executed. 
In this example it will exchange the _"helloButton"_ with a
Button.

```java
package hello.client;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.ui.*;

public class YourEntryPoint implements EntryPoint {
   @Override
   public void onModuleLoad() {
      Button button = new Button("Click me");
      button.addClickHandler(clickEvent -> { 
         Window.alert("Hello World!"); 
      });
      RootPanel.get("helloButton").add(button);
   }
}

```

In [gwt-boot-sample-basic](https://github.com/gwtboot/gwt-boot-samples/tree/master/gwt-boot-sample-basic) 
you can take a look at the basic example in GWT.

