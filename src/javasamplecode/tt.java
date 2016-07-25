package javasamplecode;

public class tt extends Thread{
	 public void run(){
	   System.out.println("running...");
	 }
	 public static void main(String args[]){
	  tt t1=new tt();
	  t1.start();
	  ThreadGroup g;
	 }
	}
