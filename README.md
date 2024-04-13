# Java Redis-like Cache Server

This project implements a simple Redis-like cache server in Java. It utilizes basic command handling (SET, GET, ECHO, PING) to interact with data stored in memory. The server uses a thread pool to handle multiple client connections concurrently and manages data in a thread-safe manner using `ConcurrentHashMap`.

## Features

- **Basic Command Support**: Handles commands like SET, GET, ECHO, and PING.
- **Concurrency Support**: Manages multiple connections using a thread pool.
- **Expiry Mechanism**: Supports setting expiration time on keys for the SET command.
- **Thread-Safe Storage**: Uses `ConcurrentHashMap` to store key-value pairs safely across multiple threads.
- **Custom Commands**: Easily extendable to add more commands by implementing the `Command` interface.

## Components

- `Main`: Initializes the server and handles incoming socket connections.
- `Cache`: Singleton class that provides a global point of access to the ConcurrentHashMap.
- `CacheValue`: Represents the value stored in the cache with optional attributes like expiry time.
- `Command`: Interface for command implementations.
- `SetCommand`, `GetCommand`, `EchoCommand`, `PingCommand`: Implementations of the respective commands.
- `CommandParserHandler`: Parses incoming client requests and delegates to the appropriate command.
- `CommandMapper`: Maintains a mapping from command strings to their corresponding `Command` objects.

## Setup and Running

### Prerequisites

- Java 11 or above
- Maven (optional for dependency management and building the project)

### Running the Server

1. Compile the Java files. If using Maven, run:
   ```bash
   mvn clean install
