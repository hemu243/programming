package coding.autocomplete;

import java.util.*;

/**
 * Tries Node which is used to build tries
 * Assumption - only store ASCII small chars in the tries
 */
public class Trie {

    /**
     * Root of the dictionary
     */
    private final TrieNode root;

    /**
     * Initialize
     */
    public Trie() {
        // Root
        root = new TrieNode('*');
    }

    /**
     * Add word with rank
     * @param word
     * @param rank
     */
    public void addWordWithRank(String word, int rank) {
        root.addWord(word, rank);
    }

    /**
     * Get auto complete list
     * @param str - prefix
     * @return list of words with ascending rank order
     */
    public List<WordWithRank> getAutoCompleteWords(String str) {
        List<WordWithRank> words = root.getAutoCompleteWordList(str);
        if(words == null) {
            return null;
        }
        Collections.sort(words);
        return words;
    }
}
