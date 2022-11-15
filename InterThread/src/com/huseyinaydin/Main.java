package com.huseyinaydin;

class Q{
	int num;
	boolean valueSet = false;
	public synchronized void put(int num) {
		while(valueSet) {
			System.out.println("while 1'e girdi");
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		System.out.println("put " + num);
		this.num = num;
		this.valueSet = true;
		notify(); //notify'i bilmiyorum. çalýþmasý için metoda synchronized eklemek gerekiyor
	}
	
	public synchronized void get() {
		while(!valueSet) {
			System.out.println("while 1'e girdi");
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		valueSet = false;
		System.out.println("num " + this.num);
	}
}

class Producer implements Runnable{

	Q q;
	
	public Producer(Q q) {
		super();
		this.q = q;
		Thread thread = new Thread(this,"Producer");
		thread.start();
	}
	@Override
	public void run() {
		int i = 0;
		while(true) {
			q.put(i++);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
}

class Consumer implements Runnable{
	Q q;
	
	public Consumer(Q q) {
		super();
		this.q = q;
		Thread thread = new Thread(this,"Consumer");
		thread.start();
	}

	@Override
	public void run() {
		while(true) {
			q.get();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
}

public class Main {

	public static void main(String[] args) {
		Q q = new Q();
		new Producer(q);
		new Consumer(q);
	}

}
