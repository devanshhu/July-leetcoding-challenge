
/*
------------------------------------PROBLEM------------------------------
A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

	"AAJF" with the grouping (1 1 10 6)
	"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

In addition to the mapping above, an encoded message may contain the '*' character, which can represent any digit from '1' to '9' ('0' is excluded). For example, the encoded message "1*" may represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19". Decoding "1*" is equivalent to decoding any of the encoded messages it can represent.

Given a string s containing digits and the '*' character, return the number of ways to decode it.

Since the answer may be very large, return it modulo 109 + 7.


Example 1:

	Input: s = "*"
	Output: 9
	Explanation: The encoded message can represent any of the encoded messages "1", "2", "3", "4", "5", "6", "7", "8", or "9".
	Each of these can be decoded to the strings "A", "B", "C", "D", "E", "F", "G", "H", and "I" respectively.
	Hence, there are a total of 9 ways to decode "*".

Example 2:

	Input: s = "1*"
	Output: 18
	Explanation: The encoded message can represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19".
	Each of these encoded messages have 2 ways to be decoded (e.g. "11" can be decoded to "AA" or "K").
	Hence, there are a total of 9 * 2 = 18 ways to decode "1*".

Example 3:

	Input: s = "2*"
	Output: 15
	Explanation: The encoded message can represent any of the encoded messages "21", "22", "23", "24", "25", "26", "27", "28", or "29".
	"21", "22", "23", "24", "25", and "26" have 2 ways of being decoded, but "27", "28", and "29" only have 1 way.
	Hence, there are a total of (6 * 2) + (3 * 1) = 12 + 3 = 15 ways to decode "2*".
 

Constraints:

	1 <= s.length <= 105
	s[i] is a digit or '*'.

-------------------------------------------------------------------------------
----------------------------------SOLUTION-------------------------------------
use DP here. The idea is to count every condition & thus use decisions for 1char & 2chars independently.
We have created 2 methods that decode 1char & 2chars on every iteration. The default value of dp[0] is 1. 
For the first character we are using our decodeChar method that decodes a single char at a time.
Then we will iterate from the 2nd character to the end( Note that our dp is len + 1 long, thus 2nd index of dp is actually 1st index of our string, hence the second character).
For each iteration we will calculate decoding of curr char & multiply it to the decoding count of prev position( since it forms permutations).
We also calculate deciding of the curr & its prev chars & multiply it to decoding count of [i-2]  position.
at the end the end of dp array contains the ans.

-------------------------------------------------------------------------------
*/

class Solution {
	public int numDecodings(String s) {
		long[] dp = new long[s.length()+1];
		dp[0] = 1;
		dp[1] = decodeChar(s.charAt(0));
		for(int i=2;i<=s.length();i++){
			char prev = s.charAt(i-2);
			char curr = s.charAt(i-1);
			
			// current char permutations x permutations of last index. since we are taking only single char at a time.
			dp[i] += decodeChar(curr) * dp[i-1];

			// 2 char permutations x permutations of [i-2] index. since we are taking 2 chars at a time.
			dp[i] += decodeTwoChar(prev, curr) * dp[i-2];
			dp[i] %= 1000000007; 

		}
		return (int)dp[s.length()];
	}

	public int decodeChar(char ch){
		switch(ch){

			// If there is a *, there could be 9 possible values.
			case '*' :return 9;
			
			// A single char 0 is not valid.
			case '0' : return 0; 
			
			// Anything else if a digit, thus there could be only 1 value possible for the index. i.e. the digit itself.
			default : return 1;
		}
	}
	public int decodeTwoChar(char prev, char ch){
		switch(prev){

			// If first char is *
			case '*':

			// & second is also *, there could be 15 possible values. [11 - 19] & [21 - 26]. 20 is not possible bcz second '*' could not be 0.
			if(ch == '*')
				return 15;

			//if second char is in range [0...6], there could be 2 values for our first *, 1 & 2, thus return 2.
			else if(ch >= '0' && ch < '7')
				return 2;

			else 
				return 1;

			// If first char is 1
			case '1':

			// If second char is *, there could be 9 possible values .[11 - 19]
			if(ch == '*')
				return 9;

			else 
				return 1;

			// If first char is 2
			case '2':

			// If second char is *. possible values are [21 -26].
			if(ch == '*')
				return 6;
			
			// If second char is in range [0...6], there could be only single possible value.
			else if(ch >= '0' && ch < '7')
				return 1;
			else
				return 0;
			default : 
				return 0;
		}
	}
}