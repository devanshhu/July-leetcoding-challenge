/*
------------------------------------PROBLEM------------------------------
Given two strings s and t, determine if they are isomorphic.
Two strings s and t are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters.
No two characters may map to the same character, but a character may map to itself.

Example 1:

	Input: s = "egg", t = "add"
	Output: true

Example 2:

	Input: s = "foo", t = "bar"
	Output: false

Example 3:

	Input: s = "paper", t = "title"
	Output: true
 
Constraints:

	1 <= s.length <= 5 * 104
	t.length == s.length
	s and t consist of any valid ascii character.

--------------------------------------------------------------------------
-----------------------------------SOLUTION-------------------------------
The idea is to use two maps(dictionaries) to keep track of mappings from first to second string & second to first string.
This solution wont work without 2 loops( or dictionaries ) because there should be unique mapping from first to second string & second to first string.
Take the example of s = "abcd", t = "aaaa", a single loop code with s -> t mapping will return true for this example, which is wrong.
The reason for this is we are able to map a,b,c,d to a but when we try the reverse( t-> s mapping), we will get false, which is the right ans.
For this reason we are using two maps.


Another solution could be to convert the first & second string based on a black-box function & then compare the two strings, if they are same,return true else false.
The black box function does like this ( "paper", "babel"):
	1. Create a Map & initialize a counter then iterate each index for both the strings.
		a. if curr index is not in map, add counter to our string & add new mapping of <character, counter> to map & do a counter++;
		b. if curr is present, get its index value & add that to our string.
		c. In the end return string.
	our "paper" will convert to 01023 & so will "babel". since they both are equal, we return true, else false.
--------------------------------------------------------------------------
*/
class Solution {
	public boolean isIsomorphic(String s, String t) {
		if(s.length() != t.length())
			return false;
		Map<Character, Character> map = new HashMap<>();
		Map<Character, Character> map2 = new HashMap<>();
		String first = s;
		String sec = t;
		int x = s.length() >= t.length() ? t.length():s.length();
		for(int i=0 ; i < x ; i++){
			if(map.containsKey( first.charAt(i) ) && map.get(first.charAt(i)) !=  sec.charAt(i)){
				return false;
			}else {
				map.put(first.charAt(i) , sec.charAt(i));
			}
		}
		for(int i=0 ; i < x ; i++){
			if(map2.containsKey( sec.charAt(i) ) && map2.get(sec.charAt(i)) !=  first.charAt(i)){
				return false;
			}else {
				map2.put(sec.charAt(i) , first.charAt(i));
			}
		}
		return true;   
	}
}