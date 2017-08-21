package coding.autocomplete;

import java.util.*;

/**
 * Created by hchoudhary on 8/11/17.
 */

public class TrieNode {

    /**
     * Node char
     */
    private final char ch;

    /**
     * Store each chars in the index
     */
    protected final Map<Character, TrieNode> chars;

    /**
     * Flag if tries end here
     */
    private boolean isEnd;

    /**
     * Rank
     */
    private int rank;

    /**
     * Initializing
     */
    public TrieNode(char c){
        chars = new HashMap<Character, TrieNode>();
        isEnd = false;
        ch = c;
        rank = -1;
    }

    /**
     * Get Rank
     * @return
     */
    public int getRank() {
        return rank;
    }

    /**
     * Set rank
     * @param rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * Add word to dic
     * @param word
     */
    public void addWord(String word, int rank) {
        if(word == null) {
            return;
        }
        this.addNode(0, word, rank);
    }

    /**
     * Add node
     * @param index - index for given string
     * @param word - word to add
     */
    private void addNode(int index, String word, int rank) {
        if(index >= word.length()) {
            this.isEnd = true;
            this.rank = rank;
            return;
        }

        TrieNode trieNode = chars.get(word.charAt(index));
        if(trieNode == null) {
            trieNode = new TrieNode(word.charAt(index));
            chars.put(word.charAt(index), trieNode);
        }
        trieNode.addNode(index+1, word, rank);
    }

    /**
     * Find partial or complete word in the list
     * @param index - index
     * @param str - String to search in the dictionary
     * @return Node if prefix exist otherwise null
     */
    private TrieNode find(int index, String str) {
        if (index >= str.length()) {
            return null;
        }

        // Char does not contains, mean prefix does not exist
        if(!this.chars.containsKey(str.charAt(index))) {
            return null;
        }

        TrieNode trieNode = this.chars.get(str.charAt(index));
        if (index == str.length() - 1) {
            return trieNode;
        } else {
            return trieNode.find(index + 1, str);
        }
    }

    /**
     * Get autocomplete words from given string
     * @param prefix - partial string
     * @return list of auto complete words
     */
    public List<WordWithRank> getAutoCompleteWordList(String prefix) {
        TrieNode node = this.find(0, prefix);

        if(node == null) {
            // word does not exist in the dict
            return null;
        }
        // Hold list of completed words
        List<WordWithRank> words = new ArrayList<WordWithRank>();
        // If current node is word then add it
        if(node.isEnd) {
            words.add(new WordWithRank(prefix, node.getRank()));
        }
        for(TrieNode child: node.chars.values()) {
            child.getAutoCompleteList(prefix, words);
        }
        return words;
    }

    /**
     * Get complete word list from given node
     * @param buffer - prefix of word
     * @param autoCompleteWords - list of words from given node which is prefix
     */
    private void getAutoCompleteList(String buffer,
            List<WordWithRank> autoCompleteWords) {

        buffer = buffer + this.ch;

        if(this.isEnd) {
            autoCompleteWords.add(new WordWithRank(buffer, this.rank));
        }

        for(TrieNode child: this.chars.values()) {
            child.getAutoCompleteList(buffer,
                    autoCompleteWords);
        }
    }
}
