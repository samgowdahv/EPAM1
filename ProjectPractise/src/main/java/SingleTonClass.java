public class SingleTonClass {
    int empID;
       private  SingleTonClass(){
           System.out.println("It is singleton class constructor");
       }
       static SingleTonClass singleTonClass;
    public static SingleTonClass createInstance(){
            synchronized (SingleTonClass.class)
            {
                if(singleTonClass == null)
                    singleTonClass = new SingleTonClass();
            }


        return  singleTonClass;
    }


}
