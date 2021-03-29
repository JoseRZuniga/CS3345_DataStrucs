import java.io.FileOutputStream;

/*
  Name: Jose Rodriguez-Zuniga
  NetId: jpr160230
  Class: 3345.001
  Project 04

  Description:

  Create a class called ExternalSort.

  Your class should have the following methods.  You may add additional methods as needed.
  You may use code from the textbook, but all other code must be your own.

  80 points
  a) public static Path extsort(Path t1, int runSize)

        Uses the external sort procedure illustrated in chapter 7 to sort the file represented
        by Path t1 using the passed size as the run size and returns a Path to the sorted result.

        For the tapes, use files named T1-T4.  The data should already be on T1 when this method
        is called.  You can assume the data consists of Integers.

        Your sort should work even if the number of input items is not a multiple of the run size.

        Note that "Path" is an object in Java's "java.nio.file" package.



   20 points
   b) public static void main(String args[])

        Accepts run size as a command line argument.
        Assumes input already exists on a file named T1.
        Calls the external sort routine.
        Prints the Path object which gives the path to the sorted file.
*/

import java.util.*;
import java.io.*;
import java.util.Arrays;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExternalSort3{

    public static Integer[] arr;

    public static Path extsort(Path t1, int runSize) throws IOException{
        
        Path filePath = Paths.get("t1.txt");
        int size = 0, runs = 0;
        
        File t2 = new File("t2.txt");
        File t3 = new File("t3.txt");
        File t4 = new File("t4.txt");

        Scanner input = new Scanner(filePath);
        List<Integer> ints = new ArrayList<>();

        while(input.hasNext()){
            if(input.hasNextInt()){
                ints.add(input.nextInt());
            } 
            else{
                input.next();
            }
        }
        input.close();

        arr = ints.toArray(new Integer[ints.size()]);

        
        Integer[] arrt1 = Arrays.copyOfRange(arr, 0, runSize);
        
        Integer[] arrt2 = Arrays.copyOfRange(arr, runSize, (2 * runSize));
        /*
        Integer[] arrt3 = Arrays.copyOfRange(arr, 0, runSize);
        Integer[] arrt4 = Arrays.copyOfRange(arr, 0, runSize);

        for(int i = 0; i < runSize; i++){
            System.out.print(arrt1[i]); 
        }

        System.out.println();

        for(int i = 0; i < runSize; i++){
            System.out.print(arrt2[i]); 
        }
        */

        
        size = ints.size();
        runs = (size / runSize);

        fileWrite(t3, arrt1, runSize);
        fileWrite(t4, arrt2, runSize);



        System.out.println(runs);

        for(int i = 0; i < runSize; i++){
            System.out.print(arr[i]);
        }

        System.out.println();

        return t1;
    }


    public static void fileWrite(File a, Integer[] arr, int runSize){
        FileWriter writer;
        
        try {
            writer = new FileWriter(a, true);
            PrintWriter printer = new PrintWriter(writer);

            for(int i = 0; i < runSize; i++){
                printer.write(Integer.toString(arr[i]));
            }
            printer.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws IOException{
        int firstArg = 0;
        
        if(args.length > 0){
            try {
            firstArg = Integer.parseInt(args[0]);
            } 
            catch(NumberFormatException e){
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }

        int runSize = firstArg;
        Path t1 = Paths.get("t1.txt");

        extsort(t1, runSize);

    }
}

//Array.Sort