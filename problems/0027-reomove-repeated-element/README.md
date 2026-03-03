# 0027. 移除元素（Remove Element）

题目链接： https://leetcode.cn/problems/remove-element/

## 标签
- 数组
- 双指针（快慢指针）

## 思路
fast 扫描数组，slow 指向下一个可写入位置。
当 nums[fast] != val 时，把它写到 nums[slow]，slow++。

## 复杂度
- 时间：O(n)
- 空间：O(1)

## 易错点
- 返回的是新长度 slow
- 不需要真正删除后面的元素