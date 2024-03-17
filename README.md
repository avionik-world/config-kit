# ConfigKit
With this project you can easily create a config file.

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

#### And this is how you can create the ConfigLoader
```kotlin
class TestConfigLoader : ConfigLoader<TestConfig>(
    File("config.json"), // Here you can set where the file should be saved
    JsonFileFormatter(TestConfig::class.java), // Here you have to adapt your format. There are JsonFileFormatter and YamlFileFormatter
    { TestConfig("123", listOf("abc", "def")) }
)
```

## How to use the ConfigLoader
Once you have successfully created a ConfigLoader, various methods are available to you. These include `configLoader.load()`, which is used to retrieve the configuration. 
If you want to save a configuration, you can use the `configLoader.save(config)` method.

## How to create the MultipleConfigLoader
Everything remains the same as with the ConfigLoader, but with one exception. The class needs the nameable implantation so that the files can be selected.
```kotlin
class KitConfig(
    val name: String,
    val secondList: List<String>
) : Nameable {
    override fun getName(): String = this.name
}
```
**Note:** If you want to save as a Yaml file, you have to write via the class `@Serialisable` to avoid errors.

#### And this is how you can create the MultipleConfigLoader
```kotlin
class TestConfigLoader : MultipleConfigLoader<KitConfig>(
    File("config/kits"), // All files are saved in this directory
    JsonFileFormatter(KitConfig::class.java) // Here you have to adapt your format. There are JsonFileFormatter and YamlFileFormatter
)
```

## How to use the MultipleConfigLoader
With `configLoader.loadAll()` you can get all configs from this directory.