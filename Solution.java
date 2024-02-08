import java.util.Vector;

public class Main {
    
    public static void main(String [] args)
    {
        String str = "abc$de@" ;
        System.out.println(reverseString(str)) ;
    }

    public int[] twoSum(int[] nums, int target) {

        int left = 0 ; 
        int right = nums.length - 1  ;
        while( right > left)
        {
            if( nums[right] + nums[left] == target )
            {
                int[] result = new int[2] ;
                result[0] = right ;
                result[1] = left ;
                return result ;
            }
            right-- ;
        }
    }
    
}
