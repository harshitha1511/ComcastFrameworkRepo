package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.comcast.basetest.BaseClass;

public class ListenersPractice implements ITestListener
{
    
	ExtentReports report;
	ExtentTest test;
	@Override
	public void onTestStart(ITestResult result) 
	{
		String testCase=result.getMethod().getMethodName();
		//Reporter.log(testCase+"Execccution Started");
		test=report.createTest(testCase);
		test.log(Status.INFO, testCase+"Exceution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		String testCase=result.getMethod().getMethodName();
		//Reporter.log(testCase+"Execccution Passed");
		Reporter.log(Status.PASS+"Execccution Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		String testCase=result.getMethod().getMethodName();
		//Reporter.log(testCase+"Execccution Failed");
		test.log(Status.FAIL, testCase+"Execution Failed");
		
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		String src=ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src);
//		File src=ts.getScreenshotAs(OutputType.FILE);
//		File dest=new File("./screenshot/"+testCase+"errorShots.png") ;
		
//		try {
//			FileHandler.copy(src, dest);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		String testCase=result.getMethod().getMethodName();		
		
		
		test.log(Status.SKIP, testCase+"Execution Failed");
	}

	@Override
	public void onStart(ITestContext context) 
	{
		ExtentSparkReporter spark=new ExtentSparkReporter("./LowLevelSpark/VtigerReport.html");
		spark.config().setDocumentTitle("AdvancedReports");
		spark.config().setReportName("vtigerReport");
		spark.config().setTheme(Theme.STANDARD);
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Laptop", "Lenovo");
		report.setSystemInfo("Os", "Windows");
		report.setSystemInfo("Browser", "Chrome");
		
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		report.flush();
	}
	
	
}
