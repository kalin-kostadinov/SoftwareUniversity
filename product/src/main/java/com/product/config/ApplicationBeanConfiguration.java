package com.product.config;

import com.product.utils.XmlParser;
import com.product.utils.XmlParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Bean
    public XmlParser xmlParser() {
        return new XmlParserImpl();
    }



}

