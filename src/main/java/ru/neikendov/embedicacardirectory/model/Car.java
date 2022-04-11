package ru.neikendov.embedicacardirectory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "car")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String registerSign;

    @Column(nullable = false)
    private String carModel;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private Integer yearOfIssue;

    @JsonIgnore
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return id != null && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
