class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 为了降低时间复杂度，确保对较短的数组进行二分查找
        // 如果 nums1 比 nums2 长，交换它们的角色
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        // 定义分割线：左半部分的元素个数
        // 加1是为了在总长度为奇数时，让左半部分多包含一个元素
        int totalLeft = (m + n + 1) / 2;
        
        // 在 nums1 上进行二分查找
        // left 和 right 代表分割线 i 可能的范围 [0, m]
        int left = 0;
        int right = m;
        
        while (left < right) {
            // i 代表在 nums1 中切一刀，左边有 i 个元素
            int i = left + (right - left) / 2;
            // j 代表在 nums2 中切一刀，左边有 j 个元素
            // 根据 totalLeft 的定义，必须满足 i + j = totalLeft
            int j = totalLeft - i;

            // 明确需要右移：只有这一种情况，我们必须动
            // 因为left<right所以i!=m,进一步分析发现j-1>=0
            if (nums1[i] < nums2[j-1]) {
                left = i + 1;
            } 
            // 其他所有情况：包括“完美”、“需要左移”、“边界”
            // 我们都通过 right = i 来缩小范围
            else {
                right = i;
            } 
            //如果上面if和else逻辑互换在，则容易在i已经实现切割的情况下出现left=i+1;
            //导致[left, right]内不包含原来的i点
        }

        // 循环结束，left 就是 nums1 的最终分割点 i
        
        int i = left;
        int j = totalLeft - i; // 根据 i 算出 nums2 的分割点 j

        // --- 计算左半边的最大值 ---
        // 处理边界：如果 i=0，说明 nums1 左边没有元素，设为负无穷
        int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
        // 处理边界：如果 j=0，说明 nums2 左边没有元素，设为负无穷
        int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
        int leftMax = Math.max(nums1LeftMax, nums2LeftMax);

        // --- 计算右半边的最小值 ---
        // 处理边界：如果 i=m，说明 nums1 右边没有元素，设为正无穷
        int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
        // 处理边界：如果 j=n，说明 nums2 右边没有元素，设为正无穷
        int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];
        int rightMin = Math.min(nums1RightMin, nums2RightMin);

        
        if ((m + n) % 2 == 0) {
            return (leftMax + rightMin) / 2.0;      //"/2.0"将结果转化为浮点数
        } 
        else {
            return leftMax;
        }
    }
}

/*为什么切割点不是循环结束后的i而是left?
*
* 1.i 是“过去式”：
*  在 if 判断块执行完后，i 的值就固定了，它不会随着 left 或 right的改变而自动改变。
*  假设本轮 left=2, right=4，算出 i=3。
*  判断后发现要向右收缩，于是 left = i + 1 = 4。
*  此时 i 依然是 3，但 left 已经变成了 4。
*  如果循环因为 left >= right 而结束，真正的答案是 left（也就是 4），而 i（3）已经过时了。
* 2.循环结束时 i 未定义：
*  int i 定义在 while 循环内部，一旦跳出循环，外部的代码根本“看不见” i 这个变量，编译器会报错。
* 3.left 是“现在时”：
*  left 和 right 是在循环体内部被不断更新的。当循环条件 while (left < right) 最终不满足而跳出时
*  left 的值就是搜索区间收缩到最后的那个点，也就是我们苦苦寻找的切割点。
*/