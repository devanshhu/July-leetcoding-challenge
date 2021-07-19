/*
-----------------------PROBLEM--------------------------------

You are given an array arr which consists of only zeros and ones, divide the array into three non-empty parts such that all of these parts represent the same binary value.
If it is possible, return any [i, j] with i + 1 < j, such that:

	arr[0], arr[1], ..., arr[i] is the first part,
	arr[i + 1], arr[i + 2], ..., arr[j - 1] is the second part, and
	arr[j], arr[j + 1], ..., arr[arr.length - 1] is the third part.
	All three parts have equal binary values.
	If it is not possible, return [-1, -1].

Note that the entire part is used when considering what binary value it represents. For example, [1,1,0] represents 6 in decimal, not 3. Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.

 

Example 1:

	Input: arr = [1,0,1,0,1]
	Output: [0,3]

Example 2:

	Input: arr = [1,1,0,1,1]
	Output: [-1,-1]

Example 3:

	Input: arr = [1,1,0,0,1]
	Output: [0,2]
 

Constraints:

	3 <= arr.length <= 3 * 104
	arr[i] is 0 or 1

---------------------------------------------------------------

----------------------SOLUTION---------------------------------
If we need to find the occurences. we must first confirm if the count of 1s in the string are divisible by 3 or not. If not divisible, no ans is possible.
else we will find first n/3 occurence of the number & search for its doppleganger parts in the array. 

---------------------------------------------------------------

*/

class Solution {
  public int[] threeEqualParts(int[] arr) {
    int[] ans = new int[] {-1,-1};

    // num Of 1
    int numOf1s = 0;
    for (int el : arr) {
      numOf1s += el;
    }

    if (numOf1s == 0) {
      return new int[]{0, 2};
    }

    if (numOf1s % 3 != 0) {
      return ans;
    }

    int noOfOnesInEachPart = numOf1s / 3;
    int indexOfFirst1InPart0 = -1;
    int indexOfFirst1InPart1 = -1;
    int indexOfFirst1InPart2 = -1;
    numOf1s = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == 1) {
        numOf1s++;
        if (numOf1s == noOfOnesInEachPart + 1) {
          indexOfFirst1InPart1 = i;
        } else if (numOf1s == 2 * noOfOnesInEachPart + 1) {
          indexOfFirst1InPart2 = i;
        } else if (numOf1s == 1) {
          indexOfFirst1InPart0 = i;
        }
      }
    }

    while (indexOfFirst1InPart2 < arr.length) {
      if (arr[indexOfFirst1InPart2] == arr[indexOfFirst1InPart0] && arr[indexOfFirst1InPart2] == arr[indexOfFirst1InPart1]) {
        indexOfFirst1InPart2++;
        indexOfFirst1InPart1++;
        indexOfFirst1InPart0++;
      } else {
        return ans;
      }
    }

    return new int[]{indexOfFirst1InPart0 - 1, indexOfFirst1InPart1};
  }
}
