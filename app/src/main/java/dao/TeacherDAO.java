package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import WebService.WSAccess;
import model.Teacher;

public class TeacherDAO {

    private final String SERVIDOR = "http://192.168.0.10:8080/PAndroidWS/resources/teacher"; /*ADD SERVER LOCATION OR THE IP OF MACHINE YOU'RE WORKING ON for example here i used 10.2.2.68*/
    private final WSAccess WEBSERVICE = new WSAccess();
    private final String REGISTER = SERVIDOR + "/register/";
    private final String EDIT = SERVIDOR + "/edit/";
    private final String DELETE = SERVIDOR + "/delete/";
    private final String SEARCH = SERVIDOR + "/search/";

    public boolean register(Teacher teacher){
        try{
            Gson gson = new Gson();
            String json = gson.toJson(teacher);

            return WEBSERVICE.httpPost(REGISTER, json);
        }catch(Exception e){e.printStackTrace();}
        return false;
    }

    public List<Teacher> listAll(){
        try{
            Gson gson = new Gson();
            String json = WEBSERVICE.httpGet(SEARCH);
            Type type = new TypeToken<List<Teacher>>() {}.getType();
            List<Teacher> teachers = gson.fromJson(json, type);
            return teachers;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }

    public Teacher findById(int id){
        try{
            Gson gson = new Gson();
            String json = WEBSERVICE.httpGet(SEARCH + "by" + String.valueOf(id));
            Type type = new TypeToken<Teacher>() {}.getType();
            Teacher teacher = gson.fromJson(json, type);
            return teacher;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }

    public boolean edit(Teacher teacher){
        try{
            Gson gson = new Gson();
            String json = gson.toJson(teacher);

            return WEBSERVICE.httpPut(EDIT, json);
        }catch(Exception e){e.printStackTrace();}
        return false;
    }

    public boolean delete(int id){
        try{
            return WEBSERVICE.httpDelete(DELETE, String.valueOf(id));
        }catch(Exception e){e.printStackTrace();}
        return false;
    }
}
