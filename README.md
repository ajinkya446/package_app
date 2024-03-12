# package_app
Native Chat bot AI package module for the development.

## How to Add
There are various steps you need to follow for the .aar creation and publishing.

1. Creation of module in android studio using project structure->Dependencies
2. Create a module named as android library and add that into your dependency in build.gradle
3. Add The required Activities and UI design+Functionality inside that library.
4. Generate artifacts by using assebling gradle command and mention that .aar file in build.gradle
5. Then add required code below inside build.gradle to upload the package on git using maven,
   ``` build.gradle
    publishing {
        publications {
            register<MavenPublication>("release") {
                groupId = "com.ajinkya"
                artifactId = "myapplication"
                version = "2.0"
                artifact("$buildDir/outputs/aar/app-release.aar")
            }
            repositories{
                maven {
                    name = "GitHubPackages"
                    url = uri("URL")
                    credentials {
                        username = "USERID"
                        password ="TOKEN"
                    }
                }
    
            }
        }
    }

   plugins{
       id("maven-publish")
    }
```

6. Publish the .aar package by using the gradle publish command it on github
7. Now, you can import the package inside the build.gradle and add the below code to project level build.gradle
   ``` build.gradle
     url = uri("URL")
        credentials {
            username = "USERID"
            password ="TOKEN"
        }
   ```

#### Note: Don't forget to add the Retrofit dependency in your app/build.gradle
