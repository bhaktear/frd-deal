package abl.frd.service;
import java.util.*;

public class CommonService {
    public static Map<String, Object> getResp(int err, String msg, List<Map<String, Object>> data) {
		Map<String, Object> resp = new HashMap<>();
		resp.put("err",err);
		resp.put("msg", msg);
		resp.put("data", data);
		return resp;
	}
    
    public static boolean checkEmptyString(String str){
        if(str == null || str.trim().isEmpty()) return true;
        try{
            //check number
            double number = Double.parseDouble(str);
            return number == 0.0;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public static String convertObjToString(Object obj){
        if(obj == null) return null;
        return String.valueOf(obj);
    }

    public static Integer convertObjToInteger(Object val){
        if (val == null) {
            return null;
        }
        if (val instanceof Number) {
            return ((Number) val).intValue();
        }
        try {
            return Integer.parseInt(String.valueOf(val).trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }


    public static Double convertStringToDouble(String str){
        Double number;
        try{
            number = Double.parseDouble(str);
        }catch(NumberFormatException e){
            number = 0.0;
        }
        return number;
    }

    public static String convertDoubleToString(Double number){
        return String.valueOf(number);
    }
}
