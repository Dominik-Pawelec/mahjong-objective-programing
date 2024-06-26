# Project Documentation

## Main
### Main sets up the JFrame and JPanel, displays the menu, and starts the game once the user initiates it. It also handles the transition from the menu to the game and after it finishes, to the results display.

+ main(String[] args): Same as above ^^^

# Tile Based Classes

## Tile (implements Comaprable)
### Tile represents tile in Mahjong game, stores number and family of a tile. Implements Comparable interfalce to allow sorting tiles based on their type and number.

+ Tile(int nr, String family): Constructor to create a tile with a specific number and family.
+ Tile(String xs): Constructor to create a tile based on a single-character string representation.
+ int getNr(): Returns the number of the tile.
+ String getFamily(): Returns the family of the tile.
+ String toString(): Converts the tile to its string representation.
+ int compareTo(Tile t): Compares this tile with another tile for sorting.

## TileGroup
### TileGroup manages a collection of Tile objects, providing functionalities to eg. add, remove, sort, shuffle, and get tiles.

+ TileGroup(): Initializes an empty group.
+ TileGroup(Tile...args): Initializes a group with specified tiles.
+ TileGroup(TileGroup copy): Initializes a group by copying + another group.
+ TileGroup(String x): Initializes a group with all possible tiles if x is "all".
+ Tile add(Tile tile): Adds a tile to the group.
+ void remove(): Removes the last tile from the group.
+ void remove(Tile t): Removes a specific tile from the group.
+ void sort(): Sorts the tiles in the group.
+ void shuffle(): Shuffles the tiles in the group.
+ int size(): Returns the number of tiles in the group.
+ int nrOfElem(Tile tile): Returns the number of occurrences of a specific tile in the group.
+ Tile get(int n): Retrieves the tile at the specified index.
+ String toString(): Returns a string representation of the group.

## River (extends TileGroup)
### River class extends the TileGroup class. It keeps track of the most recently added tile and manages a group of stolen tiles.

+ getRecent(): Returns the most recently added tile.
+ add(Tile tile): Adds a tile to the group and updates the recent tile.
+ remove(): Removes the most recently added tile from the group and adds it to the stolen tiles.
+ toString(): Returns a string representation of the river, formatting the tiles in rows of six.

## Wall (extends TileGroup; is Singleton)
### Wall extends TileGroup, ensures that only one instance of Wall exists and allows easy refreshing it (build() method) and getting access to its elements required in game.

+ getInstance(): Returns the single instance of Wall.
+ build(): Constructs the complete set of Mahjong tiles and shuffles them.
+ drawTile(): Draws and removes the last tile from the collection.
+ toString(): Returns a string representation of the Wall object.

## Hand (extends TileGroup)
### Hand extends the TileGroup class and represents a player's hand in a game. That includes functionalities for managing both closed and opened blocks of tiles, checking for winning conditions, and determining possible tile calls (chi and pon). 

+ Hand(): Default constructor initializing an empty hand.
+ Hand(Tile...args): Constructor initializing the hand with given tiles.
+ Hand(Hand copy): Copy constructor.
+ getOpened(): Returns the list of opened tile groups.
+ containsTile(Tile t): Checks if the hand contains a specific tile.
+ chiOptions(Tile t): Returns possible chi combinations for a given tile.
+ ponOptions(Tile t): Returns possible pon combinations for a given tile.
+ openBlock(TileGroup tile_g): Adds a tile group to the opened blocks and removes those tiles from the hand.
+ isOpen(): Checks if there are any opened blocks.
+ isWinning(): Checks if the hand is in a winning state.
+ winningTiles(): Returns a list of tiles that would complete a winning hand.
+ inTenpai(): Checks if the hand is one tile away from winning.
+ sort2(): Sorts the hand while keeping the last tile in place.
+ toString(): Returns a string representation of the hand.

# Player Classes

## Player
### Player class represents a player in a Mahjong game, contains their hand, river (discard pile). It includes methods for drawing and discarding tiles, making calls, and checking various game conditions such as Riichi and Tsumo.

