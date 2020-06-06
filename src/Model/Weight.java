package Model;

import java.util.Random;

public class Weight {
    private float value[];
    private int length;
    
    public Weight(float value[]){
        this.value = value;
        length = value.length;
    }
  
    public Weight(int length) {
      this.value = new float[length];
      for (int i = 0; i < length; i++)
        this.value[i] = new Random().nextFloat(); 
      this.length = length;
    }

    public void setValue(float[] value) {
        this.value = value;
    }
    
    public float[] getValue(){ 
        return value; 
    }
}
