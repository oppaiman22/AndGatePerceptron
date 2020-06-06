package Model;

public class Perceptron {
    private float threshold = 0;
    
    public Perceptron(float threshold){
        this.threshold = threshold;
    }
    
    public float andGateStepfunction(Input input,Weight weight){
        float result = 0;
        for (int i = 0; i < input.getLength() ; i++) {
            result += input.getValue()[i] * weight.getValue()[i];
        }
        return result < threshold ? 0F : 1F;
    }

    public void setThreshold(float threshold) { this.threshold = threshold; }
    
    public float getThreshold(){ return threshold; }
}
