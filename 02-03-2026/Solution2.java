// Balbir Singh is working with Binary Trees.
// The elements of the tree are given in level-order format.

// Balbir is observing the tree from the right side, meaning he 
// can only see the rightmost nodes (one node per level).

// You are given the root of a binary tree. Your task is to determine 
// the nodes visible from the right side and return them in top-to-bottom order.

// Refernce Node:
// --------------
// class TreeNode {
//     Integer val;
//     TreeNode left, right;
    
//     TreeNode(Integer val) {
//         this.val = val;
//         this.left = this.right = null;
//     }
// }


// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the node values visible from the right side


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 3, 5]



// Sample Input-2:
// ---------------
// 3 2 4 3 2

// Sample Output-2:
// ----------------
// [3, 4, 2]

import java.util.*;
public class Solution2{
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val=val;
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] inputarray = input.split(" ");
        int n =inputarray.length;
        int[] arr = new int[inputarray.length];
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(inputarray[i]);
        List<Integer> list = new ArrayList<>();
        TreeNode root=buildTree(arr);
        printRight(root,list);
        for(int i=0;i<list.size();i++) System.out.print(list.get(i)+" ");
        
    }
    public static void printRight(TreeNode root,List<Integer> list){
        if(root==null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int len=q.size();
            for(int i=0;i<len;i++){
                TreeNode curr=q.poll();
                if(curr.left!=null) q.offer(curr.left);
                if(curr.right!=null) q.offer(curr.right);
                if(i==len-1) list.add(curr.val);
            }
        }
    }
    public static TreeNode buildTree(int[] arr){
        if(arr.length==0 || arr[0]==-1) return null;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        q.offer(root);
        int i=1;
        while (!q.isEmpty() && i<arr.length){
            TreeNode curr = q.poll();
            if(i<arr.length && arr[i]!=-1){
                curr.left = new TreeNode(arr[i]);
                q.offer(curr.left);
            }
            i++;
            if(i<arr.length && arr[i]!=-1){
                curr.right = new TreeNode(arr[i]);
                q.offer(curr.right);
            }
            i++;
        }
        return root;
    }
}