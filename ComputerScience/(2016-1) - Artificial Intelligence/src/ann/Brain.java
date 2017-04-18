package ann;

import java.util.Arrays;

/**
 * Created by jvarred on 11-10-15.
 */
public class Brain {
    double[][][] w;
    double[][] space;
    double input[];
    int layers;
    int features;
    double[] outputs;
    double[][] outputsWeight;
    int genomeLenght;

    public Brain(int layers, int features, int outputs){
        this.layers = layers;
        this.features = features;
        this.w = new double[layers][features][features];
        this.space = new double[features][features]; //first layer as entry
        this.input = new double[features];
        this.outputs = new double[outputs];
        this.outputsWeight = new double[outputs][features];
        this.genomeLenght = (layers*features*features)+(outputs*features);
    }



    void setInput(double[] input){
        this.input = input;
    }

    void setWeight(double w[], int layer, int index){
        this.w[layer][index] = w;
    }

    double getResponse(double[] input, double w[]){
        double response = 0.0;
        for(int i=0; i<input.length; i++){
            //System.out.println("W.lenght=" + w.length);
            //System.out.println("Input.lenght=" + input.length);
            response += w[i] * input[i];
        }
        //return (float) (1.0f / (1.0f + Math.exp(-response)));
        return Math.tanh(response);
    }

    void fowardpropagation(){
        //compute entry layer
        for(int i=0; i<this.features; i++){
            space[0][i] = getResponse(input, w[0][i]);
        }
        for(int i=1; i<this.layers; i++){
            for(int j=0; j<this.features; j++){
                this.space[i][j] = getResponse(this.space[i-1], this.w[i][j]);
            }
        }
        for(int i=0; i<this.outputs.length; i++){
            //System.out.println("Final");
            this.outputs[i] = getResponse(this.space[this.layers-1], this.outputsWeight[i]);
        }
    }

    public double[] query(double[] input){
        this.setInput(input);
        fowardpropagation();
        return this.outputs;
    }

    public double[] getGenome(){

        double[] gnome = new double[this.genomeLenght];
        int counter = 0;
        for(int i=0; i<layers; i++){
            for(int j=0; j<features; j++){
                for(int k=0; k<features; k++){
                    gnome[counter++] = w[i][j][k];
                }
            }
        }
        for(int i=0; i<outputsWeight.length; i++){
            for(int k=0; k<outputsWeight[0].length; k++){
                gnome[counter++] = outputsWeight[i][k];
            }
        }

        return gnome;
    }


     public void setGenome(double[] gnome){
        int counter = 0;
        for(int i=0; i<layers; i++){
            for(int j=0; j<features; j++){
                for(int k=0; k<features; k++){
                     w[i][j][k] = gnome[counter++];
                }
            }
        }
        for(int i=0; i<outputsWeight.length; i++){
            for(int k=0; k<outputsWeight[0].length; k++){
                outputsWeight[i][k] = gnome[counter++];
            }
        }
    }

    public int getGnomeLenght(){
        return this.genomeLenght;
    }

}
