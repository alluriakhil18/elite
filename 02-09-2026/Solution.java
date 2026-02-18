// VishnuVardan is working with Decision Trees for AI-based predictions.
// To analyze alternative outcomes, Kishore has planned to flip the decision 
// tree horizontally to simulate a reverse processing approach.

// Rules for Flipping the Decision Tree:
// 	- The original root node becomes the new rightmost node.
// 	- The original left child becomes the new root node.
// 	- The original right child becomes the new left child.
// This transformation is applied level by level recursively.

// Note:
// ------
// - Each node in the given tree has either 0 or 2 children.
// - Every right node in the tree has a left sibling sharing the same parent.
// - Every right node has no further children (i.e., they are leaf nodes).

// Your task is to help VishnuVardan flip the Decision Tree while following 
// the given transformation rules.

// Input Format:
// -------------
// Space separated integers, nodes of the tree.

// Output Format:
// --------------
// Print the list of nodes of flipped tree as described below.


// Sample Input-1:
// ---------------
// 4 2 3 5 1

// Sample Output-1:
// ----------------
// 5 1 2 3 4


// Sample Input-2:
// ---------------
// 4 2 5

// Sample Output-2:
// ----------------
// 2 5 4

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class Solution1
{
    static TreeNode prev = new TreeNode(-1);
    public static TreeNode helper(TreeNode root)
    {
        if(root==null || root.left==null) return root;
        
       TreeNode newRoot = helper(root.left);
       prev = root.left;
       
       newRoot.right = root;
       prev.left = root.right;
      
       
       root.left = null;
       root.right = null;
       
    
       return newRoot;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        
        int n = strs.length;
        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(strs[i]);
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int idx = 1;

        while(!queue.isEmpty() && idx < n)
        {
            TreeNode temp = queue.poll();
            if(idx < n && arr[idx] != -1) {
                temp.left = new TreeNode(arr[idx]);
                queue.offer(temp.left);
            }
            idx++;

            if(idx < n && arr[idx] != -1) {
                temp.right = new TreeNode(arr[idx]);
                queue.offer(temp.right);
            }
            idx++;
        }
        
        TreeNode node = helper(root);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(node);
        while(!q.isEmpty())
        {
            TreeNode temp = q.poll();
            System.out.print(temp.val + " ");
            if(temp.left!=null)
            {
                q.offer(temp.left);
            }
            
            if(temp.right!=null)
            {
                q.offer(temp.right);
            }
        }

    }
}

