package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import com.cooksys.ftd.assignments.collections.model.WageSlave;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {
	
	List<Capitalist> corporation;
	Capitalist child;
	
	public MegaCorp(){
		this.corporation = new ArrayList<Capitalist>();
	}
	
    /**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean add(Capitalist capitalist) {
    	if(capitalist == null)
    		return false; 
    	
    	
        // if capitalist is present don't add it, return false
    	if(this.has(capitalist))
    	{
    		child = null;
    		return false; 	
    	}
    		
        
        //if element has a parent and parent isnt part of hierarchy ADD the parent and then ADD element
    	if(capitalist.hasParent())
    	{
    		// before doing recursive call set child
    		child = capitalist;    		    		
    		this.add(capitalist.getParent());
    		child = null;
    		this.corporation.add(capitalist);
    		return true;    		
    	}
    	
    	if (capitalist.getClass() == WageSlave.class) {
    	    return false;
    	} 
    	//if capitalist has no parent and isnt a parent itself dont add it, return false
    	if(child != null){
    		if(child.getParent().equals(capitalist))
    		{
    			this.corporation.add(capitalist);
            	return true;
    		}
    		
    	}
    	
    	return false;    
    }

    /**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Capitalist capitalist) {
    	for(int i = 0;i < corporation.size();i++)
    	{
    		if(corporation.get(i).equals(capitalist)){
    			return true;
    		}    			
    	}
    	return false;
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Capitalist> getElements() {
    	Set<Capitalist> hashSet = new HashSet<Capitalist>(); 
    	for(int i = 0;i < corporation.size();i++)
    	{
    		hashSet.add(corporation.get(i));    		   			
    	}
    	
    	return hashSet;
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<FatCat> getParents() {
    	Set<FatCat> hashSet = new HashSet<FatCat>(); 
    	for(int i = 0;i < corporation.size()-1;i++)
    	{
    		hashSet.add((FatCat) corporation.get(i));    		   			
    	}
    	
    	return hashSet;
    }

    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Capitalist> getChildren(FatCat fatCat) {
    	Set<Capitalist> hashSet = new HashSet<Capitalist>(); 
    	
    	boolean foundItem = false;
    	for(int i = 0;i < corporation.size();i++)
    	{
    		if(corporation.get(i).equals(fatCat)){
    			foundItem = true;
    		} 
    		
    		if(foundItem){
    			hashSet.add(corporation.get(i));
    		}
    		   			
    	}
    	return hashSet;
    }

    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
    	Map<FatCat, Set<Capitalist>> map = new HashMap<FatCat, Set<Capitalist>>();
    	
    	for(int i = 0;i < corporation.size();i++)
    	{
    		if (corporation.get(i).getClass() != WageSlave.class) {
    			map.put((FatCat) corporation.get(i), this.getChildren(corporation.get(i).getParent()));
        	}    				   			
    	}
    	
        return map;
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<FatCat> getParentChain(Capitalist capitalist) {
    	List<FatCat> fatCats = new ArrayList<FatCat>();
    	if(capitalist != null){
    		if(capitalist.hasParent()){
        		fatCats.add(capitalist.getParent());
        		FatCat fatCat = capitalist.getParent();
        		while(fatCat.hasParent()){
        			fatCats.add(fatCat.getParent());
        			fatCat = fatCat.getParent();
        		}
        	}
    	}
    	
        return fatCats;
    }
}
