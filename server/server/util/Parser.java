package util;

import util.Command;
import exception.*;


// util for tokenize and parsing commands
public class Parser {
    private static String [] tokenize(String command) {
        // tokenizing command by whitespaces
        return command.split("\\s+");
    }

    public static String parse_exec(String command) {
        String [] tokens = tokenize(command);

        // tokens[0] is the actual opcode
        switch(tokens[0]) {
            case "add":
                return Command.add(tokens);
            case "delete":
                return Command.delete(tokens);
            case "show":
               return Command.show(tokens);
            default:
               throw new InvalidCommandException(
                       "command not found! try 'help' for more informations.");
        }
    } 
}
