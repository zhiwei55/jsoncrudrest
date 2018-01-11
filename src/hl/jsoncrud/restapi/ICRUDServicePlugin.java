package hl.jsoncrud.restapi;

import hl.jsoncrud.JsonCrudException;
import hl.jsoncrud.common.http.HttpResp;
import hl.jsoncrud.restapi.CRUDServiceReq;

public interface ICRUDServicePlugin {
	
	public CRUDServiceReq preProcess(CRUDServiceReq aCrudReq);
	
	public HttpResp postProcess(CRUDServiceReq aCrudReq, HttpResp aHttpResp);
	
	public HttpResp handleException(CRUDServiceReq aCrudReq, HttpResp aHttpResp, JsonCrudException aJsonCrudException);
	
}