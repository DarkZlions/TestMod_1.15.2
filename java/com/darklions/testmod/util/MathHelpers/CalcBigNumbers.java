package com.darklions.testmod.util.MathHelpers;

public class CalcBigNumbers 
{
	private float b;	//big
	private float s;	//smöll
	
	public CalcBigNumbers(float f1, float f2)
	{
		if(f1 >= f2)
		{
			this.b = f1;
			this.s = f2;
		}
		else
		{
			this.b = f2;
			this.s = f1;
		}
	}
	
	public float ReturnBig()
	{
		return this.b;
	}
	
	public float ReturnSmall()
	{
		return this.s;
	}
}
