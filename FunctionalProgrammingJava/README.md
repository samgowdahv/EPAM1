[Functional Programming in JAVA]

    A method needs 4 things name ,
    return  type , input parameters and body

    int doStuff(int a){ 
    do stuff 
    return c; 
    }

    to implement SAM (single abstract method ) we don't need name,
    as we will be referring to only one method ,
    return type can always be inferred from body
    whether we are returning anything and what is its type
    so the above method can also be written as

    (int a)->{ do stuff return c;}

    this is nothing but lambda expression,
    it can only be employed for SAM (single abstract method )
    we will also go with difference between SAM and anonymous class.

[ Function as first class citizen ]

    function now (from java 8) has become first class citizen , 
    the can assigned to variables , passed as parameters and so on .
    

[Four New Functional Interfaces]

    consumer
    * consumer consumes, and has no return type

    predicate
    * predicate return type is boolean

    supplier 
    *Supplies a particular type of object

    function
    * takes in one type and return another type

   


[Imperative and Declarative type of programming]

    imperative , we are writing our own logic , we are modifing objects , mutability is there 

   
[Method Reference]

    Powerful feature to refer to the method of the class
    example > Student::getName

[Stream Api]

    Stream Api is used for  data processing , 
    it can be considered as conveyer belt for proicessing 
    data , From collection stream is created 
    which then is used to process data in declarative way 
    - Intermediate Operations
      filter (predicate) , map(function) .
    - Terminal Operations
      Collectors.collect , sum , max , min etc
[Stream Api's Methods ]
        
        reduce== identity (initial value ) + Accumulator + Combiner

        Example:- 

        here method reference could have been used for simplification 
        int sumV = aList.stream().reduce(0, (sum, element) -> sum + element);
        System.out.println(sumV);

        stream() method on the list to get a stream of values from the list

        mapToInt(value -> value) on the stream to get an Integer Stream

        max, min and sum to method on the stream to get the max value , 
        if used without converting to int stream need comparator for the same .

        orElseThrow() to throw an exception if no value is received 



[Date and Time API ]

[Preexisting Functional Interfaces]



[ Interview Questions ]

      | Questions     | 
      | ------------- |
      | get max     | 
      | get min     | 
      | nth heighest number |
      | sort in reverse order|
      | sort in natural order |
      | Range Sum |
      |Duplicate numbers|


[more interview questions and explanations ]

All the related and more programs available under src
[Features Introduced in Java 8 to java 14 , and Java 17]

[Todo]

To be continued | topics    | tocover
--- |-----------| ---
*Still* | `renders` | **nicely**
1 | 2         | 3
jvm| j-11      |j17


      