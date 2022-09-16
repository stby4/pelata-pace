# Pelata

Pelata is the Finnish and means "Play" => This project is just my playground.

✔️ Visit: https://www.pelata.net

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
1. Install Kotlin:
   ```bash
   $ sdk install kotlin
   ```
1. Install [Visual Studio Code](https://code.visualstudio.com/) with the following extensions:
   - [Kotlin](https://marketplace.visualstudio.com/items?itemName=fwcd.kotlin).
   - [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack).
1. Reload the VS Code window. Gradle should start to configure the project, which might take a couple of minutes.
1. Once done, the Gradle tasks will show up in the sidebar of VS Code. Alternatively, they can be accessed using "Ctrl + Shift + P" -> "Tasks: Run Task".


# Styleguide

## Colour palette

![palette](https://user-images.githubusercontent.com/4112178/190750585-b3b4ccc4-5ff2-46a1-abfc-fb338e0741ea.svg)
From [coolors.co](https://coolors.co/5bc0be-8783d1-0c0b1e-ffffff)

The colours to be used for this website are:
- Background: `#ffffff` White
- Text: `#0c0b1e` Xiketic
- Accent colour 1: `#5bc0be` Maximum Blue Green
- Accent colour 2: `#8783d1` Middle Blue Purple


# Attributions

This project uses ressources from the following awesome projects:
- Font from [Google Fonts](https://fonts.google.com/specimen/Signika+Negative/about?category=Sans+Serif&subset=latin&preview.text=Pelata&preview.text_type=custom)
