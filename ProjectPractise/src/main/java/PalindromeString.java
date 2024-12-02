public class PalindromeString {
       static boolean palindrom(String curStr){
         StringBuilder str = new StringBuilder(curStr);
         if (str.reverse().toString().equals(curStr)) {
             return true;
         }
       return false;
       }

     static String longestSubstring(){

         String s = "abcabcbb";

         for (int i=0 ; i < s.length();i++)
         {
             for (int j = i+1 ; j < s.length();j++)

                 System.out.println(s.substring(i,j+1));


         }


         return null;
     }
    public static void main(String[] args) {
        longestSubstring();
        //    Input: str = “forgeeksskeegfor”
        //    Output: “geeksskeeg”
        String input ="forgeeksskeegfor";
        int maxLenght=0;
        for(int i =0 ; i < input.length();i++){
            for (int j =i+1 ; j < input.length();j++)
            {
                String curSubStr = input.substring(i,j+1);
                if (palindrom(curSubStr) && curSubStr.length() > maxLenght) {
                    maxLenght = curSubStr.length();
                    System.out.print(curSubStr);
                }
            }

        }


    }
}
