# Minigame Framework
This is a very incomplete framework for creating Minecraft minigames for both Nukkit and Spigot servers.
The framework is loosely based on [VoxelGamesLibv2](https://github.com/VoxelGamesLib/VoxelGamesLibv2).

Testing of the framework has been rather limited and as the framework is incomplete, it should not be used for anything serious.

The project is divided into three modules: `core`, `nukkit` and `spigot`. The `core` module contains most of the logic,
the `nukkit` and `spigot` modules contain implementations for the respective platforms.

When creating a minigame with this framework, a similar structure should be used. The `core` module should contain
all minigame-related logic, the `spigot` and `nukkit` modules should only have to handle platform dependant things like commands.

### General overview
A minigame is built out of 4 components:
- Feature
- VictoryCondition
- Phase
- Game

##### 1. Feature
A Feature is a component that takes care of all the logic of the minigame. In a Feature, you can register
events and execute code when the feature starts and ends. 
An example of a Feature is the SpawnFeature: This feature takes care of spawning all players when the minigame starts.

##### 2. VictoryCondition
A VictoryCondition keeps track of when a player has won a minigame. This component is similar to a Feature.

##### 3. Phase
A Phase is a collection of features and conditions.

##### 4. Game
A Game is a collection of Phases.

### Example
An example minigame using this framework can be found [here](https://github.com/bramhaag/minigame-framework-spleef)


