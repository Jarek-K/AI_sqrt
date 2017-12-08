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
        int TrainingSetNum = 1000;
        float[][] Examples = new float[(int)(0.8*TrainingSetNum)][2];
        float[][] TestSet = new float [(int)(0.2*TrainingSetNum)][2];
        for(int i = 0 ; i < (int)0.8*TrainingSetNum;i++){
            A = (float)Math.random()*100;
            B =(float)Math.sqrt(A) ;
            Examples [i][0] = A;
            Examples [i][1] = B;
            
        }
        
        double Acc = 0;
        for (int i = 0; i<(int)(0.2*TrainingSetNum); i++)
        {
            A = (float)Math.random()*100;
            B =(float)Math.sqrt(A) ;
            TestSet [i][0] = A;
            TestSet [i][1] = B;
           Acc += Math.abs(TestSet[i][1]-AI(TestSet[1][0]));
        }
        Acc=Acc/(int)(0.2*TrainingSetNum);
        System.out.println("Solution accuracy%: "+Acc);
               //   List<Integer> ToExpand = new ArrayList();
       // float C = AI(A);
    }
    public static float AI( float A)
    {
        
      return A/4;  
    }
            
    
}
