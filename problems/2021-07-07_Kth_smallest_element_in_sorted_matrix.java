/*
------------------------------------PROBLEM------------------------------
Given an n x n matrix where each of the rows and columns are sorted in ascending order, return the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example 1:

	Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
	Output: 13
	Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13

Example 2:

	Input: matrix = [[-5]], k = 1
	Output: -5
 

Constraints:

	n == matrix.length
	n == matrix[i].length
	1 <= n <= 300
	-109 <= matrix[i][j] <= 109
	All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
	1 <= k <= n2

-------------------------------------------------------------------------------
----------------------------------SOLUTION-------------------------------------
The most naive solution is to create an array & sort the array in ASC order. Then return the Kth element.
TODO : optimize the approach using binary search. 
--------------------------------------------------------------------------------

class Solution {
    public int kthSmallest(int[][] mat, int k) {
        if(mat == null || mat.length == 0 )
            return -1;
        int r = 0, n = mat.length;
        ArrayList<Integer> arr = new ArrayList();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                arr.add(mat[i][j]);
        }
        Collections.sort(arr);
        return arr.get(k-1);
    }
}