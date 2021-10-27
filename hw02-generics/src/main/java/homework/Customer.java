package homework;

import java.util.Objects;

public class Customer {
    private long id;
    private String name;
    private long scores;

    //todo: 1. в этом классе надо исправить ошибки

    public Customer(long id, String name, long scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public Customer(Customer clone) {
        this.id = clone.getId();
        this.name = clone.getName();
        this.scores = clone.getScores();
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScores() {
        return scores;
    }

    public void setScores(long scores) {
        this.scores = scores;
    }

    public Customer clone()  {
        Customer clone = null;
        try {
            clone = (Customer)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        clone.setName(clone.getName());
        clone.setScores(clone.getScores());
        return clone;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (scores != customer.scores) return true;
        return name != null ? name.equals(customer.name) : customer.name == null;
    }

    @Override
    public int hashCode() { 

        int result = Objects.hash(id);
        return result;
    }
}
