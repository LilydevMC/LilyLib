# LilyLib

[![Modrinth Downloads](https://img.shields.io/modrinth/dt/lilylib?color=00AF5C&label=downloads&style=flat-square&logo=modrinth)](https://modrinth.com/mod/lilylib)
[![License](https://img.shields.io/github/license/Lilydev-By-Jade/LilyLib?style=flat-square)](LICENSE)
[![Lilydev Discord](https://img.shields.io/discord/995465843364343883?color=5865F2&style=flat-square&label=discord)](https://discord.gg/TZAt4PA5av)
[![Kofi](https://badgen.net/badge/icon/kofi?icon=kofi&label=jadelily&color=pink&style=flat-square)](https://ko-fi.com/jadelily)

LilyLib is a library mod for my Fabric/Quilt projects. The mod doesn't do anything
on its own so only download it if another mod requires it!

## Usage

Add the following to your `build.gradle` or `build.gradle.kts` file:

```groovy
repositories {
    // ...
    maven {
        url("https://maven.lilydev.com/[REPOSITORY]")
    }
}

dependencies {
    // ...
    modImplementation("com.lilydev:LilyLib:[VERSION]")
}
```
Replace `[REPOSITORY]` with `snapshots` or `releases` depending on the version
you're targeting, and replace `[VERSION]` with the version (e.g `0.1.0-beta.1`).
You can find every version on my [Maven repository](https://maven.lilydev.com).



## License

LilyLib is licensed under the [MIT License](LICENSE).
