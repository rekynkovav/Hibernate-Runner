package org.example.com.dmdev;

import jakarta.persistence.Table;
import org.example.com.dmdev.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

class HibernateRunnerTest {

    @Test
    void checkReflectionApi(){
        User user = User.builder()
                .username("asd@mail.com")
                .firstname("Ivan")
                .lastname("Ivanov")
                .birthDate(LocalDate.of(2000,1,19))
                .age(20)
                .build();
        String sql = """
                insert
                into
                %s
                (%s)
                values
                (%s)
                """;
        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
                .orElse(user.getClass().getName());
    }

}