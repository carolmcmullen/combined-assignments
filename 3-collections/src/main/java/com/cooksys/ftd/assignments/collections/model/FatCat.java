package com.cooksys.ftd.assignments.collections.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FatCat implements Capitalist {

	
	
	private FatCat owner;
	
	
    public FatCat(String name, int salary) { 
    	this.name = name;
    	this.salary = salary;	
    }

  
	public FatCat(String name, int salary, FatCat owner) {
		this.name = name;
		this.salary = salary;
		this.owner = owner;    
    }

	private String name;
    /**
     * @return the name of the capitalist
     */
    @Override
    public String getName() {
        
        	return name;
      }
    
    private int salary;
    /**
     * @return the salary of the capitalist, in dollars
     */
    @Override
    public int getSalary() {
       
    	return salary;
    	
    }

    /**
     * @return true if this element has a parent, or false otherwise
     */
    @Override
    public boolean hasParent() {
    	if (owner == null)
    		return false;
    	return true;
        
        
    }

    /**
     * @return the parent of this element, or null if this represents the top of a hierarchy
     */
    @Override
    public FatCat getParent() {
        return owner;
    }

    @Override
    public boolean equals(Object obj) {
    	try{
    		FatCat r1 = (FatCat)obj;
            return (this.getName() == r1.getName() && 
            		(this.getSalary() == r1.getSalary() && 
            		(this.getParent() == r1.getParent())));
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
        
    }
    
}

