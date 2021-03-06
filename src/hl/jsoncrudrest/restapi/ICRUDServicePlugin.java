package hl.jsoncrudrest.restapi;

import hl.jsoncrud.JsonCrudException;
import java.util.Properties;
import hl.common.http.HttpResp;
import hl.jsoncrudrest.restapi.CRUDServiceReq;

public interface ICRUDServicePlugin {
	
	public CRUDServiceReq preProcess(CRUDServiceReq aCrudReq) throws JsonCrudException;
	
	public HttpResp postProcess(CRUDServiceReq aCrudReq, HttpResp aHttpResp) throws JsonCrudException;
	
	public HttpResp handleException(CRUDServiceReq aCrudReq, HttpResp aHttpResp, JsonCrudException aJsonCrudException);
	
	public Properties getPluginProps(); 
}