/*
-----------------------PROBLEM--------------------------------
A peak element is an element that is strictly greater than its neighbors.
Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
You may imagine that nums[-1] = nums[n] = -∞.
You must write an algorithm that runs in O(log n) time.

Example 1:

	Input: nums = [1,2,3,1]
	Output: 2
	Explanation: 3 is a peak element and your function should return the index number 2.

Example 2:

	Input: nums = [1,2,1,3,5,6,4]
	Output: 5
	Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6. 
---------------------------------------------------------------

----------------------SOLUTION---------------------------------
Use extended binary search to find the peak of element. We are using peak finding algo here to find any valid peak.
---------------------------------------------------------------
*/

class Solution {

	public int findPeakElement(int[] nums) {
		int left = 0, right = nums.length-1;
		while(left < right){
		    // calculate mid, right - left is used to escape overflow when left & right are large values.
			int mid = left + (right- left)/2;
            //if our [mid] is greater than its right neighbour, then we know we can find the peak at the left bcz [mid] > [mid+1].
			if(nums[mid] > nums[mid+1])
				right = mid;
			else 
				left= mid+1;
		}
		return left;		
	}
}