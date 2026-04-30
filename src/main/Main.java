package main;

import view.CommandHandler;

public class Main {
    public static void main(String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.handleArgs(args);
    }
}
