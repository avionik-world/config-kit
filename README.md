# ConfigKit
In this project, you can register Bukkit listeners and add one or more requirements. A "requirement" is a condition that must be fulfilled for this event to be executed.

## Using ConfigKit in your plugin

### Maven
```xml
<repositories>
  <repository>
     <id>github</id>
     <url>https://maven.pkg.github.com/avionik-world/config-kit</url>
   </repository>
</repositories>
```

```xml
<dependencies>
 <dependency>
    <groupId>world.avionik</groupId>
    <artifactId>config-kit</artifactId>
    <version>1.0.1</version>
    <scope>provided</scope>
  </dependency>
</dependencies>
```

### Gradle
```groovy
repositories {
    maven { url = 'https://maven.pkg.github.com/avionik-world/config-kit' }
}
```

```groovy
dependencies {
    compileOnly 'world.avionik:config-kit:1.0.1'
}
```

## How to create a ConfigLoader
First of all, you need to decide which format you want to use. We support Json and Yaml. Then you need a class that you save in the appropriate format. Then you have to think about which format you want to use. For example, let's take this config:
```kotlin
class TestConfig(
    val firstString: String,
    val secondList: List<String>
)
```
**Note:** If you want to save as a Yaml file, you have to write via the class `@Serialisable` to avoid errors.

#### And this is how you can create the config loader
```kotlin
class TestConfigLoader : ConfigLoader<TestConfig>(
    File("config.json"), // Here you can set where the file should be saved
    JsonFileFormatter(TestConfig::class.java), // Here you have to adapt your format. There are JsonFileFormatter andYamlFileFormatter
    { TestConfig("123", listOf("abc", "def")) }
)
```

## How to use the ConfigLoader
Once you have successfully created a ConfigLoader, various methods are available to you. These include `configLoader.load()`, which is used to retrieve the configuration. 
If you want to save a configuration, you can use the `configLoader.save(config)` method.
