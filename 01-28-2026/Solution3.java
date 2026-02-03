// Write a program to construct a binary tree from level-order input, while treating -1 
// as a placeholder for missing nodes. The program reads input, constructs the tree, 
// and provides an in-order traversal to verify correctness.

// Input Format:
// ---------------
// Space separated integers, level order data (where -1 indiactes null node).

// Output Format:
// -----------------
// Print the in-order data of the tree.

// NODE REFERENCE
// --------------
// class Node {
//     int value;
//     Node left, right;

//     public Node(int value) {
//         this.value = value;
//         this.left = null;
//         this.right = null;
//     }
// }


// Sample Input:
// ----------------
// 1 2 3 -1 -1 4 5

// Sample Output:
// ----------------
// 2 1 4 3 5

// Explanation:
// --------------
//     1
//    / \
//   2   3
//      / \
//     4   5


// Sample Input:
// ----------------
// 1 2 3 4 5 6 7

// Sample Output:
// ----------------
// 4 2 5 1 6 3 7

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4  5 6  7

import java.util.*;
public class Solution3{
    public static class TreeNode{
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
        String[] temp=input.split(" ");
        int[] arr = new int[temp.length];
        for(int i=0;i<arr.length;i++) arr[i]=Integer.parseInt(temp[i]);
        TreeNode root= buildTree(arr);
        inorder(root);
    }
    public static TreeNode buildTree(int[] arr){
        if(arr.length==0) return null;
        TreeNode node = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(node);
        int i=1;
        while(!q.isEmpty()){
            TreeNode curr = q.poll();
            if(i<arr.length && arr[i]!=-1){
                curr.left=new TreeNode(arr[i]);
                
                q.offer(curr.left);
            }
            i++;
            if(i<arr.length && arr[i]!=-1){
                curr.right=new TreeNode(arr[i]);
                
                q.offer(curr.right);
            }
            i++;
        }
        return node;
    }
    public static void inorder(TreeNode root){
        if(root==null) return;
        inorder(root.left);
        System.out.print(root.val+" ");
        inorder(root.right);
    }
}