package com.darklions.testmod.util.MathHelpers;

import java.util.ArrayList;
import java.util.List;

public class CircleCalculator 
{
	private static final double pi = 3.141592654d;
	
	private List<Vec2> coords = new ArrayList<Vec2>();
	
	public CircleCalculator(double r, Vec2 origin)
	{
		coords = Circle(r, origin);
	}
	
	
	public List<Vec2> Circle(double r, Vec2 origin)
	{
		List<Vec2> vecs = new ArrayList<Vec2>();
		
		for(int i = 0; i < 9; i++)
		{
			double a = r * Math.sin(Math.toRadians(90 - (i * 10)));
			double b = r * Math.cos(Math.toRadians(0 + (i * 10)));
			
			double x = origin.X() + a;
			double y = origin.Y() - b;
			
			vecs.add(new Vec2(x, y));
		}
		return vecs;
	}
	
	public List<Vec2> CircleCoords()
	{
		return this.coords;
	}
}
