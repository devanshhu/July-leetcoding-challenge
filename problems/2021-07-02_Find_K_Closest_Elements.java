/*
------------------------------------PROBLEM------------------------------
https://leetcode.com/problems/find-k-closest-elements
========

Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
An integer a is closer to x than an integer b if:
	i)   |a - x| < |b - x|, or
	ii)  |a - x| == |b - x| and a < b
 
Example 1:
	Input: arr = [1,2,3,4,5], k = 4, x = 3
	Output: [1,2,3,4]

Example 2:
	Input: arr = [1,2,3,4,5], k = 4, x = -1
	Output: [1,2,3,4]

Constraints:
	1 <= k <= arr.length
	1 <= arr.length <= 104
	arr is sorted in ascending order.
	-104 <= arr[i], x <= 104
-------------------------------------------------------------------------
------------------------------------SOLUTION-----------------------------
There are multiple solutions to this problem:
	1.  The most basic solution is to use custom comparator & sort elements based on their diff with x, then sort them. The first K elements are the answer.
	
	2.  The approach we are using here uses binary search to find the element's index. 
		Then iterating whether we should include element that is left to our current position or the one to the right.
		We calculate the diff between our item & the left element & our item with right element 
		Then select the one which has least diff.
		If diff is same we pick the smaller element. Include the element & then slide towards the edge.   
			-> A better approach would be to just keep track of the left & right pointer instead of inserting them right away.
			Then convert them to list & return that list.
 -------------------------------------------------------------------------
*/

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int idx = 0, i =1, n = arr.length;
        // do a binary search & return the index
        idx = binarySearch(arr, x);
        List<Integer> list = new LinkedList<>();
        // [1,2,3,4,5,6,8,9,10] & elem is 7 then our [left] = 6 & [right] = 8.
        // Now iterate over & add elements to our final list.
        int left = idx, right = idx+1;   
        // iterate while our final list is smaller than k.
        while(list.size() < k){
        	// If diff of our X & left element is smaller or equal to the diff of X & right element, include the left element
            if(left >= 0 && right < n && (x - arr[left]) <= (arr[right] - x)){
                list.add(arr[left--]);    
            } 
            //If diff of our X & left element is greater than diff of X & right element, include our right element.
            else if(left >= 0 && right < n && (x - arr[left]) > (arr[right] - x)){
                list.add(arr[right++]);
            }
            // If we have exhausted the left subarray, then include elements from the right, since K <= arr.length, we know we wont hit OutOfBounds.
            else if(left < 0 && right < n){
                list.add(arr[right++]);
            }
            // If we have exhausted our right subarray, then include elements from the left.
            else if(right >= n && left >=0 ){
                list.add(arr[left--]);
            }
        
        }
        //Sort the list as we have inserted elements doing comparison & the array is not sorted in ASC order.
        Collections.sort(list);
        return list;
    }
    
    // This method returns the index of element if it is present.
    // If it is not present it returns the index after which the element should be inserted.
    // [1,2,4] & elem is 3 it returns index of 2 i.e. `1`. 
    public int binarySearch(int[] arr, int item){
        if(item < arr[0])
            return 0;
        else if(item > arr[arr.length-1])
            return arr.length-1;
        int left = 0, right = arr.length-1;
        int mid ;
        while(left < right){
            mid = (right + left)/2;
            if(arr[mid] == item)
                return mid;
            else if(arr[mid] < item)
                left = mid+1;
            else if(arr[mid] > item)
                right = mid-1;
        }
        
        return right - 1 < 0 ? 0 : right -1 ;
    }
}	
