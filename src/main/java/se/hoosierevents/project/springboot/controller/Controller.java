package se.hoosierevents.project.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import se.hoosierevents.project.springboot.service.Service;

public interface Controller {

    @Autowired
    Service service = null;

    public String control();
}
