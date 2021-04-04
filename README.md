# GWT Boot Samples

[![Build Status](https://travis-ci.org/gwtboot/gwt-boot-samples.svg?branch=master)](https://travis-ci.org/gwtboot/gwt-boot-samples)

Here you can find some samples on how you can use the GWT Boot Starters in 
your project. This quickstart document is based on following sample project: [gwt-boot-sample-basic](https://github.com/gwtboot/gwt-boot-samples/tree/master/gwt-boot-sample-basic).

# IDE

You can use IntelliJ or Eclipse / STS. To be able to use Java APT correctly please take a look on how
to [configure Java APT with Maven](https://immutables.github.io/apt.html) in each development environment.

# Quick Start

## Step 1 - Create a Maven Project

Just create a simple Maven Project. Add the parent project and the 
starter dependencies. To be able to compile to JavaScript you
need to add _gwt-maven-plugin_ and add your GWT module name.

```xml
   <parent>
      <groupId>com.github.gwtboot</groupId>
      <artifactId>gwt-boot-starter-parent</artifactId>
      <version>VERSION</version>
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
               <moduleName>hello.YourModule</moduleName>
               <startupUrls>
                  <url>/basic/</url>
               </startupUrls>
            </configuration>
         </plugin>
      </plugins>
   </build>
```

**If you are using the SNAPSHOT version of the starter** you need to add Sonatype Snapshots repository by extending or creating `<repositories>` section since the `gwtboot-modules` are not released yet, so the example needs to access the SNAPSHOT version of `gwtboot-modules`. 

```xml
   <repositories>
      <repository>
         <id>sonatype-snapshots</id>
         <name>Sonatype Snapshots</name>
         <url>https://oss.sonatype.org/content/repositories/snapshots</url>
         <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
            <checksumPolicy>fail</checksumPolicy>
         </snapshots>
      </repository>
   </repositories>
```

## Step 2 - Create a GWT Module Descriptor _module.gwt.xml_

Create a GWT module descriptor at _src/main_ directory. In this file
you describe the _EntryPoint_ class which is similar to Java Main class
and method. Module rename-to="basic" means that the JavaScript will
be compiled to the script _basic.nocache.js_. This module inherits
everything from the Starter module. This JavaScript
can be imported in the host HTML file on the next step.

```xml
<module rename-to="basic">
   <inherits name="com.github.gwtboot.starter.Starter"/>
   <entry-point class='hello.client.YourEntryPoint'/>
</module>
```

## Step 3 - Create a Host HTML File where your JavaScript can run

In this HTML file, located at _hello/public_, your generated JavaScript will run. 
This JavaScript can access the HTML file. In this example the generated JavaScript
will access the div with _id="helloButton"_. 

```html
<html>
<head>
   <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   <title>Demo GWT Webapp</title>
   <script type="text/javascript" language="javascript" 
      src="basic.nocache.js" async=""></script>
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

Now you are ready to start your GWT basic sample app for the first time.

# Starting GWT in SuperDev Mode

The application _[gwt-boot-sample-basic](https://github.com/gwtboot/gwt-boot-samples/tree/master/gwt-boot-sample-basic)_ 
uses integrated Jetty server from GWT to deliver the HTML host file. 
This can be done with other Servlet app as well.

## Step 1 - Run GWT DevMode to automatically compile the code

First generate the GWT Module Descriptor and then run the GWT Dev Mode 
in SuperDev mode to be able to compile the Java code to JavaScript code 
on reload in the web browser. In Maven you can run following command:

```java
mvn gwt:generate-module gwt:devmode
```

You can just generate the module once and after that just run:

```java
mvn gwt:devmode
```

![GWT Development Mode](gwt-boot-sample-development-mode.png?raw=true "GWT Development Mode")

## Step 2 - Run the App in your Browser

Run it on:

```java
http://localhost:8888/basic
```

Just reload your web app and GWT SuperDev mode will transpile your
Java code to JavaScript on the fly. That's it, now you can develop 
your web app with GWT incrementally and fast! 

## Step 3 - Debug the App in your Browser

You can debug the Java code on the browser with the help of source maps. In this example we use Google Chrome.

![GWT Debug Chrome](gwt-boot-sample-debugging.png?raw=true "GWT Debug Chrome")

Enjoy! 
