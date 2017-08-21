package coding.autocomplete;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class WordFeq {

    /**
     * Function to initialized buffer using relative file name
     * @param relativeFileName - relative file from file
     * @return BufferReader instance
     * @throws FileNotFoundException
     */
    public static BufferedReader getBufferReader(String relativeFileName)
            throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(relativeFileName)));

    }

    /**
     * Support function which is used to get prefix from std.in and print auto complete word
     * in ascending words
     * @param sc - std in
     * @param dict - dict
     */
    private static void readAndPrintAutoComplete(Scanner sc, Trie dict) {
        System.out.println("Provide String for auto complete...");
        String searchString = sc.nextLine();

        List<WordWithRank> autoCompleteWords = dict.getAutoCompleteWords(searchString);
        if (autoCompleteWords.size() > 0) {
            System.out.println("---- Auto complete list of words:  ----");
        } else {
            System.out.println("Failed to find matching words");
        }
        for (WordWithRank word : autoCompleteWords) {
            System.out.println(word.getWord() + "(" + String.valueOf(word.getRank()) + ")");
        }
        System.out.println("-----------");
    }

    /**
     * Main function
     * @param args - list of args
     */
    public static void main(String[] args) {
        try {
            BufferedReader bf = getBufferReader("./sample-files/" + "word-frequencies.txt");
            if (bf == null) {
                throw new Exception("Failed to initialized file buffer reader");
            }
            Scanner sc = new Scanner(System.in);
            Trie dict = new Trie();
            String temp;
            int line = 1;
            while ((temp = bf.readLine()) != null) {
                dict.addWordWithRank(temp, line);
                line++;
            }
            while (true) {
                readAndPrintAutoComplete(sc, dict);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
