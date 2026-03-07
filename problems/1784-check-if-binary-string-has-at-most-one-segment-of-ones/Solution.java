class Solution {
    public boolean checkOnesSegment(String s) {
        
        int check = 0;

        if(s.length() == 1)
        return true;

        for(int fast = 1; fast<s.length(); fast++) {
            if(s.charAt(fast) != s.charAt(fast - 1)) {
                ++check;
            }

            if(check > 1) {
                return false;
            } 
            
        }

        return true;

    }

}