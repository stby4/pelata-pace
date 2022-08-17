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
   sudo apt install openjdk-17-jre-headless
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

_From [coolors.co](https://coolors.co/e1b03d-477998-291f1e-fffffa)

The colours to be used for this website are:
- Background: `#fffffa` Baby Powder
- Text: `#291f1e` Raisin Black
- Accent colour 1: `#e1b03d` Goldenrof
- Accent colour 2: `#477998` CG Blue

# Attributions

This project uses ressources from the following awesome projects:
- Icons from [Reshot](https://www.reshot.com/).
- Font from [Google Fonts](https://fonts.google.com/specimen/Carter+One?category=Sans+Serif,Display&preview.text=Pelata&preview.text_type=custom)
