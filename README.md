# Death Note Mod :skull:

A Fabric Minecraft mod for Version 1.21 that adds a few new items to the game from the death note anime! Craft the deadly book and go on a killing spree! Made by 8percent and x1yl.

--> Download the latest version in the "Releases" tab. Put the jar file into your minecraft mods folder to get started!

## Items

- **Death Note**: Write an Player's name or Entity's name to kill them
- **Shinigami Apple**: Eating this will both curse you and bless you. Who knows?
- **Potato Chips**: Eating this light snack will give you a slight speed boost. Might help your writing speed in the Death Note.

## Demo

[Modrinth Page](https://skibidi)
[Trailer](https://youtube.com/)

## Screenshot

![Screenshot](https://cloud-r3kt02ah6-hack-club-bot.vercel.app/02025-01-19_17.08.46.png)

## Compiling the mod yourself

1. **Clone the Repository**  
   Clone the repository from GitHub:

   ```bash
   git clone https://github.com/ericafk0001/Death-Note-1.21.X.git
   ```

2. **Navigate to the Project Directory**  
   Move into the cloned repository's directory:

   ```bash
   cd Death-Note-1.21.X
   ```

3. **Ensure You Have Required Tools Installed**  
   Make sure you have the following installed:

   - [Java Development Kit (JDK) 21](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
   - [Gradle](https://gradle.org/install/) (optional, as the wrapper is typically included)

4. **Build the Mod**  
   Run the Gradle wrapper to build your mod:

   ```bash
   ./gradlew build
   ```

   On Windows, use:

   ```cmd
   gradlew build
   ```

   This will generate a `Death-Note-1.21.X.jar` file in the `build/libs` directory.

5. **Install the Mod**  
   Copy the generated `.jar` file to your Minecraft `mods` folder:

   - Locate your `.minecraft` directory (typically in `%appdata%/.minecraft` on Windows or `~/.minecraft` on Linux/Mac).
   - Paste the `.jar` file into the `mods` folder.

6. **Run the Mod**  
   Launch Minecraft with the Fabric Loader 1.21.0 installed.

   - Ensure Fabric Loader is installed via [Fabric Installer](https://fabricmc.net/use).
   - Check that your mod appears in the `Mods` section of the Minecraft main menu.

7. **Optional: Run the Mod in Development Environment**  
   To test the mod directly in the development environment:

   ```bash
   ./gradlew runClient
   ```

   This will start a Minecraft client with your mod preloaded for testing.

## How It Works

It uses the Fabric API and sends packets to the server to execute commands server side such as the killing mechanics. It uses fabric's item and food components to make the custom items/food.

## Features to Add

- Better Death Note functionality handling server side to handle random warnings
- More items from the Anime

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Thank you to .7410 (on discord) for helping!
