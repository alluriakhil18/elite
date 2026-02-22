// xA Kid built a structure using building blocks, 
// by placing the building-blocks adjacent to each other.

// A building-block is a vertical alignment of blocks.
// 	                            ___
// here one block each represents  as |___|.

// The following structure made up of using building blocks

//                           ___
//                       ___|___|    ___
//                      |___|___|_w_|___|___              ___
//                   ___|___|___|___|___|___| w   _w_  w |___| 
//               ___|___|___|___|___|___|___|_w__|___|_w_|___|____________
    
//                0  1   3   4   2   3    2   0   1   0   2

// Once the structure is completed, kid pour water(w) on it.

// You are given a list of integers, heights of each building-block in a row.
//  Now your task How much amount of water can be stored by the structure.

//  Input Format:
//  -------------
//  Space separated integers, heights of the blocks in the structure. 

// Output Format:
//  --------------
//  Print an integer, 
  
// Sample Input:
// -------------
//  0 1 3 4 2 3 2 0 1 0 2
    
// Sample Output:
// --------------
// 6
    
// Explanation:
// ------------
// In the above structure,  6 units of water (w represents the water in the structure)
// can be stored.

import java.util.*;
class Solution1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] inp=sc.nextLine().split(" ");
        int len=inp.length;
        int[] arr = new int[len];
        for(int i=0;i<len;i++) arr[i]=Integer.parseInt(inp[i]);
        int maxInd=0;
        for(int i=1;i<arr.length;i++){
            if(arr[i]>arr[maxInd]){
                maxInd=i;
            }
        }
        int count=0;
        int leftMax=arr[0];
        for(int i=1;i<maxInd;i++){
            if(arr[i]>leftMax){
                leftMax=arr[i];
            }else count+=leftMax-arr[i];
        }
        int rightMax=arr[len-1];
        for(int i=len-2;i>maxInd;i--){
            if(arr[i]>rightMax){
                rightMax=arr[i];
            }else count+=rightMax-arr[i];
        }

        System.out.print(count);
        
    }
}