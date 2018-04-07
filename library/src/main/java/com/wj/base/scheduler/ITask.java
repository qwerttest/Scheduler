package com.wj.base.scheduler;

public abstract class ITask {

	private boolean isDebug = true;
	protected String mCreateDesc;

	public ITask() {
		if(isDebug) {
			StackTraceElement stackTraceElement1 = Thread.currentThread().getStackTrace()[3];
			StackTraceElement stackTraceElement2 = Thread.currentThread().getStackTrace()[4];
			mCreateDesc = stackTraceElement2.getClassName() + "::" + stackTraceElement2.getMethodName() 
						+ "(" + stackTraceElement2.getFileName() + ":" + stackTraceElement2.getLineNumber() + ") ** "
						+ stackTraceElement1.getClassName() + "::" + stackTraceElement1.getMethodName() 
						+ "(" + stackTraceElement1.getFileName() + ":" + stackTraceElement1.getLineNumber() + ")";
		} else{
			mCreateDesc = "";
		}
	}

	protected abstract void run();

}