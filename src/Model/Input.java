package Model;

public class Input {
    private float value[];
    private int length;
    
    public Input(float input[]){
        this.value = input;
        this.length = input.length;
    }   

    public int getLength() { return length;}

    public float[] getValue() { return value; }
}
