/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.gui;

import com.cnone.khaled.entities.Task;
import com.cnone.khaled.services.TaskService;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author khaledguedria
 */
public class AddForm extends Form {

    //var
    TaskService ts = TaskService.getInstance();

    public AddForm() {

        //CUSTOM
        this.setLayout(BoxLayout.y());
        this.setTitle("Add Task");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeForm().showBack();
        });

        //Widgets
        TextField nameTF = new TextField("", "Task's name");
        TextField statusTF = new TextField("", "Task's status [0 - 1]");
        Button submitBtn = new Button("Submit");

        //actions
        submitBtn.addActionListener((evt) -> {
            if (ts.addTask(new Task(nameTF.getText(), Integer.parseInt(statusTF.getText())))) {
                Dialog.show("Success", "Task Inserted successfully", "Got it", null);
            } else {
                Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
            }
        });

        //end
        this.addAll(nameTF, statusTF, submitBtn);

    }

}
