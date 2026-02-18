// Charlie Brown is working with strings,
// He is a given a string S. He wants to find out the maximum substring 'MaxSub'.

// MaxSub is a substring which appears atleast twice in S with Maximum length. 

// Your task is to help Charlie Brown to find the Maximum Substring MaxSub,
// and print the length of MaxSub. If there is MaxSub, return 0.

// Input Format:
// -------------
// A string S.

// Output Format:
// --------------
// Print an integer, length of MaxSub


// Sample Input-1:
// ---------------
// babababba

// Sample Output-1:
// ----------------
// 5

// Explanation: 
// ------------
// The Maximum substring is 'babab' , which occurs 2 times.


// Sample Input-2:
// ---------------
// abbbbba

// Sample Output-2:
// ----------------
// 4

// Explanation: 
// ------------
// The Maximum substring is 'bbbb' , which occurs 2 times.


// Sample Input-3:
// ---------------
// vignesh

// Sample Output-3:
// ----------------
// 0

import java.util.*;
class Solution1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String inp=sc.nextLine();
        int len = inp.length();
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<len;i++){
            for(int j=i+1;j<=len;j++){
                String key=inp.substring(i,j);
                map.put(key,map.getOrDefault(key,0)+1);
            }
        }
        int max=0;
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(entry.getValue()>1){
                max=Math.max(max,entry.getKey().length());
            }
        }
        if(max==1) System.out.print(0);
        else System.out.print(max);
    }
    
}