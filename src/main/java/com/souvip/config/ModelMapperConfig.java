package com.souvip.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.souvip.model.entity.Client;
import com.souvip.model.entity.dto.ClientDto;

@Configuration
public class ModelMapperConfig {
   
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        Converter<String, byte[]> passwordConverter = ctx -> ctx.getSource().getBytes();

        modelMapper.createTypeMap(ClientDto.class, Client.class)
        .addMappings(mapper -> mapper.using(passwordConverter).map(ClientDto::getPassword, Client::setPassword));

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }
}