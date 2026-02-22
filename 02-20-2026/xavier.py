# // During neural network training, poor weight initialization can cause:
# //     - Vanishing gradients (activations shrink across layers)
# //     - Exploding gradients (activations grow uncontrollably)

# // To prevent this, Xavier (Glorot) initialization scales weights based on layer 
# // size to maintain stable variance across forward and backward passes 


# // You are given:
# //     - A raw weight matrix W whose values lie in the range [0, 1]
# //     - fan_in — number of input units
# //     - fan_out — number of output units

# // Your task is to transform the raw matrix into a Xavier-uniform initialized matrix.

# // For Xavier Uniform Formula look at the HINT

# // 📥 Input Format
# // r c
# // W11 W12 ... W1c
# // W21 W22 ... W2c
# // ...
# // Wr1 Wr2 ... Wrc
# // fan_in fan_out

# // r = number of rows

# // c = number of columns

# // All weight values are floats in [0, 1]

# // 📤 Output Format

# // Print the scaled matrix with values rounded to 4 decimal places.


# // Sample Input-1
# // --------------
# // 2 3
# // 0.0 0.5 1.0
# // 0.25 0.75 0.5
# // 3 2

# // Sample Output-1:
# // -----------------
# // -1.0954 0.0000 1.0954
# // -0.5477 0.5477 0.0000

# // Comput Limit: L= sqrt(6/(3+2)) = sqrt(6/5) = sqrt(1.2) =1.0954
# // Apply Mapping: 0.5 → 0.5 × 2 × 1.0954 − 1.0954 = 0



# // Sample Input-2
# // --------------
# // 1 4
# // 0.2 0.4 0.6 0.8
# // 4 4

# // Sample Output-2
# // ---------------
# // -0.5196 -0.1732 0.1732 0.5196

# // Compute Limit
# // 𝐿= Sqrt(6/(8)) = sqrt(0.75) = 0.8660


# // ⚙️ Constraints
# // ---------------
# //     1 ≤ r, c ≤ 1000
# //     fan_in ≥ 1
# //     fan_out ≥ 1
# //     Time complexity must be O(r × c)

import math
import numpy as np
def xavier_scale(W, fan_in, fan_out):
    limit=np.sqrt(6/(fan_in+fan_out))
    for i in range(len(W)):
        for j in range(len(W[0])):
            W[i][j]= W[i][j]*2*limit-limit
    
    return W      


# Main method
def main():
    r, c = map(int, input().split())
    
    W = []
    for _ in range(r):
        row = list(map(float, input().split()))
        W.append(row)
    
    fan_in, fan_out = map(int, input().split())
    
    result = xavier_scale(W, fan_in, fan_out)
    
    for row in result:
        print(" ".join(f"{val:.4f}" for val in row))


if __name__ == "__main__":
    main()