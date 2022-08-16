# Pelata

Pelata is the Finnish and means "Play" => This project is just my playground.

# Build status
![example workflow](https://github.com/stby4/pelata-pace/actions/workflows/gradle.yml/badge.svg)

# Local setup
> This project has been developed with Visual Studio Code on Ubuntu 20. Other IDEs and operating systems should work, but the setup might differ.

1. Make sure that v17 of OpenJDK is installed:
   ```bash
   $ java --version
   openjdk 17.0.4 2022-07-19
   OpenJDK Runtime Environment (build 17.0.4+8-Ubuntu-120.04)
   OpenJDK 64-Bit Server VM (build 17.0.4+8-Ubuntu-120.04, mixed mode, sharing)
   ```
   If not, use `apt` to install OpenJDK 17:
   ```bash
   sudo apt-get install openjdk-17-jdk
   ```
1. Install [Visual Studio Code](https://code.visualstudio.com/) with the following extensions:
   - [Kotlin Language](https://marketplace.visualstudio.com/items?itemName=mathiasfrohlich.Kotlin)
   - [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
1. Reload the VS Code window. Gradle should start to configure the project.
1. Once done, the Gradle tasks will show up in the sidebar of VS Code. Alternatively, they can be accessed using "Ctrl + Shift + P" -> "Tasks: Run Task".