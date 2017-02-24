
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.zh.framework.annotation.Id;


import com.zh.framework.annotation.Id;
import com.zh.framework.entity.sys.VUserInfo;
import com.zh.framework.service.sys.UserInfoServiceI;

import com.zh.framework.service.sys.UserServiceI;
import com.zh.framework.service.sys.VUserInfoServiceI;


import com.zh.framework.util.Encodes;
import com.zh.framework.util.RSAUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Decoder;


/**
 * Created by Mrkin on 2016/10/26.
 */

public class test {
    @Autowired
    VUserInfoServiceI a;

    @Test
    public void t1() {
//        String pub = Encodes.getPubStr();
//        String name="12312345478789789485456456456";
//        System.out.println("加密结果"+ Encodes.encrypt(pub,name));
//        System.out.println("解密结果" + Encodes.decode(Encodes.encrypt(pub,name)));
//        try {
//            String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaWWxKWJpng8sqhtXstNoU6V8WmjexFqWC4/qI\\r\\nuftOAEGyik9Ht1QrDdNNcdZThZo417r/iSk8RF1ziU26HTo5HyD5D03Ga2vqjq0p+whmnZ+J91iE\\r\\nZoMHN+yTo5wO72wTUntzeBzaVtT3vJS/Wu/gOawYy1MYuoF5jSl06EzsoQIDAQAB\\r\\n";
////           String result= RSAUtil.encrypt("123456",publicKey);
////
//        } catch (Exception e) { System.out.println("加密结果"+result);
////            System.out.println("解密结果"+RSAUtil.decrypt(result));
//            e.printStackTrace();
//        }
    }


    /*@Autowired
    private T_CarMapper carMapper;
    @Autowired
    private CarServiceI carServiceI;
    @Test
    public void mybatis() {
        Annotation[] classAnnotation = T_Car.class.getAnnotations();
        for(Annotation cAnnotation : classAnnotation){
            Class annotationType =  cAnnotation.annotationType();
            System.out.println("User类上的注释有: " +annotationType);
        }
        Field[] fields =  T_Car.class.getDeclaredFields();

        for(Field f : fields) {
            String filedName = f.getName();
            System.out.println("属性名称:【" + filedName + "】");

            //1、获取属性上的指定类型的注释
            Annotation annotation = f.getAnnotation(Id.class);
            System.out.println(annotation!=null?annotation.annotationType():"null");
        }
//        T_Car t_car = carServiceI.getByCarNo("黑EPT017");//carMapper.selectByPrimaryKey("1");
       // T_Car t_car2 = carServiceI.getById("1");//carMapper.selectByPrimaryKey("1");
//        System.out.println(t_car.toString());
//        System.out.println(carServiceI.getById("1"));
//        System.out.println(carServiceI.getBy("1"));

    }*/
    @Autowired
    private UserServiceI userServiceI;

    @Test
    public void test() {

    }
}
