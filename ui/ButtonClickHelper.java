package ui;
import data.GeneData;

public class ButtonClickHelper extends Thread  {
	
	private int id;
	private GeneData gd;
	
	public ButtonClickHelper(GeneData g, int i) {
		gd = g;
		id = i;
	}
	
	public void run() {
		try {
			System.out.println("alko");
			Thread.sleep(500);
			
			if (id != 0) {
				gd.current = id;
				gd.parent.update();
			}
			System.out.println("loppus");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

/*
public class ButtonClickHelper implements Runnable {
	
	Thread runner;
	
	public ButtonClickHelper() {
	}
	
	public ButtonClickHelper(String threadName) {
		runner = new Thread(this, threadName); // (1) Create a new thread.
//		System.out.println(runner.getName());
	}
	
	public void run() {
		System.out.println("run");
		try {
			this.waitt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("4000 done");
		runner.interrupt();
	}
		
	public void start() {
		System.out.println("start");
		runner.run();
	}
	
	public void stop() {
		System.out.println("stop");
		runner.interrupt();
		//runner.stop();
	}
	
	private void waitt() throws InterruptedException {
		Thread.sleep(2000);
		//this.wait(4000);
	}
}
*/