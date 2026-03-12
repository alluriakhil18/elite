// Imagine you are the curator of a historic library, where books are arranged in a 
// unique catalog system based on their publication years. The library’s archive is 
// structured like a hierarchical tree, with each book’s publication year stored at 
// a node. You are given the nodes of this catalog tree starting with main node
// and a list of query years.

// For each query year, you need to find two publication years:
// - The first is the latest year in the archive that is less than or equal to the 
//   query year. If no such book exists, use -1.
// - The second is the earliest year in the archive that is greater than or equal 
//   to the query year. If no such book exists, use -1.

// Display the results as an list of pairs, where each pair corresponds to a query.

// Example 1:
// ----------
// Input: 
// 2006 2002 2013 2001 2004 2009 2015 2014
// 2002 2005 2016

// Output:
// [[2002, 2002], [2004, 2006], [2015, -1]] 

// Archive Structure:

//           2006
//          /    \
//      2002     2013
//      /   \     /   \
//   2001  2004  2009  2015
//                      /
//                   2014
                  
// Explanation:  
// - For the query 2002, the latest publication year that is ≤ 2002 is 2002, and 
//   the earliest publication year that is ≥ 2002 is also 2002.  
// - For the query 2005, the latest publication year that is ≤ 2005 is 2004, and 
//   the earliest publication year that is ≥ 2005 is 2006.  
// - For the query 2016, the latest publication year that is ≤ 2016 is 2015, but 
//   there is no publication year ≥ 2016, so we output -1 for the second value.

// Example 2:
// ----------
// Input:  
// 2004 2009
// 2003

// Output:
// [[-1, 2004]]

// Explanation:  
// - For the query 2003, there is no publication year ≤ 2003, while the earliest 
//   publication year that is ≥ 2003 is 2004.

// Constraints:
// - The total number of books in the archive is in the range [2, 10^5].
// - Each publication year is between 1 and 10^6.
// - The number of queries n is in the range [1, 10^5].
// - Each query year is between 1 and 10^6.
import java.util.*;
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val){
        this.val=val;
    }
}
public class Solution2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] inp = sc.nextLine().split(" ");
        String[] check = sc.nextLine().split(" ");
        int[] n = new int[inp.length];
        int[] m = new int[check.length];
        for(int i=0;i<n.length;i++) n[i]=Integer.parseInt(inp[i]);
        for(int i=0;i<m.length;i++) m[i]=Integer.parseInt(check[i]);
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<n.length;i++){
            min=Math.min(min,n[i]);
            max=Math.max(max,n[i]);
        }
        TreeNode root = new TreeNode(n[0]);
        for(int i=1;i<n.length;i++){
            insertTree(root,n[i]);
        }
        for(int i=0;i<m.length;i++){
            if(m[i]<min) System.out.print("[-1, "+min+"]");
            else if(m[i]>max) System.out.print("["+max+", -1]");
            if(findNode(root,m[i])){
                System.out.print("["+m[i]+", "+m[i]+"]");
            }
            
        }
        
    }
    public static void insertTree(TreeNode root,int num){
        if(root.left==null && root.right==null){
            if(root.val>num){
                root.left=new TreeNode(num);
            }else root.right = new TreeNode(num);
            return;
        }
        else if(root.left==null && root.val>num){
            root.left=new TreeNode(num);
            return;
        }
        else if(root.right==null && root.val<num){
            root.right=new TreeNode(num);
            return;
        }else{
            if(root.val>num) insertTree(root.left,num);
            else if(root.val<num) insertTree(root.right,num);
        }
    }
    public static boolean findNode(TreeNode root,int num){
        if(root.val==num) return true;
        else if(root.val>num){
            if(root.left!=null) return findNode(root.left,num);
        } 
        else if(root.val<num){
            if(root.right!=null) return findNode(root.right,num);
        } 
        return false;
    }
    
}