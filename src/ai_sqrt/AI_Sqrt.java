/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_sqrt;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Jarek
 */
public class AI_Sqrt {

    /**
     * @param args the command line arguments
     */
     public static List <Float> Neurons = new ArrayList();

    public static void main(String[] args) {
        // TODO code application logic here
        float A = (float)Math.random()*100;
        float B =(float)Math.sqrt(A) ;
        
               //   List<Integer> ToExpand = new ArrayList();
        float C = AI(A);
    }
    public static float AI( float A)
    {
        
      return A;  
    }
            
    
}
