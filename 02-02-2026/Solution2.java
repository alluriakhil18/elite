// Given the preorder and postorder traversals of a binary tree, construct 
// the original binary tree and print its level order traversal.

// Input Format:
// ---------------
// Space separated integers, pre order data
// Space separated integers, post order data

// Output Format:
// -----------------
// Print the level-order data of the tree.


// Sample Input:
// ----------------
// 1 2 4 5 3 6 7
// 4 5 2 6 7 3 1

// Sample Output:
// ----------------
// [[1], [2, 3], [4, 5, 6, 7]]

// Explanation:
// --------------
//         1
//        / \
//       2   3.  2
//      / \  / \
//     4   5 6  7 4


// Sample Input:
// ----------------
// 1 2 3
// 2 3 1

// Sample Output:
// ----------------
// [[1], [2, 3]]

// Explanation:
// --------------
//     1
//    / \
//   2  3


import java.util.*;
public class Solution2{
    static int preindex=0;
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val=val;
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String preorder = sc.nextLine();
        String postorder = sc.nextLine();
        String[] prestr = preorder.split(" ");
        int[] pre = new int[prestr.length];
        for(int i=0;i<prestr.length;i++) pre[i]=Integer.parseInt(prestr[i]);
        String[] poststr = postorder.split(" ");
        int[] post = new int[poststr.length];
        for(int i=0;i<poststr.length;i++) post[i]=Integer.parseInt(poststr[i]);
        TreeNode root = buildTree(pre,post,0,post.length-1);
        List<List<Integer>> list = new ArrayList<>();
        levelOrder(root,list);
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }
        
    }
    public static TreeNode buildTree(int[] pre,int[] post,int l,int r){
        if(l>r || preindex>=pre.length) return null;
        TreeNode root = new TreeNode(pre[preindex++]);
        if(l==r) return root;
        int left = pre[preindex];
        int i;
        for(i=0;i<post.length;i++){
            if(post[i]==left) break;
        }
        root.left=buildTree(pre,post,l,i);
        root.right=buildTree(pre,post,i+1,r-1);
        return root;
        
    }
    public static void levelOrder(TreeNode root,List<List<Integer>> list){
        if(root==null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int len=q.size();
            List<Integer> curr=new ArrayList<>();
            for(int i=0;i<len;i++){
                TreeNode temp=q.poll();
                curr.add(temp.val);
                if(temp.left!=null) q.offer(temp.left);
                if(temp.right!=null) q.offer(temp.right);
            }
            list.add(curr);
            
        }
    }
}