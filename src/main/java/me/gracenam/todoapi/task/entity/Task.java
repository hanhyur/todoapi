package me.gracenam.todoapi.task.entity;

import lombok.*;
import me.gracenam.todoapi.task.enums.TaskStatus;
import me.gracenam.todoapi.task.enums.TaskType;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    private String type;

    private String status;

    private LocalDate registeredDate;

}
