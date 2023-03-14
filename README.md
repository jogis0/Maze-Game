# Maze-Game

## About the game
- It is a 2D video game made using Java where the purpose of the game is to collect "gems" and "keys".
- Keys in the game advance the player to the next level while gems are used to measure the score of the player.
- The game also tracks time, so players can try to finish the game as fast as possible and try to beat their record.

## Map editor and map tiles
- The game also comes equiped with a simple map editor.
- The map grid is 20 x 20, but can be easily changed by changing maxWorldCol and maxWorldRow variables.
- Below the map grid are 8 tiles used for selecting.
- The player can change the map tiles by selecting the desired tile and placing it on the grid.
- Tiles of the map are seperated into regular tiles and object tiles.
- Reuglar tiles (grass, dirt, water, bush) are used to build the map.
- Some regular tiles have collision (like water or bush) while others (like grass or dirt) do not.
- Object tiles (keys, gems, doors) are used to interact with the player.
- When a player interacts with them, certain code will be run based on the circumstances (like changing the map when the player has a key and runs into a door).

## UI
- When the game is launched, the player will be shown the title screen with three options: "Start", "Map editor" and "Quit".
- While playing, the UI shows the player the amount of keys that were collected (top left).
- Below that is the score count (gem count).
- The time is displayed in the top right corner.
- When the player finishes all levels, the UI will show the time and the score of the player.
