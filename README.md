# CoreStorage
Welcome to the CoreStorage documentation. CoreStorage is a high-performance, in-memory data storage solution, designed to provide Java applications with a fast, reliable, and easy-to-use mechanism for managing transient data.  This tool enables users to efficiently manage key-value pairs where each key is associated with a specific color, enhancing the organization and accessibility of critical data.

## Features

CoreStorage offers a range of features designed for ease of use and efficiency:

- **Store Operation**: Easily store color values associated with unique string keys.
- **Retrieve Operation**: Quickly retrieve color values by specifying their associated keys.
- **Help Command**: Access a comprehensive guide on using CoreStorage directly from the command line.
- **User-Friendly CLI**: A straightforward command-line interface ensures that operations can be performed with minimal complexity.

## Getting Started

To begin using CoreStorage, follow the steps outlined below. The application requires Java to run, ensuring wide compatibility across various operating systems.

### Prerequisites

Ensure you have Java installed on your system. CoreStorage is compatible with Java version 17 and newer. You can check your Java version by running the following command in your terminal:

```shell
java -version
```

### Installation

CoreStorage does not require a traditional installation. Simply download the latest version of the application from our repository and ensure it is accessible from your command line.

### Running CoreStorage

Navigate to the directory containing MemoryStoreApplication and execute the following command to start the application:

```shell
java -jar MemoryStoreApplication.jar
```

Upon launch, the application will present you with the command prompt, where you can begin inputting commands.

## Usage

CoreStorage supports the following commands:

- **store `<key>` `<value>`**: Stores the specified color value under the given key. Example: `store 00-03 red`
- **retrieve `<key>`**: Retrieves and displays the color associated with the specified key. If no color is found, `GREY` is returned by default. Example: `retrieve 05`
- **help**: Displays a help message with details on available commands.
- **exit**: Exits the application.

## Contributing

While CoreStorage is currently designed for specific internal use cases, we welcome feedback and suggestions from all users. If you have ideas for improvements or have identified issues, please reach out through our internal communication channels.

## Support

For assistance with CoreStorage, please consult the help command within the application or contact our internal support team.


