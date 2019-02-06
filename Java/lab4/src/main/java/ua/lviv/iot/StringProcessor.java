package main.java.ua.lviv.iot;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringProcessor {
    public StringBuilder readInputText(){
        Scanner reader = new Scanner(System.in);
        String input  = reader.nextLine();
        StringBuilder text = new StringBuilder(input);
        return text;
    }
    public void processText(StringBuilder text){
        Pattern pattern = Pattern.compile("(\\b|\\D)\\d(\\D|\\b)");
        Pattern finalPattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()){
            StringBuilder tmp = new StringBuilder(text.substring(matcher.start(), matcher.end()));
            Matcher finalMatcher = finalPattern.matcher(tmp);
            while(finalMatcher.find()){
                String finalTmp = tmp.substring(finalMatcher.start(), finalMatcher.end());
                switch (finalTmp){
                    case "0":
                        tmp.replace(finalMatcher.start(), finalMatcher.end(), "A");
                        break;
                    case "1":
                        tmp.replace(finalMatcher.start(), finalMatcher.end(), "B");
                        break;
                    case "2":
                        tmp.replace(finalMatcher.start(), finalMatcher.end(), "C");
                        break;
                    case "3":
                        tmp.replace(finalMatcher.start(), finalMatcher.end(), "D");
                        break;
                    case "4":
                        tmp.replace(finalMatcher.start(), finalMatcher.end(), "E");
                        break;
                    case "5":
                        tmp.replace(finalMatcher.start(), finalMatcher.end(), "F");
                        break;
                    case "6":
                        tmp.replace(finalMatcher.start(), finalMatcher.end(), "G");
                        break;
                    case "7":
                        tmp.replace(finalMatcher.start(), finalMatcher.end(), "H");
                        break;
                    case "8":
                        tmp.replace(finalMatcher.start(), finalMatcher.end(), "I");
                        break;
                    case "9":
                        tmp.replace(finalMatcher.start(), finalMatcher.end(), "J");
                        break;
                    }
                text.replace(matcher.start(), matcher.end(), tmp.toString());
                }
        }
    }
    public void showResult(StringBuilder text){
        System.out.println("Ur result is: ");
        System.out.println(text);
    }

    public static void main(String[] args){
        StringProcessor sp = new StringProcessor();
        StringBuilder text = sp.readInputText();
        sp.processText(text);
        sp.showResult(text);
    }
}
