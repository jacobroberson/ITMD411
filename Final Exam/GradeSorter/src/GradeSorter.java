//Programmed by Jacob Roberson
/**
 * @author Jacob Roberson
 * This program finds the student with the highest grade
 */
import java.util.*;

public class GradeSorter {

   public String name;
   public double grade;

  
   public GradeSorter(String name, double grade) {
       this.name = name;
       this.grade = grade;
   }

  //Getters and Setters
   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public double getGrade() {
       return grade;
   }

   public void setGrade(double grade) {
       this.grade = grade;
   }
  
   @Override
   public String toString() {
       return "Name: "+ name + " Grade: "+ grade;
   }
}


//Grade Sort
class SortByGrade implements Comparator<GradeSorter> {

   @Override
   public int compare(GradeSorter s1, GradeSorter s2) {
      
       if(s2.grade>s1.grade) {
           return 1;
       } else if(s2.grade<s1.grade) {
           return -1;
       } else {
           return 0;
       }
   }
  
}
