// Balbir Singh is working with Binary Trees.
// The elements of the tree is given in the level order format.
// Balbir has a task to split the tree into two parts by removing only one edge
// in the tree, such that the product of sums of both the splitted-trees should be maximum.

// You will be given the root of the binary tree.
// Your task is to help the Balbir Singh to split the binary tree as specified.
// print the product value, as the product may be large, print the (product % 1000000007)
	
// NOTE: 
// Please do consider the node with data as '-1' as null in the given trees.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6

// Sample Output-1:
// ----------------
// 110

// Explanation:
// ------------
// if you split the tree by removing edge between 1 and 4, 
// then the sums of two trees are 11 and 10. So, the max product is 110.


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 6

// Sample Output-2:
// ----------------
// 100

//         3
//     2       4
// 3       2   -1  6
    
import java.util.*;
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
    }
}
class Solution1{
    public static TreeNode buildTree(int[] arr){
        if(arr.length==0 || arr[0]==-1) return null;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        q.offer(root);
        int i=1;
        while(i<arr.length && !q.isEmpty()){
            int len=q.size();
            for(int j=0;j<len;j++){
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
        }
        return root;
        
    }
    public static int findSum(TreeNode root){
        if(root==null) return 0;
        int sum=0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int len = q.size();
            for(int i=0;i<len;i++){
                TreeNode curr = q.poll();
                sum+=curr.val;
                if(curr.left!=null) q.offer(curr.left);
                if(curr.right!=null) q.offer(curr.right);
            }
        }
        return sum;
    }
    public static int Traversal(TreeNode root){
        if(root==null) return 0;
        int max=Integer.MIN_VALUE;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int len = q.size();
            for(int i=0;i<len;i++){
                TreeNode curr = q.poll();
                TreeNode temp1=curr.right;
                curr.right=null;
                int a=findSum(curr);
                int b=findSum(temp1);
                max=Math.max(max,a*b);
                curr.right=temp1;
                TreeNode temp2=curr.left;
                curr.left=null;
                int c=findSum(curr);
                int d=findSum(temp2);
                max=Math.max(max,c*d);
                curr.left=temp2;
                if(curr.left!=null) q.offer(curr.left);
                if(curr.right!=null) q.offer(curr.right);
                
            }
        }
        return max;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] inp =sc.nextLine().split(" ");
        int n = inp.length;
        int[] arr = new int[n];
        for(int i=0;i<n;i++) arr[i] = Integer.parseInt(inp[i]);
        TreeNode root = buildTree(arr);
        System.out.print(Traversal(root)%1000000007);
    }
}