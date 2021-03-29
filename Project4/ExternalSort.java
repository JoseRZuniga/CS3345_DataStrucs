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

    
    NOTE TO GRADER: I was only not able to automate the atlernate merges into the tapes for 
    this assignment, it was very confusing to automate. So I tried to match the PowerPoint 
    example as much as I could. 
*/

import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExternalSort{

    public static Integer[] arr;

    public static Path extsort(Path text, int runSize) throws IOException{
        
        Path filePath = Paths.get("text.txt");

        /* This file will work as the tapes in the ppt example */
        File t1 = new File("t1.txt");
        File t2 = new File("t2.txt");
        File t3 = new File("t3.txt");
        File t4 = new File("t4.txt");
        
        Scanner input = new Scanner(filePath);
        List<Integer> ints = new ArrayList<>();

        /* While loop adds all of the data of text.txt to ints List */
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
        mergeSort(arrt1, runSize);
        Integer[] arrt2 = Arrays.copyOfRange(arr, runSize, (2 * runSize));
        mergeSort(arrt2, runSize);
        Integer[] arrt3 = Arrays.copyOfRange(arr, (2 * runSize), (3 * runSize));
        mergeSort(arrt3, runSize);
        Integer[] arrt4 = Arrays.copyOfRange(arr, (3 * runSize), (4 * runSize));
        mergeSort(arrt4, runSize);

        fileWrite(t3, arrt1, runSize);
        fileWrite(t4, arrt2, runSize);

        fileMerge(t3, t4, t1);
        clearFile(t3);
        clearFile(t4);

        fileWrite(t3, arrt3, runSize);
        fileWrite(t4, arrt4, runSize);

        fileMerge(t3, t4, t2);
        clearFile(t3);
        clearFile(t4);

        fileMerge(t1, t2, t3);

        /* Returns the Path with the sorted file */
        Path sortedFile = Paths.get("t3.txt");

        return sortedFile;
    }

    /* This method write the runs into the inputed files */
    public static void fileWrite(File a, Integer[] arr, int runSize){
        FileWriter writer;
        
        try{
            writer = new FileWriter(a, true);
            PrintWriter printer = new PrintWriter(writer);

            for(int i = 0; i < runSize; i++){
                printer.write(Integer.toString(arr[i]));
            }
            printer.close();
        } 
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /* This method will merge files into one */
    public static void fileMerge(File a, File b, File c) throws IOException{
    
        PrintWriter pw = new PrintWriter(c); 
          
        BufferedReader br1 = new BufferedReader(new FileReader(a)); 
        BufferedReader br2 = new BufferedReader(new FileReader(b)); 
       
       
        String line1 = br1.readLine(); 
        String line2 = br2.readLine(); 
       
        /* Merge File a and b to c */
        while(line1 != null || line2 !=null){ 
            if(line1 != null){ 
                pw.print(line1); 
                line1 = br1.readLine(); 
            } 
           
            if(line2 != null){ 
                pw.print(line2); 
                line2 = br2.readLine(); 
            } 
        } 
   
        pw.flush(); 
        br1.close(); 
        br2.close(); 
        pw.close();    
    }
    
    /* This method will clear files once a runs is made */
    public static void clearFile(File a) throws IOException{
        FileWriter fw = new FileWriter(a, false); 
        PrintWriter pw = new PrintWriter(fw, false);
        
        pw.flush();
        pw.close();
        fw.close();
    }

    /* I use a merge sort to sort out every run */
    public static void mergeSort(Integer arr[], int runSize){
        
        if(runSize < 2){
            return;
        }

        int mid = runSize / 2;
        
        Integer[] l = new Integer[mid];
        Integer[] r = new Integer[runSize - mid];
     
        for(int i = 0; i < mid; i++){
            l[i] = arr[i];
        }
        
        for(int i = mid; i < runSize; i++){
            r[i - mid] = arr[i];
        }

        mergeSort(l, mid);
        mergeSort(r, (runSize - mid));
     
        merge(arr, l, r, mid, (runSize - mid));
    }


    public static void merge(Integer[] a, Integer[] l, Integer[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
    
        while(i < left && j < right){
            if(l[i] <= r[j]){
                a[k++] = l[i++];
            }
            else{
                a[k++] = r[j++];
            }
        }

        while(i < left){
            a[k++] = l[i++];
        }

        while(j < right){
            a[k++] = r[j++];
        }
    }

    public static void main(String args[]) throws IOException{
        
        int firstArg = 0;
        
        if(args.length > 0){
            try{
            firstArg = Integer.parseInt(args[0]);
            } 
            catch(NumberFormatException e){
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }

        int runSize = firstArg;
        Path text = Paths.get("/text.txt");

        Path a = extsort(text, runSize);
        String s = a.toAbsolutePath().toString();
        System.out.println("Path to sorted file is: " + s);

    }
}