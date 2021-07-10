



/*
------------------------------------PROBLEM------------------------------
Given an integer array nums, return the length of the longest strictly increasing subsequence.
A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements.
For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

Example 1:

	Input: nums = [10,9,2,5,3,7,101,18]
	Output: 4
	Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:

	Input: nums = [0,1,0,3,2,3]
	Output: 4

Example 3:

	Input: nums = [7,7,7,7,7,7,7]
	Output: 1
 

Constraints:

	1 <= nums.length <= 2500
	-104 <= nums[i] <= 104
-------------------------------------------------------------------------------
----------------------------------SOLUTION-------------------------------------
The idea is to extend this problem to longest common subsequence. Since we need to find a sorted subsequence,
we can create a new array with DISTINCT elements sort it & apply the longest common subsequence problem between our initial array & the sorted array.

-------------------------------------------------------------------------------
*/
class Solution {
    public int lengthOfLIS(int[] nums1) {
        int n = nums1.length;
        Set<Integer> set = new HashSet();
        for(int i=0;i<n;set.add(nums1[i++]));
        int k =0;
        int[] nums2 = new int[n];
        for(int x:set)
            nums2[k++] = x;
        
        Arrays.sort(nums2);
        int greatest=0;
        int[][] dp = new int[n+1][n+1];
        for(int i=0;i<n+1;dp[0][i++]=0);
        for(int i=0;i<n+1;dp[i++][0]=0);
        for(int i=1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                if(nums1[i-1] == nums2[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                    greatest = Math.max(greatest, dp[i][j]);
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return greatest;
    }
}