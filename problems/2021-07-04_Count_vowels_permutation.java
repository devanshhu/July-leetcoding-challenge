/*
------------------------------------PROBLEM------------------------------
Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
	Each vowel 'a' may only be followed by an 'e'.
	Each vowel 'e' may only be followed by an 'a' or an 'i'.
	Each vowel 'i' may not be followed by another 'i'.
	Each vowel 'o' may only be followed by an 'i' or a 'u'.
	Each vowel 'u' may only be followed by an 'a'.
Since the answer may be too large, return it modulo 10^9 + 7.

 

Example 1:

	Input: n = 1
	Output: 5
	Explanation: All possible strings are: "a", "e", "i" , "o" and "u".

Example 2:

	Input: n = 2
	Output: 10
	Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".

Example 3: 

	Input: n = 5
	Output: 68
 

Constraints:   
	1 <= n <= 2 * 10^4
-------------------------------------------------------------------------------
-----------------------SOLUTION -----------------------------------------------
The idea is to use backtracking with memoization & try all combinations to find the answer. 
We've used switch-case to handle all the mentioned conditions
-------------------------------------------------------------------------------

*/

class Solution {
	private Map<String,Long> map = new HashMap<>();;
	private int MOD = 1000000007;

	public int countVowelPermutation(int n) {
		long total = 0;
		char[] charSet = new char[]{'a','e','i','o','u'};
		for(char c : charSet) {
			total = (total + helper(n-1,c)) % MOD;
		}
		return (int)total;
	}

	public long helper(int remaingChar, char prevChar) {
		if(remaingChar == 0) return 1;
		String key = "" + remaingChar + prevChar;
		if(map.containsKey(key)) return map.get(key);
		long total = 0;
		switch(prevChar) {
			case 'a': total = (helper(remaingChar - 1,'e')) % MOD;
			break;
			case 'e': total = (helper(remaingChar - 1,'a') + helper(remaingChar - 1, 'i')) % MOD;
			break;
			case 'i': total = (helper(remaingChar - 1,'a') + helper(remaingChar - 1, 'e') + helper(remaingChar - 1, 'o') + helper(remaingChar - 1, 'u')) % MOD;
			break;
			case 'o': total = (helper(remaingChar - 1,'i') + helper(remaingChar - 1,'u')) % MOD;
			break;
			case 'u': total = (helper(remaingChar - 1,'a')) % MOD;
			break;
		}
		map.put(key , total);
		return total;
	}
}