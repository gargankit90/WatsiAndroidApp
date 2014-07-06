[![Build Status](https://travis-ci.org/rutvijkumarshah/WatsiAndroidApp.svg?branch=master)](https://travis-ci.org/rutvijkumarshah/WatsiAndroidApp)

Watsi Android Application
===============

Android App for Watsi. "Connecting patients to donors."


# Setup Dev Environment

#### Install Gradle
Download Gradle (see specific links below) and then extract the Gradle distribution to a folder, which we will call GRADLE_HOME. Add GRADLE_HOME/bin to your PATH environment variable.

#### Define Environment Variables

Define the ANDROID_HOME environment variable which points to your Android SDK.

```bash
// Unix
export ANDROID_HOME=~/android-sdks

// Windows
set ANDROID_HOME=C:\android-sdks
```

Afterwards, you have to configure the GRADLE_HOME environment variable on your path:

```bash
export GRADLE_HOME=/your_gradle_directory
export PATH=$PATH:$GRADLE_HOME/bin
```

Finally, if you are experimenting with different versions of Gradle, remember to source your path definition file (`source ~/.bash_profile` or wherever your PATH was defined) so that $PATH points to the correct Gradle binary and the old one is no longer in your $PATH. Run `echo $PATH` to confirm.

## Install API 19 and Build Tools

In order for Gradle to work, ensure you have the API 17 SDK installed including the **latest Android SDK Platform-tools and Android SDK Build-tools**. Check this in the Android SDK Manager from within Eclipse. 

## Testing Gradle

Finally, you can check your working installation by running:

```
gradle -v
```

## Build Project
```
git clone WatsiAndroidApp
cd WatsiAndroidApp
gradle clean build
```

## Import Project in Eclipse
1. Import those folders under your project's libs directory, don't import any 'build' folders, they're generated by Gradle
2. Ensure you perform a Project -> Clean on all AAR projects you've added. 



# Adding Third party library

### 1. Adding third party library (JAR) available as gradle dependency
Add dependency in build.gradle,for example
android {
	...   
  dependencies {
          compile "joda-time:joda-time:2.2"
  }
}

### 2. Adding third party library (JAR) NOT available as gradle dependency
copy jar file to locallibs dir

### 3. Adding third party library (AAR) project
Add dependency in build.gradle,for example
android {
	...   
  dependencies {
        compile 'com.actionbarsherlock:actionbarsherlock:4.4.0@aar'
  }
}

### Rebuild Eclipse project
1. Delete Project in eclipse (without deleting source code )
2. run gradle clean build
3. Import project in Eclipse as Android project make sure to select all jars/aar projects
4. Email to team about new dependency so every one will follow all steps under Rebuild Eclipse project