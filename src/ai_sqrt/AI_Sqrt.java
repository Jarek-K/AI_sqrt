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
    public static int layerNum = 3;
    public static int neuronPerLayerNum = 3;
    public static float[] Weights = new float[(layerNum - 1) * neuronPerLayerNum * neuronPerLayerNum + neuronPerLayerNum * 2];
    public static float LearningRate = 1;
    public static float Beta = 0.5f;

    public static void main(String[] args) {
        // TODO code application logic here

        float A = (float) Math.random() * 100;
        float B = (float) Math.sqrt(A);
        int TrainingSetNum = 1000;
        float[][] Examples = new float[(int) (0.8 * TrainingSetNum)][2];
        float[][] TestSet = new float[(int) (0.2 * TrainingSetNum)][2];

        for (int i = 0; i < Weights.length - 1; i++) {
            Weights[i] = (float) Math.random(); // maybe - 0.5
        }

        for (int i = 0; i < (int) 0.8 * TrainingSetNum; i++) {
            A = (float) Math.random() * 100;
            B = (float) Math.sqrt(A);
            Examples[i][0] = A;
            Examples[i][1] = B;
            backProp(Examples[i]);
        }

        double Acc = 0;
        for (int i = 0; i < (int) (0.2 * TrainingSetNum); i++) {
            A = (float) Math.random() * 100;
            B = (float) Math.sqrt(A);
            TestSet[i][0] = A;
            TestSet[i][1] = B;
            Acc += Math.abs(TestSet[i][1] - 100 * AI(TestSet[1][0] / 100)[layerNum * neuronPerLayerNum ]);
        }
        Acc = Acc / (int) (0.2 * TrainingSetNum);
        System.out.println("Solution accuracy%: " + Acc);
        //   List<Integer> ToExpand = new ArrayList();
        // float C = AI(A);
    }

    public static float[] AI(float A) {
        float[] nodes = new float[layerNum * neuronPerLayerNum +1];
        for (int i = 0; i < layerNum+1; i++) {
            if(i == layerNum)
            {
                  int tmp = 0;
                for (int j = 0; j < neuronPerLayerNum; j++) {
                    tmp += Weights[Weights.length-neuronPerLayerNum-1+j ] * nodes[Weights.length-neuronPerLayerNum-1+j  ];
                    
                }
                nodes[nodes.length-1] = Sigmoid( tmp);
            }
            else if (i != 0) {
                for (int j = 0; j < neuronPerLayerNum; j++) {
                    float tmp = 0;
                    for (int k = 0; k < neuronPerLayerNum; k++) {
                        //  nodes[j + i] = nodes[k + i - 1] * Neurons[k + i - 1];
                        tmp += Weights[j * (i - 1) + k] * nodes[j * (i - 1) + k];
                        if (k == neuronPerLayerNum - 1) {
                            nodes[j + j * i] = Sigmoid(tmp);
                        }
                    }

                }
            } else {
                for (int j = 0; j < neuronPerLayerNum; j++) {
                    nodes[j] = Sigmoid(A * Weights[j]);
                }
            }
        }

        return nodes;
    }

    public static float Sigmoid(float prevNodeNum) {

        return (float) (1 / (1 + Math.pow(Math.E, -2*prevNodeNum*Beta)));
    }

    public static void backProp(float[] E) {
        float[] Errors = Weights;//new float[Weights.length - 1];
        float[] TempWeights = Weights;//new float[Weights.length - 1];
        float[] Solved = AI(E[0]);
           for (int i = layerNum; i >= 0; i--) {// output neurons
               if(i ==layerNum )
               {
            
                    float tmp = 0;
                       for (int k = 0; k < neuronPerLayerNum; k++) {
                 tmp+=Weights[Weights.length-k]*Solved[Solved.length-k-1];
                       }
                         Weights[Weights.length-1]= 2*LearningRate*Beta*(E[1]-Solved[Solved.length -1])*Sigmoid(1-Sigmoid(tmp));
                
               }
               else
          if (i != 0) {// inside neurons
                for (int j = 0; j < neuronPerLayerNum; j++) {
                   Weights[i*j]= 3;
                  
                }
            }
          else// for 0
          {
               for (int j = 0; j < neuronPerLayerNum; j++) {
                   Weights[i*j]= 3;
                  
                }
          }
           }
                

    }
}
