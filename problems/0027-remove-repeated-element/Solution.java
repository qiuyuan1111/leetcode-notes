/**
 * LeetCode 27. Remove Element
 * https://leetcode.cn/problems/remove-element/
 *
 * Approach: Two pointers (fast/slow)
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int slow = 1;
        for (int fast = 1; fast<nums.length; fast++) {
            if(nums[fast] != nums[fast-1]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
  
