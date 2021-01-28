import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {

   static int rowCounter = 1;
   static int globalCounter = 0;
   static String alphabet = "(?i)abcdefghijklmnopqrstuvwxyz";//En annan lösning för att få ut alfabetet [Aa][Bb][Cc][Dd][Ee][Ff][Gg][Hh][Ii][Jj][Kk][Ll][Mm][Nn][Oo][Pp][Qq][Rr][Ss][Tt][Uu][Vv][Ww][Xx][Yy][Zz]
   static String ola = "[Oo][Ll][Aa]";
   static String threeToFiveA = "[Aa]{3,5}";
   static String nonAlphaNumerical = "[\\W]{2,}";
   static String amountOfEmails = "[A-Za-z 0-9_.%+-]+@[0-9A-Za-z._-]+\\.[A-Za-z]{2,3}";
   static String differentTypeOfPhoneNumbers = "[0-9]{1,3}[\s/-]?[0-9]{6,}"; // det bör finnas 6 siffor efter riktnumret
   static String sameLettersOfFour = "([A-Za-z])\\1{3,3}";
   static String regPlates = "[A-Za-z]{3,3}[/_][0-9]{3,3}";
   static String parenthesisMatched = "[(][A-Za-z]+[)]|[(][0-9]+[)]|[(][\s]+[)]"; // denna i kommentaren hittar 2 strängar till i parenteser [(][A-Za-z0-9\s]+[)]
   static String findIfAndForLoop = "if[(]+[)]|for[(]|if[(]|for[(]+[)]";

    public static void main(String[] args) throws FileNotFoundException {

        // ========================= 1 ============================= //
        System.out.println("Part 1:");
        System.out.print("The alphabet: ");
        globalCounter = 0;
        fileReader(alphabet, true);

        // ========================= 2 ============================= //
        globalCounter = 0;
        System.out.println("Part 2:");
        System.out.print("Ola is found at: ");
        fileReader(ola, true);
        // ========================= 3 ============================= //
        globalCounter = 0;
        System.out.println("Part 3:");
        fileReader(threeToFiveA, false);
        System.out.println("There is: " + globalCounter + " a's at the interval of 3-5");
        // ========================= 4 ============================= //
        globalCounter = 0;
        System.out.println("Part 4:");
        fileReader(nonAlphaNumerical, false);
        System.out.println("Amount of non alphabetical numerics : " + globalCounter);
        // ========================= 5 ============================= //
        globalCounter = 0;
        System.out.println("Part 5:");
        fileReader(amountOfEmails, false);
        fileReader(amountOfEmails,true);
        System.out.println("Amount of emails in txt: " + globalCounter);
        // ========================= 6 ============================= //
        globalCounter = 0;
        System.out.println("Part 6:");
        fileReader(differentTypeOfPhoneNumbers,false);
        fileReader(differentTypeOfPhoneNumbers,true);
        System.out.println("Amount of phone numbers: " + globalCounter);
        // ========================= 7 ============================= //
        globalCounter = 0;
        System.out.println("Part 7:");
      //  fileReader(sameLettersOfFour, true);
        fileReader(sameLettersOfFour,false);
        System.out.println("Amount of words with same 4 letters: " + globalCounter);
        // ========================= 8 ============================= //
        globalCounter = 0;
        System.out.println("Part 8:");
        fileReader(regPlates, true);
        // ========================= 9 ============================= //
        globalCounter = 0;
        System.out.println("Part 9:");
        fileReader(parenthesisMatched,true);
        // ========================= 10 ============================= //
        globalCounter = 0;
        System.out.println("Part 10:");
        fileReader(findIfAndForLoop, true);
    }

    public static void fileReader(String inputPattern, boolean bool)throws FileNotFoundException{
        File myFile = new File("src\\text.txt");
        Scanner fileScan = new Scanner(myFile);
        rowCounter = 1;
        while (fileScan.hasNextLine()){
            String currentLineAsString = fileScan.nextLine();

            finder(currentLineAsString, inputPattern, bool);
            rowCounter++;
        }
            fileScan.close();
    }

    public static void finder(String myString, String pattern, boolean bool){

        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(myString);
        while (matcher.find()){
            if (bool){
                if (matcher.group().length() != 0){
                    System.out.print(matcher.group() + " is found at line: " + rowCounter);
                    System.out.print(" Start: " + matcher.start() + " ");
                    System.out.println("End: " + matcher.end());
                }
            }

            if (!bool){
                globalCounter++;
            }

        }
    }
}

