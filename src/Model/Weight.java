package Model;

import java.util.Random;

public class Weight {
    private float value[];
    
    public Weight(float value[]){
        this.value = value;
    }
  
    public Weight(int length) {
      this.value = new float[length];
      for (int i = 0; i < length; i++)
        this.value[i] = new Random().nextFloat(); 
    }

    public void setValue(float[] value) { this.value = value; }
    
    public float[] getValue(){  return value; }
}
