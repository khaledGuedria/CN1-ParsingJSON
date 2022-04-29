/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.services;

import com.cnone.khaled.entities.Task;
import com.cnone.khaled.utilities.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author khaledguedria
 */
public class TaskService {

    //var
    ConnectionRequest req;
    static TaskService instance = null;

    //util
    boolean resultOK = false;
    List<Task> tasks = new ArrayList<>();

    //Constructor
    private TaskService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static TaskService getInstance() {
        if (instance == null) {
            instance = new TaskService();
        }

        return instance;
    }

    //ACTIONS
    //Add
    public boolean addTask(Task t) {

        //1
        String addURL = Statics.BASE_URL + "/create";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("name", t.getName());
        req.addArgument("status", t.getStatus() + "");

        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }

    //FETCH
    public List<Task> fetchTasks() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/get";
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }

    //Parse
    public List<Task> parseTasks(String jsonText) {

        //var
        tasks = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> tasksListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) {
                
                Task t = new Task();
                t.setId((double) item.get("id"));
                t.setName((String) item.get("name"));
                t.setStatus((double) item.get("status"));
                
                tasks.add(t);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return tasks;
    }

}
