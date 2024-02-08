public class Main {
    
    public static void main()
    {
        String str = "abc$de@" ;
        System.out.println(reverseString(str)) ;
    }

    public String reverseString(String str)
    {

        StringBuilder strb =  new StringBuilder() ;
        for(int i = str.length() ; i > 0 ; i-- )
        {
            strb.append(str.charAt(i)) ; 
        }
        String newStr = new String(strb) ;
        return newStr ;
    }
}
