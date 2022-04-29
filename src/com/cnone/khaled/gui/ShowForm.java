/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cnone.khaled.gui;

import com.cnone.khaled.entities.Task;
import com.cnone.khaled.services.TaskService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author khaledguedria
 */
public class ShowForm extends Form {

    //var
    TaskService ts = TaskService.getInstance();

    public ShowForm() {

        //custom
        this.setLayout(BoxLayout.y());
        this.setTitle("All Tasks");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new HomeForm().showBack();
        });

        //widgets
        SpanLabel sl = new SpanLabel();
        sl.setText(ts.fetchTasks().toString());

        //end
        this.add(sl);

    }

}
