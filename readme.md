#Weather Telegram Bot
- This is a Telegram bot written in Java that allows users to get weather information for a given city.

Requirements
- Java 17 or later
- Maven
- Telegram API token

#Installation
1. Clone this repository to your local machine.
2. Set up your Telegram bot API token by creating a bot with the BotFather and obtaining the API token.
3. Replace BOT_TOKEN_HERE in the Bot.java file with your actual Telegram bot API token.
4. Build the project using Maven:
   - mvn clean package
5. Run the bot using the generated JAR file:
    - java -jar target/weather-tg-bot-1.0-SNAPSHOT.jar

#Usage
1. Start a chat with the bot in Telegram by searching for its username.
2. Send the name of a city to the bot to get the current weather information for that city.

#Dependencies
- This project uses the following external libraries:

1. Telegrambots
2. JSON