+ draw(): Draws a tile from the wall and adds it to the player's hand.
+ discard(Tile t): Discards a specified tile from the player's hand to the river.
+ call(TileGroup tile_group): Opens a block of tiles in the player's hand.
+ makePackage(CallPackage input_package): Prepares a call package based on the input package.
+ canChi(Tile t): Checks if the player can make a chi call with the specified tile.
+ canPon(Tile t): Checks if the player can make a pon call with the specified tile.
+ canRiichi(): Checks if the player can declare riichi.
+ canRon(Tile t): Checks if the player can win with a ron call using the specified tile.
+ canTsumo(): Checks if the player can win with a tsumo (self-draw).
+ chooseToDiscard(): Logic for choosing which tile to discard.
+ chooseToTsumo(): Logic for deciding whether to declare tsumo.
+ chooseToRiichi(): Logic for deciding whether to declare riichi.
+ chooseCall(List< String> possible_calls, Tile discarded_tile): Logic for choosing which call to make.
+ chooseGroup(List< TileGroup> groups, Tile discarded_tile): Logic for choosing which tile group to use for a call.

## Human (extends Player)
### Human extends Player class and represents a human player in a game. It handles user inputs for various game actions such as discarding tiles, choosing to declare Tsumo or Riichi, and making calls. The class interacts with a DisplayGame interface if available.

+ chooseToDiscard(): Prompts the player to choose a tile to discard.
+ chooseToTsumo(): Prompts the player to decide whether to declare Tsumo.
+ chooseToRiichi(): Prompts the player to decide whether to declare Riichi.
+ chooseCall(List< String> possible_calls, Tile discarded_tile): Prompts the player to make a call based on the discarded tile and possible calls.
+ chooseGroup(List < TileGroup> groups, Tile discarded_tile): Chooses a group of tiles to call.
+ pinToDisplay(DisplayGame dg): Links the player to a display game for graphical interaction.

## AI (extends Player)
### The AI class extends the Player class and represents an AI player in a Mahjong game. It includes methods for making decisions such as which tile to discard, whether to declare Riichi or Tsumo, and which call to make when a tile is discarded. The AI uses a Calculator to evaluate the hand's state and make strategic decisions based on the game's current situation.
+ chooseToDiscard(): Determines which tile to discard based on the current hand and game state.
+ chooseShantenPositive(): Chooses a tile to discard that will improve the hand's Shanten number.
+ chooseMostWaits(): Chooses a tile to discard that maximizes the number of potential winning tiles.
+ chooseToTsumo(): Decides whether to declare Tsumo.
+ chooseToRiichi(): Decides whether to declare Riichi.
+ chooseCall(List< String> possible_calls, Tile discarded_tile): Decides which call to make (Pon, Chi, Ron) based on the discarded tile and possible calls.
+ chooseGroup(List< TileGroup> groups, Tile discarded_tile): Chooses the best group to form when making a call.

# Game Class

## Game
### The Game class manages the overall flow and state of a Mahjong game. It handles player initialization, round preparation, game logic, and end results. The class also manages audio effects for various game actions and is sychronised with a display system (DisplayGame object) to visualize the game state.

+ Game(): Default constructor initializing an empty player list.
+ Game(Player p1, JFrame frame, boolean audio_on): Constructor initializing the game with a player, frame, and audio settings.
+ startHanchan(): Starts a new game session, filling up to four players with AI if necessary.
+ prepareRound(): Prepares the game for a new round, resetting players and dealing initial hands.
+ start(): Starts the main game loop.
+ end(int winner_index, Tile winning_tile): Ends the current round, updates scores, and prepares the next round.
+ ryukuoku(): Handles a draw situation where no player wins.
+ endResults(): Displays the final results when the game ends.

### GameLogic: Inner class of Game that manages gameloop and player turns.

+ GameLogic(): Initializes the game logic, including loading audio files.
+ mainLoop(): Runs the main game loop, handling player turns and checking for win conditions.
+ takeTurn(): Manages the actions of the current player during their turn.
+ discardTurn(int prev_id, int steal_id, CallPackage call_pack): Handles the actions when a player steals a discarded tile.
+ analyseDiscarded(Tile discard_tile): Analyzes the discarded tile to determine if any player can call an action.

# Display Classes

## Menu
### Menu allows user to start the game of Mahjong and change settings of the game, such as turning on/off audio and changing between light/dark color scheme.

+ Menu(JFrame frame): Constructor that initializes the menu, sets up buttons, and configures their actions.
+ boolean getAudio(): Returns the current audio state (on/off).
+ boolean getLight(): Returns the current theme state (light/dark).
+ void hideMenu(): Hides all buttons in the menu.

## Results
### Results displays how many winning hands each player(that is: player and 3 bots) archieved.
 
+ Results(JFrame frame, Game game, boolean light_mode): Constructor that initializes the frame, creates JLabels for each player and bot, sets their properties, and adds them to the frame. 


