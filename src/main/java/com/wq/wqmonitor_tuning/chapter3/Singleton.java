package com.wq.wqmonitor_tuning.chapter3;

public class Singleton {
	private Singleton() {}
	private static class SingletonHolder{
		private static Singleton instance = new Singleton();
	}
	public static Singleton getInstance() {
		return SingletonHolder.instance;
	}
}
