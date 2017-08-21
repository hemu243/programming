package coding.autocomplete;

/**
 * A class which hold word with rank
 */
public class WordWithRank implements Comparable<WordWithRank> {

    /**
     * Word
     */
    private final String word;

    /**
     * Rank
     */
    private final int rank;

    /**
     * Initialized
     * @param word - word
     * @param rank - word rank
     */
    public WordWithRank(String word, int rank) {
        this.word = word;
        this.rank = rank;
    }

    @Override
    public int compareTo(WordWithRank o) {
        return this.rank - o.rank;
    }

    /**
     * Get Word
     * @return
     */
    public String getWord() {
        return word;
    }

    /**
     * Get Rank
     */
    public int getRank() {
        return rank;
    }
}
