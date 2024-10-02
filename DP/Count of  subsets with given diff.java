//{ Driver Code Starts
import java.io.*;
import java.util.*;


class IntArray
{
    public static int[] input(BufferedReader br, int n) throws IOException
    {
        String[] s = br.readLine().trim().split(" ");
        int[] a = new int[n];
        for(int i = 0; i < n; i++)
            a[i] = Integer.parseInt(s[i]);

        return a;
    }

    public static void print(int[] a)
    {
        for(int e : a)
            System.out.print(e + " ");
        System.out.println();
    }

    public static void print(ArrayList<Integer> a)
    {
        for(int e : a)
            System.out.print(e + " ");
        System.out.println();
    }
}

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t;
        t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            
            int n;
            n = Integer.parseInt(br.readLine());
            
            
            int d;
            d = Integer.parseInt(br.readLine());
            
            
            int[] arr = IntArray.input(br, n);
            
            Solution obj = new Solution();
            int res = obj.countPartitions(n, d, arr);
            
            System.out.println(res);
            
        }
    }
}

// } Driver Code Ends


class Solution {
    static final int MOD = 1000000007; // Modulo as per the problem statement
    
    public static int countPartitions(int n, int d, int[] arr) {
        int totalSum = 0;
        
        // Calculate total sum of the array
        for (int i = 0; i < n; i++) {
            totalSum += arr[i];
        }
        
        // If (totalSum + d) is odd, we can't partition the array
        if ((totalSum + d) % 2 != 0 || totalSum < d) {
            return 0; // No valid partition exists
        }
        
        int requiredSum = (totalSum + d) / 2;
        
        // Count the number of subsets with sum equal to requiredSum
        return countOfSubsetSum(n, requiredSum, arr);
    }
    
    public static int countOfSubsetSum(int n, int sum, int[] arr) {
        // DP array to store the number of ways to get each sum
        int[][] dp = new int[n + 1][sum + 1];
        
        // Initialize base case: There is always one way to form sum 0 (by taking no elements)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        
        // Fill the dp array
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (arr[i - 1] <= j) {
                    // Include the current element or exclude it
                    dp[i][j] = (dp[i - 1][j - arr[i - 1]] + dp[i - 1][j]) % MOD;
                } else {
                    // Exclude the current element
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        // Return the result for the entire array and the required sum
        return dp[n][sum];
    }
}
