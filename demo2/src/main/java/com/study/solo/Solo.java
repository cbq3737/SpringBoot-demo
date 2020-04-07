package com.study.solo;

public class Solo{
	String name;
	int howLong;

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public int getHowLong()
	{
		return howLong;
	}

	public void setHowLong(int howLong)
	{
		this.howLong = howLong;
	}

	@Override
	public String toString()
	{
		return "Solo{" + 
				"name='" + name +'\''+
				", howLong ="+ howLong+
				'}'; 
	}
}