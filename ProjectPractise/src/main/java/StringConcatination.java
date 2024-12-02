public class StringConcatination {
    public static void main(String[] args) {
        //21462675756   O/P = 214*6*2*67-5-7-56
        String input = "21462675756";
        StringBuilder outPut = new StringBuilder();
        boolean isDigitEven = false;
        for (int i =0 ; i < input.length();i++) {
            int a = input.charAt(i);
            if (Character.isDigit(a))
            {
                int ab = Character.getNumericValue(a);
                if(ab % 2 == 0){
                    if(!isDigitEven)
                    {
                       outPut.append(ab);
                       isDigitEven=true;

                    }
                    else{
                        outPut.append("*").append(ab);

                    }
                }
                else{
                    if(isDigitEven) {
                        outPut.append(ab);
                        isDigitEven = false;
                    }
                    else{
                        outPut.append("-").append(ab);
                    }
                }
            }
            else {
                outPut.append(input.charAt(i));
                isDigitEven=false;
            }
        }
        System.out.print(outPut.toString());

    }
}
