// Imagine you're playing a fantasy video game where secret level codes unlock new 
// worlds. These codes are strings made up of letters, and a level code is only 
// considered valid if every shorter code formed by its leading characters has been
// discovered along the journey. In other words, a code is unlockable only when 
// all of its prefixes are also present in your collection.

// Given a list of strings representing the level codes you’ve collected, find the 
// longest valid level code such that every prefix of that code is in the list. If 
// there is more than one valid code of the same length, choose the one that comes 
// first in alphabetical order. If no such code exists, return an empty string.

// Input Format
// -------------
// Line1: Space separated codes (strings)
 
// Output Format
// --------------
// string 


// Example 1:
// ----------
// Input:
// m ma mag magi magic magici magicia magician magicw
// Output: 
// "magician"

// Explanation: The code "magician" is unlockable because every prefix—"m", "ma", 
// "mag", "magi", "magic", "magici", and "magicia"—is present in the list. Although 
// "magicw" appears too, it fails since its prefix "magica" is missing.


// Example 2:
// ----------
// Input:
// a banana app appl ap apply apple
// Output: 
// "apple"  

// Explanation: Both "apple" and "apply" have every prefix in the list, but "apple" 
// is comes first in alphabetical order.

// Example 3:
// ----------
// Input: 
// abc bc ab abcd
// Output: 
// ""

import java.util.*;
class TrieNode{
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}
class Trie{
    TrieNode root = new TrieNode();
    void insert(String word){
        TrieNode curr = root;
        for(char c:word.toCharArray()){
            int idx = c -'a';
            if(curr.children[idx]==null){
                curr.children[idx]=new TrieNode();
            }
            curr=curr.children[idx];
        }
        curr.isEnd=true;
    }
    boolean isValid(String word){
        TrieNode curr = root;
        for(char c:word.toCharArray()){
            int ind=c-'a';
            curr=curr.children[ind];
            if(curr==null || !curr.isEnd) return false;
        }
        return true;
    }
}
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] words = sc.nextLine().split(" ");
        Trie trie = new Trie();
        for(String w:words){
            trie.insert(w);
        }
        String ans="";
        for(String w:words){
            if(trie.isValid(w)){
                if(w.length()>=ans.length() && w.compareTo(ans)<0) ans=w;
            }
        }
        System.out.print(ans);
    }
    
}