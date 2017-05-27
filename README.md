# README #

JPMorgan Tech Test implementation of Message Processing system, which processes sales in memory, generate reports and print them to stdout.

### How to run? ###

There is a Main class which will generate a number of random event messages, which will simulate a third party pushing events on a messaging queue. In this case the messaging queue and the other sales and adjustments data is kept in memory in the DataStore class.