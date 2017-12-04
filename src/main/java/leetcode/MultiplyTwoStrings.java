package leetcode;

public class MultiplyTwoStrings {

    static String solution(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length(), i = 0, j = 0;
        int[] res = new int[len1+len2];
        for (i = 0; i < len1; i++) {
            for (j = 0; j < len2; j++) {
                int num1i = num1.charAt(len1-i-1) - '0';
                int num2j = num2.charAt(len2-j-1) - '0';
                int numij = num1i * num2j;
                int lo = numij % 10;
                int hi = (numij - lo) / 10;
                res[i+j] += lo;
                res[i+j+1] += hi;
            }
        }

        for (i = 1; i < res.length; i++) {
            int lo = res[i - 1];
            int newlo = lo % 10;
            res[i - 1] = newlo;
            res[i] += (lo - newlo) / 10;
        }

        for ( i = res.length - 1; i >= 0 && res[i] == 0;i--);
        if (i < 0) return "0";
        StringBuilder sb = new StringBuilder();
        for (; i >= 0; i--) sb.append(res[i]);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(solution("1234", "321"));
    }
}
