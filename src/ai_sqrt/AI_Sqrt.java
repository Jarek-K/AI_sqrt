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
    public static int layerNum = 2;
    public static int neuronPerLayerNum = 3;
    public static float[] Weights = new float[(layerNum - 1) * neuronPerLayerNum * neuronPerLayerNum + neuronPerLayerNum * 2];
    public static float LearningRate = 0.5f;
    public static float[] Bias = new float[layerNum * neuronPerLayerNum + 1];
    public static float Beta = 1;

    public static void main(String[] args) {
        // TODO code application logic here

        float A = (float) Math.random() * 100;
        float B = (float) Math.sqrt(A);
        int TrainingSetNum = 1000;
        float[][] Examples = new float[(int) (0.8 * TrainingSetNum)][2];
        float[][] TestSet = new float[(int) (0.2 * TrainingSetNum)][2];

        for (int i = 0; i < Weights.length ; i++) {
            Weights[i] = (float) Math.random(); // maybe - 0.5
        }
for(int i = 0 ;i< Bias.length;i++){
    Bias[i]= -(float)Math.random();
}
        for (int i = 0; i < (int) (0.8 * TrainingSetNum); i++) {
            A = (float) (Math.random()-0.5f );
            B = (float) Math.sqrt(A);
            Examples[i][0] = A;
            if(A>0)
            Examples[i][1] = 1;
            else
                 Examples[i][1] = 0;
             
            backProp(Examples[i]);
           
        }

        double Acc = 0;
        for (int i = 0; i < (int) (0.2 * TrainingSetNum); i++) {
            
             A = (float) (Math.random()-0.5f );
            B = (float) Math.sqrt(A);
            TestSet[i][0] = A;
            if(A>0){
            TestSet[i][1] = 1;
             if( AI(TestSet[i][0] )[layerNum * neuronPerLayerNum]>0.9f)
            Acc +=1;
            }
            else{
                 TestSet[i][1] =0;
            if( AI(TestSet[i][0] )[layerNum * neuronPerLayerNum]<0.1f)
            Acc +=1;
            }
        }
        Acc = Acc / (int) (0.2 * TrainingSetNum);
        System.out.println("Solution accuracy%: " + Acc);
        //   List<Integer> ToExpand = new ArrayList();
        // float C = AI(A);
       
    }

    public static float[] AI(float A) {
        float[] nodes = new float[layerNum * neuronPerLayerNum + 1];
        for (int i = 0; i < layerNum + 1; i++) {
            if (i == layerNum) {
                float tmp = 0;
                for (int j = 0; j < neuronPerLayerNum; j++) {
                    tmp += Weights[Weights.length - neuronPerLayerNum - 1 + j] * nodes[nodes.length - neuronPerLayerNum - 1 + j];

                }
                System.out.println("tmp at node 6=: "+tmp);
                tmp += Bias[Bias.length - 1];
                 System.out.println("tmp at node 6 + bias=: "+tmp);
                nodes[nodes.length - 1] = Sigmoid(tmp);
            } else if (i != 0) {
                for (int j = 0; j < neuronPerLayerNum; j++) {
                    float tmp = 0;
                    for (int k = 0; k < neuronPerLayerNum; k++) {
                        //  nodes[j + i] = nodes[k + i - 1] * Neurons[k + i - 1];
                        tmp += Weights[i * neuronPerLayerNum + j * neuronPerLayerNum + k] * nodes[k];

                        if (k == neuronPerLayerNum - 1) {
                            tmp += Bias[i * neuronPerLayerNum + j];
                            nodes[j + neuronPerLayerNum * i] = Sigmoid(tmp);
                           
                        }
                    }

                }
            } else {
                for (int j = 0; j < neuronPerLayerNum; j++) {

                    nodes[j] = Sigmoid(A * Weights[j] + Bias[j]);
                }
            }
        }
        System.out.println("Input: "+A+" Output: "+ nodes[nodes.length-1]);
        for(int o=0;o<nodes.length;o++)
            System.out.println("Nodes "+o+" : "+nodes[o]);
        return nodes;
    }

    public static float Sigmoid(float prevNodeNum) {
        
        return (float) (1 / (1 + Math.pow(Math.E, -2 * Beta * prevNodeNum)));
    }

    public static void backProp(float[] E) {
        float[] Errors = Weights;//new float[Weights.length - 1];
        float[] TempWeights = Weights;//new float[Weights.length - 1];
        float[] OldBias = Bias;
        float[] Delta = Weights;
        float TotError = 0;
        int c = 0;
        float[] Solved = AI(E[0]);
        for (int i = layerNum; i >= 0; i--) {// output neurons
            if (i == layerNum) {

                float tmp = 0;
                for (int k = 0; k < neuronPerLayerNum; k++) {
                    tmp += Weights[Weights.length - k - 1] * Solved[Solved.length - k - 1];

                }
                tmp += OldBias[OldBias.length - 1];

                for (int p = 0; p < neuronPerLayerNum; p++) {
                    Delta[Weights.length - 1 - p] = Math.abs(E[1] - Solved[Solved.length - 1]) * Sigmoid(1 - Sigmoid(tmp));
                    TempWeights[Weights.length - 1 - p] -= 2 * LearningRate * Beta * Delta[Weights.length - 1 - p] * Solved[Solved.length - 2 - p];
                    Delta[Weights.length - 1 - p] *= Weights[Weights.length - p - 1];
                    c++;

                }
                Bias[Bias.length - 1] += 2 * LearningRate * Beta *Math.abs(E[1] - Solved[Solved.length - 1]) * Sigmoid(1 - Sigmoid(tmp));

            } else if (i != 0) {// hidden neurons
                for (int j = 0; j < neuronPerLayerNum; j++) {
                    float tmp = 0;
                   
                    int index = 0;
                    for (int k = 0; k < neuronPerLayerNum; k++) {
                        tmp += Weights[i * neuronPerLayerNum + j * neuronPerLayerNum + k] * Solved[k];
                        // tmp += Weights[Weights.length - neuronPerLayerNum - k - 1] * Solved[Solved.length - neuronPerLayerNum - k - 1];

                    }
                    tmp += OldBias[i * neuronPerLayerNum + j];
                    for (int p = 0; p < neuronPerLayerNum; p++) {
                        index = i * neuronPerLayerNum + j * neuronPerLayerNum + p;
                        Delta[index] = Sigmoid(1 - Sigmoid(tmp)) * Delta[Weights.length - neuronPerLayerNum + j];
                        TempWeights[index] -= 2 * LearningRate * Beta * Solved[p] * Delta[index];

                        Delta[index] *= Weights[index];

                    }
                    Bias[j + i * neuronPerLayerNum] += 2 * LearningRate * Beta * Sigmoid(1 - Sigmoid(tmp)) * Delta[Weights.length - neuronPerLayerNum + j];

                }
            } else// for input layer neurons
            {
                for (int j = 0; j < neuronPerLayerNum; j++) {
                    float tmp = 0;
                    float tmpDelt = 0;
                    
                    for (int k = 0; k < neuronPerLayerNum; k++) {

                        // tmp += Weights[Weights.length - neuronPerLayerNum - k - 1] * Solved[Solved.length - neuronPerLayerNum - k - 1];
                        tmpDelt += Delta[neuronPerLayerNum * (k + 1) + j];
                    }
                    tmp = Weights[j] * E[0];
                    tmp += OldBias[i] * neuronPerLayerNum + j
                    ;
                   
						
                        TempWeights[j] -= 2 * LearningRate * Beta * E[0] * Sigmoid(1 - Sigmoid(tmp)) * tmpDelt;

                    Bias[j] += 2 * LearningRate * Beta * Sigmoid(1 - Sigmoid(tmp)) * tmpDelt;

                }
            }
        }
        Weights = TempWeights;
    }
}
