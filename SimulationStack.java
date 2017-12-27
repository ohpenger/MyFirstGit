package test_four;

public class SimulationStack {    
			public int maxSize;     
			Object[] data;  //∂—’ª    
			public int top;     //’ª∂•Œª÷√    
			//≥ı ºªØ∂—’ª   
			public SimulationStack(int maxSize) {      
				this.maxSize = maxSize;      
				data = new Object[maxSize];      
				top = -1;   
			}    

			//≈–∂œ’ªø’     
			public boolean isEmpty(){     return top == -1;     }      

			//≈–∂œ’ª¬˙     
			public boolean isFull(){     return top+1 == maxSize;    }    

			//»Î’ª    
			public boolean push(Object data){     
				if(isFull()){          
					System.out.println("’ª“—¬˙!"); 
					return false;     
				}      
				this.data[++top] = data;     
				return true;    
			} 
			//≥ˆ’ª    
			public Object pop() throws Exception{     
				if(isEmpty())     {          
					throw new Exception("’ª“—ø’!");     
					}      
				return this.data[top--];    
				}
		}
