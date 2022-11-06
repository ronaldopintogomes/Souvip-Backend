package com.souvip.service;

import com.souvip.model.entity.Client;
import com.souvip.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void saveOrUpdate(Client client) {
        clientRepository.save(client);
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