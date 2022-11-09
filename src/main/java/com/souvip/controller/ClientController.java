package com.souvip.controller;

import com.souvip.model.entity.Client;
import com.souvip.model.entity.dto.ClientDto;
import com.souvip.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/client")
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

    //resolucao temporaria. utilizar bibliotecas que facilitem o transporte de dados dos dtos para as entidades
    private Client getClient(ClientDto dto) {
        return Client.builder()
                .name(dto.getName())
                .cpf(Long.valueOf(dto.getCpf().replace(".", "").replace("-", "")))
                .email(dto.getEmail())
                .password(dto.getPassword().getBytes())
                .events(dto.getEvents())
                .tickets(dto.getTickets())
                .build();
    }
}