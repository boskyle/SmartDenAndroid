/*Team Name: Humber Elites*/
package smartden.project;

import java.io.Serializable;

public class Sensor_Info implements Serializable   {

   private String sensor_name;
   private String qrcode;
   private String location;
   private int uid;

public Sensor_Info(){
     sensor_name =" ";
     qrcode = " ";
     location = " ";
     //uid = 0;
}

    public void setname(String name){
        this.sensor_name = name;
    }

//    public void setUid(int uid){
//        this.uid = uid;
//    }

    public void setqrcode(String qr){
        this.qrcode = qr;
    }

    public void setlocation(String sensor_location){
        this.location = sensor_location;
    }

    public String getname(){
        return sensor_name;
    }

    public String getQrcode(){
        return qrcode;
    }

    public String getLocation(){
        return location;
    }

//    public String getUid(){
//    return String.valueOf(uid);
//    }


}
