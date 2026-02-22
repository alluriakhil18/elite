// Imagine you're the curator of an ancient manuscript archive. Each manuscript is 
// assigned a unique significance score, and the archive is arranged so that every 
// manuscript on the left has a lower score and every manuscript on the right has 
// a higher score, forming a special ordered display. Now, for an upcoming exhibition, 
// scholars have decided that only manuscripts with significance scores between 
// low and high (inclusive) are relevant. Your task is to update the archive by 
// removing any manuscripts whose scores fall outside the range [low, high]. 

// Importantly, while you remove some manuscripts, you must preserve the relative 
// order of the remaining ones—if a manuscript was originally displayed as a 
// descendant of another, that relationship should remain intact. It can be proven 
// that there is a unique way to update the archive.

// Return the main manuscript of the updated archive. Note that the main manuscript 
// (the root) may change depending on the given score boundaries.

// Input format:
// Line 1: space separated scores to build the manuscript archive
// Line 2: two space seperated integers, low and high.

// Output format:
// space separated scores of the updated archeive

// Example 1:
// input=
// 1 0 2
// 1 2
// output=
// 1 2

// Explanation:
// Initial archieve:
//       1
//      / \
//     0   2


// Updated archieve:
//     1
//      \
//       2
// After removing manuscripts scores below 1 (i.e. 0), only the manuscript with 1
// and its right manuscript 2 remain.

// Example 2:
// input=
// 3 0 4 2 1
// 1 3
// output=
// 3 2 1

// Explanation:
// Initial archieve:
//           3
//          / \
//         0   4
//          \
//           2
//          /
//         1

// Updated archieve:
//       3
//      /
//     2
//    /
//   1

import java.util.*;
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val=val;
    }
}
class Solution2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] inp=sc.nextLine().split(" ");
        int low=sc.nextInt();
        int high=sc.nextInt();
        int len=inp.length;
        int[] arr=new int[len];
        for(int i=0;i<len;i++) arr[i]=Integer.parseInt(inp[i]);
        TreeNode root=new TreeNode(arr[0]);
        for(int i=1;i<len;i++){
            insert(arr[i],root);
        }
   
        root=reduceTree(root,low,high);
        printTree(root);
        
    }
    public static void insert(int num,TreeNode root){
        if(num<root.val){
            if(root.left!=null) insert(num,root.left);
            else {
                root.left=new TreeNode(num);
                return;
            }
        }else{
            if(root.right!=null) insert(num,root.right);
            else {
                root.right=new TreeNode(num);
                return;
            }
        }
        
    }
    public static void printTree(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int len=q.size();
            for(int i=0;i<len;i++){
                TreeNode curr=q.poll();
                System.out.print(curr.val+" ");
                if(curr.left!=null) q.offer(curr.left);
                if(curr.right!=null) q.offer(curr.right);
            }
        }
    }
    public static TreeNode reduceTree(TreeNode root,int low,int high){
        if(root==null) return null;
        if(root.val<low) return reduceTree(root.right,low,high);
        if(root.val>high) return reduceTree(root.left,low,high);
        root.left=reduceTree(root.left,low,high);
        root.right=reduceTree(root.right,low,high);
        return root;
    }
    
}