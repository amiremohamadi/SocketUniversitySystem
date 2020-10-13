package util;

import util.Command;
import util.InvalidCommandException;


// util for tokenize and parsing commands
public class Parser {
    private static String [] tokenize(String command) {
        // tokenizing command by whitespaces
        return command.split("\\s+");
    }

    public static void parse_exec(String command) {
        String [] tokens = tokenize(command);

        // tokens[0] is the actual opcode
        switch(tokens[0]) {
            case "add":
                Command.add(tokens);
                break;
            case "delete":
                Command.delete(tokens);
               break;
            case "show":
               Command.show(tokens);
               break;
            default:
               throw new InvalidCommandException(
                       "command not found! try 'help' for more informations.");
        }
    } 
}
