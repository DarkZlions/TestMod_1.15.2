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
		
		List<Vec3> coord = new ArrayList<Vec3> ();
		
		CalcBigNumbers calcX = new CalcBigNumbers(p1.X(), p2.X());
		CalcBigNumbers calcY = new CalcBigNumbers(p1.Y(), p2.Y());
		CalcBigNumbers calcZ = new CalcBigNumbers(p1.Z(), p2.Z());
		
		x1 = calcX.ReturnBig();
		x2 = calcX.ReturnSmall();
		
		y1 = calcY.ReturnBig();
		y2 = calcY.ReturnSmall();
		
		z1 = calcZ.ReturnBig();
		z2 = calcZ.ReturnSmall();
		
		newX = x2;
		newY = y2;
		newZ = z2;
		
		sX = x1 - x2;
		sY = y1 - y2;
		sZ = z1 - z2;
		
		A = sX + sY + sZ;
		
		asX = sX / A;
		asY = sY / A;
		asZ = sZ / A;
		
		for(int i = 0; i < A; i++)
		{
			newX = newX + asX;
			newY = newY + asY;
			newZ = newZ + asZ;
			coord.add(new Vec3(newX, newY, newZ));
		}
		
		return coord;
	}
	
	public List<Vec3> CoordListVec3()
	{
		return this.CoordsVec3;
	}
}
