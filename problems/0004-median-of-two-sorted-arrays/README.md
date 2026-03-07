# 0004. 寻找两个正序数组的中位数（Median of Two Sorted Arrays）

题目链接： https://leetcode.cn/problems/median-of-two-sorted-arrays/

## 标签
- 数组
- 二分查找
- 分割线（Partition）

## 思路
核心思路是“切割/分割线”：

设 nums1 长度为 m，nums2 长度为 n（为了降低复杂度，始终让 `m <= n`，对更短的数组 nums1 二分）。  
我们在 nums1 上选一个切割点 i，使得 nums1 左侧有 i 个元素；同时 nums2 的切割点为 `j = totalLeft - i`，保证左右两边元素数量平衡：

- `totalLeft = (m + n + 1) / 2`（总长度为奇数时，左边多一个）
- `i + j = totalLeft`

目标是让切割后满足“左半部分所有元素 <= 右半部分所有元素”。  
在本实现中使用一个单调条件来做 lower_bound 式二分：

- 若 `nums1[i] < nums2[j-1]`，说明 i 太小（nums1 右边最小值太小），需要右移：`left = i + 1`
- 否则 i 已经足够大（可能正好、也可能偏大），保留 i 并收缩右边界：`right = i`

二分结束时 `left == right`，即找到最终切割点 i，再计算：
- `leftMax = max(nums1LeftMax, nums2LeftMax)`
- `rightMin = min(nums1RightMin, nums2RightMin)`
根据总长度奇偶返回中位数。

## 复杂度
- 时间：O(log m)  （只对较短数组 nums1 二分）
- 空间：O(1)

## 易错点
- 必须保证在较短数组上二分：若 `nums1.length > nums2.length` 需要交换
- `totalLeft = (m + n + 1) / 2` 这个 `+1` 很关键：总长度为奇数时让左边多一个元素
- 二分收缩时边界处理容易出错；本写法用 `nums1[i] < nums2[j-1]` 来判断“必须右移”的唯一情况，其它情况都 `right = i`（lower_bound 模板）
- 计算 `leftMax/rightMin` 时一定要处理边界：i==0 / i==m / j==0 / j==n
- 返回中位数时注意偶数长度需要返回 `(leftMax + rightMin) / 2.0`，避免整除