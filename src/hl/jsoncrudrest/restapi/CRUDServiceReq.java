package hl.jsoncrudrest.restapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import hl.restapiservices.RESTServiceReq;

public class CRUDServiceReq extends RESTServiceReq {

	//
	private String urlPath 					= null;
	//	
	private Map<String, String> mapConfigs	= null;
	//
	private String jsonCrudKey 				= null;	
	private JSONObject jsonFilters 			= null;
	private List<String> listSorting 		= null;
	private List<String> listReturns 		= null;
	private long pagination_startfrom		= 0;
	private long pagination_fetchsize		= 0;
	
	private long fetchlimit					= 0;
	private boolean isSkipJsonCrudDbProcess	= false;
	//
	
	public CRUDServiceReq(HttpServletRequest aReq, Map<String, String> aCrudConfigMap)
	{
		super(aReq, aCrudConfigMap);
		init(aReq, aCrudConfigMap);
	}
	
	private void init(HttpServletRequest aReq, Map<String, String> aMapCrudConfig)
	{
	
		String[] sPaths = CRUDServiceUtil.getUrlSegments(this.urlPath);
		this.jsonCrudKey = sPaths[0];
		
		Map<String, Map<String, String>> mapQueryParams = CRUDServiceUtil.getQueryParamsMap(aReq);
		jsonFilters = CRUDServiceUtil.getFilters(mapQueryParams);		
		listSorting = CRUDServiceUtil.getSorting(mapQueryParams);
		listReturns = CRUDServiceUtil.getReturns(mapQueryParams);
		
		long[] lStartNFetchSize = CRUDServiceUtil.getPaginationStartNFetchSize(mapQueryParams);
		this.pagination_startfrom = lStartNFetchSize[0];
		this.pagination_fetchsize = lStartNFetchSize[1];
		
		String sFetchLimit = aMapCrudConfig.get(CRUDService._RESTAPI_FETCH_LIMIT);
		if(sFetchLimit!=null)
		{
			try {
				this.fetchlimit = Long.parseLong(sFetchLimit);
			} catch(NumberFormatException ex)
			{
				this.fetchlimit = 0;
			}
		}
	}
	///
	
	public String getCrudKey()
	{
		return jsonCrudKey;
	}
	
	public boolean isSkipJsonCrudDbProcess() {
		return isSkipJsonCrudDbProcess;
	}

	public void setSkipJsonCrudDbProcess(boolean isSkipJsonCrudDbProcess) {
		this.isSkipJsonCrudDbProcess = isSkipJsonCrudDbProcess;
	}

	public Map<String, String> getCrudConfigMap()
	{
		if(mapConfigs==null)
			return new HashMap<String, String>();
		return mapConfigs;
	}
	
	public Map<String, String> addCrudConfigMap(String aKey, String aValue)
	{
		if(mapConfigs==null)
			mapConfigs = new HashMap<String, String>();
		mapConfigs.put(aKey, aValue);
		return mapConfigs;
	}
	
	public JSONObject getCrudFilters()
	{
		if(jsonFilters==null)
			return new JSONObject();
		return jsonFilters;
	}
	
	public JSONObject addCrudFilter(String aKey, Object aValue)
	{
		if(jsonFilters==null)
			jsonFilters = new JSONObject();
		return jsonFilters.put(aKey, aValue);
	}
	
	public List<String> getCrudReturns()
	{
		if(listReturns==null)
			return new ArrayList<String>();
		return listReturns;
	}
	
	public List<String> addCrudReturns(String aSortingStr)
	{
		if(listReturns==null)
			return new ArrayList<String>();
		listReturns.add(aSortingStr);
		return listReturns;
	}	
	
	public List<String> getCrudSorting()
	{
		if(listSorting==null)
			return new ArrayList<String>();
		return listSorting;
	}
	
	public List<String> addCrudSorting(String aSortingStr)
	{
		if(listSorting==null)
			return new ArrayList<String>();
		listSorting.add(aSortingStr);
		return listSorting;
	}	
	
	public long getPaginationStartFrom()
	{
		return this.pagination_startfrom;
	}
	
	public void setPaginationStartFrom(long aStarFrom)
	{
		this.pagination_startfrom = aStarFrom;
	}
	
	public long getPaginationFetchSize()
	{
		return this.pagination_fetchsize;
	}
	
	public void setPaginationFetchSize(long aFetchSize)
	{
		this.pagination_fetchsize = aFetchSize;
	}
	
	public long getFetchLimit()
	{
		return this.fetchlimit;
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("req.getPathInfo:").append(getHttpServletReq().getPathInfo());
		sb.append("\n").append("CrudKey:").append(getCrudKey());
		sb.append("\n").append("HttpMethod:").append(getHttpMethod());
		sb.append("\n").append("InputContentType:").append(getInputContentType());
		sb.append("\n").append("InputContentData:").append(getInputContentData());
		sb.append("\n").append("CrudFilters:").append(getCrudFilters());
		sb.append("\n").append("CrudSorting:").append(getCrudSorting());
		sb.append("\n").append("CrudReturns:").append(getCrudReturns());
		sb.append("\n").append("PaginationStartFrom:").append(getPaginationStartFrom());
		sb.append("\n").append("PaginationFetchSize:").append(getPaginationFetchSize());
		//
		sb.append("\n").append("FetchLimit:").append(getFetchLimit());
		sb.append("\n").append("EchoAttrs:").append(getEchoJsonAttrs());
		
		return sb.toString();
	}
}
