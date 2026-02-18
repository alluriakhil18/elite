// At university of Chicago a Computer Science programing faculty as a part of 
// teaching passion, in order to make newly joined students more enthusiastic 
// in learning the subject he is given a problem at the first day of semester.
// The student who solved it first, will be awarded with a cash prize. In regard 
// to this he asked the students to work on concept related to strings, he gave a 
// task to read a word and find the count of all the turn of phrases of the word, 
// and the phrases should be distinct.

// Now it’s your time to create a method which satisfies the above program.
// The turn of phrases of a word is obtained by deleting 
// any number of characters (possibly zero) from the front of the word and
// any number of characters (possibly zero) from the back of the word.

// Input Format:
// -------------
// A single string, the word contains only lowercase alphabets [a-z].

// Output Format:
// --------------
// Print an integer, number of distinct phrases possible.


// Sample Input-1:
// ---------------
// aabbaba

// Sample Output-1:
// ----------------
// 21

// Explanation:
// -------------
// The turn of phrases of the word, which are distinct as follows:
// a, b, aa, bb, ab, ba, aab, abb, bab, bba, aba, aabb, abba, bbab, baba, 
// aabba, abbab, bbaba, aabbab, abbaba, aabbaba


// Sample Input-2:
// ---------------
// kmithyd

// Sample Output-2:
// ----------------
// 28
import java.util.*;
class TrieNode{
    TrieNode[] child = new TrieNode[26];
    boolean end=false;
}
class Trie{
    TrieNode root;
    public Trie(){
        root=new TrieNode();
    }
    public int insert(String w){
        int count=0;
        TrieNode curr=root;
        for(char c:w.toCharArray()){
            int ind=c-'a';
            if(curr.child[ind]==null){
                curr.child[ind]=new TrieNode();
                count++;
            }
            curr=curr.child[ind];
                
        }
        return count;
    }
}
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String w= sc.nextLine();
        int ans=0;
        Trie trie = new Trie();
        for(int i=0;i<w.length();i++){
            
            for(int j=i;j<w.length();j++){
                ans+=trie.insert(w.substring(i,j+1));
            }
        }
        System.out.print(ans);
    }
}
