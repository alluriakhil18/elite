// riurA concert organizer has n stage lights, each with a certain brightness level.
// The lights must be placed in a circular arrangement around the stage.

// To ensure visual comfort, the organizer wants to minimize the maximum brightness 
// difference between any two adjacent lights.

// You are given an array brightness[] of size n, representing the brightness levels.

// Your task is to determine the minimum possible value of the maximum absolute 
// difference between adjacent lights in the circular arrangement.

// 📥 Input Format
// ---------------
// First line contains integer n
// Next line n space seperated integers, brightness values

// 📤 Output Format
// ----------------
// Print a single integer representing the smallest possible maximum adjacent difference.

// 🔒 Constraints
// --------------
// 2 ≤ n ≤ 10^5
// 1 ≤ brightness[i] ≤ 10^9


// Sample Input-1
// --------------
// 5
// 1 2 3 4 5.  

// Sample Output-1
// ---------------
// 2

// Explanation
// -----------
// Optimal circular arrangement: 1 3 5 4 2
// Maximum adjacent difference = 2


// Sample Input-2
// ---------------
// 6
// 10 3 7 12 5 9.    3 5 7 9 10 12

// Sample Output-2
// ---------------
// 4

// Explanation
// -----------
// Best circular order: 3 7 10 12 9 5
// Maximum adjacent difference = 4

import java.util.*;
class Solution3{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++) arr[i]=sc.nextInt();
        Arrays.sort(arr);
        int[] ans=new int[n];
        int l=0;
        int r=arr.length-1;
        for(int i=0;i<n;i++){
            if(i%2==0 || i==0) ans[l++]=arr[i];
            else ans[r--]=arr[i];
        }
        
        int maxDiff=Math.abs(ans[0]-ans[n-1]);
        for(int i=1;i<n-1;i++){
            maxDiff=Math.max(maxDiff,Math.abs(ans[i]-ans[i-1]));
            
        }
        System.out.println(maxDiff);
    }
}