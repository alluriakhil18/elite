// The Indian Army has established multiple military camps at strategic locations 
// along the Line of Actual Control (LAC) in Galwan. These camps are connected in 
// a hierarchical structure, with a main base camp acting as the root of the network.

// To fortify national security, the Government of India has planned to deploy 
// a protective S.H.I.E.L.D. that encloses all military camps forming the outer 
// boundary of the network.

// Structure of Military Camps:
//     - Each military camp is uniquely idxentified by an integer ID.
//     - A camp can have at most two direct connections:
//         - Left connection → Represents a subordinate camp on the left.
//         - Right connection → Represents a subordinate camp on the right.
//     - If a military camp does not exist at a specific position, it is 
//       represented by -1
	
// Mission: Deploying the S.H.I.E.L.D.
// Your task is to determine the list of military camp IDs forming the outer 
// boundary of the military network. This boundary must be traversed in 
// anti-clockwise order, starting from the main base camp (root).

// The boundary consists of:
// 1. The main base camp (root).
// 2. The left boundary:
//     - Starts from the root’s left child and follows the leftmost path downwards.
//     - If a camp has a left connection, follow it.
//     - If no left connection exists but a right connection does, follow the right connection.
//     - The leftmost leaf camp is NOT included in this boundary.
// 3. The leaf camps (i.e., camps with no further connections), ordered from left to right.
// 4. The right boundary (in reverse order):
//     - Starts from the root’s right child and follows the rightmost path downwards.
//     - If a camp has a right connection, follow it.
//     - If no right connection exists but a left connection does, follow the left connection.
//     - The rightmost leaf camp is NOT included in this boundary.

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
// space separated integers, military-camp IDs.

// Output Format:
// --------------
// Print all the military-camp IDs, which are at the edge of S.H.I.E.L.D.


// Sample Input-1:
// ---------------
// 5 2 4 7 9 8 1

// Sample Output-1:
// ----------------
// [5, 2, 7, 9, 8, 1, 4]


// Sample Input-2:
// ---------------
// 11 2 13 4 25 6 -1 -1 -1 7 18 9 10

// Sample Output-2:
// ----------------
// [11, 2, 4, 7, 18, 9, 10, 6, 13]


// Sample Input-3:
// ---------------
// 1 2 3 -1 -1 -1 5 -1 6 7

// Sample Output-3:
// ----------------
// [1, 2, 7, 6, 5, 3]


import java.util.*;
public class Solution3{
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
        String[] inparr=input.split(" ");
        int n=inparr.length;
        int[] arr = new int[n];
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(inparr[i]);
        TreeNode root=buildTree(arr);
        System.out.print(root.val+" ");
       
        printleft(root);
        printleaf(root);
        printright(root.right);
    
    }
    public static TreeNode buildTree(int[] arr){
        if(arr.length==0 || arr[0]==-1) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i=1;
        while(!q.isEmpty() && i<arr.length){
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
   public static void printleft(TreeNode root){
       TreeNode curr=root.left;
       while(curr!=null){
           if(curr.left!=null && curr.right!=null) System.out.print(curr.val+" ");
           if(curr.left!=null) curr=curr.left;
           else curr=curr.right;
       }
    }
    public static void printleaf(TreeNode root){
        if(root==null) return;
        printleaf(root.left);
        if(root.left==null && root.right==null) System.out.print(root.val+" ");
        
        printleaf(root.right);
    }
    public static void printright(TreeNode root){
        
        if(root==null) return;
        if(root.left==null && root.right==null) return;
        printright(root.right);
        
        System.out.print(root.val+" ");
    }
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
   
}