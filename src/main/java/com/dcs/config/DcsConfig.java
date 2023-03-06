package com.dcs.config;

import com.dcs.entity.ChargeDetailRecord;
import com.dcs.repo.ChargeDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DcsConfig {
    //re-init db with default values each time the project starts
    @Bean
    CommandLineRunner commandLineRunner(ChargeDetailRepository chargeDetailRepository) {
        return args -> {
            ChargeDetailRecord cdr1 = new ChargeDetailRecord(
                    "KAIH5501",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30),
                    3.05);
            ChargeDetailRecord cdr2 = new ChargeDetailRecord(
                    "FTA34443",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30),
                    3.1);
            ChargeDetailRecord cdr3 = new ChargeDetailRecord(
                    "ADFFF10122",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30),
                    3.15);
            ChargeDetailRecord cdr4 = new ChargeDetailRecord(
                    "AFDSF0302",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30),
                    3.3);
            ChargeDetailRecord cdr5 = new ChargeDetailRecord(
                    "SWW1233",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30),
                    3.5);
            ChargeDetailRecord cdr6 = new ChargeDetailRecord(
                    "ASF3466",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30),
                    6.0);
            ChargeDetailRecord cdr7 = new ChargeDetailRecord(
                    "POI93993",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30),
                    6.1);
            ChargeDetailRecord cdr8 = new ChargeDetailRecord(
                    "HJK09876",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30),
                    6.3);
            ChargeDetailRecord cdr9 = new ChargeDetailRecord(
                    "BVC45677",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30),
                    6.5);
            ChargeDetailRecord cdr10 = new ChargeDetailRecord(
                    "MHTR097554",
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30),
                    6.7);
            ChargeDetailRecord cdr11 = new ChargeDetailRecord(
                    "KAIH5501",
                    LocalDateTime.now().plusMinutes(60),
                    LocalDateTime.now().plusMinutes(90),
                    3.25);
            chargeDetailRepository.saveAll(List.of(cdr1, cdr2, cdr3, cdr4, cdr5,
                    cdr6, cdr7, cdr8, cdr9, cdr10, cdr11));
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
