package org.jLOAF.preprocessing.filter;

import java.lang.reflect.InvocationTargetException;

import org.jLOAF.casebase.CaseBase;
/**
 * a caseBase Filter is the parent class of many casebase filter, they all use the decorator pattern , where a casebase filter takes a casebase filter
 * which also takes another casebase filter, and so on.
 * @author Owner
 *
 */
public abstract  class CaseBaseFilter {
	
		
		protected CaseBaseFilter filter;
		/**
		 * Constructor
		 * @param f a filter to filter the caseBase before this filter
		 */
		public CaseBaseFilter(CaseBaseFilter f){
			filter=f;
		}
		
	
	/** 
	 * Given a CaseBase, the method will filter the
	 * CaseBase based on specific criteria. It will
	 * then return the filtered CaseBase.
	 * 
	 * @param initialCB The CaseBase to filter
	 * @return The filtered CaseBase
	 *
	 * @author Michael W. Floyd
	 * @since 0.3
	 */
	
	
	public abstract CaseBase filter(CaseBase initial);
	public void setFilter(CaseBaseFilter filter){
		this.filter=filter;
	}

	public static CaseBaseFilter getFilter(String string) {
		return Filters.valueOf(string).getFilter();
	}

	public CaseBaseFilter getCopy() {
		Class<? extends CaseBaseFilter> c=this.getClass();
		Object [] paramValuesSub ={null};
		try {
			return c.getConstructor(CaseBaseFilter.class).newInstance(paramValuesSub);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
