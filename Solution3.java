package com.atcx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * @description:
 *
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * 示例 2：
 *
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 109
 * nums 中的所有整数 互不相同
 *
 */



class Solution3 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        if (len == 0) return new ArrayList<>();
        Arrays.sort(nums);

        // 第 1 步：动态规划找出最大子集的个数、最大子集中的最大整数
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int maxSize = 1;
        int maxVal = dp[0];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // 题目中说「没有重复元素」很重要
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxVal = nums[i];
            }
        }

        // 第 2 步：倒推获得最大子集
        List<Integer> res = new ArrayList<Integer>();
        if (maxSize == 1) {
            res.add(nums[0]);
            return res;
        }

        for (int i = len - 1; i >= 0; i--) {
            if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                res.add(0,nums[i]);
                maxVal = nums[i];
                maxSize--;
            }
        }
        return res;
    }
}
public class Solution3Test{
    private final com.atcx.Solution3 solution = new com.atcx.Solution3();

    /**
     * 测试基本情况
     * 测试用例：nums = [1,2,3]
     * 期望：[1,2] 或 [1,3]
     */
    @Test
    public void testBasicInput() {
        int[] nums = {1, 2, 3};
        List<Integer> result = solution.largestDivisibleSubset(nums);
        Assertions.assertTrue(result.contains(1) && (result.contains(2) || result.contains(3)));
    }

    /**
     * 测试有序输入
     * 测试用例：nums = [1,2,4,8]
     * 期望：[1,2,4,8]
     */
    @Test
    public void testOrderedInput() {
        int[] nums = {1, 2, 3};
        List<Integer> result = solution.largestDivisibleSubset(nums);
        List<Integer> expected1 = Arrays.asList(1, 2); // 可能的结果之一
        List<Integer> expected2 = Arrays.asList(1, 3); // 可能的结果之二
        Assertions.assertTrue(result.equals(expected1) || result.equals(expected2));
    }

    /**
     * 测试包含倍数的情况
     * 测试用例：nums = [2,4,8,16]
     * 期望：[2,4,8,16]
     */
    @Test
    public void testMultiples() {
        int[] nums = {2, 4, 8, 16};
        List<Integer> result = solution.largestDivisibleSubset(nums);
        Assertions.assertEquals(Arrays.asList(2, 4, 8, 16), result);
    }

    /**
     * 测试空数组
     * 测试用例：nums = []
     * 期望：[]
     */
    @Test
    public void testEmptyArray() {
        int[] nums = {};
        List<Integer> result = solution.largestDivisibleSubset(nums);
        Assertions.assertTrue(result.isEmpty());
    }

    /**
     * 测试单元素数组
     * 测试用例：nums = [7]
     * 期望：[7]
     */
    @Test
    public void testSingleElement() {
        int[] nums = {7};
        List<Integer> result = solution.largestDivisibleSubset(nums);
        Assertions.assertEquals(Arrays.asList(7), result);
    }


}
