package test_four;

public class SimulationStack {    
			public int maxSize;     
			Object[] data;  //��ջ    
			public int top;     //ջ��λ��    
			//��ʼ����ջ   
			public SimulationStack(int maxSize) {      
				this.maxSize = maxSize;      
				data = new Object[maxSize];      
				top = -1;   
			}    

			//�ж�ջ��     
			public boolean isEmpty(){     return top == -1;     }      

			//�ж�ջ��     
			public boolean isFull(){     return top+1 == maxSize;    }    

			//��ջ    
			public boolean push(Object data){     
				if(isFull()){          
					System.out.println("ջ����!"); 
					return false;     
				}      
				this.data[++top] = data;     
				return true;    
			} 
			//��ջ    
			public Object pop() throws Exception{     
				if(isEmpty())     {          
					throw new Exception("ջ�ѿ�!");     
					}      
				return this.data[top--];    
				}
		}
