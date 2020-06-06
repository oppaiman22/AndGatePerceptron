package Controller;

import Model.Input;
import Model.Perceptron;
import Model.Weight;
import View.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class NeuralNetworkController {
    private final MainFrame frame;
    private Perceptron perceptron;    
    private Input inputs[];
    private Weight weight;
    private float learningRate;
    private float expectedOutput[];
    private int epoch;
    
    public static void main(String[] args) {
        NeuralNetworkController n = new NeuralNetworkController();
    }
    
    public NeuralNetworkController(){
        frame = new MainFrame();
        init();
        frame.setVisible(true);
    }
    
    private void init(){
        trainBtnOnclick();
        resetBtnOnClick();
        weight = new Weight(3);
        updateValueOnScreen();
    }
    
    private void pickValueOnScreen(){
        perceptron = new Perceptron(frame.getThreshold());
        weight = frame.getWeight();
        inputs = frame.getInputs();
        expectedOutput = frame.getExpectedOutput();
        printSysLog();
    }
    
    private void updateValueOnScreen(){
        frame.setWeightValue(weight.getValue());
    }
    
    private void printSysLog(){
    }
    
    private void doEpochThing(){
        for (int i = 0; i < epoch ; i++) {
            frame.appendLogTextArea("Epoch ke-"+i);
            frame.appendLogTextArea("\nWeight awal = "+Arrays.toString(weight.getValue()));
            frame.appendLogTextArea("\nThreshold = "+perceptron.getThreshold());
            oneEpochCycle();
            updateValueOnScreen();
            frame.appendLogTextArea("\nEpoch ke-"+i+" selesai\n\n");
        }
    }
    
    private void oneEpochCycle(){
        for (int i = 0; i < inputs.length; i++) {
            float error = calculateError(i);
            if(error != 0.0F){
                float[] value = new float[inputs[i].getLength()];
                for (int j = 0; j < inputs[i].getLength(); j++) {
                    value[j] = weight.getValue()[j] + learningRate * inputs[i].getValue()[j] * error;
                }
                weight.setValue(value);
            }
            printEpochLog(i, error);
        }
    }
    
    private void printEpochLog(int index,float errorCode){
        frame.appendLogTextArea("\n\nInput-"+index);
        if(errorCode != 0.0F){
            frame.appendLogTextArea(" Error!"+errorCode);
        }else{
            frame.appendLogTextArea(" tidak ada error");
        }
        frame.appendLogTextArea("\nWeight "+Arrays.toString(weight.getValue()));
    }
    
    private float calculateError(int index){
        return expectedOutput[index] - perceptron.andGateStepfunction(inputs[index], weight);
    }
    
    private void trainBtnOnclick(){
        frame.addTrainBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                epoch = frame.getEpoch();
                learningRate = frame.getLearningRate();
                pickValueOnScreen();
                doEpochThing();
            }
        });
    }
    
    private void resetBtnOnClick(){
        frame.addResetBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                weight = new Weight(3);
                frame.setThreshold(0F);
                perceptron = new Perceptron(0F);
                frame.setLogTextArea("");
                updateValueOnScreen();
            }
        });
    }
}
