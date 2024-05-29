# Arkanoid Game Project

![image](https://github.com/ilanitb16/Object-Oriented-Programming/assets/97344492/a041920a-95a9-4d40-8ea3-cc83d667a7b0)

## Overview

This project is an implementation of an Arkanoid game in Java. The project utilizes object-oriented programming principles, including the use of interfaces, to create a modular and extendable game. The game includes key components such as the Ball, Blocks, Paddle, and collision detection mechanisms.

## Project Structure

The project is organized into several classes and interfaces, each responsible for a specific part of the game:

### Geometry Primitives

- **Point**: Represents a point in a 2D space.
- **Line**: Represents a line segment in a 2D space with methods for detecting intersections.
- **Rectangle**: Represents a rectangle aligned with the axes, used for collision detection.

### Game Components

- **Ball**: Represents the ball in the game, including its movement and collision detection with other objects.
- **Block**: Represents obstacles in the game. Blocks can be collided with, and they influence the ball's direction upon collision.
- **Paddle**: Represents the player-controlled paddle that can move left and right and deflects the ball.

### Interfaces

- **Collidable**: An interface for objects that can be collided with (e.g., blocks and paddle). It defines methods for getting the collision shape and handling collisions.
- **Sprite**: An interface for game objects that can be drawn on the screen and can be notified that time has passed.

### Game Environment

- **GameEnvironment**: Manages all collidable objects in the game and handles collision detection.
- **CollisionInfo**: Holds information about a collision, including the collision point and the collidable object involved.

### Game Management

- **SpriteCollection**: Manages all sprites in the game, ensuring they are drawn and updated appropriately.
- **Game**: Manages the overall game, including initializing the game objects, running the game loop, and handling user inputs.

## Getting Started

1. **Set up the project**: Ensure you have the necessary biuoop package and set up your project environment.

2. **Create Geometry Primitives**: Implement the `Point`, `Line`, and `Rectangle` classes to handle the game's geometry.

3. **Implement Collidable Objects**: Create the `Block` and `Paddle` classes implementing the `Collidable` interface.

4. **Game Environment**: Develop the `GameEnvironment` class to manage collidable objects and handle collision detection.

5. **Sprite Management**: Create the `SpriteCollection` class to manage all sprites in the game and ensure they are drawn and updated.

6. **Game Initialization and Loop**: Implement the `Game` class to initialize game components, run the game loop, and handle user inputs.

7. **Add the Paddle**: Implement the `Paddle` class to be controlled by the player using keyboard inputs.

8. **Run the Game**: Create an `Ass3Game` class with a `main` method to initialize and run the game.

## Example Main Class

```java
public class Ass3Game {
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}

