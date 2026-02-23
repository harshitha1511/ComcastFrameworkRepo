package practice.test;

import org.testng.annotations.Test;

public class Debugging 
{
	@Test
	public void debug()
	{
        int num1 = 10;
        int num2 = 20;

        int sum = add(num1, num2);
        System.out.println(sum);
    }

    public static int add(int a, int b) 
    {
        int result = a + b;
        return result;
    }
}
