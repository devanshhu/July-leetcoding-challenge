/*
------------------------------------PROBLEM------------------------------
Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

Example 1:

	Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
	Output: 3
	Explanation: The repeated subarray with maximum length is [3,2,1].

Example 2:

	Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
	Output: 5

Constraints:

	1 <= nums1.length, nums2.length <= 1000
	0 <= nums1[i], nums2[i] <= 100
-------------------------------------------------------------------------------
----------------------------------SOLUTION-------------------------------------
The problem is an extension of the longest common subsequence problem. We can use dynamic programming to find an optimal solution.
We will create a 2d-matrix that keeps the track of longest repeated subarray. 
With that we will also keep a var "greatest", which keeps track of the longest common sequence till now. At the end of iteration, we will have the desired number.

--------------------------------------------------------------------------------

class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int greatest = 0;
        int dp[][] = new int[n+1][m+1];
        for(int i=0;i<n;dp[i++][0] = 0);
        for(int i=0;i<m;dp[0][i++] = 0);
        for(int i=1;i<= n;i++){
            for(int j=1;j<=m;j++){
                if(nums1[i-1] == nums2[j-1]){
                    dp[i][j] = dp[ i - 1][ j - 1 ] + 1 ;
                    greatest = Math.max(greatest, dp[i][j]);
                }else{
                    dp[i][j] = 0;
                }
            }
        }        
        return greatest;
    }
}