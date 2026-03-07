# 0001. 两数之和（Two Sum）

题目链接： https://leetcode.cn/problems/two-sum/

## 标签
- 数组
- 哈希表

## 思路
用 HashMap 记录已经遍历过的数字及其下标：`值 -> 下标`。  
遍历 nums，对于每个 nums[i] 计算补数 complement = target - nums[i]：
- 如果 map 里存在 complement，说明找到了两数之和为 target 的一对下标，直接返回；
- 否则把 nums[i] 和下标 i 放入 map，供后续元素匹配。

## 复杂度
- 时间：O(n)  （遍历一次数组，哈希查找均摊 `O(1)`）
- 空间：O(n)  （哈希表存储 `n` 个元素）

## 易错点
- 返回的是下标，不是数值本身
- 先查 complement 再 put 当前值，避免同一个元素被使用两次
- 题目一般保证有且仅有一个解；若不保证，需要处理无解情况（这里用抛异常）
