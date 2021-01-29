//Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program finds the student with the highest grade
 */
import java.util.*;

public class GradeSorterTest {

   public static void main(String[] args) {

       ArrayList<GradeSorter> sList = new ArrayList<GradeSorter>();
       sList.add(new GradeSorter("Jacob", 100));
       sList.add(new GradeSorter("Ryan", 72));
       sList.add(new GradeSorter("Steven", 85));
       sList.add(new GradeSorter("Emily", 74));
       sList.add(new GradeSorter("Paige", 95));
  
       System.out.println("--- Student data prior to sorting ---");
       System.out.println("----------------");
       System.out.println("Name\tGrade");
       System.out.println("----------------");
       for(GradeSorter student:sList) {
           System.out.println(student.getName()+"\t"+student.getGrade());
       }
      
       GradeSorter highestGrade = sList.get(0);
       for(GradeSorter student:sList) {
           if(student.getGrade()>=highestGrade.getGrade()) {
               highestGrade = student;
           }
       }
      
       System.out.println("\n");
       System.out.println("--- Student with the highest grade --- ");
       System.out.println(highestGrade);
      
       Collections.sort(sList, new SortByGrade());
       System.out.println("\n");
       System.out.println("--- Sorted by Grade ---");
       System.out.println("----------------");
       System.out.println("Name\tGrade");
       System.out.println("----------------");
       for(GradeSorter student:sList) {
           System.out.println(student.getName()+"\t"+student.getGrade());
       }
      
   }

}
