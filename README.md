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
1. Install SDKMAN:
   ```bash
   $ curl -s "https://get.sdkman.io" | bash
   $ source "$HOME/.sdkman/bin/sdkman-init.sh"
   ```
1. Install Kotlin and Gradle:
   ```bash
   $ sdk install kotlin
   $ sdk install gradle 7.5.1
   ```
1. Install [Visual Studio Code](https://code.visualstudio.com/) with the following extensions:
   - [Kotlin Language](https://marketplace.visualstudio.com/items?itemName=mathiasfrohlich.Kotlin)
   - [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
1. Reload the VS Code window. Gradle should start to configure the project.
1. Once done, the Gradle tasks will show up in the sidebar of VS Code. Alternatively, they can be accessed using "Ctrl + Shift + P" -> "Tasks: Run Task".


# Styleguide

## Coulor palette
![Colours](data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20xmlns%3Axlink%3D%22http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink%22%20version%3D%221.1%22%20viewBox%3D%220%200%20500%20250%22%20xml%3Aspace%3D%22preserve%22%3E%3Crect%20fill%3D%22%231fad45%22%20x%3D%220%22%20y%3D%220%22%20width%3D%22166.66666666666666%22%20height%3D%22220%22%2F%3E%2C%3Crect%20fill%3D%22%23477998%22%20x%3D%22166.66666666666666%22%20y%3D%220%22%20width%3D%22166.66666666666666%22%20height%3D%22220%22%2F%3E%2C%3Crect%20fill%3D%22%23291f1e%22%20x%3D%22333.3333333333333%22%20y%3D%220%22%20width%3D%22166.66666666666666%22%20height%3D%22220%22%2F%3E%3C%2Fsvg%3E)