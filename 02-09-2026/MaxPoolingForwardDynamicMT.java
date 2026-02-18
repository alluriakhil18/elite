// You are given a 2-dimensional integer matrix representing a feature map obtained 
// from a convolutional layer of a neural network. Your task is to compute the Max 
// Pooling – Forward Pass on this matrix using Java multithreading.

// Max Pooling reduces the spatial dimensions of the input matrix by sliding a fixed-size
// window over it and selecting the maximum value from each window.

// The computation must be performed in parallel, where multiple threads cooperate to 
// compute different parts of the output matrix.

// Pooling Rules
// -------------
// 	Pooling window size is K × K
// 	Stride is S
// 	No padding is applied
// 	Windows move from left to right and top to bottom

// The output matrix dimensions are:
// 	Output Rows  = (H − K) / S + 1
// 	Output Cols  = (W − K) / S + 1
// where H × W is the size of the input matrix.

// Multithreading Requirement
// --------------------------
// 	The forward pooling computation must be parallelized
// 	Each thread should compute one or more output rows
// 	The final output must be deterministic and ordered

// Input Format
// ------------
// H W
// H lines each containing W space-separated integers
// K
// S

// Input Description
// -----------------
// H	  Number of rows in the input matrix
// W	  Number of columns in the input matrix
// K	  Pooling window size
// S	  Stride

// Output Format
// -------------
// Output matrix with each row printed on a new line
// Each row contains space-separated integers

// Sample Input
// ------------
// 4 4
// 1 3 2 4
// 5 6 1 2
// 0 2 4 1
// 3 1 0 2
// 2
// 2

// Sample Output
// -------------
// 6 4     
// 3 4

// Explanation 
// --------------
// A 2 × 2 window slides over the matrix with stride 2
// From each window, the maximum value is selected
// These maximum values form the output matrix

// Constraints
// --------------
// 1 ≤ H, W ≤ 1000
// 1 ≤ K ≤ min(H, W)
// 1 ≤ S ≤ K
// Input values are valid integers
// (H − K) and (W − K) are divisible by S



import java.util.concurrent.*;
import java.util.Scanner;
import java.util.*;

class PoolTask implements Callable<Integer>{
    
    private int row;
    private int col;    
    private int psize;
    private int[][] mat;
    
    // Constructor
    public PoolTask(int row, int col, int psize, int [][] mat){
        // IMPLEMENT YOUR CODE
        this.row = row;
        this.col = col;
        this.psize = psize;
        this.mat = mat;
    }
    
    @Override
    public Integer call() throws Exception{
        // IMPLEMENT YOUR CODE
        int max=Integer.MIN_VALUE;
        for(int i=row;i<row+psize;i++){
            for(int j=col;j<col+psize;j++){
                max=Math.max(max,mat[i][j]);
            }
        }
        return max;
    }
}

public class MaxPoolingForwardDynamicMT {

    /* ==================================
       Implement Your Code using Callable
       ================================== */
       
    public static int [][] maxPoolForward(int [][] input, int poolSize, int stride){
        
        int n = input.length;
        int m = input[0].length;
        
        int on = (n-poolSize)/stride+1;
        int om = (m-poolSize)/stride+1;
        
        int [][] ans = new int[on][om];
        
        int tot = on*om;
        
        // Thread pool
        ExecutorService executor = Executors.newFixedThreadPool(tot);   // IMPLEMENT YOUR CODE
        
        List<PoolTask> tasks = new ArrayList<>();
        List<Future<Integer>> futures = new ArrayList<>();
        
        // Create tasks
        for (int i = 0; i <= n-poolSize; i+=stride){
            for (int j = 0; j <= m-poolSize; j+=stride){
                // IMPLEMENT YOUR CODE
                tasks.add(new PoolTask(i,j,poolSize,input));
            }
        }
        
        // Submit tasks
        for (PoolTask task : tasks){
            // IMPLEMENT YOUR CODE
            futures.add(executor.submit(task));
        }
        
        int z = 0;
        
        try{
            // Collect results
            for (int i=0; i<on; i++){
                for(int j=0; j<om; j++){
                    // IMPLEMENT YOUR CODE
                    ans[i][j]=futures.get(z++).get();
                }    
            }
        }
        catch(Exception e){
            System.out.print(e);   
        }    
        
        // Shutdown executor
        // IMPLEMENT YOUR CODE
        
        return ans;
    }
    
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Read matrix dimensions
        int H = sc.nextInt();
        int W = sc.nextInt();

        int[][] input = new int[H][W];

        // Read matrix
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                input[i][j] = sc.nextInt();
            }
        }

        // Read pooling parameters
        int poolSize = sc.nextInt();
        int stride = sc.nextInt();

        int[][] output =
                maxPoolForward(input, poolSize, stride);

        // Print output
        for (int[] row : output) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}
