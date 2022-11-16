package com.souvip.service;

import com.souvip.model.entity.Client;
import com.souvip.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void save(Client client) throws Exception {
        if(clientRepository.findByCpf(client.getCpf()).isEmpty()) {
            clientRepository.save(client);
        } else {
            throw new Exception("Cliente já cadastrado!");
        }
    }
    public void update(Client client) {
        clientRepository.findByCpf(client.getCpf()).ifPresentOrElse(
            clientBD -> {
                modelMapper.map(client, clientBD);
                clientRepository.save(clientBD);
            },
            () -> {throw new NullPointerException("Client not found!");}
        );
    }

    public void delete(Long id) {
        clientRepository.findById(id).ifPresentOrElse(
            client -> clientRepository.delete(client),
            () -> {throw new NullPointerException("Client not found!");}
        );
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NullPointerException("Client not found!"));
    }

    
}