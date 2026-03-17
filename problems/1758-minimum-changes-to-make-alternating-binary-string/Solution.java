class Solution {
    public int minOperations(String s) {
        int countA = 0;
        int countB = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(i % 2 == 0) {
                if(c != '0') {
                    countA++;
                } else {
                    countB++;
                }
            } else {
                if(c != '1') {
                    countA++;
                } else {
                    countB++;
                }
            
            }
        }
        return Math.min(countA, countB);
    }
}