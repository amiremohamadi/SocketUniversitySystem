package util;

import exception.*; 
import model.Course;
import util.DataBase;


class Command {
    public static String add(String [] tokens) {
        // format: add name unit grade
        String name;
        int unit;
        float grade;
        // only 4 tokens
        if (tokens.length != 4)
            throw new InvalidCommandException(
                       "invalid command! try 'help' for more informations.");

        // convert and validate types
        // name is okeay without validation (tokens are string by default)
        name = tokens[1];
        // unit(int) and grade(float) types should be converted and be checked
        try {
            unit = Integer.parseInt(tokens[2]);
            grade = Float.parseFloat(tokens[3]);
        } catch(NumberFormatException e) {
            throw new InvalidCommandException(
                    "invalid command! check arguments type.");
        }

        DataBase.add(new Course(name, unit, grade));
        return "Added!";
    }

    public static String delete(String [] tokens) {
        // validate index (must be integer)
        int idx;
        try {
            idx = Integer.parseInt(tokens[1]);
        } catch(NumberFormatException e) {
            throw new InvalidCommandException(
                    "invalid command! check argument type.");
        }

        DataBase.delete(idx);
        return "Deletd!";
    }

    public static String show(String [] tokens) {
        return DataBase.show();
    }
}
