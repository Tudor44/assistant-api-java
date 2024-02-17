
# OpenAI Assistant API for Java 

This project offers a lightweight and straightforward Java client for interacting with the OpenAI Assistant API. It's specifically designed to facilitate the integration of Generative AI technology into Java applications.

## Important considerations

This repository uses Assistant API to intelligently address user problems. This process may involve multiple calls to the OpenAI API. Please be aware that in the event of an error during processing a request, there is a risk of rapidly escalating API calls, which could dramatically increase usage costs. This is especially important if you plan to use it as an unsupervised service performing tasks in the background. This note is relevant for any usage of Assistant API, not just this repository. Always use Assistant API with caution.

## Features

- Create an assistant.
- Create a thread.
- Send messages in a thread.
- Fetch messages from a thread.
- Handle responses for assistants, threads, and messages.

For detailed information on the OpenAI API and its capabilities, 
visit OpenAI's official documentation.
https://platform.openai.com/docs/assistants/overview

## Requirements

- Java 17 (for now)
- Maven

## Setup and Usage

1. Clone the repository.
2. Add your OpenAI API key to `src/main/resources/application.properties`.
3. Build the project using Maven: `mvn clean install`.
4. Run the `Main.java` class to see the client in action.
5. 

## Contributing

Contributions to the project are welcome! Feel free to fork the repository, make changes, and submit pull requests.

## License

This project is open-sourced under the MIT License.
