package com.souvip.controller;

import com.souvip.model.entity.Client;
import com.souvip.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Client client) {
        clientService.saveOrUpdate(client);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }

    @GetMapping("/clients")
    @ResponseBody
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/client/{id}")
    @ResponseBody
    public Client findById(@PathVariable Long id) {
        return clientService.findById(id);
    }
}