## DisplayGame
### The DisplayGame class is responsible for managing and displaying the visual elements of a Mahjong game within a JFrame. It handles the initialization, updating, and resetting of various game components such as player hands, rivers, opened tiles, and call buttons. The class also manages the display of special game states like Riichi.

+ DisplayGame(JFrame frame, Game game, boolean light_mode, boolean audio_on): Constructor that initializes the display elements and call buttons.
+ reset(): Resets the display elements for a new game round.
+ draw(int curr_player): Updates and redraws the hand and other elements for the specified player.
+ drawRiichi(int player_nr): Displays a Riichi stick for the specified player.

## DisplayHand
### The DisplayHand class is responsible for visually representing a player's hand of tiles in a JFrame. It manages the display, updating, and interaction of the tiles in the hand, including showing and hiding the hand, and handling user interactions for selecting tiles to discard.

+ DisplayHand(int x, int y, int direction, int tile_size_x, int tile_size_y, JFrame frame, Hand hand, boolean is_hidden, boolean is_players, boolean light_mode): Constructor.
+ update(): Updates the visual representation of the hand.
+ showHand(): Reveals the hand.
+ hideHand(): Hides the hand.
+ destroy(): Removes all tile buttons from the frame.
+ getTileToDiscard(): Waits for the player to choose a tile to discard and returns its index.

## DisplayOpened
### The DisplayOpened class is responsible for displaying opened blocks from players' hands. It handles the positioning and rendering of these tiles.

+ DisplayOpened(int x, int y, JFrame frame, int direction, boolean light_mode): Constructor to initialize the display settings.
+ void displayNewBlock(TileGroup t): Adds a new block of tiles to the display.
+ void destroy(): Removes all tiles from the display and resets the state.

## DisplayRiver
### DisplayRiver allows proper rendering of players' rivers during the game.
+ DisplayRiver(int x, int y, JFrame frame, int direction, boolean light_mode): Constructor to initialize the river display.
+ void setRichiiTile(): Marks the current size of the river as the richii tile.
+ void addTile(Tile tile): Adds a tile to the river and positions it on the frame.
+ void removeLastTile(): Removes the last tile from the river.
+ public void destroy(): Clears all tiles from the river and resets the state.

## Calls
### The Calls class manages a set of buttons representing possible calls during a game. Serves as an input for CallPackage.

+ Calls(JFrame frame, int x, int y, boolean audio_on): Constructor that initializes the buttons, audio clips, and sets up mouse listeners.
+ showCalls(boolean[] available): Displays the buttons based on the available actions.
+ hideCalls(): Hides all the buttons.
+ getCall(boolean[] available): Displays the buttons, waits for the user to make a choice, and returns the chosen action as a string.

# Utility Classes:

## CallPackage
### The CallPackage class collects the details of calls from each player as reaction to discarded tile, and allows Game to decide whether call takes place in the game.

+ CallPackage(Tile t, String w): Constructor to initialize a call package with a tile and wind.
+ CallPackage(CallPackage copy): Copy constructor to create a new instance based on an existing CallPackage.
+ Tile getTile(): Returns the tile being called.
+ String getWind(): Returns the wind the Tile was taken from.
+ void preparePackage(): Prepares the package without a call.
+ void preparePackage(boolean is_call, String call_t, TileGroup whole_block): Prepares the package with a call, specifying the call type and associates tile group.
+ boolean isCalling(): Returns whether a call is being made.
+ String callType(): Returns the type of call.
+ TileGroup tileGroup(): Returns the group of tiles associated with the call.

## TileAssets
### TileAssets manages icons of tiles and stores them. Used by other clases to gain specific tile texture. It support light and dark mode!

+ getIcon(Tile tile, int direction, boolean light_mode): Returns the ImageIcon for a given tile, direction, and theme.
+ getIcon(int direction): Returns the back ImageIcon for a given direction.

## Calculator
### The Calculator class provides methods to calculate the shanten number, possible shapes, remaining tiles, and improving tiles for a given hand in a Mahjong game. It helps determine how close a hand is to winning and what tiles can improve the hand.

+ shanten(Hand h): Calculates the shanten (how far is hand from winning) for the given hand.
+ possibleShapes(TileGroup tg, int complete, int not_complete, boolean contains_pair): Recursively finds all possible shapes of tile groups in the hand.
+ remainingTiles(Hand h, Game g): Counts the remaining tiles in the game that have not been played or discarded.
+ improvingTiles(Hand h): Identifies tiles that can improve the current hand.