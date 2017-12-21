package leetcode;

public class ClosestPalindrome {

    public String nearestPalindromic(String n) {
        long nLong = Long.parseLong(n), best = Long.MAX_VALUE;
        if (nLong == 1L) return "0";
        if (nLong <= 10) return String.valueOf(nLong-1);
        if (nLong == 11) return "9";
        int len = n.length(), midIdx = (len-1)>>1;
        String half = n.substring(0, midIdx+1);
        long halfLong = Long.parseLong(half);
        if(halfLong > 1) {
            best = getTheBestPalinOddAndEven(nLong, half.matches("10+") && len % 2 == 0 ? (halfLong * 10) - 1: halfLong - 1);
        }
        long cbest = getTheBestPalinOddAndEven(nLong, halfLong);
        best = (cbest == nLong) ? best : getTheBestPalin(nLong, best, cbest);
        cbest = getTheBestPalinOddAndEven(nLong, half.matches("99+") && len % 2 == 1 ? (halfLong + 1) / 10 : halfLong + 1);
        best = (cbest == nLong) ? best : getTheBestPalin(nLong, best, cbest);

        return String.valueOf(best);
    }

    private long getTheBestPalinOddAndEven(long nLong, long halfLong) {
        long p1 = createPalin(halfLong, true);
        long p2 = createPalin(halfLong, false);
        return getTheBestPalin(nLong, p1, p2);
    }

    private long getTheBestPalin(long nLong, long p1, long p2) {
        long d1 = Math.abs(p1-nLong);
        long d2 = Math.abs(p2-nLong);
        return d1 < d2 || d1 == d2 && p1 < p2 ? p1 : p2;
    }


    private long createPalin(long input, boolean isOdd) {
        int b = 10;
        long n = input, palin = input;
        n /= isOdd ? b : 1;
        while (n > 0) {
            palin = palin * b + n % b;
            n /= b;
        }
        return palin;
    }
    public static void main(String[] args) {
        String n = "11";
        String ans = new ClosestPalindrome().nearestPalindromic(n);
        System.out.println(ans);
    }

}
