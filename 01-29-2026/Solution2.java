// You are given a crystal with an energy level n. Your goal is to discover all 
// the different ways this crystal could have been created by combining smaller shards.

// Each combination must:
// - Use only shards with energy values between 2 and n - 1.
// - Be represented as a list of shard values whose product equals n.
// - Use any number of shards (minimum 2), and the order is ascending order.

// Your task is to return all unique shard combinations that can multiply together 
// to recreate the original crystal.

// Example 1:
// ---------
// Input:
// 28

// Output:
// [[2, 14], [2, 2, 7], [4, 7]]

// Example 2:
// ----------
// Input:
// 23

// Output:
// []



// Constraints:
// - 1 <= n <= 10^4
// - Only shards with energy between 2 and n - 1 can be used.



import java.util.*;

public class Solution2{
    
    public static void backtrack(int n, int i, List<Integer> l, List<List<Integer>> ll){
        if (n==1){
            if (l.size()>=2){
                ll.add(new ArrayList<>(l));
            }
            return;
        }
        
        for (int x = i; x<=n; x++){
            if (n%x==0){
                l.add(x);
                backtrack(n/x,x,l,ll);
                l.remove(l.size()-1);
            }
        }
    }
    
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<List<Integer>> ll = new ArrayList<>();
        backtrack(n,2,new ArrayList<>(),ll);
        System.out.println(ll);
    }
}