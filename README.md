# README #

JPMorgan Tech Test implementation of Message Processing system, which processes sales in memory, generate reports and print them to stdout.

### How to run? ###

There is a Main class which will generate a number of random event messages, which will simulate a third party pushing events on a messaging queue. In this case the messaging queue and the other sales and adjustments data is kept in memory in the DataStore class.

### Dependencies ###

- Java 8
- JUnit 4 needs to be on the class path

### Assumptions ###

- Currency is only GBP
- The application will run in a single threaded environment
- All data is in memory
- Report needs to be printed out to stdout
- The third party feed will push the message events onto the DataStore.messageQueue (I've created a simulator, which will push a number of events onto this queue.