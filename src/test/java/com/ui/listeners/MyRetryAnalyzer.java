package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer{
	
	//private static final int MAX_NUMBER_OF_ATTEMPTS = Integer.parseInt(PropertiesUtil.readProperty(Env.DEV, "MAX_NUMBER_OF_ATTEMPTS"));
	private static int currentAttempt = 1;
	private static final int MAX_NUMBER_OF_ATTEMPTS = JSONUtility.readJSON(Env.QA).getMAX_NUMBER_OF_ATTEMPTS();
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(currentAttempt<=MAX_NUMBER_OF_ATTEMPTS) {
			currentAttempt++;
			return true;
		}
		return false;
	}
	
}
