package com.darklions.testmod.util.MathHelpers;

import java.util.ArrayList;
import java.util.List;

public class CalcCoordinates 
{
	private List<Vec3> CoordsVec3;

	public CalcCoordinates(Vec3 p1, Vec3 p2)
	{
		this.CoordsVec3 = CoordinatesVec3(p1, p2);
	}

	
	private List<Vec3> CoordinatesVec3(Vec3 p1, Vec3 p2)
	{
		float x1;
		float x2;
		float y1;
		float y2;
		float z1;
		float z2;
		
		float sX;	//Distance between the x1 and x2
		float sY;	//Distance between the y1 and y2
		float sZ;
		
		float A;	//How much steps from each point
		
		float asX;	//Distance between each x-point
		float asY;	//Distance between each y-point
		float asZ;
		
		float newX;
		float newY;
		float newZ;
		
		CalcNumbers calc = new CalcNumbers();
		
		List<Vec3> coord = new ArrayList<Vec3> ();
		
		float[] calcX = calc.CalcBiggerNumber(p1.X(), p2.X());
		float[] calcY = calc.CalcBiggerNumber(p1.Y(), p2.Y());
		float[] calcZ = calc.CalcBiggerNumber(p1.Z(), p2.Z());
		
		x1 = calcX[0];
		x2 = calcX[1];
		
		y1 = calcY[0];
		y2 = calcY[1];
		
		z1 = calcZ[0];
		z2 = calcZ[1];
		
		newX = p1.X();
		newY = p1.Y();
		newZ = p1.Z();
		
		sX = x1 - x2;
		sY = y1 - y2;
		sZ = z1 - z2;
		
		A = sX + sY + sZ;
		
		asX = sX / A;
		asY = sY / A;
		asZ = sZ / A;
		
		for(int i = 0; i < A; i++)
		{
			newX = calc.IfMinusNumber(p1.X(), p2.X()) ? newX - asX : newX + asX;
			newY = calc.IfMinusNumber(p1.Y(), p2.Y()) ? newY - asY : newY + asY;
			newZ = calc.IfMinusNumber(p1.Z(), p2.Z()) ? newZ - asZ : newZ + asZ;
			
			coord.add(new Vec3(newX, newY, newZ));
		}
		
		return coord;
	}
	
	public List<Vec3> CoordListVec3()
	{
		return this.CoordsVec3;
	}
}
