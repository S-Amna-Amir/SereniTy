package application.utils;

public class Validation {

    public static String validateTextField(String input, int minLength) { //textfield not empty and > minLength characters
        if (input == null || input.trim().isEmpty()) {
            return "Field cannot be empty!";
        }
        if (input.length() < minLength) {
            return "Field must be at least " + minLength + " characters long!";
        }
        return null; //passed the validation
    }
    
    public static String validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return "Email cannot be empty.";
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) { //string matching !!!
        	//regular expression (regex) to check:
        	//^[\w.-]+ : start with one or more letters, numbers, underscores, dots, or hyphens
        	//@ : symbol to separate the username and the domain section of the string
        	//[\w.-]+ : after @, it can have more letters, numbers, dots, or hyphens for the domain
        	//\\.[a-zA-Z]{2,} : then it expects a dot followed by at least two letters for the domain extension e.g., .com, .org
        	//$ : make sure the whole string matches this pattern from start to finish.
            return "Invalid email address.";
        }
        return null;
    }

    public static String validateNumericField(String value, double minValue) {
        try {
            double numericValue = Double.parseDouble(value);
            if (numericValue < minValue) {
                return "Value must be at least " + minValue + ".";
            }
        } 
        catch (NumberFormatException e) {
            return "This field must be a numeric value.";
        }
        return null;
    }
}
