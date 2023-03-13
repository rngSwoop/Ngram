/* 
 * LanguageModel.java
 *
 * Implements methods for training a language model from a text file,
 * writing a vocabulary, and randomly completing sentences 
 *
 * Students may only use functionality provided in the packages
 *     java.lang
 *     java.util 
 *     java.io
 * 
 * Use of any additional Java Class Library components is not permitted 
 * 
 * GARRETT KING
 * Winter 2022
 *
 */
import java.io.File;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
//import java.util.IOException;
//import java.util.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LanguageModel {
    HashMap<String,Double> p;         // maps ngrams to conditional probabilities
    ArrayList<String> vocab;          // stores the unique words in the input text
    int maxOrder;                     // maximum n-gram order to compute
    java.util.Random generator;       // a random number generator object
    
    // Constructor
    
    // LanguageModel
    // Preconditions:
    //  - textFilename is the name of a plaintext training file
    //  - maxOrder is the maximum n-gram order for which to estimate counts
    //  - generator is java.util.Random object
    //  - vocabFilename is the name where the vocab file will be written
    //        vocabFilename can also be null
    //  - countsFilename is the name where the counts will be written
    //        countsFilename can also be null
    // Postconditions:
    //  - this.p maps ngrams (h,w) to the the maximum likelihood estimates 
    //    of P(w|h) for all n-grams up to maxOrder 
    //    Only non-zero probabilities should be stored in this map
    //  - this.vocab contains each word appearing in textFilename exactly once
    //    in case-insensitive ascending alphabetic order
    //  - this.maxOrder is assigned maxOrder
    //  - this.generator is assigned generator
    //  - If vocabFilename is non-null, the vocabulary words are printed to it,
    //    one per line, in order
    //  - If countsFilename is non-null, the ngram counts words are printed to
    //    countsFilename, in order each line has the ngram, then a tab, then
    //    the number of times that ngram appears these should be printed in
    //    case-insensitive ascending alphabetic order by the n-grams
    // Notes:
    //  - n-gram and history counts should be computed with a call to getCounts
    //  - File saving should be accomplished by calls to saveVocab and saveCounts
    //  - convertCountsToProbabilities should be used to then get the probabilities
    //  - If opening any file throws a FileNotFoundException, print to standard error:
    //        "Error: Unable to open file " + filename
    //        (where filename contains the name of the problem file)
    //      and then exit with value 1 (i.e. System.exit(1))
    public LanguageModel( String textFilename, int maxOrder, java.util.Random generator, String vocabFilename, String countsFilename ) {
         
         //create file object associated with textFilename. pass to scanner. wrap creating file object in try/catch block
         File text = null;
         try {
            text = new File(textFilename);
         } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open file" + textFilename);
            System.exit(1);
         }
         Scanner s = new Scanner(text);
         ArrayList<String> vocab = new ArrayList<String>();
         this.maxOrder = maxOrder;
         this.generator = generator;
         HashMap<String,Integer> ngramCounts = new HashMap<String,Integer>();
         HashMap<String,Integer> historyCounts = new HashMap<String,Integer>();

         getCounts(s, ngramCounts, historyCounts, vocab, this.maxOrder); //vocab is passed to getCounts and populated
         
         //this.p = convertCountsToProbabilities(ngramCounts, historyCounts);
         
         //saveVocab(); //sometime later
         //saveCounts(); //sometime later
        return;
    }

    // Accessors

    // getMaxOrder
    // Preconditions:
    //  - None
    // Postconditions:
    //  - this.maxOrder is returned
    public int getMaxOrder() {
        return this.maxOrder;
    }

    // randomCompletion
    // Preconditions:
    //  - history contains an initial history to complete
    //  - order is the n-gram order to use when completing the sentence
    // Postconditions:
    //  - history must not be modified (i.e. make a copy of it)
    //  - Starting with an empty string, until </s> or <fail> is drawn:
    //    1) Draw a new word w according to P(w|h)
    //    2) Append a space and then w to the string you're accumulating
    //    3) w is added to the history h
    //    Once </s> or <fail> is reached, append it to the string and return the string
    // Notes:
    //  - Call randomNextWord to draw each new word
    public String randomCompletion( ArrayList<String> history, int order ) {
        return null;
    }

    // Private Helper Methods

    // saveVocab
    // Preconditions:
    //  - vocabFilename is the name where the vocab file will be written, or null
    // Postconditions:
    //  - if null, do nothing
    //  - else, this.vocab contains each word appearing in textFilename exactly
    //    once in case-insensitive ascending alphabetic order
    //  - If opening the file throws a FileNotFoundException, print to standard error:
    //        "Error: Unable to open file " + vocabFilename
    //      and then exit with value 1 (i.e. System.exit(1))
    private void saveVocab(String vocabFilename) {
         //create vocab file and write to it
         File vocab = null;
         try {
            vocab = new File(vocabFilename);
         } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open file" + vocabFilename);
            System.exit(1);
         }
         
         //print out line by line each word
         if (vocab != null) {
            
         }
        return;
    }

    // saveCounts
    // Preconditions:
    //  - countsFilename is the name where the counts will be written, or null
    //  - ngramCounts.get(ngram) returns the number of times ngram appears
    //    (ngrams with count 0 are not included)
    // Postconditions:
    //  - If countsFilename is non-null, the ngram counts words are printed to
    //    countsFilename, each line has the ngram, then a tab, then the number
    //    of times that ngram appears. ngrams should be printed in
    //    case-insensitive ascending alphabetic order
    // Notes:
    //  - If opening the file throws a FileNotFoundException, print to standard error:
    //       "Error: Unable to open file " + countsFilename
    //      and then exit with value 1 (i.e. System.exit(1))
    private void saveCounts(String countsFilename, HashMap<String,Integer> ngramCounts) {
         //create counts file and write to it
         File counts = null;
         try {
            counts = new File(countsFilename);
         } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open file" + countsFilename);
            System.exit(1);
         }
         
         //write vocab with counts to file
         if (counts != null) {
         
         }
        return;
    }

    // randomNextWord
    // Preconditions:
    //  - history is the history on which to condition the draw
    //  - order is the order of n-gram to use 
    //      (i.e. no more than n-1 history words)
    //  - this.generator is the generator passed to the constructor
    // Postconditions:
    //  - A new word is drawn (see assignment description for the algorithm to use)
    //  - If no word follows that history for the specified order, return "<fail>"
    // Notes:
    //  - The nextDouble() method draws a random number between 0 and 1
    //  - ArrayList has a subList method to return an array slice
    private String randomNextWord( ArrayList<String> history, int order) {
        return "";
    } 

    // getCounts
    // Preconditions:
    //  - input is an initialized Scanner object associated with the text input file
    //  - ngramCounts is an empty (but non-null) HashMap
    //  - historyCounts is an empty (but non-null) HashMap
    //  - vocab is an empty (but non-null) ArrayList
    //  - maxOrder is the maximum order n-gram for which to extract counts
    // Postconditions:
    //  - ngramCounts.get(ngram) contains the number of times that ngram
    //    appears in the input ngram must be 2+ words long (e.g. "<s> i")
    //  - historyCounts.get(history) contains the number of times that ngram
    //    history appears in the input histories can be a single word (e.g.,
    //    "<s>")
    //  - vocab contains each word (token) in the input file exactly once, in
    //    case-insensitive ascending alphabetic order
    // Notes:
    //  - You may find it useful to implement helper function incrementHashMap
    //    and use it
    private void getCounts(java.util.Scanner input, HashMap<String,Integer> ngramCounts, HashMap<String,Integer> historyCounts, ArrayList<String> vocab, int maxOrder) {
         //iterate through input and place each ngram in ngramCounts HashMap and history Hashmap incrementing count each time from n=2 to max order
         
         //rethink: Read next line. put into array. loop through and work with historycount and ngramcount in same loop.
         while (input.hasNextLine()) {
            ArrayList<String> sentence = new ArrayList<String>();
            sentence = stringToArray(input.nextLine());
            for (int i = 1; i < maxOrder; i++) {
               for (int j = i; j <= sentence.size(); j++) {
                  String ngram = new String("" + sentence.get(j));
                  String history = new String("");
                  for (int n = 1; n <= i; n++) {
                     ngram = sentence.get(j - n) + ngram;
                     history = sentence.get(j - n) + history;
                  }
               incrementHashMap(ngramCounts, ngram);
               incrementHashMap(historyCounts, history);      
               }
            }
         }
         
         //add words to vocab list
         while (input.hasNext()); {
            vocab.add(input.next() + "\n");
         }
         java.util.Collections.sort(vocab); //if not case-insensitive, change the way its called
        return;
    }

    // convertCountsToProbabilities
    // Preconditions:
    //  - ngramCounts.get(ngram) contains the number of times that ngram
    //    appears in the input
    //  - historyCounts.get(history) contains the number of times that ngram
    //    history appears in the input
    // Postconditions:
    //  - this.p.get(ngram) contains the conditional probability P(w|h) for
    //    ngram (h,w) only non-zero probabilities are stored in this.p
    private void convertCountsToProbabilities(HashMap<String,Integer> ngramCounts, HashMap<String,Integer> historyCounts) {
         //this.p = ngramCounts.get(value) / historyCounts.get(value); //should this be .get(value)?
        return;
    }

    // incrementHashMap
    // Preconditions:
    //  - map is a non-null HashMap 
    //  - key is a key that may or may not be in map
    // Postconditions:
    //  - If key was already in map, map.get(key) returns 1 more than it did before
    //  - If key was not in map, map.get(key) returns 1
    // Notes
    //  - This method is useful, but optional
    private void incrementHashMap(HashMap<String,Integer> map, String key) {
        int count = map.get(key);
        if (map.get(key) != null) {
            map.put(key, count + 1);
        }
        else {
            count = 1;
            map.put(key, count);
        }    
    }

    // Static Methods

    // arrayToString
    // Preconditions:
    //  - sequence is a List (e.g. ArrayList) of Strings
    // Postconditions:
    //  - sequence is returned in string form, each element joined by a single space
    //  - If sequence was length 0, the empty string is returned
    // Notes:
    //  - Already implemented for you
    public static String arrayToString(List<String> sequence) {
        java.lang.StringBuilder builder = new java.lang.StringBuilder();
        if( sequence.size() == 0 ) {
            return "";
        }
        builder.append(sequence.get(0));
        for( int i=1; i<sequence.size(); i++ ) {
            builder.append(" " + sequence.get(i));
        }
        return builder.toString();
    }

    // stringToArray
    // Preconditions: 
    //  - s is a string of words, each separated by a single space
    // Postconditions:
    //  - An ArrayList is returned containing the words in s
    // Notes:
    //  - Already implemented for you
    public static ArrayList<String> stringToArray(String s) {
        return new ArrayList<String>(java.util.Arrays.asList(s.split(" ")));
    }
}
