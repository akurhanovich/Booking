package by.bsuir.booking.client.Util;

import by.bsuir.booking.client.model.Room;
import by.bsuir.booking.client.model.Typeroom;
import by.bsuir.booking.client.model.User;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseUtil {
    //***PARSE DATE***
    public static String parseDateToString(Date date){
        System.out.println("(parseDateToString)Date: " + date);
        String result;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(date!=null) {
            result = sdf.format(date);
        }
        else
            result = "";
        System.out.println("(parseDateToString)String: " + result);
        return result;
    }

    public static Date parseStringToDate(String str) throws ParseException {
        System.out.println("(parseStringToDate)String: " + str);
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(str != null) {
            if (!str.equals("")) {
                date = sdf.parse(str);
            }
            else
                date = null;
        }
        else
            date = null;
        System.out.println("(parseStringToDate)Date: " + date.toString());
        return date;
    }

    public static Date parseLongToDate(long date){
        Date d = new Date(date * 1000);
        return d;
    }

    public static long parseDateToLong(Date date){
        return date.getTime();
    }
    //******
    //**PARSE USER***
    public static User parseJsonToUser (JSONObject obj) throws ParseException {

        System.out.println(obj.toString());
        JSONObject obj1= obj.getJSONObject("roleByIdRole");
        Date dob = null;
        if(obj.getInt("idUser")==0)
            dob = parseLongToDate(obj.getLong("dob"));
        else
            dob = parseStringToDate(obj.getString("dob"));

        User user = new User(obj.getInt("idUser"), obj.getInt("idRole"), obj.getString("secondName"), obj.getString("firstName"), obj.getString("patronymic"), obj.getString("username"), obj.getString("password"), dob, (byte) obj.getInt("sex"), obj.getString("passportSeries"), obj.getInt("passportN"), obj.getString("identificationN"), BigDecimal.valueOf(obj.getDouble("cash")), obj1);

        return user;
    }

    public static JSONObject parseUserToJson(User user){
        JSONObject jo = new JSONObject();
        JSONObject joRole = new JSONObject();

        joRole.put("idRole", user.getRoleByIdRole().getIdRole());
        joRole.put("nameRole", user.getRoleByIdRole().getNameRole());
        jo.put("idUser", user.getIdUser());
        jo.put("secondName", user.getSecondName());
        jo.put("firstName", user.getFirstName());
        jo.put("patronymic", user.getPatronymic());
        jo.put("username", user.getUsername());
        jo.put("password", user.getPassword());
        jo.put("dob", parseDateToString(user.getDobDate()));
        jo.put("sex", user.getSex());
        jo.put("passportSeries", user.getPassportSeries());
        jo.put("passportN", user.getPassportN());
        jo.put("identificationN", user.getIdentificationN());
        jo.put("cash", user.getCash());
        jo.put("roleByIdRole", joRole);

        return jo;
    }
    //******
    //***PARSE ROOM***
    public static Room parseJsonToRoom (JSONObject obj) throws ParseException {

        System.out.println(obj.toString());
        JSONObject TRobj= obj.getJSONObject("typeroomByIdTRoom");


        Room room = new Room(obj.getInt("idRoom"), obj.getInt("nRoom"), obj.getInt("idTRoom"), parseJsonToTypeRoom(TRobj));

        return room;
    }

    public static JSONObject parseRoomToJson(Room room){

        JSONObject jo = new JSONObject();
        JSONObject joTypeRoom = parseTypeRoomToJson(room.getTyperoomByIdTRoomTR());

        jo.put("idRoom", room.getIdRoom());
        jo.put("nRoom", room.getnRoom());
        jo.put("idTRoom", room.getIdTRoom());
        jo.put("typeroomByIdTRoom", joTypeRoom);

        return jo;
    }
    //******
    //***PARSE ROOM TYPE***
    public static JSONObject parseTypeRoomToJson(Typeroom typeroom){
        JSONObject jo = new JSONObject();
        JSONObject joPicture = new JSONObject();

        joPicture.put("idPicture", typeroom.getPictureByIdPic().getIdPicture());
        joPicture.put("fileName", typeroom.getPictureByIdPic().getFileName());
        joPicture.put("width", typeroom.getPictureByIdPic().getWidth());
        joPicture.put("height", typeroom.getPictureByIdPic().getHeight());
        joPicture.put("uploadedNname", typeroom.getPictureByIdPic().getUploadedNname());
        jo.put("idTRoom", typeroom.getIdTRoom());
        jo.put("nameTRoom", typeroom.getNameTRoom());
        jo.put("roominess", typeroom.getRoominess());
        jo.put("price", typeroom.getPrice());
        jo.put("idPicture", typeroom.getPictureByIdPic().getIdPicture());
        jo.put("pictureByIdPicture", joPicture);

        return jo;
    }

    public static Typeroom parseJsonToTypeRoom (JSONObject obj) throws ParseException {

        System.out.println(obj.toString());
        JSONObject obj1= obj.getJSONObject("pictureByIdPicture");

        Typeroom typeroom = new Typeroom(obj.getInt("idTRoom"), obj.getString("nameTRoom"), obj.getInt("roominess"), BigDecimal.valueOf(obj.getDouble("price")), obj.getInt("idPicture"), obj1);

        return typeroom;
    }
    //*******
}
