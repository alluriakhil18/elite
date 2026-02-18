// Imagine you are a librarian organizing books on vertical shelves in a grand 
// library. The books are currently scattered across a tree-like structure, where 
// each book (node) has a position determined by its shelf number (column) and 
// row number (level).

// Your task is to arrange the books on shelves so that:
// 1. Books are placed column by column from left to right.
// 2. Within the same column, books are arranged from top to bottom (i.e., by row).
// 3. If multiple books belong to the same shelf and row, they should be arranged 
// from left to right, just as they appear in the original scattered arrangement.

// Example 1:
// Input:
// 3 9 20 -1 -1 15 7
// Output: 
// [[9],[3,15],[20],[7]]

// Explanation:
//          3
//        /   \
//       9     20
//           /    \
//          15     7

// Shelf 1: [9]
// Shelf 2: [3, 15]
// Shelf 3: [20]
// Shelf 4: [7]


// Example 2:
// Input:
// 3 9 8 4 0 1 7
// Output: 
// [[4],[9],[3,0,1],[8],[7]]

// Explanation:
//           3
//        /     \
//       9       8
//     /   \   /   \
//    4     0 1     7

// Shelf 1: [4]
// Shelf 2: [9]
// Shelf 3: [3, 0, 1]
// Shelf 4: [8]
// Shelf 5: [7]

// Library Organization Rules:
// 1. Each column represents a shelf from left to right.
// 2. Books on the same shelf are arranged from top to bottom.
// 3. If books share the same position, they are arranged left to right in order 
// of appearance.

import java.util.*;
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val=val;
    }
}
class Pair{
    TreeNode val;
    int row;
    int col;
    Pair(TreeNode val,int col,int row){
        this.val=val;
        this.col=col;
        this.row=row;
    }
}
class Solution{
    public static TreeNode buildTree(int[] arr){
        if(arr.length==0 || arr[0]==-1) return null;
        TreeNode root=new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i=1;
        while(!q.isEmpty() && i<arr.length){
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
        return root;
    }
    public static void traversal(TreeNode root,TreeMap<Integer, TreeMap<Integer, ArrayList<Integer>>> map){
        if(root==null) return;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root,0,0));
        while(!q.isEmpty()){
            Pair curr=q.poll();
            TreeNode tree = curr.val;
            int r=curr.row;
            int c=curr.col;
            map.putIfAbsent(c,new TreeMap<>());
            map.get(c).putIfAbsent(r,new ArrayList<>());
            map.get(c).get(r).add(tree.val);
            if(tree.left!=null) q.offer(new Pair(tree.left,c-1,r+1));
            if(tree.right!=null) q.offer(new Pair(tree.right,c+1,r+1));
        }
        
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] inp = sc.nextLine().split(" ");
        int n=inp.length;
        int[] arr = new int[n];
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(inp[i]);
        TreeNode root =buildTree(arr);
        TreeMap<Integer, TreeMap<Integer, ArrayList<Integer>>> map = new TreeMap<>();
        traversal(root,map);
        List<List<Integer>> list = new ArrayList<>();
        for(TreeMap <Integer,ArrayList<Integer>> rows : map.values()){
            ArrayList<Integer> ans = new ArrayList<>();
            for(ArrayList<Integer> cols : rows.values())  ans.addAll(cols);
            list.add(ans);
        }
        System.out.print(list);
        
    }
}