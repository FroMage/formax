package model;

import java.util.Date;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Todo extends PanacheEntity {

    public String task;
    public Date completed;
    
    @ManyToOne
    public User owner;

	public static List<Todo> listAll(User user) {
		return list("owner", user);
	}
}