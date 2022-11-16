package com.souvip.controller;

import com.souvip.model.entity.Client;
import com.souvip.model.entity.dto.ClientDto;
import com.souvip.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ClientDto clientDto) throws Exception {
        clientService.save(modelMapper.map(clientDto, Client.class));
    }

    @PutMapping("/update")
    public void update(@RequestBody ClientDto clientDto) {
        clientService.update(modelMapper.map(clientDto, Client.class));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }

    @GetMapping("/all")
    public List<ClientDto> findAll() {
        return clientService.findAll()
            .stream()
            .map(client -> modelMapper.map(client, ClientDto.class))
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClientDto findById(@PathVariable Long id) {
        return modelMapper.map(clientService.findById(id), ClientDto.class);
    }
}