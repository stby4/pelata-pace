![Pelata](https://user-images.githubusercontent.com/4112178/194953064-9a195a93-a77f-4fb7-90d8-b6281adb9d94.svg)



# Pelata

Pelata is Finnish and means "Play" => This project is just my playground.



# Build status

![example workflow](https://github.com/stby4/pelata-pace/actions/workflows/gradle.yml/badge.svg)



# Local setup

This project has been developed with Visual Studio Code on Ubuntu 22.04. Other IDEs and operating systems should work, but the setup might differ.

## Option 1: Using Dev Containers

> This option is recommended! It requires some additional tools, but is virtually care-free afterwards. The required development environment is installed and maintained automatically, and does not interfere with other tools.

1. Install [Visual Studio Code](https://code.visualstudio.com/) with the following extensions:
   - [Remote Development](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.vscode-remote-extensionpack).
1. Install and start [Colima](https://github.com/abiosoft/colima?tab=readme-ov-file#installation) for Linux or macOS, or [Docker Desktop](https://www.docker.com/products/docker-desktop/) for Windows. When using Colima, make sure to also install the docker runtime (`brew install docker`).
1. Open the cloned repository in VS Code, and let it start the Dev Container when it asks.
1. This process might take a couple of minutes. Once done, the Gradle tasks will show up in the sidebar of VS Code.


## Option 2: Manually install required tools

> This is the "classical" way to set up a development environment. It is relatively easy, as long as the required tools do not conflict with other projects and do not change.

1. Install SDKMAN:
   ```bash
   $ curl -s "https://get.sdkman.io" | bash
   $ source "$HOME/.sdkman/bin/sdkman-init.sh"
   ```
1. Make sure that v21 of Zulu JDK is installed:
   ```bash
   $ java --version
   openjdk 21.0.5 2024-10-15 LTS
   OpenJDK Runtime Environment Zulu21.38+21-CA (build 21.0.5+11-LTS)
   OpenJDK 64-Bit Server VM Zulu21.38+21-CA (build 21.0.5+11-LTS, mixed mode, sharing)
   ```
   If not, install Zulu JDK 21:
   ```bash
   $ sdk install java 21.0.5-zulu
   ```
1. Install Kotlin:
   ```bash
   $ sdk install kotlin
   ```
1. Install [Visual Studio Code](https://code.visualstudio.com/) with the following extensions:
   - [Kotlin](https://marketplace.visualstudio.com/items?itemName=fwcd.kotlin).
   - [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack).
1. Reload the VS Code window. Gradle should start to configure the project, which might take a couple of minutes.
1. Once done, the Gradle tasks will show up in the sidebar of VS Code.


## Run the code
The project contains a task.json that makes the most important tasks easily available in VS Code. Hit "Ctrl + Shift + P" -> "Tasks: Run Task" and select between:
- `build` to compile the project once.
- `run` to compile the project once and start a local server which serves the website.
- `run continuous` to serve the project from a local server and have incremental updates.
- `test` to execute unit tests.
- `check` to execute all kinds of checks, including unit tests and static code analyser.



# Styleguide


## Colour palette

![palette](https://github.com/user-attachments/assets/15e5be29-e63c-4355-b5a8-72c7a3d3939e)
From [coolors.co](https://coolors.co/5bc0be-8783d1-232929-eff1f1)



# Attributions

This project uses ressources from the following awesome projects:
- Font from [Google Fonts](https://fonts.google.com/specimen/Signika+Negative/about?category=Sans+Serif&subset=latin&preview.text=Pelata&preview.text_type=custom).
- Gradle config based on the example from [kotlin-hands-on](https://github.com/kotlin-hands-on/jvm-js-fullstack/tree/final).
