
package client.qrcode;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import cn.infogiga.service.qrcode.ArrayOfQrcodeBean;
import cn.infogiga.service.qrcode.QrcodeBean;

@WebService(name = "QrcodePortType", targetNamespace = "http://qrcode.service.infogiga.cn")
@SOAPBinding(use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface QrcodePortType {


    @WebMethod(operationName = "createQrcode", action = "")
    public void createQrcode(
        @WebParam(name = "in0", targetNamespace = "http://qrcode.service.infogiga.cn")
        QrcodeBean in0);

    @WebMethod(operationName = "isNew", action = "")
    @WebResult(name = "out", targetNamespace = "http://qrcode.service.infogiga.cn")
    public boolean isNew(
        @WebParam(name = "in0", targetNamespace = "http://qrcode.service.infogiga.cn")
        String in0,
        @WebParam(name = "in1", targetNamespace = "http://qrcode.service.infogiga.cn")
        String in1,
        @WebParam(name = "in2", targetNamespace = "http://qrcode.service.infogiga.cn")
        String in2,
        @WebParam(name = "in3", targetNamespace = "http://qrcode.service.infogiga.cn")
        String in3,
        @WebParam(name = "in4", targetNamespace = "http://qrcode.service.infogiga.cn")
        String in4);

    @WebMethod(operationName = "createQrcode1", action = "")
    public void createQrcode1(
        @WebParam(name = "in0", targetNamespace = "http://qrcode.service.infogiga.cn")
        ArrayOfQrcodeBean in0);

}
