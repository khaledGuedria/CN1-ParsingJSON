/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author khaledguedria
 */
public class HomeForm extends Form{
    
    
    //var

    public HomeForm() {
        
        //custom
        this.setLayout(BoxLayout.yCenter());
        this.setTitle("Home");
        
        //widgets
        Button addTaskBtn = new Button("Add Task");
        Button showTaskBtn = new Button("Show Task");
        
        //actions
        addTaskBtn.addActionListener((evt) -> {
           
            new AddForm().show();
            
        });
        //..
        showTaskBtn.addActionListener((evt) -> {
           
            new ShowForm().show();
            
        });
        
        //end
        this.addAll(addTaskBtn, showTaskBtn);
        
    }
    
    
    
}
