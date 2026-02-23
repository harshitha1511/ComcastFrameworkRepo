package com.comcast.crm.generic.webdrieverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility 
{
	public int getRandomNumber()
	{
		Random random= new Random();
		int randomInt=random.nextInt(5000);
		
		return randomInt;
	}
	
	public String getSystemDateYYYYDDMM()
	{
		Date dateObj=new Date();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(dateObj);
		
		return date;
		
	}
	
	public String getRequiredDataYYYYDDMM(int days)
	{
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String requiredData=sim.format(cal.getTime());
		
		return requiredData;
	}
}
