package ru.specialist.service;

public class MyServiceProxy implements ru.specialist.service.MyService {
  private String _endpoint = null;
  private ru.specialist.service.MyService myService = null;
  
  public MyServiceProxy() {
    _initMyServiceProxy();
  }
  
  public MyServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initMyServiceProxy();
  }
  
  private void _initMyServiceProxy() {
    try {
      myService = (new ru.specialist.service.MyServiceServiceLocator()).getMyService();
      if (myService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)myService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)myService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (myService != null)
      ((javax.xml.rpc.Stub)myService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ru.specialist.service.MyService getMyService() {
    if (myService == null)
      _initMyServiceProxy();
    return myService;
  }
  
  public java.lang.String serverTime() throws java.rmi.RemoteException{
    if (myService == null)
      _initMyServiceProxy();
    return myService.serverTime();
  }
  
  
}