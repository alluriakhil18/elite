// There are some pages in a website, each page links with atmost two other pages.
// Each page displays a number on it. The complete website is given as binary tree 
// using the level order insertion technique.

// You need to return the number of pages where the number in the page is equal to 
// the sum of the numbers of its descendants. A descendant refers to any page that 
// is linked but lower down the tree stucture of the website, no matter how many 
// levels lower.

// Input Format:
// -------------
// Single line of space separated integers, numbers displayed in web-pages as Tree.

// Output Format:
// --------------
// Print an integer result.


// Sample Input-1:
// ---------------
// 11 3 5 2 1

// Sample Output-1:
// ----------------
// 2


// Sample Input-2:
// ---------------
// 3 2 1 0 0

// Sample Output-2:
// ----------------
// 3

// Explanation:
// ------------
// For the pages diplaying the number 0: The sum of descendants is 0,
// since they have no descendant pages.

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
    static int count;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] inp = sc.nextLine().split(" ");
        int l=inp.length;
        int[] arr = new int[l];
        for(int i=0;i<l;i++) arr[i]=Integer.parseInt(inp[i]);
        TreeNode root=buildTree(arr);
        count=0;
        int sum=checkSum(root,0);
        System.out.print(count);
        
    }
    public static TreeNode buildTree(int[] arr){
        if(arr.length==0 &&arr[0]==-1) return null;
        TreeNode root=new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i=1;
        while(i<arr.length){
            TreeNode curr=q.poll();
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
    public static int checkSum(TreeNode root,int sum){
        if(root==null) return 0;
        int left= checkSum(root.left,sum);
        int right=checkSum(root.right,sum);
        if(left+right==root.val) count++;
        return left+right+root.val;
    }
}