# 1758. 生成交替二进制字符串的最少操作数（Minimum Changes To Make Alternating Binary String）

题目链接： https://leetcode.cn/problems/minimum-changes-to-make-alternating-binary-string/

## 标签
- 字符串
- 贪心
- 计数

## 思路
交替二进制字符串只有两种可能的目标形式：

- 形式 A：`010101...`（偶数下标是 '0'，奇数下标是 '1'）
- 形式 B：`101010...`（偶数下标是 '1'，奇数下标是 '0'）

遍历字符串 s，分别统计把 s 变成 A 需要改多少次（countA），变成 B 需要改多少次（countB）：
- 当 i 为偶数：
  - 若 `s[i] != '0'`，则 A 需要修改（countA++）；否则 B 需要修改（countB++）
- 当 i 为奇数：
  - 若 `s[i] != '1'`，则 A 需要修改（countA++）；否则 B 需要修改（countB++）

最终答案是 `min(countA, countB)`。

## 复杂度
- 时间：O(n)
- 空间：O(1)

## 易错点
- 交替串只有两种模式（0101... 或 1010...），不要遗漏其中一种
- 下标奇偶与字符期望值要对应好（0-based 下标：偶数位/奇数位）
- 统计时注意“当前字符符合 A 时不一定符合 B”，要同时维护两套计数