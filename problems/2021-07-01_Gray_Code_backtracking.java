/*
-------------------------------------------------PROBLEM---------------------------------
An n-bit gray code sequence is a sequence of 2n integers where:
Every integer is in the inclusive range [0, 2n - 1],
The first integer is 0,
An integer appears no more than once in the sequence,
The binary representation of every pair of adjacent integers differs by exactly one bit, and
The binary representation of the first and last integers differs by exactly one bit.
Given an integer n, return any valid n-bit gray code sequence.

 

Example 1:

	Input: n = 2
	Output: [0,1,3,2]
	Explanation:
		The binary representation of [0,1,3,2] is [00,01,11,10].
		- 00 and 01 differ by one bit
		- 01 and 11 differ by one bit
		- 11 and 10 differ by one bit
		- 10 and 00 differ by one bit
		[0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
		- 00 and 10 differ by one bit
		- 10 and 11 differ by one bit
		- 11 and 01 differ by one bit
		- 01 and 00 differ by one bit
Example 2:

	Input: n = 1
	Output: [0,1]
----------------------------------------------------------------------------------------------
------------------------------------------SOLUTION--------------------------------------------
The idea is to backtrack & form all the combinations, since at each step we are changing only a single bit. The constraints are met.
ALGO :
	1. Initialize a global variable with 0. Also maintain a list that contains our number.
	2. Call our recurse funtion with value n. We are following top down approach. So we will take the base case where our n is 0. If n is zero( we are at the leaf of recursion) we will add the number to the list.
	3. first recurse with (n-1) that will carry the combination with bit value 0. Then flip the (n-1)th bit & again recurse, this will cover our case of bit value 1.
	4. return the list (result in our case). 
----------------------------------------------------------------------------------------------
*/

class Solution {
    List<Integer> result;
    int numbr = 0 ;
    public List<Integer> grayCode(int n) {
        this.result = new LinkedList<>();
        recurse(n);
        return this.result;
        
    
    }
    
    public void recurse(int n){
       if(n == 0){
           result.add(numbr);
           return ;
       }
        
        recurse(n-1);
        numbr = numbr ^ (1 << (n-1));
        recurse(n-1);
    }
}