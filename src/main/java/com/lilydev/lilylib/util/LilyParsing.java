package com.lilydev.lilylib.util;

public class LilyParsing {

    public LilyParsing() {

    }

    /**
     * Returns the string parameter, replacing a variable of the supplied
     * variableName with variableReplacement.
     *
     * @param string A string with a variable (e.g. %player%)
     * @param variableName The name of a variable in a string (e.g. a
     *                     variableName of `player` would find `%player%`
     *                     in the string and replace it with variableReplacement)
     * @param variableReplacement The string to replace said variable with.
     * @return a string with a parsed variable
     */
    public static String parseStringWithVariable(String string, String variableName, String variableReplacement) {
        return string.replaceAll("%" + variableName + "%", variableReplacement);
    }

}
